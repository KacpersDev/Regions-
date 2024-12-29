package net.pulsir.regions.editor;

import lombok.Getter;
import net.pulsir.regions.region.Region;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Getter
public class Editor {

    private final Map<UUID, Region> regions = new HashMap<>();
}
