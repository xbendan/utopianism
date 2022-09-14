package io.myosotisdev.minestom.command;

import io.myosotisdev.minestom.permission.Permission;
import io.myosotisdev.minestom.util.component.Components;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import net.minestom.server.command.builder.Command;
import net.minestom.server.command.builder.arguments.Argument;
import net.minestom.server.command.builder.arguments.ArgumentEnum;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Iterator;

public abstract class AbstractCommand extends Command
{
    private AbstractCommand parent;

    public AbstractCommand(AbstractCommand command, @NotNull String name, @Nullable String... aliases)
    {
        super(name, aliases);

        this.parent = command;

        setDefaultExecutor((sender, context) -> {
            sender.sendMessage(Components.fromLegacy("&aCommand: &8[&6" + getName() + "&8]"));

            sender.sendMessage(Components.fromLegacy("&7Aliases:"));
            for (String alias : getAliases())
            {
                sender.sendMessage(Components.fromLegacy("&7 - &6" + alias));
            }

            sender.sendMessage(Components.fromLegacy("&7Syntaxes:"));
            getSyntaxes().stream().forEach(syntax -> {
                String syntaxContent = "&7 - &f" + getName();

                for (Argument arg : syntax.getArguments())
                {
                    syntaxContent += (" " + arg.getId());

                    /**
                    switch (arg.getClass().getName())
                    {
                        case "ArgumentEntity":
                            syntaxContent += "<player>";
                            break;
                        case "ArgumentEnum":
                            String enumText = "<";
                            Iterator<String> enumIterator = ((ArgumentEnum) arg).entries().iterator();
                            while(enumIterator.hasNext())
                            {

                            }
                    }
                     */
                }

                sender.sendMessage(Components.fromLegacy(syntaxContent));
            });
        });
    }

    public AbstractCommand getParent()
    {
        return this.parent;
    }

    public abstract Permission permission();
}
