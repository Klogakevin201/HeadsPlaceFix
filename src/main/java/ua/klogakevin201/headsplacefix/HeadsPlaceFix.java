package ua.klogakevin201.headsplacefix;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public final class HeadsPlaceFix extends JavaPlugin implements Listener {
    private static HeadsPlaceFix instance;

    public void onLoad() {
        instance = this;
    }

    @Override
    public void onEnable() {
        saveDefaultConfig();
        Bukkit.getPluginManager().registerEvents(new PlaceListener(), this);
    }

    public static HeadsPlaceFix getInstance() {
        return instance;
    }
}
