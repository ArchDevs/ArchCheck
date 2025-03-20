package me.arch.archcheck.managers;

import me.arch.archcheck.logic.CheckSession;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.*;
import java.util.stream.Collectors;

public class CheckManager {
    private static final HashMap<UUID, CheckSession> activeChecks = new HashMap<>();
    private static final Collection<CheckSession> activeSessions = activeChecks.values();

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

    public static List<Player> getPlayersOnCheck() {
        return activeChecks.values().stream()
                .map(session -> Bukkit.getPlayer(session.getPlayerUuid()))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    public static Player getSuspectForChecker(Player checker) {
        for (CheckSession session : activeSessions) {
            if (checker.getUniqueId().equals(session.getCheckerUuid())) {
                return Bukkit.getPlayer(session.getPlayerUuid());
            }
        }
        return null;
    }

    public static void applyEffects(Player player) {
        player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 999999, 1, true, true));
    }

    public static void removeEffects(Player player) {
        player.removePotionEffect(PotionEffectType.BLINDNESS);
    }

}
