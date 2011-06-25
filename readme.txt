## Creepers Gonna Creep

# Description
Creepers Gonna Creep is a Minecraft Bukkit plugin that lets ops prank their users by spawning creepers around them randomly when they move. The frequency of creeper spawns is determined by a probability bestowed upon each user. Use a low probability (0.005 ish) to give the user an occasional heart attack and make them wonder why they have such bad luck. Use anything over 0.01 to crash the server :D

I mainly wrote this to familiarise myself with the Bukkit API and put to use the things I have learned in my OO class the last few months. I welcome feedback on the source.

# Changelog
1.0.1 - Made the default probability a property
1.0 - Initial release

# Commands
  creeperprank:
    description: Adds a player to the list of prankees Probability optional. Default probability is 0.005.
    usage: |
           /<command> [player name] [probability]
           Example: /<command> name 0.1 - Adds "name" to the list of players to be pranked with probability 0.1.
           Example: /<command> name - Adds "name" to the list of players to be pranked with probability 0.005.
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