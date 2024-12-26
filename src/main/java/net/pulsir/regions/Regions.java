package net.pulsir.regions;

import lombok.Getter;
import net.pulsir.regions.command.RegionCommand;
import net.pulsir.regions.region.claim.manager.RegionClaimManager;
import net.pulsir.regions.region.listener.ClaimListener;
import net.pulsir.regions.region.listener.RegionListener;
import net.pulsir.regions.region.manager.RegionManager;
import net.pulsir.regions.utils.inventory.manager.InventoryManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

@Getter
public final class Regions extends JavaPlugin {

    @Getter
    private static Regions instance;

    private RegionManager regionManager;
    private InventoryManager inventoryManager;
    private RegionClaimManager regionClaimManager;

    @Override
    public void onEnable() {
        instance = this;

        getConfig().options().copyDefaults();
        saveDefaultConfig();

        Objects.requireNonNull(getCommand("region")).setExecutor(new RegionCommand());

        Bukkit.getPluginManager().registerEvents(new RegionListener(), this);
        Bukkit.getPluginManager().registerEvents(new ClaimListener(), this);

        this.regionManager = new RegionManager();
        this.inventoryManager = new InventoryManager();
        this.regionClaimManager = new RegionClaimManager();
    }

    @Override
    public void onDisable() {
        instance = null;
    }
}
