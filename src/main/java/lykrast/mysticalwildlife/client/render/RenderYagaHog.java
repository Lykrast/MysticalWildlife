package lykrast.mysticalwildlife.client.render;

import lykrast.mysticalwildlife.common.entity.EntityYagaHog;
import lykrast.mysticalwildlife.common.util.ResourceUtil;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.model.ModelPig;
import net.minecraft.util.ResourceLocation;

public class RenderYagaHog extends RenderLiving<EntityYagaHog> {
	private static final ResourceLocation NORMAL = ResourceUtil.getEntityTexture("yaga_hog"),
			DIRTY = ResourceUtil.getEntityTexture("yaga_hog_dirt");
	
	public RenderYagaHog(RenderManager renderManagerIn)
	{
		super(renderManagerIn, new ModelPig(), 0.7F);
        this.addLayer(new LayerEyesYagaHog(this));
	}

    @Override
    protected ResourceLocation getEntityTexture(EntityYagaHog entity)
    {
        return entity.isDirty() ? DIRTY : NORMAL;
    }

}
