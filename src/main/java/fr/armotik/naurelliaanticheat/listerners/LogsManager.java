package fr.armotik.naurelliaanticheat.listerners;

import fr.armotik.naurelliaanticheat.logs.Logs;
import fr.armotik.naurelliaanticheat.utiles.FilesReader;
import org.bukkit.event.Listener;

import java.util.ArrayList;
import java.util.List;

public class LogsManager implements Listener {

    private static List<Logs> logs__join_quit = new ArrayList<>();
    private final static List<Logs> logs__chat = new ArrayList<>();

    public LogsManager() {

        logs__join_quit = FilesReader.readJoinQuitLogs();
    }

    public static List<Logs> getLogs__join_quit() {
        return logs__join_quit;
    }

    public static List<Logs> getLogs__chat() {
        return logs__chat;
    }
}
