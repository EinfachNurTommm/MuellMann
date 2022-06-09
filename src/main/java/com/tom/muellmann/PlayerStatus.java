package com.tom.muellmann;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlayerStatus {


    private Player player;

    private List<Location> usedMuelleimer = new ArrayList<Location>();

    private static Map<String, PlayerStatus> newplayer = new HashMap<String, PlayerStatus>();

    private PlayerStatus(Player player) {
        this.player = player;
        newplayer.put(player.getName(), this);
    }

    // Return a running instance (or create a new one)
    public static PlayerStatus getInstanceOfPlayer(Player player) {
        if(!newplayer.containsKey(player.getName())) {
            return new PlayerStatus(player);
        }
        else if(newplayer.containsKey(player.getName())) {
            return newplayer.get(player.getName());
        } else {

        }
        return null;
    }


    private boolean setUsedMuelleimer(List<Location> locList) {
        usedMuelleimer = locList;
        return true;
    }

    public boolean addUsedMuelleimer(Location loc) {
        List<Location> locList = usedMuelleimer;
        locList.add(loc);
        setUsedMuelleimer(locList);
        return true;
    }

    public List<Location> getUsedMuelleimer() {
        return usedMuelleimer;
    }


}
