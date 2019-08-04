package lykrast.mysticalwildlife.client.render;

import lykrast.mysticalwildlife.client.model.ModelVrontausaurus;
import lykrast.mysticalwildlife.common.entity.EntityVrontausaurus;
import lykrast.mysticalwildlife.common.util.ResourceUtil;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class RenderVrontausaurus extends MobRenderer<EntityVrontausaurus, ModelVrontausaurus<EntityVrontausaurus>> {
	private static final ResourceLocation TEXTURES = ResourceUtil.getEntityTexture("vrontausaurus");
	
	public RenderVrontausaurus(EntityRendererManager renderManagerIn)
	{
		super(renderManagerIn, new ModelVrontausaurus<>(), 1.2F);
	}

    @Override
    protected ResourceLocation getEntityTexture(EntityVrontausaurus entity)
    {
        return TEXTURES;
    }

}
