package fr.silenthill99.restrictionpl.inventory.hook.modo;

import fr.silenthill99.restrictionpl.ItemBuilder;
import fr.silenthill99.restrictionpl.inventory.AbstractInventory;
import fr.silenthill99.restrictionpl.inventory.InventoryManager;
import fr.silenthill99.restrictionpl.inventory.InventoryType;
import fr.silenthill99.restrictionpl.inventory.Metiers;
import fr.silenthill99.restrictionpl.inventory.holder.modo.ChooseMetierHolder;
import org.anjocaido.groupmanager.GMConfiguration;
import org.anjocaido.groupmanager.GroupManager;
import org.anjocaido.groupmanager.commands.BaseCommand;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class ChooseMetierInventory extends AbstractInventory<ChooseMetierHolder> {
    public ChooseMetierInventory() {
        super(ChooseMetierHolder.class);
    }

    @Override
    public void openInventory(Player player, Object... args) {
        OfflinePlayer target = (OfflinePlayer) args[0];

        ChooseMetierHolder holder = new ChooseMetierHolder(target);

        Inventory inv = createInventory(holder, 9, ChatColor.DARK_RED + "Métier | " + target.getName());
        int slot = 0;
        for (Metiers metiers : Metiers.values()) {
            holder.metiers.put(slot, metiers);
            inv.setItem(slot++, new ItemBuilder(Material.PAPER).setName(ChatColor.GOLD + metiers.getName()).toItemStack());
        }
        inv.setItem(inv.getSize()-1, AbstractInventory.CLOSE);
        player.openInventory(inv);
    }

    @Override
    public void manageInventory(InventoryClickEvent event, ItemStack current, Player player, ChooseMetierHolder holder) {
        OfflinePlayer target = holder.getTarget();
        Metiers metiers = holder.metiers.get(event.getSlot());
        if (current.getType().equals(Material.PAPER)) {
            InventoryManager.openInventory(player, InventoryType.DURATION, target, metiers);
        }
    }
}
