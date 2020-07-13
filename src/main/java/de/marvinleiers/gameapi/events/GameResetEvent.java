package de.marvinleiers.gameapi.events;

import de.marvinleiers.gameapi.game.Game;
import org.bukkit.plugin.java.JavaPlugin;

public class GameResetEvent extends GameEvent
{
    public GameResetEvent(JavaPlugin plugin, Game game)
    {
        super(plugin, game);
    }
}
