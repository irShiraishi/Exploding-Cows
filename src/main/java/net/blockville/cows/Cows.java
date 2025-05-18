package net.blockville.cows;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.plugin.java.JavaPlugin;
import java.util.Random;

public final class Cows extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        // Plugin startup logic

        System.out.println("Exploding cows has been enabled!");

        getServer().getPluginManager().registerEvents(this, this);

    }

    @EventHandler
    public void onCowDeath(EntityDeathEvent e) {
        Random rand = new Random();

        if ((rand.nextInt(10)) == 1) {

            if (e.getEntityType() == EntityType.COW) {
                System.out.println("poor cow: " + e.getEntity().getKiller().getType() + ", " + EntityType.PLAYER);
                if (e.getEntity().getKiller() == null) return;
                if (e.getEntity().getKiller().getType() == EntityType.PLAYER) {

                    Player p = (Player) e.getEntity().getKiller();
                    assert p != null;
                    World w = p.getWorld();
                    Location loc = p.getEyeLocation();

                    w.createExplosion(loc, 20, false, false);
                    p.sendMessage(ChatColor.DARK_GRAY + "** " + ChatColor.GRAY + "Damn.. that's unlucky!");
                    p.damage(10);
                }
            }
        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic

        System.out.println("Exploding cows has been disabled!");
    }
}