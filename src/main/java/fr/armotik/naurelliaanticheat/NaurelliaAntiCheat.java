package fr.armotik.naurelliaanticheat;

import fr.armotik.naurelliaanticheat.commands.NaurelliaLogCommand;
import fr.armotik.naurelliaanticheat.commands.TestCommand;
import fr.armotik.naurelliaanticheat.listerners.EventManager;
import fr.armotik.naurelliaanticheat.listerners.LogsManager;
import fr.armotik.naurelliaanticheat.utiles.Database;
import fr.armotik.naurelliaanticheat.utiles.FilesReader;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class NaurelliaAntiCheat extends JavaPlugin {

    private static NaurelliaAntiCheat plugin;
    private static final Logger logger = Logger.getLogger(NaurelliaAntiCheat.class.getName());

    /**
     * Get plugin method
     * Singleton pattern
     * @return plugin
     */
    public static NaurelliaAntiCheat getPlugin() {
        if (plugin == null) {
            plugin = new NaurelliaAntiCheat();
        }

        return plugin;
    }

    @Override
    public void onEnable() {
        // Plugin startup logic
        plugin = this;
        logger.log(Level.INFO, "[NaurelliaAntiCheat] -> NaurelliaAntiCheat is loading ...");
        this.getConfig().options().copyDefaults(true);
        this.saveConfig();

        Database.databaseTest();

        /*
        Command
         */
        Objects.requireNonNull(this.getCommand("nlog")).setExecutor(new NaurelliaLogCommand());
        Objects.requireNonNull(this.getCommand("tlog")).setExecutor(new TestCommand());

        /*
        Listeners
         */
        this.getServer().getPluginManager().registerEvents(new LogsManager(), this);
        this.getServer().getPluginManager().registerEvents(new EventManager(), this);

        logger.log(Level.INFO, "[NaurelliaAntiCheat] -> Successfully loaded NaurelliaAntiCheat");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic

        FilesReader.writeLogs();
        Database.closeAll();
    }
}
