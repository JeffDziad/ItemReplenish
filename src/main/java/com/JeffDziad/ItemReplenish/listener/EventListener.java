package com.JeffDziad.ItemReplenish.listener;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerItemBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public class EventListener implements Listener {

    @EventHandler
    public void onToolBreak(PlayerItemBreakEvent event) {
        Player player = event.getPlayer();
        PlayerInventory inventory = player.getInventory();
        for (int i = 0; i < inventory.getSize(); i++) {
            ItemStack item = inventory.getItem(i);
            if (item != null && item.getType().equals(event.getBrokenItem().getType()) && i != inventory.getHeldItemSlot()) {
                inventory.setItem(inventory.getHeldItemSlot(), item);
                inventory.setItem(i, new ItemStack(Material.AIR));
                break;
            }
        }
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        Player player = event.getPlayer();
        Block block = event.getBlock();
        ItemStack handItem = player.getInventory().getItemInMainHand();
        PlayerInventory inventory = player.getInventory();
        if(!handItem.getType().equals(Material.AIR) && handItem.getAmount() == 1) {
            for (int i = 0; i < inventory.getSize(); i++) {
                ItemStack item = inventory.getItem(i);
                if(item != null && item.getType().equals(block.getType()) && i != inventory.getHeldItemSlot()) {
                    inventory.setItem(inventory.getHeldItemSlot(), item);
                    inventory.setItem(i, new ItemStack(Material.AIR));
                    break;
                }
            }

        }
    }

//  Add food replenish capabilities.

}
