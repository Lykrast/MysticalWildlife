package lykrast.mysticalwildlife.common.entity;

import java.util.List;

import javax.annotation.Nullable;

import lykrast.mysticalwildlife.common.init.ModEntities;
import lykrast.mysticalwildlife.common.init.ModItems;
import lykrast.mysticalwildlife.common.init.ModSounds;
import lykrast.mysticalwildlife.common.util.LootUtil;
import lykrast.mysticalwildlife.common.util.ResourceUtil;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.entity.ai.EntityAIFollowParent;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMate;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.entity.ai.EntityAIWanderAvoidWater;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.Particles;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.storage.loot.LootTable;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class EntityDuskLurker extends EntityFurzard {
    public static final ResourceLocation LOOT = ResourceUtil.getEntityLootTable("dusk_lurker");
    public static final ResourceLocation LOOT_BRUSH = ResourceUtil.getSpecialLootTable("brush_dusk_lurker");
    private static final Ingredient TEMPTATION_ITEMS = Ingredient.fromItems(Items.RABBIT, Items.COOKED_RABBIT, ModItems.cicapteraRaw, ModItems.cicapteraCooked);
	
	public EntityDuskLurker(World worldIn) {
		super(ModEntities.duskLurker, worldIn);
        this.setSize(0.9F, 0.9F);
	}
	
    @Override
	protected void initEntityAI() {
        tasks.addTask(0, new EntityAISwimming(this));
        tasks.addTask(1, new EntityAIPanic(this, 1.25D));
        tasks.addTask(2, new EntityAIMate(this, 1.0D));
        tasks.addTask(3, new AITempt(this, 0.8D, false, TEMPTATION_ITEMS));
        tasks.addTask(4, new EntityAIAvoidEntity<EntityPlayer>(this, EntityPlayer.class, 10.0F, 1.0D, 1.25D));
        tasks.addTask(5, new EntityAIFollowParent(this, 1.1D));
        tasks.addTask(6, new EntityAIWanderAvoidWater(this, 1.0D));
        tasks.addTask(7, new EntityAIWatchClosest(this, EntityPlayer.class, 24.0F, 1.0F));
        tasks.addTask(8, new EntityAILookIdle(this));
    }
    
    @Override
	protected void registerAttributes() {
        super.registerAttributes();
        getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(12.0D);
        getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.25D);
    }
    
    private void spawnAshParticles() {
    	for (int i = 0; i < 10; ++i)
    	{
    		this.world.spawnParticle(Particles.LARGE_SMOKE, 
    				this.posX + (this.rand.nextDouble() - 0.5D) * (double)this.width, 
    				this.posY + this.rand.nextDouble() * (double)this.height, 
    				this.posZ + (this.rand.nextDouble() - 0.5D) * (double)this.width, 
    				0.0D, 0.0D, 0.0D);
    	}
    }
    
    @Override
    public boolean attackEntityFrom(DamageSource source, float amount) {
    	if (world.isRemote && amount > 0) spawnAshParticles();
    	
    	return super.attackEntityFrom(source, amount);
    }

	@Override
	public List<ItemStack> onBrushed(EntityPlayer player, ItemStack item, BlockPos pos, int fortune) {		
		//Spawns the particles
        this.world.setEntityState(this, (byte)10);
        playSound(ModSounds.brushing, 1.0F, 1.0F);
        
        if (rand.nextInt(5) == 0) setBrushTimer(3600 + rand.nextInt(2401));
        
    	LootTable loottable = world.getServer().getLootTableManager().getLootTableFromLocation(LOOT_BRUSH);
		return loottable.generateLootForPools(rand, LootUtil.getBrushingContext(this, player, fortune));
	}

    /**
     * Handler for {@link World#setEntityState}
     */
    @Override
    @OnlyIn(Dist.CLIENT)
	public void handleStatusUpdate(byte id) {
		if (id == 10) spawnAshParticles();
		else super.handleStatusUpdate(id);
	}

	@Override
	public EntityAgeable createChild(EntityAgeable ageable) {
		return new EntityDuskLurker(world);
	}

	@Override
    protected SoundEvent getAmbientSound()
    {
        return ModSounds.lizardIdle;
    }

	@Override
    protected SoundEvent getHurtSound(DamageSource p_184601_1_)
    {
        return ModSounds.lizardHurt;
    }

	@Override
    protected SoundEvent getDeathSound()
    {
        return ModSounds.lizardDeath;
    }

    protected void playStepSound(BlockPos pos, Block blockIn)
    {
        this.playSound(SoundEvents.ENTITY_COW_STEP, 0.15F, 1.0F);
    }

	@Override
    @Nullable
    protected ResourceLocation getLootTable() {
        return LOOT;
    }

    /**
     * Checks if the parameter is an item which this animal can be fed to breed it (wheat, carrots or seeds depending on
     * the animal type)
     */
    @Override
	public boolean isBreedingItem(ItemStack stack)
    {
    	if (world.isDaytime()) return false;
        return TEMPTATION_ITEMS.test(stack);
    }
    
    private static class AITempt extends EntityAITempt {
    	//Private in the super class, damn it
    	private EntityCreature tempted;
    	
		public AITempt(EntityCreature temptedEntityIn, double speedIn, boolean scaredByPlayerMovementIn, Ingredient temptItemIn) {
			super(temptedEntityIn, speedIn, scaredByPlayerMovementIn, temptItemIn);
			tempted = temptedEntityIn;
		}

		@Override
	    public boolean shouldExecute() {
			if (tempted.world.isDaytime()) return false;
			return super.shouldExecute();
		}
    }
}
