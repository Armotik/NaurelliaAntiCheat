package fr.armotik.naurelliaanticheat.logs;


import org.bukkit.Location;

import java.util.Date;
import java.util.UUID;

public abstract class Logs {

    private final UUID targetUUID;
    private Date date;
    private LogsType logsType;
    private Location location;

    public Logs(UUID targetUUID, Location location, LogsType logsType) {
        this.targetUUID = targetUUID;
        this.location = location;
        this.logsType =  logsType;

        date = new Date();
    }

    public UUID getTargetUUID() {
        return targetUUID;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public LogsType getLogsType() {
        return logsType;
    }

    public void setLogsType(LogsType logsType) {
        this.logsType = logsType;
    }
}
