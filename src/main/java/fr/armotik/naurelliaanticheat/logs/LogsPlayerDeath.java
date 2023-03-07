package fr.armotik.naurelliaanticheat.logs;

import fr.armotik.naurelliaanticheat.listerners.LogsManager;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.Date;
import java.util.UUID;

public class LogsPlayerDeath extends Logs {

    private final Player killer;
    private final String deathMessage;
    private final String deathCause;

    /**
     * Constructor (without date)
     * @param targetUUID target UUID
     * @param location target location
     * @param logsType logs type
     * @param killer killer
     * @param deathMessage death message
     * @param deathCause death cause
     */
    public LogsPlayerDeath(UUID targetUUID, Location location, LogsType logsType, Player killer, String deathMessage, String deathCause) {
        super(targetUUID, location, logsType);

        this.killer = killer;
        this.deathMessage = deathMessage;
        this.deathCause = deathCause;

        LogsManager.getLogs__player_death().add(this);
        LogsManager.getLogs().add(this);
    }

    /**
     * Constructor (with date)
     * @param targetUUID target UUID
     * @param date date
     * @param location target location
     * @param logsType logs type
     * @param killer killer
     * @param deathMessage death message
     * @param deathCause death cause
     */
    public LogsPlayerDeath(UUID targetUUID, Date date, Location location, LogsType logsType, Player killer, String deathMessage, String deathCause) {
        super(targetUUID, date, location, logsType);

        this.killer = killer;
        this.deathMessage = deathMessage;
        this.deathCause = deathCause;

        LogsManager.getLogs__player_death().add(this);
        LogsManager.getLogs().add(this);
    }

    /**
     * Return the killer
     * @return Player
     */
    public Player getKiller() {
        return killer;
    }

    /**
     * Return the death message
     * @return String
     */
    public String getDeathMessage() {
        return deathMessage;
    }

    /**
     * Return the death cause
     * @return String
     */
    public String getDeathCause() {
        return deathCause;
    }

    /**
     * Return the logs in a string
     *
     * @return String
     */
    @Override
    public String toString() {
        return "LogsPlayerDeath::{" +
                "targetUUID=" + targetUUID +
                ", date=" + date +
                ", logsType=" + logsType +
                ", location=" + location +
                ", killer=" + killer +
                ", deathMessage='" + deathMessage + '\'' +
                ", deathCause='" + deathCause + '\'' +
                "},\n";
    }
}
