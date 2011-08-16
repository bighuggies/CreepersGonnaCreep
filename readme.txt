## Creepers Gonna Creep

# Description
Creepers Gonna Creep is a Minecraft Bukkit plugin that lets ops prank their users by spawning creepers around them randomly when they move. Make your users wonder why god hates them so much :P

I mainly wrote this to familiarise myself with the Bukkit API and put to use the things I have learned in my OO class the last few months. I welcome feedback on the source.

# Changelog
1.1     Added permissions support
        Removed the concept of a probability (unnecessary)
        Added the ability to stop pranking all users at once (/stoppranking all)
1.0.1   Made the default probability a property
1.0     Initial release

# Commands:
  creeperprank:
    description: Adds a player to the list of players to be pranked.
    usage: |
           /<command> [player name]
           Example: /<command> name - Adds "name" to the list of players to be pranked.
  stoppranking:
    description: Stops pranking a player, or all players.
    usage: |
           /<command> [player name]
           /<command> all 
           Example: /<command> name
  beingpranked:
    description: Checks if a player is being pranked.
    usage: |
           /<command> [player name]
           Example: /<command> name

# Permissions:
    creeperprank.creeperprank:
        description: Gives access to all creeper prank commands
        children:
            creeperprank.beingpranked: true
        default: op
    creeperprank.beingpranked:
        description: Allows you to check if a user is being pranked
        default: op