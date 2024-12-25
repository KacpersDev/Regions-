package net.pulsir.regions.utils.inventory;

import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public interface IInventory {

    String name();
    int size();
    ItemStack[] items();

    default Inventory create() {
        Inventory inventory = Bukkit.getServer().createInventory(null, size(), name());
        inventory.setContents(items());
        return inventory;
    }
}
