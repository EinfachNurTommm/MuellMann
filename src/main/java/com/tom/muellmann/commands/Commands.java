package com.tom.muellmann.commands;

import com.tom.muellmann.Main;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class Commands implements CommandExecutor {

    public Main plugin;

    public Commands(Main main) {
        this.plugin = main;
    }


    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String cmdlabel, String[] args) {
        if(sender instanceof Player) {
            Player p = (Player) sender;
            Inventory inv = p.getInventory();
            if(args.length == 1) {
                if(args[0].equalsIgnoreCase("getMuelleimer") || args[0].equalsIgnoreCase("getMülleimer")) {
                    inv.addItem(plugin.buildIS(Material.CAULDRON_ITEM, "§4Mülleimer", 1, 0));
                    p.sendMessage("Du hast einen Mülleimer bekommen!");

                    // Commands um die Sachen zu bekommen, die man normalerweise in Shops oder von NPCs bekommt.
                } else if(args[0].equalsIgnoreCase("verbrennung")) {
                    p.openInventory(plugin.mySellInv.getSellInv(p));

                } else if(args[0].equalsIgnoreCase("gettuete") || args[0].equalsIgnoreCase("gettüte")) {
                    inv.addItem(plugin.buildIS(Material.WOOD_SWORD, plugin.muelltueteName, 1, 0));

                } else if(args[0].equalsIgnoreCase("help")) {
                    sendHelp(p);

                } else {
                    sendHelp(p);
                }
            } else {
                sendHelp(p);
            }
        } else {
            sender.sendMessage("Der Befehl kann nur als Spieler ausgefuehrt werden!");
        }

        return true;
    }


    private void sendHelp(Player p) {
        p.sendMessage("                 §3----------§r§cMüllmann§3----------");
        p.sendMessage("§6/mm getMuelleimer §c- §bUm einen Mülleimer zu bekommen!");
        p.sendMessage("§6/mm verbrennung §c- §bUm das Inventar für die Müllverbrennen zu öffnen!");
        p.sendMessage("§6/mm getTüte §c- §bUm eine Mülltüte zu bekommen!");
    }
}
