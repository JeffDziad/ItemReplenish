package com.JeffDziad.ItemReplenish.listener;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerItemBreakEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public class EventListener implements Listener {

    @EventHandler
    public void onToolBreak(PlayerItemBreakEvent event) {
        Player player = event.getPlayer();
        PlayerInventory inventory = player.getInventory();
        for (int i = 0; i < inventory.getSize(); i++) {
            ItemStack item = inventory.getItem(i);
            if (item != null && item.getType().equals(event.getBrokenItem().getType()) &&
                    i != inventory.getHeldItemSlot()) {
                inventory.setItem(inventory.getHeldItemSlot(), item);
                inventory.setItem(i, new ItemStack(Material.AIR));
                break;
            }
        }
    }

    //! Might be incorrectly triggered.
    //! With a tool in the main hand and a consumable/placeable in the offhand, tools can be overwritten because:
    //! - They are always in stacks of 1, thus onBlockPlace thinks its the end of a stack.
    //? What does onBlock replace the tool with? (Offhand item or other?)
    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        Player player = event.getPlayer();
        Block block = event.getBlock();
        ItemStack handItem = player.getInventory().getItemInMainHand();
        PlayerInventory inventory = player.getInventory();
        if(handItem.getType().isBlock() && handItem.getAmount() == 1) {
            for (int i = 0; i < inventory.getSize(); i++) {
                ItemStack item = inventory.getItem(i);
                if(item != null && item.getType().equals(block.getType()) &&
                        i != inventory.getHeldItemSlot()) {
                    inventory.setItem(inventory.getHeldItemSlot(), item);
                    inventory.setItem(i, new ItemStack(Material.AIR));
                    break;
                }
            }

        }
    }

    @EventHandler
    public void onPlayerEat(PlayerItemConsumeEvent event) {
        Player player = event.getPlayer();
        ItemStack food = event.getItem();
        PlayerInventory inventory = player.getInventory();
        ItemStack handItem = inventory.getItemInMainHand();
        if(!handItem.getType().equals(Material.AIR) && handItem.getAmount() == 1) {
            for (int i = 0; i < inventory.getSize(); i++) {
                ItemStack item = inventory.getItem(i);
                if (item != null && item.getType().equals(food.getType()) &&
                        i != inventory.getHeldItemSlot()) {
                    inventory.setItem(inventory.getHeldItemSlot(), item);
                    inventory.setItem(i, new ItemStack((Material.AIR)));
                    break;
                }
            }
        }
    }

}
