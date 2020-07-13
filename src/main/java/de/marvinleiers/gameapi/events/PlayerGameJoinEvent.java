package de.marvinleiers.gameapi.events;

import de.marvinleiers.gameapi.game.Game;
import org.bukkit.entity.Player;

public class PlayerGameJoinEvent extends PlayerGameEvent
{
    public PlayerGameJoinEvent(Game game, Player who)
    {
        super(game, who);
    }
}
