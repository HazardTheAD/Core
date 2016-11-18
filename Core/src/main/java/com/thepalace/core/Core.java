package com.thepalace.core;

import com.comphenix.protocol.ProtocolLibrary;
import com.thepalace.core.config.LanguageFormatter;
import com.thepalace.core.packets.adapters.SettingsAdapter;
import com.thepalace.core.player.CPlayerManager;
import com.thepalace.core.player.impl.CorePlayerManager;
import com.thepalace.core.plugin.Plugin;
import com.thepalace.core.library.LibraryHandler;
import com.thepalace.core.plugin.PluginInfo;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@PluginInfo(name = "Core")
public class Core extends JavaPlugin {

    @Getter private static Core instance;
    private List<Plugin> plugins = new ArrayList<>();

    private LanguageFormatter languageFormatter;
    private CPlayerManager playerManager;

    @Override
    public final void onEnable() {
        instance = this;
        // Kick all players on reload
        for (Player player : Bukkit.getOnlinePlayers()) {
            player.kickPlayer(ChatColor.RED + "Server is reloading!");
        }
        // Libraries
        LibraryHandler.loadLibraries(this);
        // Formatter
        try {
            languageFormatter = new LanguageFormatter(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Protocol lib adapters
        ProtocolLibrary.getProtocolManager().addPacketListener(new SettingsAdapter());
        // Register plugin channel
        getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
        // Managers
        playerManager = new CorePlayerManager();
        logMessage("Core", ChatColor.DARK_GREEN + "Enabled");
    }

    @Override
    public final void onDisable() {
        logMessage("Core", ChatColor.DARK_RED + "Disabled");
    }

    /** Bukkit Utils */
    public static void callEvent(Event event) {
        Bukkit.getPluginManager().callEvent(event);
    }

    public static void registerListener(Listener listener) {
        Bukkit.getServer().getPluginManager().registerEvents(listener, getInstance());
    }

    public static int runTaskLater(Runnable task, long delay) {
        return Bukkit.getScheduler().runTaskLater(getInstance(), task, delay).getTaskId();
    }

    /* Log Utils */
    public static void logMessage(String name, String message) {
        logInfo(ChatColor.GOLD + name + ChatColor.DARK_GRAY + " > " + message);
    }

    public static void logInfo(String message) {
        Bukkit.getServer().getConsoleSender().sendMessage(message);
    }

    /* Plugin Utils */
    public static <T extends Plugin> T getPluginInstance(Class<T> pluginClass) {
        for (Plugin plugin : getInstance().plugins) {
            if (pluginClass.equals(plugin.getClass())) {
                return (T) plugin;
            }
        }
        return null;
    }

    public static void onPluginEnable(Plugin plugin) {
        getInstance().plugins.add(plugin);
    }

    public static void onPluginDisable(Plugin plugin) {
        getInstance().plugins.remove(plugin);
    }

    /* Core Info */
    public static double getVersion() {
        PluginInfo annotation = getInstance().getClass().getAnnotation(PluginInfo.class);
        if (annotation != null){
            return annotation.version();
        } else {
            return 1.0;
        }
    }

    /* Managers */
    public static CPlayerManager getPlayerManager() {
        return getInstance().playerManager;
    }

    public static LanguageFormatter getLanguageFormatter() {
        return getInstance().languageFormatter;
    }
}
