package fr.silenthill99.restrictionpl.commands;

import fr.silenthill99.restrictionpl.Main;
import fr.silenthill99.restrictionpl.inventory.InventoryManager;
import fr.silenthill99.restrictionpl.inventory.InventoryType;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RestrictionCommand implements CommandExecutor {
    Main main = Main.getInstance();
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) return false;

        Player player = (Player) sender;

        if (args.length != 1) {
            player.sendMessage(ChatColor.RED + "/restriction <joueur>");
            return false;
        }

        OfflinePlayer target = Bukkit.getOfflinePlayer(args[0]);

        InventoryManager.openInventory(player, InventoryType.CHOOSE_METIER, target);

        return true;
    }
}
