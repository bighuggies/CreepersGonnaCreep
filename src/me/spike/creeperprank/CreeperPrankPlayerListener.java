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
 * @author Andrew
 */
public class CreeperPrankPlayerListener extends PlayerListener {
	ConfigurationManager configMan;

	/**
	 * Constructor with a specified configuration manager.
	 * @param configMan The configuration manager.
	 */
	public CreeperPrankPlayerListener(ConfigurationManager configMan) {
		this.configMan = configMan;
	}

	/**
	 * Each time a player moves, check if they are on the list of prankees. If they are,
	 * generate a random number. If that random number is within the specified probability
	 * of a creeper spawning for that player, spawn a creeper at the block which the player
	 * is looking at. 
	 */
	public void onPlayerMove(PlayerMoveEvent event) {
		Player p = event.getPlayer();
		String probability = configMan.checkPlayer(p.getName());

		if (probability != null) {
			World w = p.getWorld();
			Block b = p.getTargetBlock(null, 256);

			Random rand = new Random(System.currentTimeMillis());

			if (rand.nextDouble() < Double.valueOf(probability)) {
				w.spawnCreature(b.getLocation(), CreatureType.CREEPER);
			}
		}

		super.onPlayerMove(event);
	}
}