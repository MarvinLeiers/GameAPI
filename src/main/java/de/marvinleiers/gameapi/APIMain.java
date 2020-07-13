package de.marvinleiers.gameapi;

import org.bukkit.plugin.java.JavaPlugin;

public class APIMain extends JavaPlugin
{
    private static APIMain plugin;

    public static APIMain getPlugin()
    {
        return plugin;
    }

    @Override
    public void onEnable()
    {
        plugin = this;
    }
}
