package net.pulsir.regions.command;

import net.pulsir.regions.Regions;
import net.pulsir.regions.region.Region;
import net.pulsir.regions.region.claim.RegionClaim;
import net.pulsir.regions.region.flag.RegionFlags;
import net.pulsir.regions.region.state.FlagState;
import net.pulsir.regions.utils.color.Color;
import net.pulsir.regions.utils.inventory.InventoryType;
import net.pulsir.regions.utils.items.Items;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

public class RegionCommand implements CommandExecutor, TabCompleter {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String str, String[] args) {

        if (args.length == 0) {
            if (!(sender instanceof Player player)) return false;
            if (!player.hasPermission("region.menu")) {
                sender.sendMessage(Objects.requireNonNull(Regions.getInstance().getConfig().getString("no-permissions")));
                return false;
            }

            player.openInventory(Regions.getInstance().getInventoryManager().getInventories().get(InventoryType.REGIONS).create());
        } else if (args[0].equalsIgnoreCase("create")) {
            if (!(sender instanceof Player player)) return false;
            if (!sender.hasPermission("region.create")) {
                sender.sendMessage(Objects.requireNonNull(Regions.getInstance().getConfig().getString("no-permissions")));
                return false;
            }

            if (args.length == 1) {
                usage(sender);
            } else {
                String regionName = args[1];

                if (Regions.getInstance().getRegionManager().hasName(regionName)) {
                    sender.sendMessage(Objects.requireNonNull(Objects.requireNonNull(Regions.getInstance().getConfig().getString("region.exists"))
                            .replace("{region}", regionName)));
                    return false;
                }

                if (!Regions.getInstance().getRegionClaimManager().getClaims().containsKey(player.getUniqueId())) {
                    player.sendMessage(Color.translate("&cYou do not have a region claim! /region wand"));
                    return false;
                }

                if (!Regions.getInstance().getRegionClaimManager().getClaims().get(player.getUniqueId()).isClaimable()) {
                    player.sendMessage(Color.translate("&cYou did not selected all corners! /region wand"));
                    return false;
                }

                Regions.getInstance().getRegionClaimManager().getClaims().remove(player.getUniqueId());
                Regions.getInstance().getRegionManager().getRegions().add(new Region(regionName,
                        Regions.getInstance().getRegionClaimManager().getClaims().get(player.getUniqueId()).toLocations(), new HashSet<>(), new HashMap<>()));
                player.sendMessage(Color.translate("&aSuccessfully created region."));
            }
        } else if (args[0].equalsIgnoreCase("wand")) {
            if (!(sender instanceof Player player)) return false;
            if (!sender.hasPermission("region.create")) {
                sender.sendMessage(Objects.requireNonNull(Regions.getInstance().getConfig().getString("no-permissions")));
                return false;
            }

            player.getInventory().addItem(Items.wand());
            Regions.getInstance().getRegionClaimManager().getClaims().put(player.getUniqueId(),
                    new RegionClaim(null, null, null));
            player.sendMessage(Color.translate("&f&m--------------------------------"));
            player.sendMessage(Color.translate("&aLeft click BLOCK to select first location."));
            player.sendMessage(Color.translate("&aRight click BLOCK to select second location."));
            player.sendMessage(Color.translate("&aRight click AIR to claim."));
            player.sendMessage(Color.translate("&cDrop wand in order to cancel claiming."));
            player.sendMessage(Color.translate("&f&m--------------------------------"));
        } else if (args[0].equalsIgnoreCase("add")) {
            if (args.length == 1) {
                usage(sender);
            } else {
                String regionName = args[1];

                if (!Regions.getInstance().getRegionManager().hasName(regionName)) {
                    sender.sendMessage(Color.translate("&aRegion does not exists!"));
                    return false;
                }

                if (args.length == 2) {
                    usage(sender);
                } else {
                    OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(args[2]);

                    Regions.getInstance().getRegionManager().getRegion(regionName).getWhitelistedPlayers().add(offlinePlayer.getUniqueId());
                    sender.sendMessage(Color.translate("&aSuccessfully added player to whitelist of region."));
                }
            }
        } else if (args[0].equalsIgnoreCase("remove")) {
            if (args.length == 1) {
                usage(sender);
            } else {
                String regionName = args[1];

                if (!Regions.getInstance().getRegionManager().hasName(regionName)) {
                    sender.sendMessage(Color.translate("&aRegion does not exists!"));
                    return false;
                }

                if (args.length == 2) {
                    usage(sender);
                } else {
                    OfflinePlayer player = Bukkit.getOfflinePlayer(args[2]);

                    Regions.getInstance().getRegionManager().getRegion(regionName).getWhitelistedPlayers().remove(player.getUniqueId());
                    sender.sendMessage(Color.translate("&aSuccessfully added player to whitelist of region."));
                }
            }
        } else if (args[0].equalsIgnoreCase("whitelist")) {
            if (args.length == 1) {
                usage(sender);
            } else {
                String regionName = args[1];

                if (!Regions.getInstance().getRegionManager().hasName(regionName)) {
                    sender.sendMessage(Color.translate("&aRegion does not exists!"));
                    return false;
                }

                for (final UUID uuid : Regions.getInstance().getRegionManager().getRegion(regionName).getWhitelistedPlayers()) {
                    OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(uuid);

                    sender.sendMessage(Color.translate("&aPlayer: &f" + offlinePlayer.getName()));
                }
            }
        } else if (args[0].equalsIgnoreCase("flag")) {
            if (args.length == 1) {
                usage(sender);
            } else {
                String regionName = args[1];

                if (args.length == 2) {
                    usage(sender);
                } else {
                    RegionFlags regionFlags = RegionFlags.valueOf(args[2]);

                    if (args.length == 3) {
                        usage(sender);
                    } else {
                        FlagState flagState = FlagState.valueOf(args[3]);

                        Regions.getInstance().getRegionManager().getRegion(regionName)
                                .getRegionFlags().replace(regionFlags, flagState);
                        sender.sendMessage(Color.translate("&aSuccessfully added flag."));
                    }
                }
            }
        }

        return true;
    }

    private void usage(CommandSender sender) {
        for (final String s : Regions.getInstance().getConfig().getStringList("command.usage")) {
            sender.sendMessage(Color.translate(s));
        }
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if (args.length == 1) {
            return new ArrayList<>(List.of("create", "wand", "remove", "whitelist", "flag"));
        }

        return new ArrayList<>();
    }
}
