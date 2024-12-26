package net.pulsir.regions.utils.items;

import net.pulsir.regions.utils.color.Color;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Items {

    public static ItemStack wand() {
        ItemStack itemStack = new ItemStack(Material.WOODEN_AXE);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(Color.translate("&aAmazing Wand"));
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
}
