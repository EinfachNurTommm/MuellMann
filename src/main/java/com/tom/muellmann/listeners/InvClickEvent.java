package com.tom.muellmann.listeners;

import com.tom.muellmann.Main;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

import java.util.ArrayList;
import java.util.List;

public class InvClickEvent implements Listener {

    private Main plugin;

    public InvClickEvent(Main plugin) {
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onInvClick(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();

        // Prüfen ob der Klick im Inventar war
        if(e.getSlot() !=-999) {
            if(p.getOpenInventory().getTopInventory().getName() == plugin.sellInvName) {
                if(e.getCurrentItem().hasItemMeta() || e.getCurrentItem().getType().equals(Material.AIR)) {
                    Material cI = e.getCurrentItem().getType();

                    if (cI.equals(Material.AIR)) {
                        return;
                    }else if (cI.equals(Material.STONE_SWORD) && e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(plugin.muellsackName)) {
                        return;


                        //Bestätigen / Abbrechen
                    } else if(cI.equals(Material.STAINED_GLASS_PANE)) {

                        Inventory openInv = p.getOpenInventory().getTopInventory();
                        List<Integer> slotList = new ArrayList<Integer>();

                        for(int i = 0; i<plugin.invSize; i++) {
                            if(openInv.getItem(i) != null) {
                                if(openInv.getItem(i).getType().equals(Material.STONE_SWORD)) {
                                    if(openInv.getItem(i).getItemMeta().getDisplayName().equalsIgnoreCase(plugin.muellsackName)) {
                                        slotList.add(i);
                                    }
                                }
                            }
                        }

                        // Wenn Rote Glasscheibe geklickt wird und der Displayname stimmt, werden die Items aus dem Sell Inventar zurück gegeben.
                        if(e.getCurrentItem().getDurability() == 14 && e.getCurrentItem().getItemMeta().getDisplayName().equals(plugin.cancel)) {
                            for(int i = 0; i<slotList.size(); i++) {
                                p.getInventory().addItem(plugin.buildIS(Material.STONE_SWORD, "§6Müllsack", 1, 1));
                            }

                            plugin.canClose.put(p, true);
                            p.closeInventory();
                            return;

                        // Wenn Grüne Glasscheibe geklickt wird und der Displayname stimmt, wird das Inventar geschlossen und man bekommt Geld pro verbrannten Sack
                        } else if(e.getCurrentItem().getDurability() == 5 && e.getCurrentItem().getItemMeta().getDisplayName().equals(plugin.sell)) {
                            int muellsaecke = slotList.size();
                            int myMoney = muellsaecke*plugin.money;

                            // Hier kann man der Person noch das Geld geben

                            plugin.canClose.put(p, true);
                            p.closeInventory();
                            if(muellsaecke <= 0) {

                            } else {
                                p.sendMessage("§aDu bekommst für den verkauf von §c" + muellsaecke + " §aMüllsäcken §c" + myMoney + "€.");
                            }
                            return;
                        }
                    }
                }

                e.setCancelled(true);
                p.sendMessage("Du kannst nur Müllsäcke verbrennen!");
            }
        }
    }

}
