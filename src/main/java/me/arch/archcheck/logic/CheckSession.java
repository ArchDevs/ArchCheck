package me.arch.archcheck.logic;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.UUID;

@ToString
@EqualsAndHashCode
@Getter
public class CheckSession {
    private final UUID playerUuid;
    private final UUID checkerUuid;

    public CheckSession(UUID playerUuid, UUID checkerUuid) {
        this.playerUuid = playerUuid;
        this.checkerUuid = checkerUuid;
    }
}
