package fr.armotik.naurelliaanticheat.utiles;

import fr.armotik.naurelliaanticheat.listerners.LogsManager;
import fr.armotik.naurelliaanticheat.logs.Logs;
import fr.armotik.naurelliaanticheat.logs.LogsChat;
import fr.armotik.naurelliaanticheat.logs.LogsJoinQuit;
import fr.armotik.naurelliaanticheat.logs.LogsType;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FilesReader {

    private static final Logger logger = Logger.getLogger(FilesReader.class.getName());

    private FilesReader() {

        throw new IllegalStateException("Utility Class");
    }

    public static List<Logs> readJoinQuitLogs() {

        List<Logs> data = new ArrayList<>();
        File file = new File("server_logs/players_logs/logs__join-quit.txt");
        try {

            FileInputStream inputStream = new FileInputStream(file);
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader reader = new BufferedReader(inputStreamReader);

            String line;

            while ((line = reader.readLine()) != null) {

                String[] args = line.split(" - ");

                SimpleDateFormat format = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
                format.setTimeZone(TimeZone.getTimeZone("GMT"));
                format.parse(args[0]);

                UUID targetUUID = UUID.fromString(args[1]);

                String[] locationPart = args[3].split(",");
                locationPart[5] = locationPart[5].substring(0, locationPart[5].length() -1);
                World world = Bukkit.getWorld(locationPart[0].substring(locationPart[0].indexOf("=") + 1));

                double x = Double.parseDouble(locationPart[1].substring(locationPart[1].indexOf("=") + 1));
                double y = Double.parseDouble(locationPart[2].substring(locationPart[2].indexOf("=") + 1));
                double z = Double.parseDouble(locationPart[3].substring(locationPart[3].indexOf("=") + 1));
                float pitch = Float.parseFloat(locationPart[4].substring(locationPart[4].indexOf("=") + 1));
                float yaw = Float.parseFloat(locationPart[5].substring(locationPart[5].indexOf("=") + 1));

                Location location = new Location(world, x, y, z, pitch, yaw);

                switch (args[2].toUpperCase(Locale.ENGLISH)) {
                    case "LOG__JOIN" -> {
                        data.add(new LogsJoinQuit(targetUUID, location, LogsType.LOG__JOIN));
                    }
                    case "LOG__QUIT" -> {
                        data.add(new LogsJoinQuit(targetUUID, location, LogsType.LOG__QUIT));
                    }
                }

            }
            reader.close();

        } catch (FileNotFoundException e) {
            ExceptionsManager.fileNotFoundExceptionLog(e);
        } catch (IOException e) {
            ExceptionsManager.ioExceptionLog(e);
        } catch (ParseException e) {
            ExceptionsManager.parseExceptionLog(e);
        }

        logger.log(Level.INFO, "[NaurelliaAntiCheat] -> FilesReader : successfully read logs");
        return data;
    }

    public static void writeLogs() {

        List<Logs> logs__join_quit = LogsManager.getLogs__join_quit();
        List<Logs> logs__chat = LogsManager.getLogs__chat();

        if (logs__join_quit.isEmpty()) {

            logger.log(Level.INFO, "[NaurelliaAntiCheat] -> FilesReader : unable to write Logs - logs join quit is empty");
            return;
        }

        if (logs__chat.isEmpty()) {

            logger.log(Level.INFO, "[NaurelliaAntiCheat] -> FilesReader : unable to write Logs - logs chat is empty");
            return;
        }

        String folderPath = "server_logs/players_logs";
        File folder = new File(folderPath);

        if (!folder.exists()) {
            if (folder.mkdirs()) {

                logger.log(Level.INFO, "[NaurelliaAntiCheat] -> FilesReader : created server_logs/players_logs folder");
            }
        }

        logs__join_quit.forEach(log -> {

            try {

                BufferedWriter writerJoinQuit = null;

                switch (log.getLogsType()) {

                    case LOG__JOIN, LOG__QUIT -> {

                        writerJoinQuit = new BufferedWriter(new FileWriter("server_logs/players_logs/logs__join-quit.txt", false));

                        writerJoinQuit.flush();

                        writerJoinQuit
                                .append(log.getDate().toString())
                                .append(" - ").append(log.getTargetUUID().toString())
                                .append(" - ").append(log.getLogsType().toString());

                        if (log.getLocation() != null) {

                            writerJoinQuit.append(" - ").append(log.getLocation().toString());
                        }

                        writerJoinQuit.append("\n");
                        writerJoinQuit.close();
                    }

                }
            } catch (IOException e) {
                ExceptionsManager.ioExceptionLog(e);
            }
        });

        logs__chat.forEach(log -> {

            try {

                BufferedWriter writerChat = null;

                switch (log.getLogsType()) {

                    case LOG__JOIN, LOG__QUIT -> {

                        assert log instanceof LogsChat;

                        writerChat = new BufferedWriter(new FileWriter("server_logs/players_logs/logs__chat.txt", false));

                        writerChat.flush();

                        writerChat
                                .append(log.getDate().toString())
                                .append(" - ").append(log.getTargetUUID().toString())
                                .append(" - ").append(((LogsChat) log).getMessage())
                                .append(" - ").append(log.getLogsType().toString());

                        if (log.getLocation() != null) {

                            writerChat.append(" - ").append(log.getLocation().toString());
                        }

                        writerChat.append("\n");
                        writerChat.close();
                    }

                }
            } catch (IOException e) {
                ExceptionsManager.ioExceptionLog(e);
            }
        });

        logger.log(Level.INFO, "[NaurelliaAntiCheat] -> FilesReader : successfully wrote logs");
    }
}
