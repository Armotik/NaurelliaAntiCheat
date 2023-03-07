package fr.armotik.naurelliaanticheat.listerners;

import fr.armotik.naurelliaanticheat.logs.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.*;

import java.util.Objects;

public class EventManager implements Listener {

    /**
     * Player Join Event
     * @param event PlayerJoinEvent
     */
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        new LogsJoinQuit(event.getPlayer().getUniqueId(), event.getPlayer().getLocation(), LogsType.LOG__JOIN);
    }

    /**
     * Player Quit Event
     * @param event PlayerQuitEvent
     */
    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        new LogsJoinQuit(event.getPlayer().getUniqueId(), event.getPlayer().getLocation(), LogsType.LOG__QUIT);
    }

    /**
     * Player Command Event
     * @param event PlayerCommandPreprocessEvent
     */
    @EventHandler
    public void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent event) {
        new LogsChat(event.getPlayer().getUniqueId(), event.getPlayer().getLocation(), LogsType.LOG__COMMAND_SEND, event.getMessage());
    }

    /**
     * Player Chat Event
     * @param event AsyncPlayerChatEvent
     */
    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        new LogsChat(event.getPlayer().getUniqueId(), event.getPlayer().getLocation(), LogsType.LOG__MESSAGE_SEND, event.getMessage());
    }

    /**
     * Player Death Event
     * @param event PlayerDeathEvent
     */
    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {

        if (event.getEntity().getLastDamageCause() != null) {

            new LogsPlayerDeath(event.getEntity().getUniqueId(), event.getEntity().getLastDeathLocation(), LogsType.LOG__DEATH_PLAYER, event.getEntity().getKiller(),event.getDeathMessage(), Objects.requireNonNull(event.getEntity().getLastDamageCause()).getCause().name());
        } else {

            new LogsPlayerDeath(event.getEntity().getUniqueId(), event.getEntity().getLastDeathLocation(), LogsType.LOG__DEATH_PLAYER, event.getEntity().getKiller(),event.getDeathMessage(), "UNKNOWN");
        }
    }

    /**
     * Player Move Event
     * @param event PlayerMoveEvent
     */
    @EventHandler
    public void onPlayerDropItem(PlayerDropItemEvent event) {
        new LogsDropItems(event.getPlayer().getUniqueId(), event.getPlayer().getLocation(), LogsType.LOG__DROP_ITEM, event.getItemDrop().getItemStack(), event.getItemDrop().getLocation());
    }

    /**
     * Player Move Event
     * @param event PlayerMoveEvent
     */
    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        new LogsBlock(event.getPlayer().getUniqueId(), event.getPlayer().getLocation(), LogsType.LOG__PLACE_BLOCK, event.getBlockPlaced().getType(), event.getBlockPlaced().getLocation());
    }

    /**
     * Player Move Event
     * @param event PlayerMoveEvent
     */
    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        new LogsBlock(event.getPlayer().getUniqueId(), event.getPlayer().getLocation(), LogsType.LOG__BREAK_BLOCK, event.getBlock().getType(), event.getBlock().getLocation());
    }

    /**
     * Player Move Event
     * @param event PlayerMoveEvent
     */
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {

        if ((event.getClickedBlock() != null) && (event.getClickedBlock().getType().isInteractable())) {
            new LogsBlockInteract(event.getPlayer().getUniqueId(), event.getPlayer().getLocation(), LogsType.LOG__INTERACT_BLOCK, event.getClickedBlock().getType(), event.getClickedBlock().getLocation(), event.getAction());
        }
    }

    /**
     * Player Move Event
     * @param event PlayerMoveEvent
     */
    @EventHandler
    public void onPlayerBedEnter(PlayerBedEnterEvent event) {

        new LogsBed(event.getPlayer().getUniqueId(), event.getPlayer().getLocation(), LogsType.LOG__BED_ENTER, event.getBed().getType(), event.getBed().getLocation());
    }

    /**
     * Player Move Event
     * @param event PlayerMoveEvent
     */
    @EventHandler
    public void onPlayerBedLeave(PlayerBedLeaveEvent event) {

        new LogsBed(event.getPlayer().getUniqueId(), event.getPlayer().getLocation(), LogsType.LOG__BED_LEAVE, event.getBed().getType(), event.getBed().getLocation());
    }
}
