package net.pulsir.regions.command;

import net.pulsir.regions.Regions;
import net.pulsir.regions.region.Region;
import net.pulsir.regions.utils.color.Color;
import net.pulsir.regions.utils.inventory.InventoryType;
import org.bukkit.Location;
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
            if (args.length == 1) {
                usage(sender);
            } else {
                String regionName = args[1];

                if (Regions.getInstance().getRegionManager().hasName(regionName)) {
                    sender.sendMessage(Objects.requireNonNull(Objects.requireNonNull(Regions.getInstance().getConfig().getString("region.exists"))
                            .replace("{exists}", regionName)));
                    return false;
                }

                Regions.getInstance().getRegionManager().getRegions().add(new Region(regionName,
                        new Location[2], new HashSet<>(), new HashMap<>()));
            }
        } else if (args[0].equalsIgnoreCase("wand")) {

        } else if (args[0].equalsIgnoreCase("add")) {

        } else if (args[0].equalsIgnoreCase("remove")) {

        } else if (args[0].equalsIgnoreCase("whitelist")) {

        } else if (args[0].equalsIgnoreCase("flag")) {

        } else {

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
