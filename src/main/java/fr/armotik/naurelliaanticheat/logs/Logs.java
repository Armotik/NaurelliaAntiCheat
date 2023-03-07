package fr.armotik.naurelliaanticheat.logs;


import org.bukkit.Location;

import java.util.Date;
import java.util.UUID;

public abstract class Logs {

    protected final UUID targetUUID;
    protected final Date date;
    protected final LogsType logsType;
    protected final Location location;

    /**
     * Create a new logs (Default date is the current date)
     * @param targetUUID The target UUID
     * @param location The location of the event
     * @param logsType The type of the logs
     */
    public Logs(UUID targetUUID ,Location location, LogsType logsType) {
        this.targetUUID = targetUUID;
        this.location = location;
        this.logsType =  logsType;

        date = new Date();
    }

    /**
     * Create a new logs
     * @param targetUUID The target UUID
     * @param date The date of the event
     * @param location The location of the event
     * @param logsType The type of the logs
     */
    public Logs(UUID targetUUID, Date date ,Location location, LogsType logsType) {
        this.targetUUID = targetUUID;
        this.location = location;
        this.logsType =  logsType;

        this.date = date;
    }

    /**
     * Return the target UUID
     * @return UUID
     */
    public UUID getTargetUUID() {
        return targetUUID;
    }

    /**
     * Return the date of the logs
     * @return Date
     */
    public Date getDate() {
        return date;
    }

    /**
     * Return the location of the logs
     * @return Location
     */
    public Location getLocation() {
        return location;
    }

    /**
     * Return the type of the logs
     * @return LogsType
     */
    public LogsType getLogsType() {
        return logsType;
    }

    /**
     * Return the logs in a string
     * @return String
     */
    public abstract String toString();
}
