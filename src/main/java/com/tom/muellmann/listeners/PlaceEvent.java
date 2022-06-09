package com.tom.muellmann.listeners;

import com.tom.muellmann.Main;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.Inventory;

public class PlaceEvent implements Listener {

    private Main plugin;

    public PlaceEvent(Main plugin) {
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onPlace(BlockPlaceEvent e) {
        Player p = e.getPlayer();
        Inventory inv = p.getInventory();
        if(e.getBlock().getType().equals(Material.CAULDRON)) {
            if (p.getItemInHand().getType().equals(Material.CAULDRON_ITEM)) {
                // Exception abfangen, da sonst in der Console eine IndexOutOfBounce-Exception kommt, da die Range von Click zu weit entfernt ist.
                try {
                    if (p.getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase("§4Mülleimer")) {
                        plugin.myCM.setMuelleimer(e.getBlockPlaced().getLocation());
                        p.sendMessage("§aDu hast erfolgreich einen Mülleimer platziert!");
                    }
                } catch (Exception ex) {

                }
            }
        }
    }

}
