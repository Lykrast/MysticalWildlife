package lykrast.mysticalwildlife.common.entity;

import java.util.Set;

import javax.annotation.Nullable;

import com.google.common.collect.Sets;

import lykrast.mysticalwildlife.common.init.ModSounds;
import lykrast.mysticalwildlife.common.util.ResourceUtil;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIFollowParent;
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
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public abstract class EntityCicaptera extends EntityAnimal {
    private static final Set<Item> TEMPTATION_ITEMS = Sets.newHashSet(Items.WHEAT_SEEDS, Items.BEETROOT_SEEDS, Items.MELON_SEEDS, Items.PUMPKIN_SEEDS);
	
	public EntityCicaptera(World worldIn)
	{
		super(worldIn);
        this.setSize(0.9F, 0.4F);
	}

    protected void initEntityAI()
    {
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(1, new EntityAIPanic(this, 1.25D));
        this.tasks.addTask(3, new EntityAIMate(this, 1.0D));
        this.tasks.addTask(4, new EntityAITempt(this, 1.2D, false, TEMPTATION_ITEMS));
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
        return TEMPTATION_ITEMS.contains(stack.getItem());
    }
    
    public static class Azure extends EntityCicaptera {
        public static final ResourceLocation LOOT = ResourceUtil.getEntityLootTable("cicaptera/azure");

    	public Azure(World worldIn) {
    		super(worldIn);
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

        //TODO: Crimson are hostile
    	public Crimson(World worldIn) {
    		super(worldIn);
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
    }
    
    public static class Sandy extends EntityCicaptera {
        public static final ResourceLocation LOOT = ResourceUtil.getEntityLootTable("cicaptera/sandy");

    	public Sandy(World worldIn) {
    		super(worldIn);
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
        @Nullable
        protected ResourceLocation getLootTable() {
            return LOOT;
        }

    	@Override
    	protected EntityAgeable createOwnChild() {
    		return new Wintry(world);
    	}
    }
}
