package lykrast.mysticalwildlife.common.entity;

import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public abstract class EntityFurzard extends EntityAnimal implements IBrushable {
    private static final DataParameter<Boolean> BRUSHABLE = EntityDataManager.<Boolean>createKey(EntityFurzard.class, DataSerializers.BOOLEAN);
    private int brushTimer;

	public EntityFurzard(World worldIn) {
		super(worldIn);
	}

	@Override
	public boolean isBrushable(EntityPlayer player, ItemStack item, IBlockAccess world, BlockPos pos) {
		return !isChild() && isBrushable();
	}

	@Override
    protected void entityInit() {
        super.entityInit();
        dataManager.register(BRUSHABLE, Boolean.TRUE);
    }
	
	public boolean isBrushable() {
		return world.isRemote ? ((Boolean)this.dataManager.get(BRUSHABLE)).booleanValue() : brushTimer <= 0;
	}
	
    public void setBrushTimer(int timer)
    {
        dataManager.set(BRUSHABLE, Boolean.valueOf(timer <= 0));
        brushTimer = timer;
    }
    
    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();
        if (!world.isRemote && !isBrushable()) setBrushTimer(Math.max(0, brushTimer - 1));
    }

}
