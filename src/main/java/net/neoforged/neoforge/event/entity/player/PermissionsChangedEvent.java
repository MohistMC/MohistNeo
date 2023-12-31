/*
 * Copyright (c) Forge Development LLC and contributors
 * SPDX-License-Identifier: LGPL-2.1-only
 */

package net.neoforged.neoforge.event.entity.player;

import net.minecraft.server.level.ServerPlayer;
import net.neoforged.bus.api.ICancellableEvent;

/**
 * This event will fire when the player is opped or deopped.
 * <p>
 * This event is cancelable which will stop the op or deop from happening.
 */
public class PermissionsChangedEvent extends PlayerEvent implements ICancellableEvent {
    private final int newLevel;
    private final int oldLevel;

    public PermissionsChangedEvent(ServerPlayer player, int newLevel, int oldLevel) {
        super(player);
        this.oldLevel = oldLevel;
        this.newLevel = newLevel;
    }

    /**
     * @return The new permission level.
     */
    public int getNewLevel() {
        return newLevel;
    }

    /**
     * @return The old permission level.
     */
    public int getOldLevel() {
        return oldLevel;
    }
}
