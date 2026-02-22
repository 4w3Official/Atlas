package dev.awe.atlas.hook.migration;

import dev.awe.atlas.Atlas;
import dev.awe.atlas.util.Logger;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;

public class PinataPartyMigration {
    private final Atlas plugin;
    private final Logger log;

    public PinataPartyMigration(Atlas plugin) {
        this.plugin = plugin;
        this.log = plugin.getPluginLogger();
    }

    public void migrate() {
        File legacyFile = new File(plugin.getDataFolder(), "data.yml");
        if (!legacyFile.exists()) {
            log.error("Migration failed: data.yml not found in plugin folder.");
            return;
        }

        YamlConfiguration oldData = YamlConfiguration.loadConfiguration(legacyFile);
        ConfigurationSection section = oldData.getConfigurationSection("player-votes");

        if (section == null || section.getKeys(false).isEmpty()) {
            log.warn("Migration: No 'player-votes' section found in data.yml.");
            return;
        }

        log.info("Migration: Starting import of " + section.getKeys(false).size() + " records...");

        String votesTable = plugin.getConfiguration().getMainConfig().database.tablePrefix + "votes";
        String sql =
                "INSERT INTO " + votesTable + " (uuid, username, service, amount, timestamp) VALUES (?, ?, ?, ?, ?);";

        try (Connection conn = plugin.getDatabaseManager().getConnection()) {
            conn.setAutoCommit(false);
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                for (String uuidStr : section.getKeys(false)) {
                    int votes = section.getInt(uuidStr);
                    if (votes <= 0) continue;

                    stmt.setString(1, uuidStr);
                    stmt.setString(2, "Legacy-Player");
                    stmt.setString(3, "Atlas-Migration");
                    stmt.setInt(4, votes);
                    stmt.setLong(5, 0L);
                    stmt.addBatch();
                }
                stmt.executeBatch();
                conn.commit();
                log.info("Migration: Successfully imported legacy data.");
            } catch (SQLException e) {
                conn.rollback();
                throw e;
            }
        } catch (SQLException e) {
            log.error("Database error during migration: " + e.getMessage());
            return;
        }

        File backup = new File(plugin.getDataFolder(), "data.yml.converted");
        legacyFile.renameTo(backup);
    }
}
