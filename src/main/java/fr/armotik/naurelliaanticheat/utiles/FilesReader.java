package fr.armotik.naurelliaanticheat.utiles;

import fr.armotik.naurelliaanticheat.logs.*;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.io.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FilesReader {

    private static final Logger logger = Logger.getLogger(FilesReader.class.getName());

    private FilesReader() {

        throw new IllegalStateException("Utility Class");
    }

    public static boolean checkFileExist(String path) {

        File file = new File(path);
        return file.exists();
    }

    public static void createFile(String s) {

        File file = new File(s);
        try {
            if (file.createNewFile()) {
                logger.log(Level.INFO, "[NaurelliaAntiCheat] -> FilesReader : successfully created file " + s);
            }
        } catch (IOException e) {
            ExceptionsManager.ioExceptionLog(e);
        }
    }

    public static void writeInFile(String s, String toString) {

        File file = new File(s);
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
            writer.flush();
            writer.append(toString);
            writer.close();
        } catch (IOException e) {
            ExceptionsManager.ioExceptionLog(e);
        }
    }

    public static List<Logs> readLogs() {

        List<Logs> logs = new ArrayList<>();

        if (!checkFileExist("server_logs/players_logs/logs.txt")) {

            logger.log(Level.INFO, "[NaurelliaAntiCheat] -> FilesReader : readLogs - file server_logs/players_logs/logs.txt not found");
            return new ArrayList<>();
        }

        try {
            BufferedReader reader = new BufferedReader(new FileReader("server_logs/players_logs/logs.txt"));
            String line;
            while ((line = reader.readLine()) != null) {

                String information = line.split("::")[1];
                information = information.substring(1, information.length() - 2);

                String[] data = information.split(", ");

                UUID targetUUID = null;
                Date date = null;
                LogsType logsType = null;
                Location location = null;

                for (String datum : data) {

                    String[] split = datum.split("=");

                    if (split[0].equals("targetUUID")) {
                        targetUUID = UUID.fromString(split[1]);
                    }

                    if (split[0].equals("date")) {
                        date = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").parse(split[1]);
                        assert date != null;
                    }

                    if (split[0].equals("location")) {

                        String[] locationData = split[1].split(",");

                        World world = null;
                        double x = 0;
                        double y = 0;
                        double z = 0;
                        float yaw = 0;
                        float pitch = 0;

                        for (String locationDatum : locationData) {

                            String[] locationDataSplit = locationDatum.split("=");

                            if (locationDataSplit[0].startsWith("Location{world=CraftWorld{name=")) {

                                String worldName = locationDataSplit[0].substring(31, locationDataSplit[0].length() - 1);
                                world = Bukkit.getWorld(worldName);
                            }

                            if (locationDataSplit[0].equals("x")) {
                                x = Double.parseDouble(locationDataSplit[1]);
                            }

                            if (locationDataSplit[0].equals("y")) {
                                y = Double.parseDouble(locationDataSplit[1]);
                            }

                            if (locationDataSplit[0].equals("z")) {
                                z = Double.parseDouble(locationDataSplit[1]);
                            }

                            if (locationDataSplit[0].equals("yaw")) {
                                yaw = Float.parseFloat(locationDataSplit[1]);
                            }

                            if (locationDataSplit[0].equals("pitch")) {
                                pitch = Float.parseFloat(locationDataSplit[1]);
                            }
                        }

                        location = new Location(world, x, y, z, yaw, pitch);
                    }

                    if (split[0].equals("logsType")) {
                        logsType = LogsType.valueOf(split[1]);

                        switch (logsType) {

                            case LOG__JOIN, LOG__QUIT -> new LogsJoinQuit(targetUUID, date, location, logsType);
                            case LOG__COMMAND_SEND, LOG__MESSAGE_SEND -> {

                                String message = "null";

                                if (split[0].equals("message")) {
                                    message = split[1];
                                }

                                new LogsChat(targetUUID, date, location, logsType, message);
                            }
                            case LOG__DEATH_PLAYER -> {

                                Player killer = null;
                                String deathMessage = null;
                                String deathCause = null;

                                if (split[0].equals("killer")) {

                                    if (!split[1].equals("null")) {

                                        String killerName = split[1].substring(13, split[1].length() - 1);
                                        ResultSet res = Database.executeQuery("SELECT uuid FROM players WHERE name = '" + killerName + "'");

                                        if (res == null) {
                                            logger.log(Level.WARNING, "[NaurelliaAntiCheat] -> FilesReader : readLogs - res == null");
                                            Database.close();
                                            return new ArrayList<>();
                                        }

                                        if (res.next()) {
                                            killer = Bukkit.getOfflinePlayer(UUID.fromString(res.getString("uuid"))).getPlayer();
                                        } else {

                                            logger.log(Level.WARNING, "[NaurelliaAntiCheat] -> FilesReader : readLogs - killer not found");
                                        }

                                        Database.close();
                                    }
                                }

                                if (split[0].equals("deathMessage")) {
                                    deathMessage = split[1];
                                }

                                if (split[0].equals("deathCause")) {
                                    deathCause = split[1];
                                }

                                new LogsPlayerDeath(targetUUID, date, location, logsType, killer, deathMessage, deathCause);
                            }
                        }
                    }
                }
            }
            reader.close();
        } catch (IOException e) {
            ExceptionsManager.ioExceptionLog(e);
            return new ArrayList<>();
        } catch (ParseException e) {
            ExceptionsManager.parseExceptionLog(e);
            return new ArrayList<>();
        } catch (SQLException e) {
            ExceptionsManager.sqlExceptionLog(e);
            Database.close();
            return new ArrayList<>();
        }

        return logs;
    }
}
