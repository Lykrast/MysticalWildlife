package lykrast.mysticalwildlife.client.render;

import lykrast.mysticalwildlife.client.model.ModelCicaptera;
import lykrast.mysticalwildlife.common.entity.EntityCicaptera;
import lykrast.mysticalwildlife.common.util.ResourceUtil;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public abstract class RenderCicaptera extends RenderLiving<EntityCicaptera> {	
	public RenderCicaptera(RenderManager renderManagerIn)
	{
		super(renderManagerIn, new ModelCicaptera(), 0.4F);
	}

    @Override
    protected float getDeathMaxRotation(EntityCicaptera entityLivingBaseIn)
    {
        return 180.0F;
    }
    
    public static class Azure extends RenderCicaptera {
    	private static final ResourceLocation TEXTURES = ResourceUtil.getEntityTexture("cicaptera_azure");
        public static final Factory FACTORY = new Factory();
    	
    	public Azure(RenderManager renderManagerIn)
    	{
    		super(renderManagerIn);
    	}
    	
        protected ResourceLocation getEntityTexture(EntityCicaptera entity)
        {
            return TEXTURES;
        }
        
        public static class Factory implements IRenderFactory<EntityCicaptera> {
            @Override
            public Render<? super EntityCicaptera> createRenderFor(RenderManager manager) {
                return new Azure(manager);
            }
        }
    }
    
    public static class Verdant extends RenderCicaptera {
    	private static final ResourceLocation TEXTURES = ResourceUtil.getEntityTexture("cicaptera_verdant");
        public static final Factory FACTORY = new Factory();
    	
    	public Verdant(RenderManager renderManagerIn)
    	{
    		super(renderManagerIn);
    	}
    	
        protected ResourceLocation getEntityTexture(EntityCicaptera entity)
        {
            return TEXTURES;
        }
        
        public static class Factory implements IRenderFactory<EntityCicaptera> {
            @Override
            public Render<? super EntityCicaptera> createRenderFor(RenderManager manager) {
                return new Verdant(manager);
            }
        }
    }
    
    public static class Crimson extends RenderCicaptera {
    	private static final ResourceLocation TEXTURES = ResourceUtil.getEntityTexture("cicaptera_crimson");
        public static final Factory FACTORY = new Factory();
    	
    	public Crimson(RenderManager renderManagerIn)
    	{
    		super(renderManagerIn);
    	}
    	
        protected ResourceLocation getEntityTexture(EntityCicaptera entity)
        {
            return TEXTURES;
        }
        
        public static class Factory implements IRenderFactory<EntityCicaptera> {
            @Override
            public Render<? super EntityCicaptera> createRenderFor(RenderManager manager) {
                return new Crimson(manager);
            }
        }
    }
    
    public static class Sandy extends RenderCicaptera {
    	private static final ResourceLocation TEXTURES = ResourceUtil.getEntityTexture("cicaptera_sandy");
        public static final Factory FACTORY = new Factory();
    	
    	public Sandy(RenderManager renderManagerIn)
    	{
    		super(renderManagerIn);
    	}
    	
        protected ResourceLocation getEntityTexture(EntityCicaptera entity)
        {
            return TEXTURES;
        }
        
        public static class Factory implements IRenderFactory<EntityCicaptera> {
            @Override
            public Render<? super EntityCicaptera> createRenderFor(RenderManager manager) {
                return new Sandy(manager);
            }
        }
    }
    
    public static class Wintry extends RenderCicaptera {
    	private static final ResourceLocation TEXTURES = ResourceUtil.getEntityTexture("cicaptera_wintry");
        public static final Factory FACTORY = new Factory();
    	
    	public Wintry(RenderManager renderManagerIn)
    	{
    		super(renderManagerIn);
    	}
    	
        protected ResourceLocation getEntityTexture(EntityCicaptera entity)
        {
            return TEXTURES;
        }
        
        public static class Factory implements IRenderFactory<EntityCicaptera> {
            @Override
            public Render<? super EntityCicaptera> createRenderFor(RenderManager manager) {
                return new Wintry(manager);
            }
        }
    }
}
