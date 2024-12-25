package net.pulsir.regions.utils.color;

import org.bukkit.ChatColor;

@Deprecated
public class Color {

    public static String translate(String s) {
        return ChatColor.translateAlternateColorCodes('&', s);
    }
}
