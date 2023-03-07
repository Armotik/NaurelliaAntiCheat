package fr.armotik.naurelliaanticheat.logs;

import fr.armotik.naurelliaanticheat.listerners.LogsManager;
import org.bukkit.Location;
import org.bukkit.Material;

import java.util.Date;
import java.util.UUID;

public class LogsBed extends Logs{

    private final Material bedType;
    private final Location bedLocation;

    /**
     * Constructor (without date)
     * @param targetUUID target UUID
     * @param location target location
     * @param logsType type of logs
     * @param bedType type of bed
     * @param bedLocation location of bed
     */
    public LogsBed(UUID targetUUID, Location location, LogsType logsType, Material bedType, Location bedLocation) {
        super(targetUUID, location, logsType);

        this.bedType = bedType;
        this.bedLocation = bedLocation;

        LogsManager.getLogs__bed().add(this);
        LogsManager.getLogs().add(this);
    }

    /**
     * Constructor (with date)
     * @param targetUUID target UUID
     * @param date date of logs
     * @param location target location
     * @param logsType type of logs
     * @param bedType type of bed
     * @param bedLocation location of bed
     */
    public LogsBed(UUID targetUUID, Date date, Location location, LogsType logsType, Material bedType, Location bedLocation) {
        super(targetUUID, date, location, logsType);

        this.bedType = bedType;
        this.bedLocation = bedLocation;

        LogsManager.getLogs__bed().add(this);
        LogsManager.getLogs().add(this);
    }

    /**
     * Return the logs in a string
     *
     * @return String
     */
    @Override
    public String toString() {
        return "LogsBed::{" +
                "targetUUID=" + targetUUID +
                ", date=" + date +
                ", logsType=" + logsType +
                ", location=" + location +
                ", bedType=" + bedType +
                ", bedLocation=" + bedLocation +
                "},\n";
    }

    /**
     * Return type of bed
     * @return Material type of bed
     */
    public Material getBedType() {
        return bedType;
    }

    /**
     * Return location of bed
     * @return Location of bed
     */
    public Location getBedLocation() {
        return bedLocation;
    }
}
