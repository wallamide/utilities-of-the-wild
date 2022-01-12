package net.servate.uotw.reg;

import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class UotWSounds {
    
    public static final Identifier BOOMERANG_THROW = new Identifier("uotw:boonmerang_throw");
    
    public static SoundEvent BOOMERANG_THROW_EVENT = new SoundEvent(BOOMERANG_THROW);

    public static void register() {
        Registry.register(Registry.SOUND_EVENT, UotWSounds.BOOMERANG_THROW, BOOMERANG_THROW_EVENT);
    }
}
