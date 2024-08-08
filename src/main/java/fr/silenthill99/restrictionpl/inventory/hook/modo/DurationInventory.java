package fr.silenthill99.restrictionpl.inventory.hook.modo;

import fr.silenthill99.restrictionpl.ItemBuilder;
import fr.silenthill99.restrictionpl.Main;
import fr.silenthill99.restrictionpl.inventory.AbstractInventory;
import fr.silenthill99.restrictionpl.inventory.InventoryManager;
import fr.silenthill99.restrictionpl.inventory.InventoryType;
import fr.silenthill99.restrictionpl.inventory.Metiers;
import fr.silenthill99.restrictionpl.inventory.holder.modo.DurationHolder;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class DurationInventory extends AbstractInventory<DurationHolder> {

    Main main = Main.getInstance();

    public DurationInventory() {
        super(DurationHolder.class);
    }

    @Override
    public void openInventory(Player player, Object... args) {
        OfflinePlayer target = (OfflinePlayer) args[0];
        Metiers metiers = (Metiers) args[1];

        DurationHolder holder = new DurationHolder(target, metiers);
        Inventory inv = createInventory(holder, 27, ChatColor.RED + "Durée de la restriction");
        int slot = 0;
        for (Duree duree : Duree.values()) {
            holder.duree.put(slot, duree);
            inv.setItem(slot++, new ItemBuilder(Material.PAPER).setName(duree.getName()).toItemStack());
        }
        inv.setItem(18, RETOUR);
        player.openInventory(inv);
    }

    @Override
    public void manageInventory(InventoryClickEvent event, ItemStack current, Player player, DurationHolder holder) {
        OfflinePlayer target = holder.getTarget();
        Metiers metiers = holder.getMetiers();

        Duree duree = holder.duree.get(event.getSlot());

        switch (current.getType()) {
            case DOUBLE_PLANT: {
                InventoryManager.openInventory(player, InventoryType.CHOOSE_METIER, target);
                break;
            }
            case PAPER: {
                Bukkit.getScheduler().runTaskAsynchronously(main, () -> main.getRestrictionManager().restrictPlayer(target, metiers, duree));
                break;
            }
            default:
                break;
        }
    }

    public enum Duree {
        ONE_WEEK("1 semaine", 3600*24*7*1000),
        TWO_WEEKS("2 semaines", 3600*24*7*2*1000),
        THREE_WEEKS("3 semaines", 3600*24*7*3*1000),
        ONE_MONTH("1 mois", 3600L *24*30*1000);

        private final String name;
        private final long endToMillis;

        Duree(String name, long endToMillis) {
            this.name = name;
            this.endToMillis = endToMillis;
        }

        public String getName() {
            return name;
        }

        public long getEndToMillis() {
            return endToMillis;
        }
    }
}
