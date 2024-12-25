package net.pulsir.regions.utils.inventory.impl;

import net.pulsir.regions.Regions;
import net.pulsir.regions.utils.color.Color;
import net.pulsir.regions.utils.inventory.IInventory;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class RegionsInventory implements IInventory {

    @Override
    public String name() {
        return Color.translate(Regions.getInstance().getConfig().getString("regions.inventory.title"));
    }

    @Override
    public int size() {
        return Regions.getInstance().getConfig().getInt("regions.inventory.size");
    }

    @Override
    public ItemStack[] items() {
        return new ItemStack[size()];
    }

    @Override
    public Inventory create() {
        return IInventory.super.create();
    }
}
