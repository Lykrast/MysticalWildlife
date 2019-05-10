package lykrast.mysticalwildlife.common.entity;

import java.util.Set;

import javax.annotation.Nullable;

import com.google.common.collect.Sets;

import lykrast.mysticalwildlife.common.init.ModItems;
import lykrast.mysticalwildlife.common.init.ModSounds;
import lykrast.mysticalwildlife.common.util.ModConfig;
import lykrast.mysticalwildlife.common.util.ResourceUtil;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIFollowParent;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILeapAtTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMate;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.entity.ai.EntityAIWanderAvoidWater;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

public abstract class EntityCicaptera extends EntityAnimal {
    private static final Set<Item> SEEDS = Sets.newHashSet(Items.WHEAT_SEEDS, Items.BEETROOT_SEEDS, Items.MELON_SEEDS, Items.PUMPKIN_SEEDS);
    private static final Set<Item> FRUITS = Sets.newHashSet(Items.APPLE);
    private static final Set<Item> CACTUS = Sets.newHashSet(Item.getItemFromBlock(Blocks.CACTUS));
    private static final Set<Item> SUGAR = Sets.newHashSet(Items.SUGAR);
	
	public EntityCicaptera(World worldIn)
	{
		super(worldIn);
        this.setSize(0.9F, 0.4F);
	}
	
	protected abstract Set<Item> getTemptationItems();

    protected void initEntityAI()
    {
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(1, new EntityAIPanic(this, 1.25D));
        this.tasks.addTask(3, new EntityAIMate(this, 1.0D, EntityCicaptera.class));
        this.tasks.addTask(4, new EntityAITempt(this, 1.2D, false, getTemptationItems()));
        this.tasks.addTask(5, new EntityAIFollowParent(this, 1.1D));
        this.tasks.addTask(6, new EntityAIWanderAvoidWater(this, 1.0D));
        this.tasks.addTask(7, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
        this.tasks.addTask(8, new EntityAILookIdle(this));
    }
    
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(10.0D);
        this.getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(6.0D);
        this.getEntityAttribute(SharedMonsterAttributes.ARMOR_TOUGHNESS).setBaseValue(4.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.27D);
    }

    /**
     * Called frequently so the entity can update its state every tick as required. For example, zombies and skeletons
     * use this to react to sunlight and start to burn.
     */
    @Override
    public void onLivingUpdate()
    {
        super.onLivingUpdate();
        
        if (!this.onGround && this.motionY < 0.0D) this.motionY *= 0.6D;
    }

    public void fall(float distance, float damageMultiplier)
    {
    }

	@Override
	public EntityAgeable createChild(EntityAgeable ageable) {
		if (ageable instanceof EntityCicaptera && rand.nextBoolean()) return ((EntityCicaptera) ageable).createOwnChild();
		else return createOwnChild();
	}

	@Override
    public boolean canMateWith(EntityAnimal otherAnimal)
    {
        if (otherAnimal == this || !(otherAnimal instanceof EntityCicaptera)) return false;
        
        return this.isInLove() && otherAnimal.isInLove();
    }

    /**
     * Get this Entity's EnumCreatureAttribute
     */
	@Override
    public EnumCreatureAttribute getCreatureAttribute()
    {
        return EnumCreatureAttribute.ARTHROPOD;
    }
	
	protected abstract EntityAgeable createOwnChild();
    
    protected SoundEvent getAmbientSound()
    {
        return ModSounds.cicapteraIdle;
    }

    protected SoundEvent getHurtSound(DamageSource p_184601_1_)
    {
        return ModSounds.cicapteraHurt;
    }

    protected SoundEvent getDeathSound()
    {
        return ModSounds.cicapteraDeath;
    }

    protected void playStepSound(BlockPos pos, Block blockIn)
    {
        this.playSound(SoundEvents.ENTITY_CHICKEN_STEP, 0.15F, 1.0F);
    }

    /**
     * Checks if the parameter is an item which this animal can be fed to breed it (wheat, carrots or seeds depending on
     * the animal type)
     */
    public boolean isBreedingItem(ItemStack stack)
    {
        return getTemptationItems().contains(stack.getItem());
    }
    
    public static class Azure extends EntityCicaptera {
        public static final ResourceLocation LOOT = ResourceUtil.getEntityLootTable("cicaptera/azure");

    	public Azure(World worldIn) {
    		super(worldIn);
    	}

    	@Override
    	protected Set<Item> getTemptationItems() {
    		return SEEDS;
    	}

    	@Override
        @Nullable
        protected ResourceLocation getLootTable() {
            return LOOT;
        }

    	@Override
    	protected EntityAgeable createOwnChild() {
    		return new Azure(world);
    	}
    }
    
    public static class Verdant extends EntityCicaptera {
        public static final ResourceLocation LOOT = ResourceUtil.getEntityLootTable("cicaptera/verdant");

    	public Verdant(World worldIn) {
    		super(worldIn);
    	}

    	@Override
    	protected Set<Item> getTemptationItems() {
    		return FRUITS;
    	}

    	@Override
        @Nullable
        protected ResourceLocation getLootTable() {
            return LOOT;
        }

    	@Override
    	protected EntityAgeable createOwnChild() {
    		return new Verdant(world);
    	}
    }
    
    public static class Crimson extends EntityCicaptera {
        public static final ResourceLocation LOOT = ResourceUtil.getEntityLootTable("cicaptera/crimson");

    	public Crimson(World worldIn) {
    		super(worldIn);
    	}

    	@Override
    	protected Set<Item> getTemptationItems() {
    		return CACTUS;
    	}
    	
    	@Override
        protected void initEntityAI()
        {
            this.tasks.addTask(0, new EntityAISwimming(this));
            this.tasks.addTask(1, new AICrimsonLeap(this));
            this.tasks.addTask(2, new EntityAIAttackMelee(this, 1.2D, false));
            this.tasks.addTask(3, new EntityAIMate(this, 1.0D));
            this.tasks.addTask(4, new EntityAITempt(this, 1.2D, false, SEEDS));
            this.tasks.addTask(5, new EntityAIFollowParent(this, 1.1D));
            this.tasks.addTask(6, new EntityAIWanderAvoidWater(this, 1.0D));
            this.tasks.addTask(7, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
            this.tasks.addTask(8, new EntityAILookIdle(this));
            this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true, new Class[0]));
        }
        
        @Override
        public boolean attackEntityAsMob(Entity entityIn)
        {
        	boolean flag = entityIn.attackEntityFrom(DamageSource.causeMobDamage(this), 3.0F);
        	
            if (flag)
            {
            	entityIn.motionX += motionX;
            	entityIn.motionY += motionY;
            	entityIn.motionZ += motionZ;
            }
        	motionX *= -1;
        	motionY *= -1;
        	motionZ *= -1;
        	
        	return flag;
        }

        @Override
        protected void updateAITasks()
        {
        	if (world.getDifficulty() == EnumDifficulty.PEACEFUL)
        	{
            	if (this.getAttackTarget() != null) this.setAttackTarget(null);
            	if (this.getRevengeTarget() != null) this.setRevengeTarget(null);
        	}
        	
        	super.updateAITasks();
        }

    	@Override
        @Nullable
        protected ResourceLocation getLootTable() {
            return LOOT;
        }

    	@Override
    	protected EntityAgeable createOwnChild() {
    		return new Crimson(world);
    	}
    	
    	private static class AICrimsonLeap extends EntityAILeapAtTarget {
    		//Damn it package private access
    		private EntityLiving leaper;
    		//Only attack once per leap
    	    private boolean attacked;
    		
			public AICrimsonLeap(EntityCicaptera leapingEntity) {
				super(leapingEntity, 0.5F);
				this.leaper = leapingEntity;
			}
			
			@Override
		    public void startExecuting()
		    {
				attacked = false;
				EntityLivingBase leapTarget = leaper.getAttackTarget();
				if (leapTarget == null) return;
				
		        double d0 = leapTarget.posX - this.leaper.posX;
		        double d1 = leapTarget.posZ - this.leaper.posZ;
		        float f = MathHelper.sqrt(d0 * d0 + d1 * d1);

		        if ((double)f >= 1.0E-4D)
		        {
		            this.leaper.motionX += d0 / (double)f * 0.8D + this.leaper.motionX * 0.2D;
		            this.leaper.motionZ += d1 / (double)f * 0.8D + this.leaper.motionZ * 0.2D;
		        }

		        this.leaper.motionY = 0.5D;
		    }
			
			@Override
		    public void updateTask()
		    {
				EntityLivingBase leapTarget = leaper.getAttackTarget();
				if (leapTarget == null || attacked) return;
				double distance = this.leaper.getDistanceSq(leapTarget.posX, 
						(leapTarget.getEntityBoundingBox().minY + leapTarget.getEntityBoundingBox().maxY) / 2.0, 
						leapTarget.posZ);
				double range = (double)(this.leaper.width * this.leaper.width + leapTarget.width);
				
				if (distance <= range)
				{
					attacked = true;
					leaper.attackEntityAsMob(leapTarget);
				}
		    }
    	}
    }
    
    public static class Sandy extends EntityCicaptera {
        public static final ResourceLocation LOOT = ResourceUtil.getEntityLootTable("cicaptera/sandy");

    	public Sandy(World worldIn) {
    		super(worldIn);
    	}

    	@Override
    	protected Set<Item> getTemptationItems() {
    		return CACTUS;
    	}

    	@Override
        @Nullable
        protected ResourceLocation getLootTable() {
            return LOOT;
        }

    	@Override
    	protected EntityAgeable createOwnChild() {
    		return new Sandy(world);
    	}
    }
    
    public static class Wintry extends EntityCicaptera {
        public static final ResourceLocation LOOT = ResourceUtil.getEntityLootTable("cicaptera/wintry");

    	public Wintry(World worldIn) {
    		super(worldIn);
    	}

    	@Override
    	protected Set<Item> getTemptationItems() {
    		return SEEDS;
    	}

    	@Override
        @Nullable
        protected ResourceLocation getLootTable() {
            return LOOT;
        }

    	@Override
    	protected EntityAgeable createOwnChild() {
    		return new Wintry(world);
    	}
    }
    
    public static class Lovely extends EntityCicaptera {
        public static final ResourceLocation LOOT = ResourceUtil.getEntityLootTable("cicaptera/lovely");
        public int timeUntilNextEssence;

    	public Lovely(World worldIn) {
    		super(worldIn);
    		resetEssenceTime();
    	}
    	
    	@Override
        public void onLivingUpdate() {
    		super.onLivingUpdate();

			if (!world.isRemote && !isChild() && --timeUntilNextEssence <= 0) {
				playSound(SoundEvents.ENTITY_CHICKEN_EGG, 1.0F, (rand.nextFloat() - rand.nextFloat()) * 0.2F + 1.0F);
				dropItem(ModItems.aphroditeEssence, 1);
				resetEssenceTime();
			}
    	}
    	
    	private void resetEssenceTime() {
    		timeUntilNextEssence = rand.nextInt(ModConfig.cicapteraLovelyEssenceTimeBase) + ModConfig.cicapteraLovelyEssenceTimeExtra;
    	}
    	

    	@Override
		public void readEntityFromNBT(NBTTagCompound compound) {
			super.readEntityFromNBT(compound);
			if (compound.hasKey("EssenceTime")) timeUntilNextEssence = compound.getInteger("EssenceTime");
		}

		@Override
		public void writeEntityToNBT(NBTTagCompound compound) {
			super.writeEntityToNBT(compound);
			compound.setInteger("EssenceTime", timeUntilNextEssence);
		}

    	@Override
    	protected Set<Item> getTemptationItems() {
    		return SUGAR;
    	}

    	@Override
        @Nullable
        protected ResourceLocation getLootTable() {
            return LOOT;
        }

    	@Override
    	protected EntityAgeable createOwnChild() {
    		return new Lovely(world);
    	}
    }
}
