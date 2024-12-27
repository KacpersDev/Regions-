package net.pulsir.regions.region.listener;

import net.pulsir.regions.Regions;
import net.pulsir.regions.utils.color.Color;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.persistence.PersistentDataType;

public class InventoryListener implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getView().getTitle().equalsIgnoreCase(Color.translate(Regions.getInstance()
                .getConfig().getString("regions.inventory.title")))
        || event.getView().getTitle().equalsIgnoreCase(Color.translate("&aRegion Settings"))) event.setCancelled(true);
        Player player = (Player) event.getWhoClicked();

        if (event.getCurrentItem() != null && event.getCurrentItem().getPersistentDataContainer().has(Regions.getInstance().getRegionKey())) {
            String regionName = event.getCurrentItem().getPersistentDataContainer().get(Regions.getInstance().getRegionKey(), PersistentDataType.STRING);

            Inventory inventory = Bukkit.getServer().createInventory(null, 54, "&aRegion Settings");

            player.openInventory(inventory);
        }
    }
}
