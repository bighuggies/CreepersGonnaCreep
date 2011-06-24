## Creepers Gonna Creep

# Description
Creepers Gonna Creep is a Minecraft Bukkit plugin that lets ops prank their users by making them spawn creepers occasionally when they move. Whether or not a creeper spawns is determined by a probability bestowed upon the user. Use a low probability (0.0005 ish) to give the user an occasional heart attack and make them wonder why they have such bad luck. Use anything over 0.001 to crash the server :D

Written by Andrew (SpikeMeister/Spike) as a test to become more familiar with the Bukkit API and writing plugins for MineCraft. Feedback welcome.

# Changelog
1.0 - Initial release

# Commands
  creeperprank:
    description: Adds a player to the list of prankees Probability optional. Default probability is 0.01.
    usage: |
           /<command> [player name] [probability]
           Example: /<command> name 0.1 - Adds "name" to the list of players to be pranked with probability 0.1.
           Example: /<command> name - Adds "name" to the list of players to be pranked with probability 0.01.
  setspawnprob:
    description: Sets the probability that player will spawn a creeper.
    usage: |
           /<command> [player name] [probability]
           Example: /<command> name 0.01 - Sets the probability of "name" spawning a creeper to 1 in 100.
  stoppranking:
    description: Sets the probability of a player spawning a creeper to 0.
    usage: |
           /<command> [player name]
           Example: /<command> name
  beingpranked:
    description: Checks if a player is being pranked and returns the probability they will spawn a creeper.
    usage: |
           /<command> [player name]
           Example: /<command> name