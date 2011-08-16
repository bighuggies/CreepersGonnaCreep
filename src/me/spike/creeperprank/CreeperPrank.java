package me.spike.creeperprank;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.event.Event;
import org.bukkit.plugin.PluginDescriptionFile;
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
	private final ConfigurationManager configMan = ConfigurationManager.getConfigurationManager();

	/**
	 * Executed when the plugin is disabled.
	 */
	public void onDisable() {
		PluginDescriptionFile pdfFile = this.getDescription();
		System.out.println( pdfFile.getName() + " version " + pdfFile.getVersion() + " is disabled!" );
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
		CreeperPrankPlayerListener playerListener = new CreeperPrankPlayerListener();
		pm.registerEvent(Event.Type.PLAYER_MOVE, playerListener, Event.Priority.Normal, this);

		PluginDescriptionFile pdfFile = this.getDescription();
		System.out.println( pdfFile.getName() + " version " + pdfFile.getVersion() + " is enabled!" );
	}

	/**
	 * React to user commands, see plugin.yml for more.
	 */
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (sender.hasPermission("creeperprank.creeperprank") && args.length >= 1) {
			// Prank a player
			if (command.getName().equalsIgnoreCase("creeperprank")) {
				configMan.addPlayer(args[0]);
				sender.sendMessage(args[0] + " is being creeper pranked!");
				return true;
			}

			// Stop pranking a player
			if (command.getName().equalsIgnoreCase("stoppranking")) {
				if (configMan.checkPlayer(args[0]) == false) {
					sender.sendMessage(args[0] + " is not being pranked!");
				} else {
					configMan.removePlayer(args[0]);
					sender.sendMessage(args[0] + " is no longer being pranked!");
				}
				return true;
			}
		}

		if (sender.hasPermission("creeperprank.beingpranked")) {
			//Check if a player is being pranked
			if (command.getName().equalsIgnoreCase("beingpranked")) {
				boolean beingPranked = configMan.checkPlayer(args[0]);
				if (beingPranked == false) {
					sender.sendMessage(args[0] + " is not being pranked");
				} else {
					sender.sendMessage(args[0] + " is being pranked");
				}
				return true;
			}
		}
		return false;
	}
}
