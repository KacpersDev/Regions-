package net.pulsir.regions.region;

import lombok.Getter;
import lombok.Setter;
import net.pulsir.regions.region.flag.RegionFlags;
import net.pulsir.regions.region.state.FlagState;
import net.pulsir.regions.utils.cuboid.Cuboid;
import org.bukkit.Location;

import java.util.Map;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
public class Region {

    private String name;
    private Location[] cuboidLocations;
    private Set<UUID> whitelistedPlayers;
    private Map<RegionFlags, FlagState> regionFlags;

    public Region(String name, Location[] cuboidLocations, Set<UUID> whitelistedPlayers, Map<RegionFlags, FlagState> regionFlags) {
        this.name = name;
        this.cuboidLocations = cuboidLocations;
        this.whitelistedPlayers = whitelistedPlayers;
        this.regionFlags = regionFlags;
    }

    public Cuboid getCuboid() {
        return new Cuboid(cuboidLocations[0], cuboidLocations[1]);
    }

    public String databaseWhitelistedPlayers() {
        String string = "";

        for (UUID uuid : whitelistedPlayers) {
            if (string.isEmpty()) {
                string = uuid.toString();
            } else {
                string = string + ":" + uuid.toString();
            }
        }

        return string;
    }

    public String databaseRegionFlags() {
        String string = "";

        for (RegionFlags flag : regionFlags.keySet()) {
            if (string.isEmpty()) {
                string = flag + ":" + regionFlags.get(flag);
            } else {
                string = string + "," + flag + ":" + regionFlags.get(flag);
            }
        }

        return string;
    }

    public String databaseLocation(Location location) {
        return null;
    }
}
