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
    public LogsBlockInteract(UUID targetUUID, Location location, LogsType logsType, Material material, Location blockLocation, Action action) {
        super(targetUUID, location, logsType);

        this.material = material;
        this.blockLocation = blockLocation;
        this.action = action;

        LogsManager.getLogs().add(this);
    }

    public LogsBlockInteract(UUID targetUUID, Date date, Location location, LogsType logsType, Material material, Location blockLocation, Action action) {
        super(targetUUID, date, location, logsType);

        this.material = material;
        this.blockLocation = blockLocation;
        this.action = action;

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

    public Material getMaterial() {
        return material;
    }

    public Location getBlockLocation() {
        return blockLocation;
    }

    public Action getAction() {
        return action;
    }
}
