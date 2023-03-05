package fr.armotik.naurelliaanticheat.listerners;

import fr.armotik.naurelliaanticheat.logs.LogsChat;
import fr.armotik.naurelliaanticheat.logs.LogsJoinQuit;
import fr.armotik.naurelliaanticheat.logs.LogsType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

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
}
