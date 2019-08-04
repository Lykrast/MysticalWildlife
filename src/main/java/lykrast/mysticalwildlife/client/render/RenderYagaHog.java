package lykrast.mysticalwildlife.client.render;

import lykrast.mysticalwildlife.common.entity.EntityYagaHog;
import lykrast.mysticalwildlife.common.util.ResourceUtil;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.model.PigModel;
import net.minecraft.util.ResourceLocation;

public class RenderYagaHog extends MobRenderer<EntityYagaHog, PigModel<EntityYagaHog>> {
	private static final ResourceLocation NORMAL = ResourceUtil.getEntityTexture("yaga_hog"),
			DIRTY = ResourceUtil.getEntityTexture("yaga_hog_dirt");
	
	public RenderYagaHog(EntityRendererManager renderManagerIn)
	{
		super(renderManagerIn, new PigModel<>(), 0.7F);
        addLayer(new LayerEyesYagaHog<>(this));
	}

    @Override
    protected ResourceLocation getEntityTexture(EntityYagaHog entity)
    {
        return entity.isDirty() ? DIRTY : NORMAL;
    }

}
