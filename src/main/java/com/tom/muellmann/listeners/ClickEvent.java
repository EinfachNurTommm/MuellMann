package com.tom.muellmann.listeners;

import com.tom.muellmann.Main;
import com.tom.muellmann.PlayerStatus;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.ArrayList;
import java.util.List;

public class ClickEvent implements Listener {

    private Main plugin;

    int spam = 0;

    public ClickEvent(Main plugin) {
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onClick(PlayerInteractEvent e) {
        Player p = e.getPlayer();

        // PlayerStatus bekommen um später vom spieler Informationen zu bekommen
        PlayerStatus ps =  PlayerStatus.getInstanceOfPlayer(p);

        // spam nutzen, da sonst das event zweimal aufgerufen wird (wegen off und mainhand)
        if(spam != 0) {
            spam = 0;
            return;
        }


        if(e.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if(e.getClickedBlock().getType().equals(Material.CAULDRON)) {
                if(plugin.myCM.getMuelleimer().contains(e.getClickedBlock().getLocation())) { //Ob der Cauldron als Mülleimer registriert ist
                    if(!ps.getUsedMuelleimer().contains(e.getClickedBlock().getLocation())) { // Ob man den Mülleimer noch nicht eingesammelt hat
                        if(p.getItemInHand().getType().equals(Material.WOOD_SWORD) && p.getItemInHand().getDurability() < 10) {
                            if(p.getItemInHand().getItemMeta().getDisplayName() == plugin.muelltueteName) {
                                if(p.getInventory().firstEmpty() != -1) { // Abfrage ob noch genug Slots im Inventar frei sind
                                    int db = p.getItemInHand().getDurability();
                                    p.getItemInHand().setDurability((short) (db+1));
                                    p.getInventory().addItem(plugin.buildIS(Material.STONE_SWORD, "§6Müllsack", 1, 1));
                                    p.sendMessage("§aDu hast einen Müllsack eingesammelt!");
                                    ps.addUsedMuelleimer(e.getClickedBlock().getLocation());

                                    if(p.getItemInHand().getDurability() == 10) {
                                        p.sendMessage("§cDu hast deine Mülltüten aufgebraucht!");
                                        p.getItemInHand().setAmount(0);
                                    }
                                } else {
                                    p.sendMessage("§cDu hast nicht mehr genügend Platz!");
                                }
                            }
                        }
                    } else {
                        p.sendMessage("§cDu hast diesen Mülleimer bereits eingesammelt!");
                    }
                    spam++;
                }
            }
        }
    }

}
