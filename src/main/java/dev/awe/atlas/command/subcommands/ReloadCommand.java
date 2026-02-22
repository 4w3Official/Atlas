package dev.awe.atlas.command.subcommands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import dev.awe.atlas.Atlas;
import dev.awe.atlas.config.ConfigManager;
import dev.awe.atlas.util.MessageUtils;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;

public class ReloadCommand {
    private final Atlas plugin;
    private final ConfigManager config;
    private final MessageUtils messageUtils;

    public ReloadCommand(Atlas plugin) {
        this.plugin = plugin;
        this.config = plugin.getConfiguration();
        this.messageUtils = plugin.getMessageUtils();
    }

    public LiteralArgumentBuilder<CommandSourceStack> build() {
        return Commands.literal("reload")
                .requires(sender -> sender.getSender().hasPermission("atlas.reload"))
                .executes(ctx -> {
                    var sender = ctx.getSource().getSender();
                    if (plugin.reload()) {
                        plugin.getPluginLogger().info("Configuration reloaded by " + sender.getName());
                        messageUtils.send(sender, config.getMessageConfig().general.reloadSuccess);
                    } else {
                        plugin.getPluginLogger().warn("Failed to reload configuration by " + sender.getName());
                        messageUtils.send(sender, config.getMessageConfig().general.reloadFail);
                    }
                    return Command.SINGLE_SUCCESS;
                });
    }
}
