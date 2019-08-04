package lykrast.mysticalwildlife.client.render;

import lykrast.mysticalwildlife.client.model.ModelPlumper;
import lykrast.mysticalwildlife.common.entity.EntityPlumper;
import lykrast.mysticalwildlife.common.util.ResourceUtil;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class RenderPlumper extends MobRenderer<EntityPlumper, ModelPlumper<EntityPlumper>> {
	private static final ResourceLocation TEXTURES = ResourceUtil.getEntityTexture("plumper");
	
	public RenderPlumper(EntityRendererManager renderManagerIn)
	{
		super(renderManagerIn, new ModelPlumper<>(), 0.7F);
	}

    @Override
    protected ResourceLocation getEntityTexture(EntityPlumper entity)
    {
        return TEXTURES;
    }

}
