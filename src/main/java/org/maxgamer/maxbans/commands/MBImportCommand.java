package org.maxgamer.maxbans.commands;

import org.maxgamer.maxbans.commands.bridge.DynamicBanBridge;
import org.bukkit.ChatColor;
import org.maxgamer.maxbans.commands.bridge.VanillaBridge;
import org.maxgamer.maxbans.util.Formatter;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class MBImportCommand extends CmdSkeleton
{
    public MBImportCommand() {
        super("mbimport", "RNBans.import");
        this.namePos = -1;
    }
    
    public boolean run(final CommandSender sender, final Command cmd, final String label, final String[] args) {
        if (args.length == 0) {
            sender.sendMessage(Formatter.primary + "MaxBans Importer:");
            sender.sendMessage(Formatter.secondary + "/mbimport vanilla " + Formatter.primary + " - Imports vanilla bans.");
            sender.sendMessage(Formatter.secondary + "/mbimport dynamicban " + Formatter.primary + " - Imports dynamicBan bans.");
        }
        else if (args[0].equalsIgnoreCase("vanilla")) {
            final VanillaBridge bridge = new VanillaBridge();
            bridge.load();
            sender.sendMessage(Formatter.secondary + "Success.");
        }
        else if (args[0].equalsIgnoreCase("dynamicban")) {
            sender.sendMessage(ChatColor.RED + "Importing bans. This may take a while!");
            try {
                final DynamicBanBridge bridge2 = new DynamicBanBridge();
                bridge2.load();
                sender.sendMessage(ChatColor.GREEN + "Import successful!");
            }
            catch (Exception e) {
                sender.sendMessage(ChatColor.RED + "Error importing: " + e.getMessage());
            }
        }
        else {
            sender.sendMessage(Formatter.secondary + "Failed.  No known importer: " + args[0]);
        }
        return true;
    }
}
