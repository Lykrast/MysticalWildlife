package lykrast.mysticalwildlife.common.entity;

import javax.annotation.Nullable;

import lykrast.mysticalwildlife.common.init.ModEntities;
import lykrast.mysticalwildlife.common.init.ModItems;
import lykrast.mysticalwildlife.common.init.ModSounds;
import lykrast.mysticalwildlife.common.util.ConfigHandler;
import lykrast.mysticalwildlife.common.util.ResourceUtil;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.CreatureAttribute;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.goal.BreedGoal;
import net.minecraft.entity.ai.goal.FollowParentGoal;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.LeapAtTargetGoal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.PanicGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.TemptGoal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.Difficulty;
import net.minecraft.world.World;

public abstract class EntityCicaptera extends AnimalEntity {
    private static final Ingredient SEEDS = Ingredient.fromItems(Items.WHEAT_SEEDS, Items.BEETROOT_SEEDS, Items.MELON_SEEDS, Items.PUMPKIN_SEEDS);
    private static final Ingredient FRUITS = Ingredient.fromItems(Items.APPLE);
    private static final Ingredient CACTUS = Ingredient.fromItems(Blocks.CACTUS, Items.GREEN_DYE);
    private static final Ingredient SUGAR = Ingredient.fromItems(Items.SUGAR);
	
	public EntityCicaptera(EntityType<? extends EntityCicaptera> type, World worldIn) {
		super(type, worldIn);
	}
	
	protected abstract Ingredient getTemptationItems();

    @Override
	protected void registerGoals() {
    	goalSelector.addGoal(0, new SwimGoal(this));
    	goalSelector.addGoal(1, new PanicGoal(this, 1.25D));
        goalSelector.addGoal(3, new BreedGoal(this, 1.0D, EntityCicaptera.class));
        goalSelector.addGoal(4, new TemptGoal(this, 1.2D, false, getTemptationItems()));
        goalSelector.addGoal(5, new FollowParentGoal(this, 1.1D));
        goalSelector.addGoal(6, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
        goalSelector.addGoal(7, new LookAtGoal(this, PlayerEntity.class, 6.0F));
        goalSelector.addGoal(8, new LookRandomlyGoal(this));
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

        Vec3d motion = getMotion();
        if (!onGround && motion.y < 0.0D) setMotion(motion.mul(1.0D, 0.6D, 1.0D));
    }

    @Override
	public void fall(float distance, float damageMultiplier) {}

	@Override
	public AgeableEntity createChild(AgeableEntity ageable) {
		if (ageable instanceof EntityCicaptera && rand.nextBoolean()) return ((EntityCicaptera) ageable).createOwnChild();
		else return createOwnChild();
	}

	@Override
    public boolean canMateWith(AnimalEntity otherAnimal)
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
	
	protected abstract AgeableEntity createOwnChild();
    
	@Override
	protected SoundEvent getAmbientSound() {
		return ModSounds.cicapteraIdle;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource p_184601_1_) {
		return ModSounds.cicapteraHurt;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return ModSounds.cicapteraDeath;
	}

	@Override
	protected void playStepSound(BlockPos pos, BlockState blockIn) {
		playSound(SoundEvents.ENTITY_CHICKEN_STEP, 0.15F, 1.0F);
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

    	public Azure(EntityType<? extends Azure> type, World worldIn) {
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
    	protected AgeableEntity createOwnChild() {
    		return ModEntities.cicapteraAzure.create(world);
    	}
    }
    
    public static class Verdant extends EntityCicaptera {
        public static final ResourceLocation LOOT = ResourceUtil.getEntityLootTable("cicaptera/verdant");

    	public Verdant(EntityType<? extends Verdant> type, World worldIn) {
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
    	protected AgeableEntity createOwnChild() {
    		return ModEntities.cicapteraVerdant.create(world);
    	}
    }
    
    public static class Crimson extends EntityCicaptera {
        public static final ResourceLocation LOOT = ResourceUtil.getEntityLootTable("cicaptera/crimson");

    	public Crimson(EntityType<? extends Crimson> type, World worldIn) {
    		super(ModEntities.cicapteraCrimson, worldIn);
    	}

    	@Override
    	protected Ingredient getTemptationItems() {
    		return CACTUS;
    	}
    	
    	@Override
        protected void registerGoals() {
            goalSelector.addGoal(0, new SwimGoal(this));
            goalSelector.addGoal(1, new AICrimsonLeap(this));
            goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.2D, false));
            goalSelector.addGoal(3, new BreedGoal(this, 1.0D, EntityCicaptera.class));
            goalSelector.addGoal(4, new TemptGoal(this, 1.2D, false, SEEDS));
            goalSelector.addGoal(5, new FollowParentGoal(this, 1.1D));
            goalSelector.addGoal(6, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
            goalSelector.addGoal(7, new LookAtGoal(this, PlayerEntity.class, 6.0F));
            goalSelector.addGoal(8, new LookRandomlyGoal(this));
            targetSelector.addGoal(1, new HurtByTargetGoal(this));
        }
        
        @Override
        public boolean attackEntityAsMob(Entity entityIn)
        {
        	boolean flag = entityIn.attackEntityFrom(DamageSource.causeMobDamage(this), 3.0F);
        	
        	Vec3d motion = getMotion();
            if (flag) entityIn.setMotion(entityIn.getMotion().add(motion));
            setMotion(motion.mul(-1, -1, -1));
        	
        	return flag;
        }

        @Override
        protected void updateAITasks()
        {
        	if (world.getDifficulty() == Difficulty.PEACEFUL) {
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
    	protected AgeableEntity createOwnChild() {
    		return ModEntities.cicapteraCrimson.create(world);
    	}
    	
    	private static class AICrimsonLeap extends LeapAtTargetGoal {
    		//Damn it package private access
    		private MobEntity leaper;
    		//Only attack once per leap
    	    private boolean attacked;
    		
			public AICrimsonLeap(MobEntity leapingEntity) {
				super(leapingEntity, 0.5F);
				this.leaper = leapingEntity;
			}
			
			@Override
		    public void startExecuting() {
				attacked = false;
				LivingEntity leapTarget = leaper.getAttackTarget();
				if (leapTarget == null) return;
				
		        double d0 = leapTarget.posX - this.leaper.posX;
		        double d1 = leapTarget.posZ - this.leaper.posZ;
		        float f = MathHelper.sqrt(d0 * d0 + d1 * d1);

		        Vec3d motion = leaper.getMotion();
		        double x, z;
		        if ((double)f >= 1.0E-4D) {
		        	x = d0 / f * 0.8D + motion.x * 1.2;
		        	z = d0 / f * 0.8D + motion.z * 1.2;
		        }
		        else {
		        	x = motion.x;
		        	z = motion.z;
		        }
		        leaper.setMotion(x, 0.5, z);
		    }
			
			@Override
		    public void tick() {
				LivingEntity leapTarget = leaper.getAttackTarget();
				if (leapTarget == null || attacked) return;
				double distance = this.leaper.getDistanceSq(leapTarget.posX, 
						(leapTarget.getBoundingBox().minY + leapTarget.getBoundingBox().maxY) / 2.0, 
						leapTarget.posZ);
				double range = (double)(leaper.getWidth() * leaper.getWidth() + leapTarget.getWidth());
				
				if (distance <= range) {
					attacked = true;
					leaper.attackEntityAsMob(leapTarget);
				}
		    }
    	}
    }
    
    public static class Sandy extends EntityCicaptera {
        public static final ResourceLocation LOOT = ResourceUtil.getEntityLootTable("cicaptera/sandy");

    	public Sandy(EntityType<? extends Sandy> type, World worldIn) {
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
    	protected AgeableEntity createOwnChild() {
    		return ModEntities.cicapteraSandy.create(world);
    	}
    }
    
    public static class Wintry extends EntityCicaptera {
        public static final ResourceLocation LOOT = ResourceUtil.getEntityLootTable("cicaptera/wintry");

    	public Wintry(EntityType<? extends Wintry> type, World worldIn) {
    		super(ModEntities.cicapteraWintry, worldIn);
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
    	protected AgeableEntity createOwnChild() {
    		return ModEntities.cicapteraWintry.create(world);
    	}
    }
    
    public static class Lovely extends EntityCicaptera {
        public static final ResourceLocation LOOT = ResourceUtil.getEntityLootTable("cicaptera/lovely");
        public int timeUntilNextEssence;

    	public Lovely(EntityType<? extends Lovely> type, World worldIn) {
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
    		int base = ConfigHandler.Common.CONFIG.cicapteraLovelyEssenceTime.get();
    		timeUntilNextEssence = rand.nextInt(base) + base;
    	}
    	

    	@Override
		public void readAdditional(CompoundNBT compound) {
			super.readAdditional(compound);
			if (compound.contains("EssenceTime")) timeUntilNextEssence = compound.getInt("EssenceTime");
		}

		@Override
		public void writeAdditional(CompoundNBT compound) {
			super.writeAdditional(compound);
			compound.putInt("EssenceTime", timeUntilNextEssence);
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
    	protected AgeableEntity createOwnChild() {
    		return ModEntities.cicapteraLovely.create(world);
    	}
    }
}
