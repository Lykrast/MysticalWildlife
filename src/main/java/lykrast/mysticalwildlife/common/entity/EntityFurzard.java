package lykrast.mysticalwildlife.common.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public abstract class EntityFurzard extends AnimalEntity implements IBrushable {
    private static final DataParameter<Boolean> BRUSHABLE = EntityDataManager.<Boolean>createKey(EntityFurzard.class, DataSerializers.BOOLEAN);
    private int brushTimer;

	public EntityFurzard(EntityType<? extends EntityFurzard> type, World worldIn) {
		super(type, worldIn);
	}

	@Override
	public boolean isBrushable(PlayerEntity player, ItemStack item, BlockPos pos) {
		return !isChild() && isBrushable();
	}

	@Override
    protected void registerData() {
        super.registerData();
        getDataManager().register(BRUSHABLE, Boolean.TRUE);
    }
	
	public boolean isBrushable() {
		return world.isRemote ? ((Boolean)getDataManager().get(BRUSHABLE)).booleanValue() : brushTimer <= 0;
	}
	
    public void setBrushTimer(int timer) {
    	getDataManager().set(BRUSHABLE, Boolean.valueOf(timer <= 0));
        brushTimer = timer;
    }
    
    @Override
    public void livingTick() {
        super.livingTick();
        if (!world.isRemote && brushTimer > 0) setBrushTimer(Math.max(0, brushTimer - 1));
    }
	
	@Override
	public void readAdditional(CompoundNBT compound) {
		super.readAdditional(compound);
		if (compound.contains("BrushTimer")) brushTimer = compound.getInt("BrushTimer");
	}

	@Override
	public void writeAdditional(CompoundNBT compound) {
		super.writeAdditional(compound);
		compound.putInt("BrushTimer", brushTimer);
	}

}
