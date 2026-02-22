package dev.awe.atlas.api.event.pinata;

import dev.awe.atlas.api.event.AtlasEvent;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.Event;

public abstract class PinataEvent extends Event implements AtlasEvent {
    protected final LivingEntity pinata;

    protected PinataEvent(LivingEntity pinata) {
        this.pinata = pinata;
    }

    public LivingEntity getPinata() {
        return pinata;
    }
}
