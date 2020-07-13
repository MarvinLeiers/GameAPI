package de.marvinleiers.gameapi.events;

import de.marvinleiers.gameapi.game.Game;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;

public class GameEvent extends Event
{
    private static final HandlerList handlers = new HandlerList();

    private final JavaPlugin plugin;
    private final Game game;

    public GameEvent(JavaPlugin plugin, Game game)
    {
        this.plugin = plugin;
        this.game = game;
    }

    public JavaPlugin getPlugin()
    {
        return plugin;
    }

    public Game getGame()
    {
        return game;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
