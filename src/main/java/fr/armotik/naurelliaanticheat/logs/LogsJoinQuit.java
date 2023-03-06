package fr.armotik.naurelliaanticheat.logs;

import fr.armotik.naurelliaanticheat.listerners.LogsManager;
import org.bukkit.Location;

import java.util.UUID;

public class LogsJoinQuit extends Logs{
    public LogsJoinQuit(UUID targetUUID, Location location, LogsType logsType) {
        super(targetUUID, location, logsType);
        LogsManager.getLogs__join_quit().add(this);
        LogsManager.getLogs().add(this);
    }

    /**
     * Return the logs in a string
     *
     * @return String
     */
    @Override
    public String toString() {
        return "LogsJoinQuit{" +
                "targetUUID=" + targetUUID +
                ", date=" + date +
                ", logsType=" + logsType +
                ", location=" + location +
                '}';
    }
}
