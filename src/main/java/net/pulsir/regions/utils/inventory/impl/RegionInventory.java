package net.pulsir.regions.utils.inventory.impl;

import net.pulsir.regions.utils.inventory.IInventory;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class RegionInventory implements IInventory {
    @Override
    public String name() {
        return "";
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public ItemStack[] items() {
        return new ItemStack[0];
    }

    @Override
    public Inventory create() {
        return IInventory.super.create();
    }
}
