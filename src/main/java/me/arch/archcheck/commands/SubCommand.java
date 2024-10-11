package me.arch.archcheck.commands;

import org.bukkit.entity.Player;

public abstract class SubCommand {
    public abstract String getName(); // Command subcommand "name". Example: /<command> [name]
    public abstract String getDescription(); // Command description
    public abstract String getSyntax(); // Syntax of command. Example: /<command> [name] (player). How ot use command
    public abstract void perform(Player player, String args[]); // Performing command for player

}
