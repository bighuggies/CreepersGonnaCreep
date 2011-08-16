package me.spike.creeperprank;

import java.util.Random;

import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.CreatureType;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerListener;
import org.bukkit.event.player.PlayerMoveEvent;

/**
 * Listens for PlayerMoveEvents and either spawns a creeper or not depending
 * on the current configuration of the plugin.
 * 
 * @author Andrew "Spike"
 */
public class CreeperPrankPlayerListener extends PlayerListener {
	private static final ConfigurationManager configMan = ConfigurationManager.getConfigurationManager();
	private Random rand = new Random(System.currentTimeMillis());
	private static final double DEFAULT_PROBABILITY = 0.005;

	/**
	 * Each time a player moves, check if they are on the list of prankees. If they are,
	 * generate a random number. If that random number is within the specified probability
	 * of a creeper spawning for that player, spawn a creeper at the block which the player
	 * is looking at. 
	 */
	public void onPlayerMove(PlayerMoveEvent event) {
		Player p = event.getPlayer();
		boolean beingPranked = configMan.checkPlayer(p.getName());

		if (beingPranked == true) {
			if (rand.nextDouble() < DEFAULT_PROBABILITY) {
				World w = p.getWorld();
				Block b = p.getTargetBlock(null, 256);

				w.spawnCreature(b.getLocation(), CreatureType.CREEPER);
			}
		}

		super.onPlayerMove(event);
	}
}