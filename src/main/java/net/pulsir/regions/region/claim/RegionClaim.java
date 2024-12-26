package net.pulsir.regions.region.claim;

import lombok.Getter;
import lombok.Setter;
import net.pulsir.regions.Regions;
import org.bukkit.Location;

@Getter
@Setter
public class RegionClaim {

    private String regionName;
    private Location firstCorner, secondCorner;
    private int maxY;

    public RegionClaim(String regionName, Location firstCorner, Location secondCorner) {
        this.regionName = regionName;
        this.firstCorner = firstCorner;
        this.secondCorner = secondCorner;
        this.maxY = 256;
    }

    public boolean isClaimable() {
        return false;
    }

    public void claim() {

    }

    public Location[] toLocations() {
        Location[] locations = new Location[2];
        locations[0] = firstCorner;
        locations[1] = secondCorner;
        return locations;
    }
}
