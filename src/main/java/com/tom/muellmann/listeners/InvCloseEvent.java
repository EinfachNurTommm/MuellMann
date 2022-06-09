package com.tom.muellmann.listeners;

import com.tom.muellmann.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.scheduler.BukkitRunnable;

public class InvCloseEvent implements Listener {

    private Main plugin;

    public InvCloseEvent(Main plugin){
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);

    }

    @EventHandler
    public void onClose(InventoryCloseEvent e) {
        Player p = (Player) e.getPlayer();
        Inventory inv = e.getInventory();

        if (e.getInventory().getTitle().equals(plugin.sellInvName)) {
            if(!plugin.canClose.get(p)) {
                new BukkitRunnable() {
                    @Override
                    public void run () {
                        p.openInventory(inv);
                    }
                }.runTaskLater(plugin, 1);
            }
        }
    }
}
