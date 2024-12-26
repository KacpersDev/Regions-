package net.pulsir.regions.utils.inventory.impl;

import net.pulsir.regions.Regions;
import net.pulsir.regions.region.Region;
import net.pulsir.regions.utils.color.Color;
import net.pulsir.regions.utils.inventory.IInventory;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

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
        ItemStack[] itemStacks = new ItemStack[size()];
        int i = 0;
        if (Regions.getInstance().getRegionManager().getRegions().isEmpty()) return itemStacks;
        for (Region region : Regions.getInstance().getRegionManager().getRegions()) {
            ItemStack itemStack = new ItemStack(Material.PAPER);
            ItemMeta itemMeta = itemStack.getItemMeta();
            itemMeta.setDisplayName(Color.translate(region.getName() + " &a's Region"));
            itemStack.setItemMeta(itemMeta);
            itemStacks[i] = itemStack;
            i++;
        }
        return itemStacks;
    }

    @Override
    public Inventory create() {
        return IInventory.super.create();
    }
}
