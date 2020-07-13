package de.marvinleiers.gameapi.events;

import de.marvinleiers.gameapi.game.Game;
import org.bukkit.plugin.java.JavaPlugin;

public class GameStartEvent extends GameEvent
{
    public GameStartEvent(JavaPlugin plugin, Game game)
    {
        super(plugin, game);
    }
}
