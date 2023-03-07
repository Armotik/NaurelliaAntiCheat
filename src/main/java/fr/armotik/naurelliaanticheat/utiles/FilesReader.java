package fr.armotik.naurelliaanticheat.utiles;

import fr.armotik.naurelliaanticheat.listerners.LogsManager;
import fr.armotik.naurelliaanticheat.logs.*;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.inventory.ItemStack;

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

    /**
     * Check if the file exist
     *
     * @param path path of the file
     * @return true if the file exist
     */
    public static boolean checkFileExist(String path) {

        File file = new File(path);
        return !file.exists();
    }

    /**
     * Create a file
     *
     * @param s path of the file
     */
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

    /**
     * Write in a file
     */
    public static void writeLogs() {

        if (FilesReader.checkFileExist("server_logs/players_logs/logs.txt")) {

            FilesReader.createFile("server_logs/players_logs/logs.txt");
        }

        File file = new File("server_logs/players_logs/logs.txt");

        List<Logs> existingLogs = readLogs();

        LogsManager.getLogs().forEach(log -> {

            boolean exist = false;

            for (Logs existingLog : existingLogs) {

                if (existingLog.equals(log)) {

                    exist = true;
                    break;
                }
            }

            if (!exist) {

                try {
                    BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
                    writer.flush();
                    writer.append(log.toString());
                    writer.close();
                } catch (IOException e) {
                    ExceptionsManager.ioExceptionLog(e);
                }
            }
        });

        logger.log(Level.INFO, "[NaurelliaAntiCheat] -> FilesReader : writeLogs - logs written");
    }

    /**
     * Read the logs
     *
     * @return list of logs
     */
    public static List<Logs> readLogs() {

        List<Logs> logs = new ArrayList<>();

        if (checkFileExist("server_logs/players_logs/logs.txt")) {

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
                LogsType logsType;
                Location location = null;

                for (String datum : data) {

                    String[] elements = datum.split(",(?![^{]*})|(?<=\\}),(?=\\w)");
                    World world = null;

                    for (String ignored : elements) {

                        String[] split = datum.split(",(?![^{]*})|(?<=\\}),(?=\\w)");

                        if (split[0].equals("targetUUID")) {
                            targetUUID = UUID.fromString(split[1]);
                        }

                        if (split[0].equals("date")) {
                            date = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy").parse(split[1]);
                            assert date != null;
                        }

                        if (split[0].equals("location")) {

                            world = getWorldFromString(split[1]);
                        }

                        if (split[0].matches("^x=-?\\d+(\\.\\d+)?,y=-?\\d+(\\.\\d+)?,z=-?\\d+(\\.\\d+)?,pitch=-?\\d+(\\.\\d+)?,yaw=-?\\d+(\\.\\d+)?$\n")) {

                            String[] locationData = split[1].split(",");
                            location = getLocationFromString(world, locationData);
                        }

                        if (split[0].equals("logsType")) {
                            logsType = LogsType.valueOf(split[1]);

                            switch (logsType) {

                                case LOG__JOIN, LOG__QUIT -> new LogsJoinQuit(targetUUID, date, location, logsType);
                                case LOG__BED_ENTER, LOG__BED_LEAVE -> {

                                    Material material = null;
                                    Location blockLocation = null;

                                    if (split[0].equals("material")) {

                                        material = Material.valueOf(split[1]);
                                    }

                                    if (split[0].equals("blockLocation")) {

                                        world = getWorldFromString(split[1]);
                                    }

                                    if (split[0].matches("^x=-?\\d+(\\.\\d+)?,y=-?\\d+(\\.\\d+)?,z=-?\\d+(\\.\\d+)?,pitch=-?\\d+(\\.\\d+)?,yaw=-?\\d+(\\.\\d+)?$\n")) {

                                        String[] locationData = split[1].split(",");
                                        blockLocation = getLocationFromString(world, locationData);
                                    }

                                    new LogsBed(targetUUID, date, location, logsType, material, blockLocation);
                                }
                                case LOG__INTERACT_BLOCK -> {

                                    Material material = null;
                                    Location blockLocation = null;
                                    Action action = null;

                                    if (split[0].equals("material")) {

                                        material = Material.valueOf(split[1]);
                                    }

                                    if (split[0].equals("blockLocation")) {

                                        world = getWorldFromString(split[1]);
                                    }

                                    if (split[0].matches("^x=-?\\d+(\\.\\d+)?,y=-?\\d+(\\.\\d+)?,z=-?\\d+(\\.\\d+)?,pitch=-?\\d+(\\.\\d+)?,yaw=-?\\d+(\\.\\d+)?$\n")) {

                                        String[] locationData = split[1].split(",");
                                        blockLocation = getLocationFromString(world, locationData);
                                    }

                                    if (split[0].equals("action")) {

                                        action = Action.valueOf(split[1]);
                                    }

                                    Logs log = new LogsBlockInteract(targetUUID, date, location, logsType, material, blockLocation, action);
                                    logs.add(log);
                                }
                                case LOG__BREAK_BLOCK, LOG__PLACE_BLOCK -> {

                                    Material material = null;
                                    Location blockLocation = null;

                                    if (split[0].equals("material")) {

                                        material = Material.valueOf(split[1]);
                                    }

                                    if (split[0].equals("blockLocation")) {

                                        world = getWorldFromString(split[1]);
                                    }

                                    if (split[0].matches("^x=-?\\d+(\\.\\d+)?,y=-?\\d+(\\.\\d+)?,z=-?\\d+(\\.\\d+)?,pitch=-?\\d+(\\.\\d+)?,yaw=-?\\d+(\\.\\d+)?$\n")) {

                                        String[] locationData = split[1].split(",");
                                        blockLocation = getLocationFromString(world, locationData);
                                    }

                                    Logs log = new LogsBlock(targetUUID, date, location, logsType, material, blockLocation);
                                    logs.add(log);
                                }
                                case LOG__DROP_ITEM -> {

                                    ItemStack itemStack = null;
                                    Location dropLocation = null;
                                    int amount = 0;

                                    if (split[0].equals("itemStack")) {

                                        String[] itemStackData = split[1].split(" x ");
                                        String material = itemStackData[0].substring(11);
                                        amount = Integer.parseInt(itemStackData[1].substring(2));

                                        itemStack = new ItemStack(Material.valueOf(material), amount);
                                    }

                                    if (split[0].equals("dropLocation")) {

                                        world = getWorldFromString(split[1]);
                                    }

                                    if (split[0].matches("^x=-?\\d+(\\.\\d+)?,y=-?\\d+(\\.\\d+)?,z=-?\\d+(\\.\\d+)?,pitch=-?\\d+(\\.\\d+)?,yaw=-?\\d+(\\.\\d+)?$\n")) {

                                        String[] locationData = split[1].split(",");
                                        dropLocation = getLocationFromString(world, locationData);
                                    }

                                    if (!(amount < 1 || itemStack.getType() == Material.AIR)) {

                                        Logs log = new LogsDropItems(targetUUID, date, location, logsType, itemStack, dropLocation);
                                        logs.add(log);
                                    }
                                }
                                case LOG__COMMAND_SEND, LOG__MESSAGE_SEND -> {

                                    String message = "null";

                                    if (split[0].equals("message")) {
                                        message = split[1];
                                    }

                                    Logs log = new LogsChat(targetUUID, date, location, logsType, message);
                                    logs.add(log);
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

                                    Logs log = new LogsPlayerDeath(targetUUID, date, location, logsType, killer, deathMessage, deathCause);
                                    logs.add(log);
                                }
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

        logger.log(Level.INFO, "[NaurelliaAntiCheat] -> FilesReader : readLogs - logs read");
        return logs;
    }

    /**
     * Get world from string
     *
     * @param worldNamePath String
     * @return World
     */
    private static World getWorldFromString(String worldNamePath) {

        World world = null;

        String worldName = worldNamePath.split("=")[2];
        world = Bukkit.getWorld(worldName);

        return world;
    }

    /**
     * Get location from string
     *
     * @param locationData String[]
     * @return Location
     */
    private static Location getLocationFromString(World world, String[] locationData) {

        double x = 0;
        double y = 0;
        double z = 0;
        float yaw = 0;
        float pitch = 0;

        for (String locationDatum : locationData) {

            String[] locationDataSplit = locationDatum.split("=");

            if (locationDataSplit[1].endsWith("}")) {
                locationDataSplit[1] = locationDataSplit[1].substring(0, locationDataSplit[1].length() - 1);
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

        return new Location(world, x, y, z, yaw, pitch);
    }
}
