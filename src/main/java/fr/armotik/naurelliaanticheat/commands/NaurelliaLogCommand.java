package fr.armotik.naurelliaanticheat.commands;

import fr.armotik.naurelliaanticheat.Louise;
import fr.armotik.naurelliaanticheat.utiles.Database;
import fr.armotik.naurelliaanticheat.utiles.ExceptionsManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class NaurelliaLogCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        Logger logger = Logger.getLogger(NaurelliaLogCommand.class.getName());

        if (!sender.hasPermission("naurellia.mod")) {
            sender.sendMessage(Louise.permissionMissing());
        }

        if (args.length < 1) {
            sender.sendMessage(Louise.wrongCommand());
            return false;
        }

        ResultSet res = Database.executeQuery("SELECT uuid FROM usersIG WHERE username = '" + args[0] + "'");

        if (res == null) {

            logger.log(Level.WARNING, "[NaurelliaAntiCheat] -> NaurelliaLogCommand : onCommand ERROR - res == null");
            sender.sendMessage(Louise.commandError());
            Database.close();
            return false;
        }

        try {

            if (!res.next()) {

                sender.sendMessage(Louise.playerNotFound());
                Database.close();
                return false;
            }

            Database.close();
            return true;
        } catch (SQLException e) {
            ExceptionsManager.sqlExceptionLog(e);
            Database.close();
            return false;
        }
    }
}
