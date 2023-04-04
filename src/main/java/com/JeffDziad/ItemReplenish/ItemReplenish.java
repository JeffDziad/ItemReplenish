package com.JeffDziad.ItemReplenish;

import org.bukkit.plugin.java.JavaPlugin;

public class ItemReplenish extends JavaPlugin {

    @Override
    public void onLoad() {
        getLogger().info("Loading ItemReplenish...");
    }

    @Override
    public void onEnable() {
        getLogger().info("ItemReplenish enabled.");
    }

    @Override
    public void onDisable() {
        getLogger().info("ItemReplenish disabled.");
    }

}
