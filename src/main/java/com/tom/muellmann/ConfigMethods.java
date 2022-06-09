package com.tom.muellmann;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class ConfigMethods {

    private Main plugin;

    public ConfigMethods(Main plugin) {
        this.plugin=plugin;
    }


    // Mülleimer hinzufügen
    public void setMuelleimer(Location loc) { // - world,-253,71,-128
        List<String> locList = plugin.cfg.getStringList("Muelleimer" + ".Locations");

        //prüfen ob schon ein Mülleimer mit dieser Location in der Config ist
        if(!getMuelleimer().contains(loc)) {

            //Die Location in einen String umwalndeln, um es nachher besser auslesen zu können
            String s = loc.getWorld().getName() + "," + loc.getX() + "," + loc.getY() + "," + loc.getZ();

            // String zu einer StringList hinzufügen
            locList.add(s);

            // StringList zu Config hinzufügen
            plugin.cfg.set("Muelleimer" + ".Locations", locList);
            plugin.saveConfig();
        }
    }

    //Mülleimer abrufen
    public List<Location> getMuelleimer() {
        List<Location> locList = new ArrayList<Location>();
        List<String> locStringList = plugin.cfg.getStringList("Muelleimer" + ".Locations");

        //Durch die Liste der Mülleimer aus der Config durchgehen

        for(int i = 0; i <= locStringList.size()-1; i++) {
            //Den eintrag von i nehemn und bei jedem Komma splitten
            //Da immer gleichviele Kommas in einer Zeile sind kann man world, x, y, z bestimmen
            String s = locStringList.get(i);
            String[] split = s.split(",");
            String world = split[0];
            double x = Double.parseDouble(split[1]);
            double y = Double.parseDouble(split[2]);
            double z = Double.parseDouble(split[3]);

            // x, y, z zu einer Location machen und in die locList adden
            Location loc = new Location(Bukkit.getWorld(world), x, y, z);
            loc.setYaw(0);
            loc.setPitch(0);
            locList.add(loc);
        }

        // Alle Locations aus der Config mithilfe einer List<Location> returnen
        return locList;
    }

    //Mülleimer entfernen
    public void removeMuelleimer(Location loc) {
        List<Location> locList = new ArrayList<Location>();
        List<String> newLocList = new ArrayList<String>();

        //Mülleimer in eine List reintun, die nicht der Location loc entsprechen
        for(int i = 0; i < getMuelleimer().size(); i++) {
            if(!getMuelleimer().get(i).equals(loc)) {
                locList.add(getMuelleimer().get(i));
            }
        }

        //Die Liste mit den übrigen Mülleimern in eine StringList ändern und in die Config setzten
        for(int i = 0; i < locList.size(); i++) {
            Location newLoc = locList.get(i);
            String s = newLoc.getWorld().getName() + "," + newLoc.getX() + "," + newLoc.getY() + "," + newLoc.getZ();
            newLocList.add(s);
        }

        plugin.cfg.set("Muelleimer" + ".Locations", newLocList);
        plugin.saveConfig();

    }


}
