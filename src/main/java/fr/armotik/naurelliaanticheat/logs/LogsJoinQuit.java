package fr.armotik.naurelliaanticheat.logs;

import fr.armotik.naurelliaanticheat.listerners.LogsManager;
import org.bukkit.Location;

import java.util.Date;
import java.util.UUID;

public class LogsJoinQuit extends Logs{

    /**
     * Constructor (without date)
     * @param targetUUID target UUID
     * @param location target location
     * @param logsType logs type
     */
    public LogsJoinQuit(UUID targetUUID, Location location, LogsType logsType) {
        super(targetUUID, location, logsType);
        LogsManager.getLogs__join_quit().add(this);
        LogsManager.getLogs().add(this);
    }

    /**
     * Constructor (with date)
     * @param targetUUID target UUID
     * @param date date
     * @param location target location
     * @param logsType logs type
     */
    public LogsJoinQuit(UUID targetUUID, Date date, Location location, LogsType logsType) {
        super(targetUUID, date, location, logsType);
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
        return "LogsJoinQuit::{" +
                "targetUUID=" + targetUUID +
                ", date=" + date +
                ", logsType=" + logsType +
                ", location=" + location +
                "},\n";
    }
}
