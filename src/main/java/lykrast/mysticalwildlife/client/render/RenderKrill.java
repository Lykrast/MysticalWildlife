package lykrast.mysticalwildlife.client.render;

import lykrast.mysticalwildlife.client.model.ModelKrill;
import lykrast.mysticalwildlife.common.entity.EntityKrill;
import lykrast.mysticalwildlife.common.util.ResourceUtil;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class RenderKrill extends MobRenderer<EntityKrill, ModelKrill<EntityKrill>> {
	private static final ResourceLocation TEXTURES = ResourceUtil.getEntityTexture("krill");
	
	public RenderKrill(EntityRendererManager renderManagerIn) {
		super(renderManagerIn, new ModelKrill<>(), 0.45F);
	}

    @Override
	protected float getDeathMaxRotation(EntityKrill entityLivingBaseIn) {
        return 180.0F;
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityKrill entity) {
        return TEXTURES;
    }

}
