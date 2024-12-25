package net.pulsir.regions.region;

import lombok.Getter;
import lombok.Setter;
import net.pulsir.regions.region.flag.RegionFlags;
import net.pulsir.regions.region.state.FlagState;
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
}
