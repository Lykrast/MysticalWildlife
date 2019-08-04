package lykrast.mysticalwildlife.client.render;

import lykrast.mysticalwildlife.client.model.ModelDuskLurker;
import lykrast.mysticalwildlife.common.entity.EntityDuskLurker;
import lykrast.mysticalwildlife.common.util.ResourceUtil;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderDuskLurker extends RenderLiving<EntityDuskLurker> {
	private static final ResourceLocation TEXTURES = ResourceUtil.getEntityTexture("dusk_lurker"),
			EYES = ResourceUtil.getEntityTexture("dusk_lurker_eyes");
	
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

}
