package fr.silenthill99.restrictionpl.inventory.holder.modo;

import fr.silenthill99.restrictionpl.inventory.Metiers;
import fr.silenthill99.restrictionpl.inventory.SilenthillHolder;
import org.bukkit.OfflinePlayer;

import java.util.HashMap;

public class ChooseMetierHolder extends SilenthillHolder {

    private final OfflinePlayer target;

    public ChooseMetierHolder(OfflinePlayer target) {
        this.target = target;
    }

    public OfflinePlayer getTarget() {
        return target;
    }

    public HashMap<Integer, Metiers> metiers = new HashMap<>();
}
