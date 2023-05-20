package it.lior215.frostpunks.util;

import it.lior215.frostpunks.Frostpunks;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModSoundEvents {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS  =
            DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, Frostpunks.MOD_ID);


    public static final RegistryObject<SoundEvent> RISE_AND_SHINE =
            registerSoundEvent("rise_and_shine");
    public static final RegistryObject<SoundEvent> DAY_ENDS =
            registerSoundEvent("day_ends");
    public static final RegistryObject<SoundEvent> FROST_HERE =
            registerSoundEvent("frost_here");





    private static RegistryObject<SoundEvent> registerSoundEvent(String name) {
        return SOUND_EVENTS.register(name, () -> new SoundEvent(new ResourceLocation(Frostpunks.MOD_ID, name)));
    }




    public static void register(IEventBus eventBus) {
        SOUND_EVENTS.register(eventBus);

    }
}
