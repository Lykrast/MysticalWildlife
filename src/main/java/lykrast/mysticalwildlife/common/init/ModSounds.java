package lykrast.mysticalwildlife.common.init;

import java.util.ArrayList;
import java.util.List;

import lykrast.mysticalwildlife.core.MysticalWildlife;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class ModSounds {
	public static SoundEvent lizardIdle, lizardHurt, lizardDeath, 
			cicapteraIdle, cicapteraHurt, cicapteraDeath;
	private static List<SoundEvent> soundList = new ArrayList<>();
	
	
	static
	{
		lizardIdle = registerSoundEvent("lizard.idle");
		lizardHurt = registerSoundEvent("lizard.hurt");
		lizardDeath = registerSoundEvent("lizard.death");
		cicapteraIdle = registerSoundEvent("cicaptera.idle");
		cicapteraHurt = registerSoundEvent("cicaptera.hurt");
		cicapteraDeath = registerSoundEvent("cicaptera.death");
	}

	@SubscribeEvent
	public static void registerSoundEvents(RegistryEvent.Register<SoundEvent> event)
	{
		for (SoundEvent s : soundList) event.getRegistry().register(s);
	}
	
	public static SoundEvent registerSoundEvent(String name)
	{
		ResourceLocation location = new ResourceLocation(MysticalWildlife.MODID, name);
		SoundEvent event = new SoundEvent(location).setRegistryName(location);
		
		soundList.add(event);
		
		return event;
	}

}
