package me.spike.creeperprank;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Manages the configuration of the plugin, ie who is currently being pranked
 * and what the probability any given player being pranked will spawn a creeper.
 * 
 * @author Andrew "Spike"
 */
public class ConfigurationManager {
	private final Properties properties = new Properties();
	private final File cfg = new File("plugins" + File.separator + "creepersgonnacreep.cfg");

	/**
	 * Save the configuration file to disk in a file named creeperprank.cfg.
	 */
	private void saveConfiguration() {
		try {
			FileOutputStream cfgWriter = new FileOutputStream(cfg);
			properties.store(cfgWriter, "Be careful when manually editing this file!");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Load the configuration from disk. If none is found, create one.
	 */
	public void loadConfiguration() {
		try {
			FileInputStream cfgReader = new FileInputStream(cfg);
			properties.load(cfgReader);
		} catch (FileNotFoundException e) {
			saveConfiguration();
			loadConfiguration();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Add a player to the list of players to be pranked with a default
	 * probability of spawning a creeper (0.005).
	 * @param name The name of the player to be added.
	 */
	public void addPlayer(String name) {
		properties.setProperty(name, "0.005");
		saveConfiguration();
	}

	/**
	 * Add a player to the list of players to be pranked with a specified
	 * probability of spawning a creeper. If the player is already on the list
	 * their probability will be changed.
	 * @param name The name of the player to be added.
	 * @param probability The probability that this player will spawn a creeper.
	 */
	public void addPlayer(String name, String probability) {
		properties.setProperty(name, probability);
		saveConfiguration();
	}

	/**
	 * Set a player's probability of spawning a creeper to 0.
	 * @param name The name of the player to be removed.
	 */
	public void removePlayer(String name) {
		properties.setProperty(name, "0");
		saveConfiguration();
	}

	/**
	 * Check if the player is in the list and if they are to be pranked.
	 * @param name The name of the player being checked for.
	 * @return String The probability of a player spawning a creeper or null if they are not on the list.
	 */
	public String checkPlayer(String name) {
		if (properties.containsKey(name)) {
			return properties.getProperty(name);
		}
		return null;
	}
}
