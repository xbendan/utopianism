package io.myosotisdev.utopianism.modules.craft;

import io.myosotisdev.minestom.Minestom;
import io.myosotisdev.utopianism.Ut;
import io.myosotisdev.utopianism.util.SingleSelectionModel;
import net.kyori.adventure.audience.Audience;
import net.minestom.server.Viewable;
import net.minestom.server.coordinate.Pos;
import net.minestom.server.entity.Player;
import net.minestom.server.inventory.Inventory;
import net.minestom.server.item.ItemStack;
import net.minestom.server.timer.Task;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public abstract class CraftingStation
        implements Viewable
{
    private final String                                               key;
    private final Set<Recipe>                                          acceptedRecipes;
    private final SingleSelectionModel<Player, CraftingStation.Worker> workersModel;
    private final Set<Player>                                          viewingPlayers;
    private       boolean                                              shared;
    private       Pos                                                  position;

    public CraftingStation(String key, boolean shared, Pos pos)
    {
        this.key = key;
        this.shared = shared;
        this.position = pos;
        this.acceptedRecipes = new HashSet<>();
        this.workersModel = new SingleSelectionModel<>();
        this.viewingPlayers = new HashSet<>();
    }

    public CraftingStation(String key, boolean shared, Pos pos, String recipesKey)
    {
        this(key, shared, pos);
        this.acceptedRecipes.addAll(Ut.crafting()
                                      .getRecipes(recipesKey));
    }

    public String getKey()
    {
        return key;
    }

    public boolean isShared()
    {
        return shared;
    }

    public void setShared(boolean shared)
    {
        //
        this.shared = shared;
    }

    public Set<Recipe> getAcceptedRecipes()
    {
        return acceptedRecipes;
    }

    public void addRecipe(Recipe recipe)
    {
        this.acceptedRecipes.add(recipe);
    }

    public SingleSelectionModel<Player, Worker> getWorkersModel()
    {
        return workersModel;
    }

    public Pos getPosition()
    {
        return position;
    }

    public abstract Worker createWorker(Player player);

    @Override
    public boolean isViewer(@NotNull Player player)
    {
        return shared ?
                viewingPlayers.contains(player) :
                workersModel.containsKey(player);
    }

    @Override
    public @NotNull Iterable<? extends Audience> getViewersAsAudiences()
    {
        return Viewable.super.getViewersAsAudiences();
    }

    @Override
    public boolean addViewer(@NotNull Player player)
    {
        viewingPlayers.add(player);
        if (!shared)
        {
            workersModel.add(player, createWorker(player));
        }
        return false;
    }

    @Override
    public boolean removeViewer(@NotNull Player player)
    {
        viewingPlayers.remove(player);
        if (!shared)
        {

        }
        return false;
    }

    @Override
    public @NotNull Set<@NotNull Player> getViewers()
    {
        return this.viewingPlayers;
    }

    public static abstract class Worker
    {
        private CraftingStation   station;
        private Inventory         inventory;
        private Task              timerTask;
        private Queue<WorkerTask> taskQueue;
        private List<WorkerTask>  finished;

        public Worker(CraftingStation station, Inventory inventory)
        {
            this.station = station;
            this.inventory = inventory;
            this.taskQueue = new PriorityQueue<>();
            this.finished = new LinkedList<>();
        }

        public boolean appendWork(Player player, Recipe recipe, Map<Integer, ItemStack> ingredients)
        {
            if (!recipe.checkPlayer(player) || !recipe.checkIngredients(ingredients))
                return false;

            WorkerTask newTask = new WorkerTask(this, player, recipe);
            if (recipe.getDuration() != 0)
            {
                this.taskQueue.add(newTask);
            }
            else
            {
                this.finished.add(newTask);
                craft(player, recipe);
            }

            submitIngredients(recipe, newTask, ingredients);

            return true;
        }

        public void submitIngredients(Recipe recipe, WorkerTask workerTask, Map<Integer, ItemStack> slotItems)
        {
            List<ItemStack> saveList = workerTask != null ?
                    workerTask.getIngredients() :
                    new ArrayList<>();
            recipe.getIngredients()
                  .forEach((slot, itemStack) -> {
                      saveList.add(slotItems.get(slot)
                                            .withAmount(itemStack.amount()));
                      slotItems.put(slot, slotItems.get(slot)
                                                   .consume(itemStack.amount()));
                  });
        }

        public List<ItemStack> craft(Player player, Recipe recipe)
        {
            return recipe.getOutputs();
        }

        public void start()
        {
            if (this.timerTask != null || taskQueue.isEmpty())
                return;

            this.timerTask = Minestom.scheduleTaskAsync(() -> {
                WorkerTask currentTask = taskQueue.peek();
                if (currentTask.reduceTickRemaining())
                {
                    taskQueue.remove();
                    craft(currentTask.player, currentTask.recipe);
                }
            }, 0, 20);
        }

        public Map<Integer, ItemStack> getItems()
        {
            return null;
        }

        public CraftingStation getStation()
        {
            return station;
        }

        public Inventory getInventory()
        {
            return inventory;
        }

        public boolean isWorkFinished()
        {
            return this.taskQueue.isEmpty();
        }
    }

    public static class WorkerTask
    {
        private          Worker          worker;
        private          Player          player;
        private          Recipe          recipe;
        private          List<ItemStack> ingredients;
        private          List<ItemStack> results;
        private volatile int             remainTicks;

        public WorkerTask(Worker worker, Player player, Recipe recipe)
        {
            this.worker = worker;
            this.player = player;
            this.ingredients = new ArrayList<>();
            this.recipe = recipe;
        }

        public int getTickRemaining()
        {
            return this.remainTicks;
        }

        public boolean reduceTickRemaining()
        {
            return --remainTicks == 0;
        }

        public List<ItemStack> getIngredients()
        {
            return this.ingredients;
        }

        public List<ItemStack> getResults()
        {
            return this.results;
        }

        public Player getPlayer()
        {
            return this.player;
        }

        public Recipe getRecipe()
        {
            return recipe;
        }
    }
}
