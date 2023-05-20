package it.lior215.frostpunks.util;

import it.lior215.frostpunks.Frostpunks;
import net.minecraft.client.Minecraft;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.world.SleepFinishedTimeEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import javax.xml.ws.Holder;

@Mod.EventBusSubscriber(modid = Frostpunks.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class ModClientEvents {

    @Mod.EventBusSubscriber(modid = Frostpunks.MOD_ID)
    public static class ModEventsWake {

        static boolean soundDone = false;
        static boolean snowing = false;
        @SubscribeEvent
        public static void daySoundEvents(TickEvent.WorldTickEvent event) {
            Long currentDayTime = event.world.getDayTime() % 24000;
            if (currentDayTime == 101 && !soundDone ) {
                event.world.playSound(Minecraft.getInstance().player, Minecraft.getInstance().player.getPosition(), ModSoundEvents.RISE_AND_SHINE.get(), SoundCategory.VOICE, 1f, 1f);
                soundDone = true;
            }
            if (currentDayTime == 102  ) {
                soundDone = false;
            }
            if (currentDayTime == 13000 && !soundDone  ) {
                event.world.playSound(Minecraft.getInstance().player, Minecraft.getInstance().player.getPosition(), ModSoundEvents.DAY_ENDS.get(), SoundCategory.VOICE, 1f, 1f);
                soundDone = true;
            }
            if (currentDayTime == 13001  ) {
                soundDone = false;
            }
        }
        @SubscribeEvent
        public static void rainSoundEvents(TickEvent.PlayerTickEvent event) {
            Biome biome = event.player.world.getBiome(event.player.getPosition());
            if (event.player.world.isRaining() && biome.getPrecipitation() == Biome.RainType.SNOW && snowing == false) {
                event.player.world.playSound(Minecraft.getInstance().player, event.player.getPosition(), ModSoundEvents.FROST_HERE.get(), SoundCategory.VOICE, 1f, 1f);
                snowing = true;
                //System.out.println("raining");
            }
            else if(!event.player.world.isRaining()) {
                delayT(event);
            }


        }
        @SubscribeEvent
        public static void delayT(TickEvent.PlayerTickEvent event) {
            long totaltime = event.player.world.getDayTime();
            if(totaltime % 100 == 0 && !event.player.world.isRaining()) { //ticks
                snowing = false;
                //System.out.println("not raining");
            }
        }
        @SubscribeEvent
        public static void soundDoneOnWakeUp(SleepFinishedTimeEvent event) {
            if (!event.getWorld().isRemote()) {
                event.getWorld().playSound(Minecraft.getInstance().player, Minecraft.getInstance().player.getPosition(), ModSoundEvents.RISE_AND_SHINE.get(), SoundCategory.VOICE, 1f, 1f);
                soundDone = true;
            }
        }
    }
}
