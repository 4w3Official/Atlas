package dev.awe.atlas.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.tree.LiteralCommandNode;
import dev.awe.atlas.Atlas;
import dev.awe.atlas.command.subcommands.HelpCommand;
import dev.awe.atlas.command.subcommands.PinataCommand;
import dev.awe.atlas.command.subcommands.ReloadCommand;
import dev.awe.atlas.command.subcommands.VoteCommand;
import dev.awe.atlas.util.MessageUtils;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;

public class AtlasCommand {
    private final Atlas plugin;
    private final MessageUtils messageUtils;

    public AtlasCommand(Atlas plugin) {
        this.plugin = plugin;
        this.messageUtils = plugin.getMessageUtils();
    }

    public LiteralCommandNode<CommandSourceStack> createCommand(final String commandName) {
        var root = Commands.literal(commandName).executes(ctx -> {
            var sender = ctx.getSource().getSender();
            messageUtils.send(
                    sender,
                    "<prefix> <gray>Plugin version: <green>"
                            + plugin.getPluginMeta().getVersion());
            messageUtils.send(
                    sender, "<green>🛈</green> <gray>Type <white>/as help</white> for a list of commands.</gray>");
            return Command.SINGLE_SUCCESS;
        });

        root.then(new HelpCommand(plugin).build());
        root.then(new ReloadCommand(plugin).build());
        root.then(new PinataCommand(plugin).build());
        root.then(new VoteCommand(plugin).build());

        return root.build();
    }
}
