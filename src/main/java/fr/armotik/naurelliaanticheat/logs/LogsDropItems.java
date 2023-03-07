package fr.armotik.naurelliaanticheat.logs;

import fr.armotik.naurelliaanticheat.listerners.LogsManager;
import org.bukkit.Location;
import org.bukkit.inventory.ItemStack;

import java.util.Date;
import java.util.UUID;

public class LogsDropItems extends Logs {

    private final ItemStack itemStack;
    private final Location dropLocation;

    public LogsDropItems(UUID uuid, Location location, LogsType logsType, ItemStack itemStack, Location dropLocation) {
        super(uuid, location, logsType);

        this.itemStack = itemStack;
        this.dropLocation = dropLocation;

        LogsManager.getLogs__drop_items().add(this);
        LogsManager.getLogs().add(this);
    }

    public LogsDropItems(UUID uuid, Date date, Location location, LogsType logsType, ItemStack itemStack, Location dropLocation) {
        super(uuid, date, location, logsType);

        this.itemStack = itemStack;
        this.dropLocation = dropLocation;

        LogsManager.getLogs__drop_items().add(this);
        LogsManager.getLogs().add(this);
    }

    public ItemStack getItemStack() {
        return itemStack;
    }

    public Location getDropLocation() {
        return dropLocation;
    }

    /**
     * Return the logs in a string
     * @return String
     */
    @Override
    public String toString() {
        return "LogsDropItems::{" +
                "targetUUID=" + targetUUID +
                ", date=" + date +
                ", logsType=" + logsType +
                ", location=" + location +
                ", itemStack=" + itemStack +
                ", dropLocation=" + dropLocation +
                "},\n";
    }
}
