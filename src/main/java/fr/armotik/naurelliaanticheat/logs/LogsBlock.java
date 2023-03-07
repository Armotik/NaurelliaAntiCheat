package fr.armotik.naurelliaanticheat.logs;

import fr.armotik.naurelliaanticheat.listerners.LogsManager;
import org.bukkit.Location;
import org.bukkit.Material;

import java.util.Date;
import java.util.UUID;

public class LogsBlock extends Logs{
    private final Material material;
    private final Location blockLocation;

    /**
     * Constructor (without date)
     * @param targetUUID target UUID
     * @param location target location
     * @param logsType logs type
     * @param material material
     * @param blockLocation block location
     */
    public LogsBlock(UUID targetUUID, Location location, LogsType logsType, Material material, Location blockLocation) {
        super(targetUUID, location, logsType);

        this.material = material;
        this.blockLocation = blockLocation;

        LogsManager.getLogs__block().add(this);
        LogsManager.getLogs().add(this);
    }

    /**
     * Constructor (with date)
     * @param targetUUID target UUID
     * @param date date
     * @param location target location
     * @param logsType logs type
     * @param material material
     * @param blockLocation block location
     */
    public LogsBlock(UUID targetUUID, Date date, Location location, LogsType logsType, Material material, Location blockLocation) {
        super(targetUUID, date, location, logsType);

        this.material = material;
        this.blockLocation = blockLocation;

        LogsManager.getLogs__block().add(this);
        LogsManager.getLogs().add(this);
    }

    /**
     * Return the logs in a string
     *
     * @return String
     */
    @Override
    public String toString() {
        return "LogsBlock::{" +
                "targetUUID=" + targetUUID +
                ", date=" + date +
                ", logsType=" + logsType +
                ", location=" + location +
                ", material=" + material +
                ", blockLocation=" + blockLocation +
                "},\n";
    }

    /**
     * Return the material
     * @return Material
     */
    public Material getMaterial() {
        return material;
    }

    /**
     * Return the block location
     * @return Location
     */
    public Location getBlockLocation() {
        return blockLocation;
    }
}
