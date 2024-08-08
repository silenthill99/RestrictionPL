package fr.silenthill99.restrictionpl.inventory;

import fr.silenthill99.restrictionpl.inventory.hook.modo.ChooseMetierInventory;
import fr.silenthill99.restrictionpl.inventory.hook.modo.DurationInventory;

import java.util.Arrays;
import java.util.List;

public enum InventoryType {
    CHOOSE_METIER(new ChooseMetierInventory()),
    DURATION(new DurationInventory())
    ;
    private final AbstractInventory<?> inv;

    InventoryType(AbstractInventory<?> inv) {
        this.inv = inv;
    }

    public AbstractInventory<?> getInv() {
        return inv;
    }

    public static List<InventoryType> asList() {
        return Arrays.asList(values());
    }
}
