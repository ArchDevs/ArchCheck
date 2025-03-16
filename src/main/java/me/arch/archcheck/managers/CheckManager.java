package me.arch.archcheck.managers;

import me.arch.archcheck.utils.CheckSession;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

public class CheckManager {
    private static final HashMap<UUID, CheckSession> activeChecks = new HashMap<>();

    public static void startCheck(UUID player, UUID checker) {
        CheckSession session = new CheckSession(player, checker);
        activeChecks.put(player, session);
    }

    public static void stopCheck(UUID player, UUID checker) {
        activeChecks.remove(player);
    }

    public static CheckSession getCheckSession(UUID player) { return activeChecks.get(player); }

    public static boolean isOnCheck(UUID player) { return activeChecks.containsKey(player); }

    public static boolean isPlayerChecker(UUID checker) {
        return activeChecks.values().stream().anyMatch(session -> session.getCheckerUuid().equals(checker));
    }

    public static Player getChecker(Player player) {
        CheckSession session = getCheckSession(player.getUniqueId());
        return session != null ? Bukkit.getPlayer(session.getCheckerUuid()) : null;
    }

    public static Player getPlayer(Player player) {
        CheckSession session = getCheckSession(player.getUniqueId());
        return session != null ? Bukkit.getPlayer(session.getPlayerUuid()) : null;
    }

    public static List<Player> getPlayersOnCheck() {
        return activeChecks.values().stream()
                .map(session -> Bukkit.getPlayer(session.getPlayerUuid()))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

}
