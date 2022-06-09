package com.tom.muellmann;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class SellInventory {

    private Main plugin;

    public SellInventory(Main plugin) {
        this.plugin=plugin;
    }


    /**
     * Liefert ein verkaufs Inventar
     * @param p
     * @return Inventory
     */
    public Inventory getSellInv(Player p) {
        Inventory inv;
        inv = Bukkit.getServer().createInventory(null, plugin.invSize, plugin.sellInvName);

        for(int i=36; i<54; i++) {
            inv.setItem(i, plugin.buildIS(Material.STAINED_GLASS_PANE, "§a", 1, 15));
        }
        inv.setItem(45, plugin.buildIS(Material.STAINED_GLASS_PANE, plugin.cancel, 1, 14));
        inv.setItem(46, plugin.buildIS(Material.STAINED_GLASS_PANE, plugin.cancel, 1, 14));
        inv.setItem(47, plugin.buildIS(Material.STAINED_GLASS_PANE, plugin.cancel, 1, 14));

        inv.setItem(51, plugin.buildIS(Material.STAINED_GLASS_PANE, plugin.sell, 1, 5));
        inv.setItem(52, plugin.buildIS(Material.STAINED_GLASS_PANE, plugin.sell, 1, 5));
        inv.setItem(53, plugin.buildIS(Material.STAINED_GLASS_PANE, plugin.sell, 1, 5));

        plugin.canClose.put(p, false);

        return inv;
    }



}
