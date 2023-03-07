package fr.armotik.naurelliaanticheat.logs;


import org.bukkit.Location;

import java.util.Date;
import java.util.UUID;

public abstract class Logs {

    protected final UUID targetUUID;
    protected final Date date;
    protected final LogsType logsType;
    protected final Location location;

    public Logs(UUID targetUUID ,Location location, LogsType logsType) {
        this.targetUUID = targetUUID;
        this.location = location;
        this.logsType =  logsType;

        date = new Date();
    }

    public Logs(UUID targetUUID, Date date ,Location location, LogsType logsType) {
        this.targetUUID = targetUUID;
        this.location = location;
        this.logsType =  logsType;

        this.date = date;
    }



    public UUID getTargetUUID() {
        return targetUUID;
    }

    public Date getDate() {
        return date;
    }

    public Location getLocation() {
        return location;
    }

    public LogsType getLogsType() {
        return logsType;
    }

    /**
     * Return the logs in a string
     * @return String
     */
    public abstract String toString();
}
