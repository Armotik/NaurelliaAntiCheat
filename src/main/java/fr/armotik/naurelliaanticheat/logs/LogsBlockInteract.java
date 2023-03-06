package fr.armotik.naurelliaanticheat.logs;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.block.Action;

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

    }

    /**
     * Return the logs in a string
     *
     * @return String
     */
    @Override
    public String toString() {
        return "LogsBlockInteract:{" +
                "targetUUID=" + targetUUID +
                ", date=" + date +
                ", logsType=" + logsType +
                ", location=" + location +
                ", material=" + material +
                ", blockLocation=" + blockLocation +
                ", action=" + action +
                "},";
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
