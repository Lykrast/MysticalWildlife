package lykrast.mysticalwildlife.common.util;

import lykrast.mysticalwildlife.core.MysticalWildlife;
import net.minecraft.util.ResourceLocation;

public class ResourceUtil {
	private ResourceUtil() {}
	
	public static ResourceLocation getEntityTexture(String name) {
		return new ResourceLocation(MysticalWildlife.MODID, "textures/entity/" + name + ".png");
	}
	
	public static ResourceLocation getEntityLootTable(String name) {
		return new ResourceLocation(MysticalWildlife.MODID, "entities/" + name);
	}
	
	public static ResourceLocation getSpecialLootTable(String name) {
		return new ResourceLocation(MysticalWildlife.MODID, "special/" + name);
	}
}
