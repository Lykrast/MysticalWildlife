package lykrast.mysticalwildlife.common.entity;

import java.util.Set;

import javax.annotation.Nullable;

import com.google.common.collect.Sets;

import lykrast.mysticalwildlife.common.init.ModPotions;
import lykrast.mysticalwildlife.core.MysticalWildlife;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIFollowParent;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMate;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.entity.ai.EntityAIWanderAvoidWater;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

public class EntityVrontausaurus extends EntityAnimal {
    public static final ResourceLocation LOOT = new ResourceLocation(MysticalWildlife.MODID, "entities/vrontausaurus");
    private static final Set<Item> TEMPTATION_ITEMS = Sets.newHashSet(Items.BEEF);
	
	public EntityVrontausaurus(World worldIn)
	{
		super(worldIn);
        this.setSize(2.2F, 1.4F);
	}

    protected void entityInit()
    {
        super.entityInit();
    }

    protected void initEntityAI()
    {
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(1, new AIPanic(1.2D));
        this.tasks.addTask(2, new AIAttackMeleeShortrange(this, 1.2D, false));
        this.tasks.addTask(3, new EntityAIMate(this, 1.0D));
        this.tasks.addTask(4, new EntityAITempt(this, 1.2D, false, TEMPTATION_ITEMS));
        this.tasks.addTask(5, new EntityAIFollowParent(this, 1.1D));
        this.tasks.addTask(6, new EntityAIWanderAvoidWater(this, 1.0D));
        this.tasks.addTask(7, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
        this.tasks.addTask(8, new EntityAILookIdle(this));
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false, new Class[0]));
    }
    
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(30.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.24D);
    }
    
    @Override
    public boolean attackEntityAsMob(Entity entityIn)
    {
        if (entityIn.attackEntityFrom(DamageSource.causeMobDamage(this), 5.0F))
        {
            if (entityIn instanceof EntityLivingBase)
            {
                int i = -1;

                if (this.world.getDifficulty() == EnumDifficulty.NORMAL)
                {
                    i = 0;
                }
                else if (this.world.getDifficulty() == EnumDifficulty.HARD)
                {
                    i = 1;
                }

                if (i >= 0)
                {
                    ((EntityLivingBase)entityIn).addPotionEffect(new PotionEffect(ModPotions.shocked, 60, i));
                }
            }

            return true;
        }
        else
        {
            return false;
        }
    }
    
    protected void updateAITasks()
    {
    	if (world.getDifficulty() == EnumDifficulty.PEACEFUL)
    	{
        	if (this.getAttackTarget() != null) this.setAttackTarget(null);
        	if (this.getRevengeTarget() != null) this.setRevengeTarget(null);
    	}
    	
    	super.updateAITasks();
    }

    /**
     * Called frequently so the entity can update its state every tick as required. For example, zombies and skeletons
     * use this to react to sunlight and start to burn.
     */
    @Override
    public void onLivingUpdate()
    {
    	//TODO: lightning attack
    	
        super.onLivingUpdate();
    }

	@Override
	public EntityAgeable createChild(EntityAgeable ageable) {
		return new EntityVrontausaurus(world);
	}
    
    protected SoundEvent getAmbientSound()
    {
        return SoundEvents.ENTITY_COW_AMBIENT;
    }

    protected SoundEvent getHurtSound(DamageSource p_184601_1_)
    {
        return SoundEvents.ENTITY_COW_HURT;
    }

    protected SoundEvent getDeathSound()
    {
        return SoundEvents.ENTITY_COW_DEATH;
    }

    protected void playStepSound(BlockPos pos, Block blockIn)
    {
        this.playSound(SoundEvents.ENTITY_IRONGOLEM_STEP, 1.0F, 1.0F);
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
    public boolean isBreedingItem(ItemStack stack)
    {
    	if (!world.isRaining()) return false;
        return TEMPTATION_ITEMS.contains(stack.getItem());
    }
    
    class AIPanic extends EntityAIPanic
    {
        public AIPanic(double speed)
        {
            super(EntityVrontausaurus.this, speed);
        }

        /**
         * Returns whether the EntityAIBase should begin execution.
         */
        public boolean shouldExecute()
        {
            return !EntityVrontausaurus.this.isChild() && !EntityVrontausaurus.this.isBurning() ? false : super.shouldExecute();
        }
    }
    
    private static class AIAttackMeleeShortrange extends EntityAIAttackMelee {

		public AIAttackMeleeShortrange(EntityCreature creature, double speedIn, boolean useLongMemory) {
			super(creature, speedIn, useLongMemory);
		}

		@Override
        protected double getAttackReachSqr(EntityLivingBase attackTarget)
        {
            return (double)(9.0F + attackTarget.width);
        }
    	
    }

}
