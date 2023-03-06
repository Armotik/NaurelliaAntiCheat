package fr.armotik.tests.logs;

import fr.armotik.naurelliaanticheat.listerners.LogsManager;
import fr.armotik.naurelliaanticheat.logs.LogsJoinQuit;
import fr.armotik.naurelliaanticheat.logs.LogsType;
import fr.armotik.tests.Tests;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TestLogsJoinQuit extends Tests {

    @Test
    public void testOnJoin() {

        PlayerJoinEvent playerJoinEvent = mock(PlayerJoinEvent.class);
        when(playerJoinEvent.getPlayer()).thenReturn(Armotik);

        Assertions.assertTrue(LogsManager.getLogs().isEmpty());

        eventManager.onPlayerJoin(playerJoinEvent);

        Assertions.assertFalse(LogsManager.getLogs().isEmpty());
        Assertions.assertEquals(LogsType.LOG__JOIN, LogsManager.getLogs().get(0).getLogsType());
        System.out.println(LogsManager.getLogs());

        assertTrue(LogsManager.getLogs().get(0) instanceof LogsJoinQuit);

        LogsManager.getLogs().clear();
    }

    @Test
    public void testOnQuit() {

        PlayerQuitEvent playerQuitEvent = mock(PlayerQuitEvent.class);
        when(playerQuitEvent.getPlayer()).thenReturn(Armotik);

        Assertions.assertTrue(LogsManager.getLogs().isEmpty());

        eventManager.onPlayerQuit(playerQuitEvent);

        Assertions.assertFalse(LogsManager.getLogs().isEmpty());
        Assertions.assertEquals(LogsType.LOG__QUIT, LogsManager.getLogs().get(0).getLogsType());
        System.out.println(LogsManager.getLogs());

        assertTrue(LogsManager.getLogs().get(0) instanceof LogsJoinQuit);

        LogsManager.getLogs().clear();
    }
}
