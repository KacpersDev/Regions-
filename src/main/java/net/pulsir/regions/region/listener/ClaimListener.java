package net.pulsir.regions.region.listener;

import net.pulsir.regions.Regions;
import net.pulsir.regions.utils.color.Color;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.Objects;

public class ClaimListener implements Listener {

    @EventHandler
    public void onClaim(PlayerInteractEvent event) {
        if (!event.getPlayer().getInventory().getItemInMainHand().getItemMeta().getDisplayName()
                .equalsIgnoreCase(Color.translate("&aAmazing Wand"))) return;
        if (event.getAction().equals(Action.LEFT_CLICK_BLOCK)) {
            if (Regions.getInstance().getRegionClaimManager().getClaims().containsKey(event.getPlayer().getUniqueId())) {
                Regions.getInstance().getRegionClaimManager().getClaims().get(event.getPlayer().getUniqueId())
                        .setFirstCorner(Objects.requireNonNull(event.getClickedBlock()).getLocation());

                event.getPlayer().sendMessage(Color.translate("&aSuccessfully selected first corner at " + event.getClickedBlock().getLocation()));
            }
        } else if (event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            if (Regions.getInstance().getRegionClaimManager().getClaims().containsKey(event.getPlayer().getUniqueId())) {
                Regions.getInstance().getRegionClaimManager().getClaims().get(event.getPlayer().getUniqueId())
                        .setSecondCorner(Objects.requireNonNull(event.getClickedBlock()).getLocation());

                event.getPlayer().sendMessage(Color.translate("&aSuccessfully selected second corner at " + event.getClickedBlock().getLocation()));
            }
        }
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        if (event.getPlayer().getInventory().getItemInMainHand().getItemMeta().getDisplayName().equalsIgnoreCase(Color.translate("&aAmazing Wand"))) {
            event.setCancelled(true);
        }
    }
}
