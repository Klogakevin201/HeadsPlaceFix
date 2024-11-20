package ua.klogakevin201.headsplacefix;

import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.ArrayList;
import java.util.List;

public class PlaceListener implements Listener {
    private static final List<Player> onConfirm = new ArrayList<>();


    @EventHandler
    public void onPlace(BlockPlaceEvent e) {
        Player player = e.getPlayer();
        Block block = e.getBlock();
        FileConfiguration config = HeadsPlaceFix.getInstance().getConfig();

        boolean confirm = config.getBoolean("settings.confirm");

        if (block.getType().toString().contains("PLAYER")) {
            if (!confirm) {
                e.setCancelled(true);
                return;
            }

            if (onConfirm.contains(player)) {
                onConfirm.remove(player);
                e.setCancelled(true);
                return;
            }

            onConfirm.add(player);
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        Player player = e.getPlayer();

        if (onConfirm.contains(player)) {
            onConfirm.remove(player);
        }
    }
}
