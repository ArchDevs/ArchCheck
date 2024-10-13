package me.arch.archcheck.commands.subcommands;

import me.arch.archcheck.ArchCheck;
import me.arch.archcheck.ConfigManager;
import me.arch.archcheck.commands.SubCommand;
import me.arch.archcheck.utils.BossBarUtil;
import me.arch.archcheck.utils.ChatUtil;
import me.arch.archcheck.utils.StartCheckUtil;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class StartSubCommand extends SubCommand {
    @Override
    public String getName() {
        return "start";
    }

    @Override
    public String getDescription() {
        return "Start a player checking";
    }

    @Override
    public String getSyntax() {
        return "/check start <player> [time in seconds]";
    }

    @Override
    public void perform(Player checker, String[] args) {
        if (ArchCheck.getPlayersOnCheck().containsValue(checker.getUniqueId())) {
            checker.sendMessage(ChatUtil.format(ConfigManager.getString("checker-already-checking", "&cYou are already checking player")));
            return;
        }
        final Player player = Bukkit.getPlayerExact(args[1]);
        if (player == null) {
            checker.sendMessage(ChatUtil.format(ConfigManager.getString("messages.player-not-found", "&cPlayer not found")));
            return;
        }

        BossBarUtil.startBossBar(player);
        StartCheckUtil.startCheck(player, checker);
    }
}