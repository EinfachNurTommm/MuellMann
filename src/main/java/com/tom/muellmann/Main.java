package com.tom.muellmann;

import com.tom.muellmann.commands.Commands;
import com.tom.muellmann.listeners.*;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.List;

public final class Main extends JavaPlugin {

    public FileConfiguration cfg = getConfig();

    public ConfigMethods myCM = new ConfigMethods(this);
    public SellInventory mySellInv = new SellInventory(this);

    public String muelltueteName = "§eMülltüte";
    public String muellsackName = "§6Müllsack";
    public String sellInvName = "§8» §aMüllverbrennung";

    public int invSize = 54;
    public String cancel = "§cAbbrechen";
    public String sell = "§aVerkaufen";
    public int money = 10;

    public HashMap<Player, Boolean> canClose = new HashMap<>();

    @Override
    public void onEnable() {
        registerCommands();
        registerMyEvents();
        System.out.println("Müllmann erfolgreich geladen");
    }

    @Override
    public void onDisable() {
    }

    private void registerCommands() {
        Commands Commands = new Commands(this);
        getCommand("muellmann").setExecutor(Commands);
        getCommand("müllmann").setExecutor(Commands);
        getCommand("mm").setExecutor(Commands);
    }

    private void registerMyEvents() {
        new PlaceEvent(this);
        new ClickEvent(this);
        new BreakEvent(this);
        new InvClickEvent(this);
        new InvCloseEvent(this);
    }

    /**
     * Erstellt einen ItemStack, welcher in ein Inventar gesetzt werden kann
     * @param mat
     * @param name
     * @param amount
     * @param s
     * @return
     */
    public ItemStack buildIS(Material mat, String name, int amount, int s){
        ItemStack is = new ItemStack(mat, amount, (byte)s);
        ItemMeta im = is.getItemMeta();
        im.setDisplayName(name);
        is.setItemMeta(im);
        return is;
    }

}
