package lykrast.mysticalwildlife.common.entity;

import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public abstract class EntityFurzard extends EntityAnimal implements IBrushable {
    private static final DataParameter<Boolean> BRUSHABLE = EntityDataManager.<Boolean>createKey(EntityAgeable.class, DataSerializers.BOOLEAN);
    private int brushTimer;

	public EntityFurzard(EntityType<? extends EntityFurzard> type, World worldIn) {
		super(type, worldIn);
	}

	@Override
	public boolean isBrushable(EntityPlayer player, ItemStack item, BlockPos pos) {
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
        if (!world.isRemote && !isBrushable()) setBrushTimer(Math.max(0, brushTimer - 1));
    }
	
	@Override
	public void readAdditional(NBTTagCompound compound) {
		super.readAdditional(compound);
		if (compound.hasKey("BrushTimer")) brushTimer = compound.getInt("BrushTimer");
	}

	@Override
	public void writeAdditional(NBTTagCompound compound) {
		super.writeAdditional(compound);
		compound.setInt("BrushTimer", brushTimer);
	}

}
