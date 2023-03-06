package fr.armotik.naurelliaanticheat.logs;

import fr.armotik.naurelliaanticheat.listerners.LogsManager;
import org.bukkit.Location;

import java.util.UUID;

public class LogsChat extends Logs {

    private String message;

    public LogsChat(UUID targetUUID, Location location, LogsType logsType, String message) {
        super(targetUUID, location, logsType);

        this.message = message;

        if (message.contains(" - ")) {
            this.message = message.replace(" - ", "-");
        }

        if (message.contains("\n")) {
            this.message = message.replace("\n", " ");
        }

        LogsManager.getLogs__chat().add(this);
        LogsManager.getLogs().add(this);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Return the logs in a string
     * @return String
     */
    @Override
    public String toString() {
        return "LogsChat{" +
                "targetUUID=" + targetUUID +
                ", date=" + date +
                ", logsType=" + logsType +
                ", location=" + location +
                ", message='" + message + '\'' +
                '}';
    }
}
