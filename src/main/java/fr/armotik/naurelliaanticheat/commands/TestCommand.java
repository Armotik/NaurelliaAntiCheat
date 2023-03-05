package fr.armotik.naurelliaanticheat.commands;

import fr.armotik.naurelliaanticheat.utiles.FilesReader;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class TestCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        if (!sender.isOp()) return false;

        FilesReader.writeLogs();

        return false;
    }
}
