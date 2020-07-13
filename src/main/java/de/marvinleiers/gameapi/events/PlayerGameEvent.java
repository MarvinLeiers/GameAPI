package de.marvinleiers.gameapi.events;

import de.marvinleiers.gameapi.game.Game;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;

public class PlayerGameEvent extends PlayerEvent
{
    private static final HandlerList handlers = new HandlerList();

    private Game game;

    public PlayerGameEvent(Game game, Player who)
    {
        super(who);

        this.game = game;
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
