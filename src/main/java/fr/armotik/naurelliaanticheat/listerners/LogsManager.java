package fr.armotik.naurelliaanticheat.listerners;

import fr.armotik.naurelliaanticheat.logs.Logs;
import fr.armotik.naurelliaanticheat.utiles.FilesReader;
import org.bukkit.event.Listener;

import java.util.ArrayList;
import java.util.List;

public class LogsManager implements Listener {

    private static final List<Logs> logs = new ArrayList<>();
    private static final List<Logs> logs__join_quit = new ArrayList<>();
    private final static List<Logs> logs__chat = new ArrayList<>();
    private final static List<Logs> logs__player_death = new ArrayList<>();
    private final static List<Logs> logs__drop_items = new ArrayList<>();
    private final static List<Logs> logs__block = new ArrayList<>();
    private final static List<Logs> logs__blockInteract = new ArrayList<>();
    private final static List<Logs> logs__bed = new ArrayList<>();

    /**
     * Constructor
     */
    public LogsManager() {

        FilesReader.readLogs();
    }

    /**
     * Get logs__join_quit
     */
    public static List<Logs> getLogs__join_quit() {
        return logs__join_quit;
    }

    /**
     * Get logs__chat
     */
    public static List<Logs> getLogs__chat() {
        return logs__chat;
    }

    /**
     * Get logs
     */
    public static List<Logs> getLogs() {
        return logs;
    }

    /**
     * Get logs__player_death
     */
    public static List<Logs> getLogs__player_death() {
        return logs__player_death;
    }

    /**
     * Get logs__drop_items
     */
    public static List<Logs> getLogs__drop_items() {
        return logs__drop_items;
    }

    /**
     * Get logs__block
     */
    public static List<Logs> getLogs__block() {

        return logs__block;
    }

    /**
     * Get logs__blockInteract
     */
    public static List<Logs> getLogs__blockInteract() {

            return logs__blockInteract;
    }

    /**
     * Get logs__bed
     */
    public static List<Logs> getLogs__bed() {

        return logs__bed;
    }
}
