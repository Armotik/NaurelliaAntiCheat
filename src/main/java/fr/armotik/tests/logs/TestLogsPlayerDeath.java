package fr.armotik.tests.logs;

import fr.armotik.naurelliaanticheat.listerners.LogsManager;
import fr.armotik.naurelliaanticheat.logs.LogsPlayerDeath;
import fr.armotik.tests.Tests;
import org.bukkit.Material;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TestLogsPlayerDeath extends Tests {

    @Test
    public void testOnDeath() {

        PlayerDeathEvent playerDeathEvent = mock(PlayerDeathEvent.class);
        when(playerDeathEvent.getEntity()).thenReturn(Armotik);

        assertTrue(LogsManager.getLogs().isEmpty());

        EntityDamageEvent damageEvent = mock(EntityDamageEvent.class);
        when(damageEvent.getEntity()).thenReturn(Armotika);
        when(damageEvent.getCause()).thenReturn(EntityDamageEvent.DamageCause.VOID);
        when(damageEvent.isCancelled()).thenReturn(false);

        List<ItemStack> drops = new ArrayList<>();
        drops.add(new ItemStack(Material.DIAMOND));

        // Créer l'événement de mort simulé
        PlayerDeathEvent deathEvent = new PlayerDeathEvent(Armotik, drops, 0, "Death message");
        deathEvent.setDeathMessage("Death message");
        deathEvent.setKeepInventory(true);
        deathEvent.setKeepLevel(true);
        deathEvent.setDroppedExp(0);
        deathEvent.setNewLevel(0);
        deathEvent.getEntity().setLastDamageCause(damageEvent);

        // Vérifier que l'événement de mort a été correctement créé
        assertEquals(Armotik.getUniqueId(), deathEvent.getEntity().getUniqueId());
        assertEquals("Death message", deathEvent.getDeathMessage());

        eventManager.onPlayerDeath(deathEvent);

        assertTrue(LogsManager.getLogs().get(0) instanceof LogsPlayerDeath);
        assertEquals("UNKNOWN", ((LogsPlayerDeath) LogsManager.getLogs().get(0)).getDeathCause());

        System.out.println(LogsManager.getLogs());
    }
}
