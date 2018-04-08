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
public class RenderCicapteraAzure extends RenderLiving<EntityCicaptera> {
	private static final ResourceLocation TEXTURES = ResourceUtil.getEntityTexture("cicaptera_azure");
    public static final Factory FACTORY = new Factory();
	
	public RenderCicapteraAzure(RenderManager renderManagerIn)
	{
		super(renderManagerIn, new ModelCicaptera(), 0.4F);
	}
	
    protected ResourceLocation getEntityTexture(EntityCicaptera entity)
    {
        return TEXTURES;
    }

    @Override
    protected float getDeathMaxRotation(EntityCicaptera entityLivingBaseIn)
    {
        return 180.0F;
    }

    public static class Factory implements IRenderFactory<EntityCicaptera> {

        @Override
        public Render<? super EntityCicaptera> createRenderFor(RenderManager manager) {
            return new RenderCicapteraAzure(manager);
        }

    }

}
