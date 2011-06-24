package me.spike.creeperprank;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.event.Event;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Reacts to the enabling and disabling of the plugin and any commands
 * given by the user.
 * 
 * @author Andrew "Spike"
 */
public class CreeperPrank extends JavaPlugin {
	// Create a ConfigurationManager to handle configuration of the plugin.
	private final ConfigurationManager configMan = new ConfigurationManager();

	/**
	 * Executed when the plugin is disabled.
	 */
	public void onDisable() {
		System.out.println("Creeper Prank version 0.1 is disabled!");
	}

	/**
	 * Executed when the plugin is enabled.
	 */
	public void onEnable() {
		// Get a pluginmanager
		PluginManager pm = getServer().getPluginManager();

		// Make sure that user plugin config is loaded
		configMan.loadConfiguration();

		// Create and register a playerlistener.
		CreeperPrankPlayerListener playerListener = new CreeperPrankPlayerListener(configMan);
		pm.registerEvent(Event.Type.PLAYER_MOVE, playerListener, Event.Priority.Normal, this);

		System.out.println("Creeper Prank version 0.1 is enabled!");
	}

	/**
	 * React to user commands, see plugin.yml for more.
	 */
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		// All commands only available to ops.
		if (sender.isOp()) {
			// All two argument commands.
			if (args.length >= 2) {
				try {
					// Adding a player to be pranked with a custom spawn probability or changing the probability of a player already being pranked.
					if ((command.getName().equalsIgnoreCase("creeperprank") || command.getName().equalsIgnoreCase("setspawnprob"))) {
						// Make sure the number given is valid.
						checkProbability(Double.parseDouble(args[1]));
						configMan.addPlayer(args[0], args[1]);
						sender.sendMessage(args[0] + " is being creeper pranked with a " + args[1] + " probability of spawning a creeper!");
						return true;
					}
				} catch (NumberFormatException e) {
					sender.sendMessage("Please enter a number between 0 and 1!");
				}
			// All one argument commands.
			} else if (args.length == 1) {
				// Add a player to be pranked with the default probability of spawning a creeper.
				if (command.getName().equalsIgnoreCase("creeperprank")) {
					configMan.addPlayer(args[0]);
					sender.sendMessage(args[0] + " is being creeper pranked with a 0.0005 probability of spawning a creeper!");
					return true;
				}
				// Stop pranking a player.
				if (command.getName().equalsIgnoreCase("stoppranking")) {
					if (configMan.checkPlayer(args[0]) == null) {
						sender.sendMessage(args[0] + " was never being pranked!");
					} else {
						configMan.removePlayer(args[0]);
						sender.sendMessage(args[0] + " is no longer being pranked!");
					}
					return true;
				}
				// Check if a player is being pranked.
				if (command.getName().equalsIgnoreCase("beingpranked")) {
					String probability = configMan.checkPlayer(args[0]);
					if (probability == null || probability.equalsIgnoreCase("0")) {
						sender.sendMessage(args[0] + " is not being pranked");
					} else {
						sender.sendMessage(args[0] + " is being pranked with probability " + probability);
					}
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Method to check if a number is a valid probability. Throws a NumberFormatException
	 * if it isn't. CBF making a Probability type.
	 * @param prob The number to be checked.
	 * @throws NumberFormatException Thrown if the number is not a valid probability.
	 */
	private void checkProbability(double prob) throws NumberFormatException {
		if (!(prob <= 1.0 && prob >= 0.0)) {
			throw new NumberFormatException();
		}
	}
}
