package de.marvinleiers.gameapi;

import de.marvinleiers.gameapi.game.Game;
import de.marvinleiers.gameapi.game.GamePlayer;
import de.marvinleiers.gameapi.game.Playable;
import de.marvinleiers.gameapi.utils.CustomConfig;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public final class GameAPI
{
    public static Set<Game> games = new HashSet<>();
    public static HashMap<Plugin, GameAPI> apis = new HashMap<>();
    public static HashMap<Player, GamePlayer> gameplayers = new HashMap<>();
    public static CustomConfig config;

    private JavaPlugin plugin;

    private GameAPI(JavaPlugin plugin)
    {
        this.plugin = plugin;

        config = new CustomConfig(plugin.getDataFolder().getPath() + "/config.yml");
        config.addDefault("min-players", 2);
        config.addDefault("max-players", 8);
    }

    public Game getGame(String name)
    {
        if (exists(name))
            return getGameFromName(name);

        Game game = new Playable(plugin, name);
        games.add(game);

        return game;
    }

    public Game getGameFromName(String name)
    {
        for (Game game : games)
        {
            if (game.getName().equals(name))
                return game;
        }

        return null;
    }

    public boolean exists(String name)
    {
        for (Game game : games)
        {
            if (game.getName().equals(name))
                return true;
        }

        return false;
    }

    public static GameAPI getInstance(JavaPlugin plugin)
    {
        if (apis.containsKey(plugin))
            return apis.get(plugin);

        GameAPI api = new GameAPI(plugin);
        apis.put(plugin, api);

        return api;
    }
}
