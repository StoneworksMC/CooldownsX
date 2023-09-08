package com.github.sirblobman.plugin.cooldown.task;

import java.util.Collection;
import java.util.Set;

import com.github.sirblobman.plugin.cooldown.CooldownPlugin;
import org.jetbrains.annotations.NotNull;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.github.sirblobman.plugin.cooldown.api.CooldownsX;
import com.github.sirblobman.plugin.cooldown.api.configuration.Cooldown;
import com.github.sirblobman.plugin.cooldown.api.data.PlayerCooldown;

public final class ExpireTask extends CooldownTask {
    public ExpireTask(@NotNull CooldownPlugin plugin) {
        super(plugin);
    }

    @Override
    public void run() {
        Collection<? extends Player> onlinePlayerCollection = Bukkit.getOnlinePlayers();
        for (Player player : onlinePlayerCollection) {
            checkPlayer(player);
        }
    }

    private void checkPlayer(@NotNull Player player) {
        PlayerCooldown cooldownData = getCooldownData(player);
        Set<Cooldown> activeCooldownSet = cooldownData.getActiveCooldowns();
        for (Cooldown cooldown : activeCooldownSet) {
            checkCooldown(cooldownData, cooldown);
        }
    }

    private void checkCooldown(@NotNull PlayerCooldown data, @NotNull Cooldown cooldown) {
        long expireMillis = data.getCooldownExpireTime(cooldown);
        long systemMillis = System.currentTimeMillis();
        if (systemMillis >= expireMillis) {
            data.removeCooldown(cooldown);
        }
    }
}
