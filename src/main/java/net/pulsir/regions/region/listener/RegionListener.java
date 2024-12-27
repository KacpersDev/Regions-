package net.pulsir.regions.region.listener;

import net.pulsir.regions.Regions;
import net.pulsir.regions.region.Region;
import net.pulsir.regions.region.flag.RegionFlags;
import net.pulsir.regions.region.state.FlagState;
import net.pulsir.regions.utils.color.Color;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
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

    @EventHandler
    public void onBreakBlock(BlockBreakEvent event) {
        Region region = Regions.getInstance().getRegionManager().getRegionByLocation(event.getPlayer().getLocation());
        if (region == null || region.getRegionFlags().isEmpty()) return;

        if (region.getRegionFlags().containsKey(RegionFlags.BLOCK_BREAK)) {
            FlagState flagState = region.getRegionFlags().get(RegionFlags.BLOCK_BREAK);

            if (flagState.equals(FlagState.NONE) && !event.getPlayer().hasPermission("region.bypass")) {
                event.setCancelled(true);
            } else if (flagState.equals(FlagState.WHITELIST) && !event.getPlayer().hasPermission("region.bypass")
                && !region.getWhitelistedPlayers().contains(event.getPlayer().getUniqueId())) {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onPlaceBlock(BlockPlaceEvent event) {
        Region region = Regions.getInstance().getRegionManager().getRegionByLocation(event.getPlayer().getLocation());
        if (region == null || region.getRegionFlags().isEmpty()) return;

        if (region.getRegionFlags().containsKey(RegionFlags.BLOCK_PLACE)) {
            FlagState flagState = region.getRegionFlags().get(RegionFlags.BLOCK_PLACE);

            if (flagState.equals(FlagState.NONE) && !event.getPlayer().hasPermission("region.bypass")) {
                event.setCancelled(true);
            } else if (flagState.equals(FlagState.WHITELIST) && !event.getPlayer().hasPermission("region.bypass")
                    && !region.getWhitelistedPlayers().contains(event.getPlayer().getUniqueId())) {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onBreakBlock(EntityDamageEvent event) {
        Region region = Regions.getInstance().getRegionManager().getRegionByLocation(event.getEntity().getLocation());
        if (region == null || region.getRegionFlags().isEmpty()) return;

        if (region.getRegionFlags().containsKey(RegionFlags.BLOCK_PLACE)) {
            FlagState flagState = region.getRegionFlags().get(RegionFlags.BLOCK_PLACE);

            if (flagState.equals(FlagState.NONE) && !event.getEntity().hasPermission("region.bypass")) {
                event.setCancelled(true);
            } else if (flagState.equals(FlagState.WHITELIST) && !event.getEntity().hasPermission("region.bypass")
                    && !region.getWhitelistedPlayers().contains(event.getEntity().getUniqueId())) {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        Region region = Regions.getInstance().getRegionManager().getRegionByLocation(event.getPlayer().getLocation());
        if (region == null || region.getRegionFlags().isEmpty()) return;

        if (region.getRegionFlags().containsKey(RegionFlags.BLOCK_PLACE)) {
            FlagState flagState = region.getRegionFlags().get(RegionFlags.BLOCK_PLACE);

            if (flagState.equals(FlagState.NONE) && !event.getPlayer().hasPermission("region.bypass")) {
                event.setCancelled(true);
            } else if (flagState.equals(FlagState.WHITELIST) && !event.getPlayer().hasPermission("region.bypass")
                    && !region.getWhitelistedPlayers().contains(event.getPlayer().getUniqueId())) {
                event.setCancelled(true);
            }
        }
    }
}
