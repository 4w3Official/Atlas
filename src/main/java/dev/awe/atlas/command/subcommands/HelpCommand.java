package dev.awe.atlas.command.subcommands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import dev.awe.atlas.Atlas;
import dev.awe.atlas.util.MessageUtils;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;
import org.bukkit.command.CommandSender;

public class HelpCommand {
    private final Atlas plugin;
    private final MessageUtils messageUtils;

    public HelpCommand(Atlas plugin) {
        this.plugin = plugin;
        this.messageUtils = plugin.getMessageUtils();
    }

    public LiteralArgumentBuilder<CommandSourceStack> build() {
        return Commands.literal("help").executes(this::executeHelp);
    }

    private int executeHelp(CommandContext<CommandSourceStack> ctx) {
        CommandSender sender = ctx.getSource().getSender();

        messageUtils.send(sender, plugin.getConfiguration().getMessageConfig().help.header);

        sendHelpLine(sender, "as reload", "Reload configuration", "atlas.reload");

        sendHelpLine(sender, "as pinata start <pinata> <location>", "Start pinata countdown", "atlas.pinata.start");
        sendHelpLine(sender, "as pinata spawn <pinata> <location>", "Spawn pinata immediately", "atlas.pinata.spawn");
        sendHelpLine(sender, "as pinata killall", "Remove all active pinatas", "atlas.pinata.killall");

        sendHelpLine(sender, "as pinata spawnpoint add <name>", "Save current location", "atlas.pinata.spawnpoint.add");
        sendHelpLine(
                sender,
                "as pinata spawnpoint remove <name>",
                "Remove saved location",
                "atlas.pinata.spawnpoint.remove");

        sendHelpLine(sender, "as vote check <player>", "Check player votes", "atlas.vote.check");
        sendHelpLine(sender, "as vote <add|remove|set>", "Modify vote data", "atlas.vote.add/remove/set");
        sendHelpLine(sender, "as vote send <player> [service]", "Simulate a vote", "atlas.vote.send");
        sendHelpLine(sender, "as vote migrate <plugin>", "Migrate data from another plugin", "atlas.vote.migrate");

        return Command.SINGLE_SUCCESS;
    }

    private void sendHelpLine(CommandSender sender, String command, String description, String permission) {
        if (permission != null && !sender.hasPermission(permission)) {
            return;
        }

        String format = plugin.getConfiguration().getMessageConfig().help.entry;

        messageUtils.send(
                sender, format, messageUtils.tag("command", command), messageUtils.tag("description", description));
    }
}
