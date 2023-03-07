package fr.armotik.naurelliaanticheat.logs;

import fr.armotik.naurelliaanticheat.listerners.LogsManager;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.block.Action;

import java.util.Date;
import java.util.UUID;

public class LogsBlockInteract extends Logs{

    private final Material material;
    private final Location blockLocation;
    private final Action action;

    /**
     * Constructor (without date)
     * @param targetUUID target UUID
     * @param location target location
     * @param logsType logs type
     * @param material material
     * @param blockLocation block location
     * @param action action
     */
    public LogsBlockInteract(UUID targetUUID, Location location, LogsType logsType, Material material, Location blockLocation, Action action) {
        super(targetUUID, location, logsType);

        this.material = material;
        this.blockLocation = blockLocation;
        this.action = action;

        LogsManager.getLogs__blockInteract().add(this);
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
     * @param action action
     */
    public LogsBlockInteract(UUID targetUUID, Date date, Location location, LogsType logsType, Material material, Location blockLocation, Action action) {
        super(targetUUID, date, location, logsType);

        this.material = material;
        this.blockLocation = blockLocation;
        this.action = action;

        LogsManager.getLogs__blockInteract().add(this);
        LogsManager.getLogs().add(this);
    }

    /**
     * Return the logs in a string
     *
     * @return String
     */
    @Override
    public String toString() {
        return "LogsBlockInteract::{" +
                "targetUUID=" + targetUUID +
                ", date=" + date +
                ", logsType=" + logsType +
                ", location=" + location +
                ", material=" + material +
                ", blockLocation=" + blockLocation +
                ", action=" + action +
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

    /**
     * Return the action
     * @return Action
     */
    public Action getAction() {
        return action;
    }
}
