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

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        new LogsJoinQuit(event.getPlayer().getUniqueId(), event.getPlayer().getLocation(), LogsType.LOG__JOIN);
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        new LogsJoinQuit(event.getPlayer().getUniqueId(), event.getPlayer().getLocation(), LogsType.LOG__QUIT);
    }

    @EventHandler
    public void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent event) {
        new LogsChat(event.getPlayer().getUniqueId(), event.getPlayer().getLocation(), LogsType.LOG__COMMAND_SEND, event.getMessage());
    }

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        new LogsChat(event.getPlayer().getUniqueId(), event.getPlayer().getLocation(), LogsType.LOG__MESSAGE_SEND, event.getMessage());
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {

        if (event.getEntity().getLastDamageCause() != null) {

            new LogsPlayerDeath(event.getEntity().getUniqueId(), event.getEntity().getLastDeathLocation(), LogsType.LOG__DEATH_PLAYER, event.getEntity().getKiller(),event.getDeathMessage(), Objects.requireNonNull(event.getEntity().getLastDamageCause()).getCause().name());
        } else {

            new LogsPlayerDeath(event.getEntity().getUniqueId(), event.getEntity().getLastDeathLocation(), LogsType.LOG__DEATH_PLAYER, event.getEntity().getKiller(),event.getDeathMessage(), "UNKNOWN");
        }
    }

    @EventHandler
    public void onPlayerDropItem(PlayerDropItemEvent event) {
        new LogsDropItems(event.getPlayer().getUniqueId(), event.getPlayer().getLocation(), LogsType.LOG__DROP_ITEM, event.getItemDrop().getItemStack(), event.getItemDrop().getLocation());
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        new LogsBlock(event.getPlayer().getUniqueId(), event.getPlayer().getLocation(), LogsType.LOG__PLACE_BLOCK, event.getBlockPlaced().getType(), event.getBlockPlaced().getLocation());
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        new LogsBlock(event.getPlayer().getUniqueId(), event.getPlayer().getLocation(), LogsType.LOG__BREAK_BLOCK, event.getBlock().getType(), event.getBlock().getLocation());
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {

        if ((event.getClickedBlock() != null) && (event.getClickedBlock().getType().isInteractable())) {
            new LogsBlockInteract(event.getPlayer().getUniqueId(), event.getPlayer().getLocation(), LogsType.LOG__INTERACT_BLOCK, event.getClickedBlock().getType(), event.getClickedBlock().getLocation(), event.getAction());
        }
    }
}
