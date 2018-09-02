package lykrast.mysticalwildlife.common.util;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.WorldServer;
import net.minecraft.world.storage.loot.LootContext;

public class LootUtil {
	private LootUtil() {}
	
	//Only call on server
	public static LootContext getBrushingContext(Entity entity, EntityPlayer brusher, int fortune) {
		return new LootContextBrushing(0, (WorldServer)entity.world, entity, brusher, fortune);
	}
	
	//Custom context to have fortune on the Brush work like looting
	public static class LootContextBrushing extends LootContext {
		private int looting;
		
		public LootContextBrushing(float luckIn, WorldServer worldIn, Entity lootedEntityIn, EntityPlayer playerIn, int looting) {
			super(luckIn, worldIn, worldIn.getLootTableManager(), lootedEntityIn, playerIn, null);
			this.looting = looting;
		}
		
		@Override
	    public int getLootingModifier() {
			return looting;
	    }
		
		@Override
	    public Entity getKiller() {
	        return getKillerPlayer();
	    }
		
	}
}
