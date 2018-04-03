package lykrast.mysticalwildlife.common.util;

import lykrast.mysticalwildlife.core.MysticalWildlife;
import net.minecraft.util.ResourceLocation;

public class TextureUtil {
	private TextureUtil() {}
	
	public static ResourceLocation getEntityTexture(String name) {
		return new ResourceLocation(MysticalWildlife.MODID, "textures/entity/" + name + ".png");
	}
}
