package lykrast.mysticalwildlife.common.util;

import lykrast.mysticalwildlife.common.init.ModItems;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class CreativeTabsMysticalWildlife extends CreativeTabs {
	
	public static final CreativeTabs instance = new CreativeTabsMysticalWildlife(CreativeTabs.getNextID(), "mysticalwildlife");

	public CreativeTabsMysticalWildlife(int index, String label) {
		super(index, label);
	}

	@Override
	public ItemStack getTabIconItem() {
		return new ItemStack(ModItems.vrontausurusFur);
	}

}
