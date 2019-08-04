package lykrast.mysticalwildlife.client.render;

import lykrast.mysticalwildlife.client.model.ModelDuskLurker;
import lykrast.mysticalwildlife.common.entity.EntityDuskLurker;
import lykrast.mysticalwildlife.common.util.ResourceUtil;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class RenderDuskLurker extends MobRenderer<EntityDuskLurker, ModelDuskLurker<EntityDuskLurker>> {
	private static final ResourceLocation TEXTURES = ResourceUtil.getEntityTexture("dusk_lurker"),
			EYES = ResourceUtil.getEntityTexture("dusk_lurker_eyes");
	
	public RenderDuskLurker(EntityRendererManager renderManagerIn)
	{
		super(renderManagerIn, new ModelDuskLurker<>(), 0.7F);
        addLayer(new LayerEyes<>(EYES, this));
	}

    @Override
    protected ResourceLocation getEntityTexture(EntityDuskLurker entity)
    {
        return TEXTURES;
    }

}
