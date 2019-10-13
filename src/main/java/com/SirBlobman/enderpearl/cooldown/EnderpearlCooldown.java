package com.SirBlobman.enderpearl.cooldown;

import com.SirBlobman.api.utility.Util;
import com.SirBlobman.enderpearl.cooldown.hook.placeholder.PlaceholderHookClip;
import com.SirBlobman.enderpearl.cooldown.hook.placeholder.PlaceholderHookMVdW;
import com.SirBlobman.enderpearl.cooldown.listener.ListenerEnderpearlCooldown;
import com.SirBlobman.enderpearl.cooldown.task.EnderpearlCooldownTask;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class EnderpearlCooldown extends JavaPlugin {
    @Override
    public void onEnable() {
        saveDefaultConfig();

        PluginManager manager = Bukkit.getPluginManager();
        manager.registerEvents(new ListenerEnderpearlCooldown(this), this);

        EnderpearlCooldownTask task = new EnderpearlCooldownTask(this);
        task.runTaskTimerAsynchronously(this, 0L, 1L);

        if(manager.isPluginEnabled("MVdWPlaceholderAPI")) {
            Plugin plugin = manager.getPlugin("MVdWPlaceholderAPI");
            String version = plugin.getDescription().getVersion();

            new PlaceholderHookMVdW(this).register();
            getLogger().info("Hooked into MVdWPlaceholderAPI v" + version);
        }

        if(manager.isPluginEnabled("PlaceholderAPI")) {
            Plugin plugin = manager.getPlugin("PlaceholderAPI");
            String version = plugin.getDescription().getVersion();

            new PlaceholderHookClip(this).register();
            getLogger().info("Hooked into PlaceholderAPI v" + version);
        }
    }

    public String getConfigMessage(String path) {
        FileConfiguration config = getConfig();

        String message = config.getString("messages." + path);
        return (message == null ? "" : Util.color(message));
    }
}