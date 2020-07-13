package de.marvinleiers.gameapi.events;

import de.marvinleiers.gameapi.game.Game;
import org.bukkit.entity.Player;

public class PlayerGameLeaveEvent extends PlayerGameEvent
{
    public PlayerGameLeaveEvent(Game game, Player who)
    {
        super(game, who);
    }
}
