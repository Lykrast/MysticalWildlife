package lykrast.mysticalwildlife.client.render;

import lykrast.mysticalwildlife.client.model.ModelDuskLurker;
import lykrast.mysticalwildlife.common.entity.EntityDuskLurker;
import lykrast.mysticalwildlife.common.util.ResourceUtil;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderDuskLurker extends RenderLiving<EntityDuskLurker> {
	private static final ResourceLocation TEXTURES = ResourceUtil.getEntityTexture("dusk_lurker"),
			EYES = ResourceUtil.getEntityTexture("dusk_lurker_eyes");
    public static final Factory FACTORY = new Factory();
	
	public RenderDuskLurker(RenderManager renderManagerIn)
	{
		super(renderManagerIn, new ModelDuskLurker(), 0.7F);
        this.addLayer(new LayerEyes<EntityDuskLurker>(EYES, this));
	}

    @Override
    protected ResourceLocation getEntityTexture(EntityDuskLurker entity)
    {
        return TEXTURES;
    }

    public static class Factory implements IRenderFactory<EntityDuskLurker> {

        @Override
        public Render<? super EntityDuskLurker> createRenderFor(RenderManager manager) {
            return new RenderDuskLurker(manager);
        }

    }

}
