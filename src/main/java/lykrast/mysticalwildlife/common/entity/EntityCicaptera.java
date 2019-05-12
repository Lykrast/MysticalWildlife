package lykrast.mysticalwildlife.common.entity;

import javax.annotation.Nullable;

import lykrast.mysticalwildlife.common.init.ModEntities;
import lykrast.mysticalwildlife.common.init.ModItems;
import lykrast.mysticalwildlife.common.init.ModSounds;
import lykrast.mysticalwildlife.common.util.ModConfig;
import lykrast.mysticalwildlife.common.util.ResourceUtil;
import net.minecraft.block.Block;
import net.minecraft.entity.CreatureAttribute;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EntityType;
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
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

public abstract class EntityCicaptera extends EntityAnimal {
    private static final Ingredient SEEDS = Ingredient.fromItems(Items.WHEAT_SEEDS, Items.BEETROOT_SEEDS, Items.MELON_SEEDS, Items.PUMPKIN_SEEDS);
    private static final Ingredient FRUITS = Ingredient.fromItems(Items.APPLE);
    private static final Ingredient CACTUS = Ingredient.fromItems(Blocks.CACTUS, Items.CACTUS_GREEN);
    private static final Ingredient SUGAR = Ingredient.fromItems(Items.SUGAR);
	
	public EntityCicaptera(EntityType<? extends EntityCicaptera> type, World worldIn) {
		super(type, worldIn);
        this.setSize(0.9F, 0.4F);
	}
	
	protected abstract Ingredient getTemptationItems();

    @Override
	protected void initEntityAI() {
        tasks.addTask(0, new EntityAISwimming(this));
        tasks.addTask(1, new EntityAIPanic(this, 1.25D));
        tasks.addTask(3, new EntityAIMate(this, 1.0D, EntityCicaptera.class));
        tasks.addTask(4, new EntityAITempt(this, 1.2D, false, getTemptationItems()));
        tasks.addTask(5, new EntityAIFollowParent(this, 1.1D));
        tasks.addTask(6, new EntityAIWanderAvoidWater(this, 1.0D));
        tasks.addTask(7, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
        tasks.addTask(8, new EntityAILookIdle(this));
    }
    
    @Override
	protected void registerAttributes() {
        super.registerAttributes();
        getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(10.0D);
        getAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(6.0D);
        getAttribute(SharedMonsterAttributes.ARMOR_TOUGHNESS).setBaseValue(4.0D);
        getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.27D);
    }

    /**
     * Called frequently so the entity can update its state every tick as required. For example, zombies and skeletons
     * use this to react to sunlight and start to burn.
     */
    @Override
    public void livingTick() {
        super.livingTick();
        
        if (!this.onGround && this.motionY < 0.0D) this.motionY *= 0.6D;
    }

    @Override
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
    public CreatureAttribute getCreatureAttribute() {
        return CreatureAttribute.ARTHROPOD;
    }
	
	protected abstract EntityAgeable createOwnChild();
    
    @Override
	protected SoundEvent getAmbientSound()
    {
        return ModSounds.cicapteraIdle;
    }

    @Override
	protected SoundEvent getHurtSound(DamageSource p_184601_1_)
    {
        return ModSounds.cicapteraHurt;
    }

    @Override
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
    @Override
	public boolean isBreedingItem(ItemStack stack) {
        return getTemptationItems().test(stack);
    }
    
    public static class Azure extends EntityCicaptera {
        public static final ResourceLocation LOOT = ResourceUtil.getEntityLootTable("cicaptera/azure");

    	public Azure(World worldIn) {
    		super(ModEntities.cicapteraAzure, worldIn);
    	}

    	@Override
    	protected Ingredient getTemptationItems() {
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
    		super(ModEntities.cicapteraVerdant, worldIn);
    	}

    	@Override
    	protected Ingredient getTemptationItems() {
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
    		super(ModEntities.cicapteraCrimson, worldIn);
    	}

    	@Override
    	protected Ingredient getTemptationItems() {
    		return CACTUS;
    	}
    	
    	@Override
        protected void initEntityAI()
        {
            tasks.addTask(0, new EntityAISwimming(this));
            tasks.addTask(1, new AICrimsonLeap(this));
            tasks.addTask(2, new EntityAIAttackMelee(this, 1.2D, false));
            tasks.addTask(3, new EntityAIMate(this, 1.0D));
            tasks.addTask(4, new EntityAITempt(this, 1.2D, false, SEEDS));
            tasks.addTask(5, new EntityAIFollowParent(this, 1.1D));
            tasks.addTask(6, new EntityAIWanderAvoidWater(this, 1.0D));
            tasks.addTask(7, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
            tasks.addTask(8, new EntityAILookIdle(this));
            targetTasks.addTask(1, new EntityAIHurtByTarget(this, true));
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
		    public void tick()
		    {
				EntityLivingBase leapTarget = leaper.getAttackTarget();
				if (leapTarget == null || attacked) return;
				double distance = this.leaper.getDistanceSq(leapTarget.posX, 
						(leapTarget.getBoundingBox().minY + leapTarget.getBoundingBox().maxY) / 2.0, 
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
    		super(ModEntities.cicapteraSandy, worldIn);
    	}

    	@Override
    	protected Ingredient getTemptationItems() {
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
    		super(ModEntities.cicapteraLovely, worldIn);
    	}

    	@Override
    	protected Ingredient getTemptationItems() {
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
    		super(ModEntities.cicapteraLovely, worldIn);
    		resetEssenceTime();
    	}
    	
    	@Override
        public void livingTick() {
    		super.livingTick();

			if (!world.isRemote && !isChild() && --timeUntilNextEssence <= 0) {
				playSound(SoundEvents.ENTITY_CHICKEN_EGG, 1.0F, (rand.nextFloat() - rand.nextFloat()) * 0.2F + 1.0F);
				entityDropItem(ModItems.aphroditeEssence, 1);
				resetEssenceTime();
			}
    	}
    	
    	private void resetEssenceTime() {
    		timeUntilNextEssence = rand.nextInt(ModConfig.cicapteraLovelyEssenceTimeBase) + ModConfig.cicapteraLovelyEssenceTimeExtra;
    	}
    	

    	@Override
		public void readAdditional(NBTTagCompound compound) {
			super.readAdditional(compound);
			if (compound.hasKey("EssenceTime")) timeUntilNextEssence = compound.getInt("EssenceTime");
		}

		@Override
		public void writeAdditional(NBTTagCompound compound) {
			super.writeAdditional(compound);
			compound.setInt("EssenceTime", timeUntilNextEssence);
		}

    	@Override
    	protected Ingredient getTemptationItems() {
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
