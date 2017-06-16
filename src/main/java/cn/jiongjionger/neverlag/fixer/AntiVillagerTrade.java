package cn.jiongjionger.neverlag.fixer;

import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;

import cn.jiongjionger.neverlag.config.ConfigManager;

public class AntiVillagerTrade implements Listener {

	private final ConfigManager cm = ConfigManager.getInstance();

	@EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
	public void onTouchEntity(PlayerInteractEntityEvent e) {
		if (!cm.isAntiVillagerTrade()) {
			return;
		}
		Entity entity = e.getRightClicked();
		if (entity == null || entity.hasMetadata("NPC") || entity.hasMetadata("Mypet")) {
			return;
		}
		World world = entity.getWorld();
		if (world == null) {
			return;
		}
		if (cm.getDisableVillagerTradeWorldSet().contains(world.getName())) {
			if (entity instanceof Villager) {
				e.setCancelled(true);
			}
		}
	}
}