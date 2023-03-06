package fr.armotik.tests;

import fr.armotik.naurelliaanticheat.listerners.EventManager;
import org.bukkit.Location;
import org.bukkit.Server;
import org.bukkit.entity.Player;
import org.junit.jupiter.api.BeforeEach;

import java.util.UUID;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public abstract class Tests {

    protected Server server = mock(Server.class);
    protected Player Armotik = mock(Player.class);
    protected Player Armotika = mock(Player.class);
    protected EventManager eventManager = new EventManager();
    protected Location location = new Location(null, 0, 0, 0);

    @BeforeEach
    public void setUp() {


        // Set the server of the Armotik
        when(Armotik.getServer()).thenReturn(server);
        when(Armotika.getServer()).thenReturn(server);

        // Set the name and the UUID of the Armotik
        when(Armotik.getName()).thenReturn("Armotik");
        when(Armotik.getUniqueId()).thenReturn(UUID.fromString("0c06acbf-5114-4153-82c5-2a85a3f4320e"));

        when(Armotika.getName()).thenReturn("Armotika");
        when(Armotika.getUniqueId()).thenReturn(UUID.fromString("d4d2ad76-f412-4c1e-8858-43045093e4ed"));
    }
}
