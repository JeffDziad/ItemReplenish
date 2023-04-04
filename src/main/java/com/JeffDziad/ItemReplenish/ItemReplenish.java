package com.JeffDziad.ItemReplenish;

import com.JeffDziad.ItemReplenish.listener.EventListener;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public class ItemReplenish extends JavaPlugin {

    @Override
    public void onLoad() {
        getLogger().info("Loading ItemReplenish...");
    }

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new EventListener(), this);
        getLogger().info("Item Replenish enabled.");
    }

    @Override
    public void onDisable() {
        getLogger().info("ItemReplenish disabled.");
    }

}
