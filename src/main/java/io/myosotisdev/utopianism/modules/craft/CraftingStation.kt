package io.myosotisdev.utopianism.modules.craft

import io.myosotisdev.minestom.Minestom
import io.myosotisdev.utopianism.util.SingleSelectionModel
import net.minestom.server.Viewable
import net.minestom.server.coordinate.Pos
import net.minestom.server.entity.Player
import net.minestom.server.inventory.Inventory
import net.minestom.server.item.ItemStack
import net.minestom.server.timer.Task
import java.util.*

abstract class CraftingStation(
        val key: String, //
        var isShared: Boolean, val position: Pos
) : Viewable
{
    private val acceptedRecipes: MutableSet<Recipe>
    val workersModel: SingleSelectionModel<Player, Worker>
    private val viewingPlayers: MutableSet<Player>

    init
    {
        acceptedRecipes = HashSet()
        workersModel = SingleSelectionModel()
        viewingPlayers = HashSet()
    }

    constructor(key: String, shared: Boolean, pos: Pos, recipesKey: String?) : this(key, shared, pos)
    {
        acceptedRecipes.addAll(Crafts.getRecipes(recipesKey))
    }

    fun getAcceptedRecipes(): Set<Recipe>
    {
        return acceptedRecipes
    }

    fun addRecipe(recipe: Recipe)
    {
        acceptedRecipes.add(recipe)
    }

    abstract fun createWorker(player: Player?): Worker
    override fun isViewer(player: Player): Boolean
    {
        return if (isShared) viewingPlayers.contains(player) else workersModel.containsKey(player)
    }

    override fun addViewer(player: Player): Boolean
    {
        viewingPlayers.add(player)
        if (!isShared)
        {
            workersModel[player] = createWorker(player)
        }
        return false
    }

    override fun removeViewer(player: Player): Boolean
    {
        viewingPlayers.remove(player)
        if (!isShared)
        {
        }
        return false
    }

    override fun getViewers(): Set<Player>
    {
        return viewingPlayers
    }

    class Worker(val station: CraftingStation, val inventory: Inventory)
    {
        private var timerTask: Task? = null
        private val taskQueue: Queue<WorkerTask>
        private val finished: MutableList<WorkerTask>

        init
        {
            taskQueue = PriorityQueue()
            finished = LinkedList()
        }

        fun appendWork(player: Player, recipe: Recipe, ingredients: MutableMap<Int?, ItemStack>): Boolean
        {
            if (!recipe.checkPlayer(player) || !recipe.checkIngredients(ingredients)) return false
            val newTask = WorkerTask(this, player, recipe)
            if (recipe.duration != 0)
            {
                taskQueue.add(newTask)
            }
            else
            {
                finished.add(newTask)
                craft(player, recipe)
            }
            submitIngredients(recipe, newTask, ingredients)
            return true
        }

        fun submitIngredients(recipe: Recipe, workerTask: WorkerTask?, slotItems: MutableMap<Int?, ItemStack>)
        {
            val saveList = workerTask?.ingredients ?: ArrayList()
            recipe.ingredients
                    .forEach { (slot: Int?, itemStack: ItemStack) ->
                        saveList.add(slotItems[slot]
                                             ?.withAmount(itemStack.amount()) ?: ItemStack.AIR)
                        slotItems[slot] = slotItems[slot]
                                                  ?.consume(itemStack.amount()) ?: ItemStack.AIR
                    }
        }

        fun craft(player: Player?, recipe: Recipe): List<ItemStack>
        {
            return recipe.outputs
        }

        fun start()
        {
            if (timerTask != null || taskQueue.isEmpty()) return
            timerTask = Minestom.scheduleTaskAsync({
                val currentTask = taskQueue.peek()
                if (currentTask.reduceTickRemaining())
                {
                    taskQueue.remove()
                    craft(currentTask.player, currentTask.recipe)
                }
            }, 0, 20)
        }

        val items: Map<Int, ItemStack>?
            get() = null
        val isWorkFinished: Boolean
            get() = taskQueue.isEmpty()
    }

    class WorkerTask(private val worker: Worker, val player: Player?, recipe: Recipe)
    {
        val recipe: Recipe
        val ingredients: MutableList<ItemStack>
        val results: List<ItemStack>? = null

        @Volatile
        var tickRemaining = 0
            private set

        init
        {
            ingredients = ArrayList()
            this.recipe = recipe
        }

        fun reduceTickRemaining(): Boolean
        {
            return --tickRemaining == 0
        }
    }
}