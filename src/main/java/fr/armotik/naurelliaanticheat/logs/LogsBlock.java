package fr.armotik.naurelliaanticheat.logs;

import org.bukkit.Location;
import org.bukkit.Material;

import java.util.UUID;

public class LogsBlock extends Logs{
    private final Material material;
    private final Location blockLocation;
    public LogsBlock(UUID targetUUID, Location location, LogsType logsType, Material material, Location blockLocation) {
        super(targetUUID, location, logsType);

        this.material = material;
        this.blockLocation = blockLocation;
    }

    /**
     * Return the logs in a string
     *
     * @return String
     */
    @Override
    public String toString() {
        return "LogsBlock:{" +
                "targetUUID=" + targetUUID +
                ", date=" + date +
                ", logsType=" + logsType +
                ", location=" + location +
                ", material=" + material +
                ", blockLocation=" + blockLocation +
                "},";
    }

    public Material getMaterial() {
        return material;
    }

    public Location getBlockLocation() {
        return blockLocation;
    }
}
