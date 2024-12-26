package net.pulsir.regions.region.claim.manager;

import lombok.Getter;
import net.pulsir.regions.region.claim.RegionClaim;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Getter
public class RegionClaimManager {

    private final Map<UUID, RegionClaim> claims = new HashMap<>();
}
