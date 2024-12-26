package net.pulsir.regions.region.listener;

import net.pulsir.regions.Regions;
import net.pulsir.regions.region.claim.RegionClaim;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.Objects;

public class ClaimListener implements Listener {

    @EventHandler
    public void onClaim(PlayerInteractEvent event) {
        if (event.getAction().equals(Action.LEFT_CLICK_BLOCK)) {
            if (Regions.getInstance().getRegionClaimManager().getClaims().containsKey(event.getPlayer().getUniqueId())) {
                Regions.getInstance().getRegionClaimManager().getClaims().get(event.getPlayer().getUniqueId())
                        .setFirstCorner(Objects.requireNonNull(event.getClickedBlock()).getLocation());
            }
        } else if (event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            if (Regions.getInstance().getRegionClaimManager().getClaims().containsKey(event.getPlayer().getUniqueId())) {
                Regions.getInstance().getRegionClaimManager().getClaims().get(event.getPlayer().getUniqueId())
                        .setSecondCorner(Objects.requireNonNull(event.getClickedBlock()).getLocation());
            }
        } else if (event.getAction().equals(Action.LEFT_CLICK_AIR)) {
            RegionClaim regionClaim = Regions.getInstance().getRegionClaimManager().getClaims().get(event.getPlayer().getUniqueId());
            if (regionClaim != null) {
                if (regionClaim.isClaimable()) {
                    regionClaim.claim();
                }
            }
        }
    }
}
