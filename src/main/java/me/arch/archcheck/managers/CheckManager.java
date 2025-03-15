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

    public static void startCheck(UUID player, UUID checker) { activeChecks.put(player, new CheckSession(player, checker)); }

    public static void stopCheck(UUID player, UUID checker) { activeChecks.remove(player); }

    public static CheckSession getCheckSession(UUID player) { return activeChecks.get(player); }

    public static boolean isOnCheck(UUID player) { return  activeChecks.containsKey(player); }

    public static boolean isPlayerChecker(UUID player) { return player.equals(getCheckSession(player).getCheckerUuid()); }

    public static Player getChecker(Player player) {
        if (isOnCheck(player.getUniqueId())) {
            UUID checkerUUID = getCheckSession(player.getUniqueId()).getCheckerUuid();
            return Bukkit.getPlayer(checkerUUID);
        }
        return null;
    }

    public static List<Player> getPlayersOnCheck() {
        return activeChecks.values().stream()
                .map(session -> Bukkit.getPlayer(session.getPlayerUuid()))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

}
