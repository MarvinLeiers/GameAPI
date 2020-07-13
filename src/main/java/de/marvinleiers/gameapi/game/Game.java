package de.marvinleiers.gameapi.game;

import de.marvinleiers.gameapi.APIMain;
import de.marvinleiers.gameapi.utils.CustomConfig;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;

public interface Game
{
    ArrayList<Player> players = new ArrayList<>();

    void start();

    void stop();

    void join(Player player);

    void leave(Player player);

    void sendMessage(String message);

    void setEntryPoint(Location location);

    Location getEntryPoint();

    String getName();

    Plugin getPlugin();

    boolean hasStarted();
}
