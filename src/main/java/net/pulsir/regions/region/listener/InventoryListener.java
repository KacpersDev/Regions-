package net.pulsir.regions.region.listener;

import net.pulsir.regions.Regions;
import net.pulsir.regions.utils.color.Color;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
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
            Regions.getInstance().getEditor().getRegions().put(player.getUniqueId(), Regions.getInstance().getRegionManager().getRegion(regionName));

            Inventory inventory = Bukkit.getServer().createInventory(null, 9, Color.translate("&aRegion Settings"));

            ItemStack rename = new ItemStack(Material.PAPER);
            ItemMeta renameMeta = rename.getItemMeta();
            renameMeta.setDisplayName(Color.translate("&aRename Region"));
            rename.setItemMeta(renameMeta);

            ItemStack whitelistAdd = new ItemStack(Material.PAPER);
            ItemMeta whitelistAddMeta = whitelistAdd.getItemMeta();
            whitelistAddMeta.setDisplayName(Color.translate("&aWhitelist Add Player"));
            whitelistAdd.setItemMeta(whitelistAddMeta);

            ItemStack whitelistRemove = new ItemStack(Material.PAPER);
            ItemMeta whitelistRemoveMeta = whitelistRemove.getItemMeta();
            whitelistRemoveMeta.setDisplayName(Color.translate("&aWhitelist Remove Player"));
            whitelistRemove.setItemMeta(whitelistRemoveMeta);

            ItemStack location = new ItemStack(Material.PAPER);
            ItemMeta locationMeta = location.getItemMeta();
            locationMeta.setDisplayName(Color.translate("&aRedefine Location"));
            location.setItemMeta(locationMeta);

            ItemStack flags = new ItemStack(Material.PAPER);
            ItemMeta flagsMeta = flags.getItemMeta();
            flagsMeta.setDisplayName(Color.translate("&aFlags Editor"));
            flags.setItemMeta(flagsMeta);

            inventory.addItem(rename);
            inventory.addItem(whitelistAdd);
            inventory.addItem(whitelistRemove);
            inventory.addItem(location);
            inventory.addItem(flags);

            player.openInventory(inventory);
        }
    }
}
