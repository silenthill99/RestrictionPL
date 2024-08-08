package fr.silenthill99.restrictionpl.inventory.holder.modo;

import fr.silenthill99.restrictionpl.inventory.Metiers;
import fr.silenthill99.restrictionpl.inventory.SilenthillHolder;
import fr.silenthill99.restrictionpl.inventory.hook.modo.DurationInventory.*;
import org.bukkit.OfflinePlayer;

import java.util.HashMap;

public class DurationHolder extends SilenthillHolder {

    private final OfflinePlayer target;
    private final Metiers metiers;

    public DurationHolder(OfflinePlayer target, Metiers metiers) {
        this.target = target;
        this.metiers = metiers;
    }

    public Metiers getMetiers() {
        return metiers;
    }

    public OfflinePlayer getTarget() {
        return target;
    }

    public HashMap<Integer, Duree> duree = new HashMap<>();

}
