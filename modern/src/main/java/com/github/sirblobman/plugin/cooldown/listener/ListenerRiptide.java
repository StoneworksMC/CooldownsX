package com.github.sirblobman.plugin.cooldown.listener;

import java.util.Set;

import dev.apposed.combattag.CombatTag;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.entity.EntityResurrectEvent;

import com.github.sirblobman.api.shaded.xseries.XMaterial;
import com.github.sirblobman.plugin.cooldown.api.CooldownsX;
import com.github.sirblobman.plugin.cooldown.api.configuration.CooldownType;
import com.github.sirblobman.plugin.cooldown.api.configuration.Cooldown;
import com.github.sirblobman.plugin.cooldown.api.data.PlayerCooldown;
import com.github.sirblobman.plugin.cooldown.api.listener.CooldownListener;
import org.bukkit.event.player.PlayerRiptideEvent;
import org.bukkit.plugin.PluginManager;

public final class ListenerRiptide extends CooldownListener {
    public ListenerRiptide (CooldownsX plugin) {
        super(plugin);
    }
    CombatTag ct = (CombatTag) Bukkit.getPluginManager().getPlugin("CombatTag");
    @EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = true)
    public void isRiptiding(PlayerRiptideEvent e) {


        Player player = e.getPlayer();

     /*   ICooldownData cooldownData = getCooldownData(player);
        Set<ICooldownSettings> activeCooldowns = cooldownData.getActiveCooldowns(CooldownType.RIPTIDE);

        ICooldownSettings activeCooldown = checkActiveCooldowns(player, activeCooldowns);
        if (activeCooldown != null) {
            e.setCancelled(true);
            sendCooldownMessage(player, activeCooldown, XMaterial.TOTEM_OF_UNDYING);
            updateInventoryLater(player);
            return;
        }
*/
        if(ct.getCombatHandler().isInCombat(player)) {
            Set<Cooldown> validCooldowns = fetchCooldowns(CooldownType.RIPTIDE);
            checkValidCooldowns(player, validCooldowns);
        }
    }
}
