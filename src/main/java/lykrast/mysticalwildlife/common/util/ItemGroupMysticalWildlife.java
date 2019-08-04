package lykrast.mysticalwildlife.common.util;

import lykrast.mysticalwildlife.common.init.ModItems;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class ItemGroupMysticalWildlife extends ItemGroup {
	
	public static final ItemGroup INSTANCE = new ItemGroupMysticalWildlife(ItemGroup.getGroupCountSafe(), "mysticalwildlife");

	public ItemGroupMysticalWildlife(int index, String label) {
		super(index, label);
	}

	@Override
	public ItemStack createIcon() {
		return new ItemStack(ModItems.vrontausaurusFur);
	}

}
