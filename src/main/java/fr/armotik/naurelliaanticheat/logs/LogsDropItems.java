package fr.armotik.naurelliaanticheat.logs;

import fr.armotik.naurelliaanticheat.listerners.LogsManager;
import org.bukkit.Location;
import org.bukkit.inventory.ItemStack;

import java.util.Date;
import java.util.UUID;

public class LogsDropItems extends Logs {

    private final ItemStack itemStack;
    private final Location dropLocation;

    /**
     * Constructor (without date)
     * @param uuid target uuid
     * @param location target location
     * @param logsType logs type
     * @param itemStack item stack
     * @param dropLocation drop location
     */
    public LogsDropItems(UUID uuid, Location location, LogsType logsType, ItemStack itemStack, Location dropLocation) {
        super(uuid, location, logsType);

        this.itemStack = itemStack;
        this.dropLocation = dropLocation;

        LogsManager.getLogs__drop_items().add(this);
        LogsManager.getLogs().add(this);
    }

    /**
     * Constructor (with date)
     * @param uuid target uuid
     * @param date date
     * @param location target location
     * @param logsType logs type
     * @param itemStack item stack
     * @param dropLocation drop location
     */
    public LogsDropItems(UUID uuid, Date date, Location location, LogsType logsType, ItemStack itemStack, Location dropLocation) {
        super(uuid, date, location, logsType);

        this.itemStack = itemStack;
        this.dropLocation = dropLocation;

        LogsManager.getLogs__drop_items().add(this);
        LogsManager.getLogs().add(this);
    }

    /**
     * Return the item stack
     * @return ItemStack
     */
    public ItemStack getItemStack() {
        return itemStack;
    }

    /**
     * Return the drop location
     * @return Location
     */
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
