package net.pulsir.regions.region.listener;

import net.pulsir.regions.Regions;
import net.pulsir.regions.utils.color.Color;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.inventory.ItemStack;

public class RegionListener implements Listener {

    @EventHandler
    public void onWandDrop(PlayerDropItemEvent event) {
        if (event.getItemDrop().getItemStack().getItemMeta().getDisplayName().equalsIgnoreCase(Color.translate("&aAmazing Wand"))) {
            event.getPlayer().getInventory().setItemInMainHand(new ItemStack(Material.AIR));
            event.setCancelled(true);
        }

        Regions.getInstance().getRegionClaimManager().getClaims().remove(event.getPlayer().getUniqueId());
        event.getPlayer().sendMessage(Color.translate("&aClaim selection has been removed."));
    }
}
