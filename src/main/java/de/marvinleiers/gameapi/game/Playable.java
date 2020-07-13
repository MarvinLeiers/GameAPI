package de.marvinleiers.gameapi.game;

import de.marvinleiers.gameapi.APIMain;
import de.marvinleiers.gameapi.GameAPI;
import de.marvinleiers.gameapi.events.GameResetEvent;
import de.marvinleiers.gameapi.events.GameStartEvent;
import de.marvinleiers.gameapi.events.PlayerGameJoinEvent;
import de.marvinleiers.gameapi.events.PlayerGameLeaveEvent;
import de.marvinleiers.gameapi.exceptions.EntryPointNotSetException;
import de.marvinleiers.gameapi.utils.CountdownTimer;
import de.marvinleiers.gameapi.utils.CustomConfig;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class Playable implements Game
{
    private static final int MIN_PLAYERS = GameAPI.config.getInt("min-players");
    private static final int MAX_PLAYERS = GameAPI.config.getInt("max-players");

    private static CustomConfig gameConfig;

    private JavaPlugin plugin;
    private String name;
    private boolean hasStarted;
    private Location entryPoint;

    private CountdownTimer timer;

    public Playable(JavaPlugin plugin, String name)
    {
        this.plugin = plugin;
        this.name = name;
        this.hasStarted = false;

        gameConfig = new CustomConfig(plugin.getDataFolder().getPath() + "/games/games.yml");
    }

    @Override
    public void start()
    {
        hasStarted = true;

        timer = new CountdownTimer(plugin, 30,
                () -> {},
                this::startGame,
                (t) -> sendMessage("Game starts in " + t.getTotalSeconds()));

        timer.scheduleTimer();
    }

    private void startGame()
    {
        hasStarted = true;

        for (Player player : players)
        {
            player.getInventory().clear();
            player.getInventory().setArmorContents(null);
            player.updateInventory();
        }

        Bukkit.getPluginManager().callEvent(new GameStartEvent(plugin, this));
    }

    @Override
    public void stop()
    {
        Bukkit.getPluginManager().callEvent(new GameResetEvent(plugin, this));

        for (Player player : players)
        {
            GameAPI.gameplayers.get(player).leave();
            GameAPI.gameplayers.remove(player);
            Bukkit.getPluginManager().callEvent(new PlayerGameLeaveEvent(this, player));
        }

        players.clear();
    }

    @Override
    public void join(Player player)
    {
        players.add(player);
        GameAPI.gameplayers.put(player, new GamePlayer(this, player));

        player.teleport(getEntryPoint());
        Bukkit.getPluginManager().callEvent(new PlayerGameJoinEvent(this, player));

        if (players.size() >= MIN_PLAYERS && !hasStarted())
            start();
    }

    @Override
    public void leave(Player player)
    {
        players.remove(player);
        GameAPI.gameplayers.get(player).leave();
        GameAPI.gameplayers.remove(player);

        Bukkit.getPluginManager().callEvent(new PlayerGameLeaveEvent(this, player));
    }

    @Override
    public void sendMessage(String message)
    {
        for (Player player : players)
            player.sendMessage(message);
    }

    @Override
    public void setEntryPoint(Location location)
    {
        entryPoint = location;
        gameConfig.setLocation("games." + getName() + ".lobby", entryPoint);
    }

    @Override
    public Location getEntryPoint()
    {
        if (entryPoint == null)
            throw new EntryPointNotSetException("Entry Point for Game " + getName() + " has not been set!");

        return entryPoint;
    }

    @Override
    public String getName()
    {
        return name;
    }

    @Override
    public Plugin getPlugin()
    {
        return plugin;
    }

    @Override
    public boolean hasStarted()
    {
        return hasStarted;
    }
}
