package com.tom.muellmann.listeners;

import com.tom.muellmann.Main;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class BreakEvent implements Listener {

    private Main plugin;

    public BreakEvent(Main plugin) {
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onBreak(BlockBreakEvent e) {
        Player p = e.getPlayer();
        Location bLoc = e.getBlock().getLocation();
        if(p.hasPermission("muellmann.all")) {
            if(e.getBlock().getType().equals(Material.CAULDRON)) {
                if(plugin.myCM.getMuelleimer().contains(bLoc)) { // Prüfen, ob der Cauldron als Mülleimer registriert ist
                    plugin.myCM.removeMuelleimer(bLoc);
                    p.sendMessage("§cAchtung, du hast einen Mülleimer abgebaut!");
                }
            }
        }
    }
}
