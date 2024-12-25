package net.pulsir.regions.utils.inventory.manager;

import lombok.Getter;
import net.pulsir.regions.utils.inventory.IInventory;
import net.pulsir.regions.utils.inventory.InventoryType;
import net.pulsir.regions.utils.inventory.impl.RegionInventory;
import net.pulsir.regions.utils.inventory.impl.RegionsInventory;

import java.util.HashMap;
import java.util.Map;

@Getter
public class InventoryManager {

    private final Map<InventoryType, IInventory> inventories = new HashMap<>();

    public InventoryManager() {
        inventories.put(InventoryType.REGIONS, new RegionsInventory());
        inventories.put(InventoryType.REGION, new RegionInventory());
    }
}
