package lykrast.mysticalwildlife.common.util;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.IntValue;

public class MobConfig {
	//Inspired from Mystical World
	//https://github.com/MysticMods/MysticalWorld/blob/1.14/src/main/java/epicsquid/mysticalworld/config/MobConfig.java
	private String name, registry;
	private int weight, min, max;
	private IntValue cfgWeight, cfgMin, cfgMax;
	
	public MobConfig(String name, String registry, int weight, int min, int max) {
		this.name = name;
		this.registry = registry;
		this.weight = weight;
		this.min = min;
		this.max = max;
	}
	
	public int getWeight() {
		return cfgWeight.get();
	}
	
	public int getMin() {
		return cfgMin.get();
	}
	
	public int getMax() {
		return cfgMax.get();
	}
	
	public boolean isSpawnEnabled() {
		return cfgWeight.get() > 0;
	}
	
	public MobConfig make(ForgeConfigSpec.Builder builder) {
		builder.comment(name + " spawn").push(registry + "_spawn");
		
		cfgWeight = builder
				.comment("Spawn weight (higher = more frequent, 0 disables spawn)")
				.defineInRange("weight", weight, 0, 2048);
		cfgMin = builder
				.comment("Minimum group size")
				.defineInRange("min", min, 1, 255);
		cfgMax = builder
				.comment("Maximum group size")
				.defineInRange("max", max, 1, 255);
		
		builder.pop();
		return this;
	}
}
