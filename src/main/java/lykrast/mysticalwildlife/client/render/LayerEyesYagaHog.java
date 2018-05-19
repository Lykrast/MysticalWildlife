package lykrast.mysticalwildlife.client.render;

import lykrast.mysticalwildlife.common.entity.EntityYagaHog;
import lykrast.mysticalwildlife.common.util.ResourceUtil;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class LayerEyesYagaHog extends LayerEyesAbstract<EntityYagaHog> {
	private static final ResourceLocation NORMAL = ResourceUtil.getEntityTexture("yaga_hog_eyes"),
			DIRTY = ResourceUtil.getEntityTexture("yaga_hog_dirt_eyes");

    public LayerEyesYagaHog(RenderLiving<EntityYagaHog> render) {
    	super(render);
    }

	@Override
	protected ResourceLocation getEyeTexture(EntityYagaHog entity) {
		return entity.isDirty() ? DIRTY : NORMAL;
	}
}
