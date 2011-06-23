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
 * @author Andrew
 */
public class ConfigurationManager {
	private final Properties properties = new Properties();
	private final File cfg = new File("plugins" + File.separator + "creeperprank.cfg");

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
	 * probability of spawning a creeper (0.01).
	 * @param name The name of the player to be added.
	 */
	public void addPlayer(String name) {
		properties.setProperty(name, "0.01");
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
	 * Remove a player from the list of players to be pranked.
	 * @param name The name of the player to be removed.
	 */
	public void removePlayer(String name) {
		properties.setProperty(name, "0");
		saveConfiguration();
	}

	/**
	 * Check if the player is in the list and if they are to be pranked.
	 * @param name The name of the player being checked for.
	 * @return True if the player is to be pranked, false otherwise.
	 */
	public String checkPlayer(String name) {
		if (properties.containsKey(name)) {
			return properties.getProperty(name);
		}
		return null;
	}
	
	/**
	 * Get the current probability of a creeper spawning on a player move event.
	 * @return String representing the spawn probability.
	 */
	public String getSpawnProbability(String name) {
		return properties.getProperty(name);
	}
}
