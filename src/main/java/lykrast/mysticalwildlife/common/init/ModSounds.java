package lykrast.mysticalwildlife.common.init;

import lykrast.mysticalwildlife.core.MysticalWildlife;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, modid = MysticalWildlife.MODID)
public class ModSounds {
	public static SoundEvent lizardIdle, lizardHurt, lizardDeath, 
			cicapteraIdle, cicapteraHurt, cicapteraDeath,
			plumperIdle, plumperHurt, plumperDeath,
			krillIdle, krillHurt, krillDeath,
			brushing, spark;

	@SubscribeEvent
	public static void registerSoundEvents(RegistryEvent.Register<SoundEvent> event) {
		IForgeRegistry<SoundEvent> reg = event.getRegistry();
		
		lizardIdle = registerSoundEvent(reg, "lizard.idle");
		lizardHurt = registerSoundEvent(reg, "lizard.hurt");
		lizardDeath = registerSoundEvent(reg, "lizard.death");
		
		cicapteraIdle = registerSoundEvent(reg, "cicaptera.idle");
		cicapteraHurt = registerSoundEvent(reg, "cicaptera.hurt");
		cicapteraDeath = registerSoundEvent(reg, "cicaptera.death");
		
		plumperIdle = registerSoundEvent(reg, "plumper.idle");
		plumperHurt = registerSoundEvent(reg, "plumper.hurt");
		plumperDeath = registerSoundEvent(reg, "plumper.death");
		
		krillIdle = registerSoundEvent(reg, "krill.idle");
		krillHurt = registerSoundEvent(reg, "krill.hurt");
		krillDeath = registerSoundEvent(reg, "krill.death");
		
		brushing = registerSoundEvent(reg, "brushing");
		spark = registerSoundEvent(reg, "lizard.spark");
	}
	
	public static SoundEvent registerSoundEvent(IForgeRegistry<SoundEvent> reg, String name) {
		ResourceLocation location = new ResourceLocation(MysticalWildlife.MODID, name);
		SoundEvent event = new SoundEvent(location).setRegistryName(location);
		
		reg.register(event);
		
		return event;
	}

}
