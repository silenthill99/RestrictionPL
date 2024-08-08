package fr.silenthill99.restrictionpl;

import fr.silenthill99.restrictionpl.commands.RestrictionCommand;
import fr.silenthill99.restrictionpl.inventory.InventoryManager;
import fr.silenthill99.restrictionpl.manager.RestrictionManager;
import fr.silenthill99.restrictionpl.manager.ShowRestrict;
import fr.silenthill99.restrictionpl.mysql.DatabaseManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public final class Main extends JavaPlugin {

    private static Main instance;
    private DatabaseManager manager;
    private RestrictionManager restrictionManager;

    public static Main getInstance() {
        return instance;
    }
    @Override
    public void onEnable() {
        instance = this;
        getLogger().info("Le plugin est opérationnel !");
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new InventoryManager(), this);
        commands();
        manager = new DatabaseManager();
        restrictionManager = new RestrictionManager();
    }

    private void commands() {
        getCommand("restriction").setExecutor(new RestrictionCommand());
    }

    @Override
    public void onDisable() {
        manager.close();
    }

    public DatabaseManager getManager() {
        return manager;
    }

    public RestrictionManager getRestrictionManager() {
        return restrictionManager;
    }
}
