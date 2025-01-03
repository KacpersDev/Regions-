package net.pulsir.regions.region.manager;

import lombok.Getter;
import net.pulsir.regions.region.Region;
import org.bukkit.Location;

import java.util.HashSet;
import java.util.Set;

@Getter
public class RegionManager {

    private final Set<Region> regions = new HashSet<>();

    public boolean hasName(String name) {
        for (final Region region : regions) {
            if (region.getName().equalsIgnoreCase(name)) {
                return true;
            }
        }

        return false;
    }

    public Region getRegion(String regionName) {
        for (final Region region : regions) {
            if (region.getName().equalsIgnoreCase(regionName)) {
                return region;
            }
        }

        return null;
    }

    public Region getRegionByLocation(Location location) {
        for (final Region region : regions) {
            if (region.getCuboid().contains(location)) {
                return region;
            }
        }

        return null;
    }
}
