package lykrast.mysticalwildlife.client.render;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.ResourceLocation;

public class LayerEyes<T extends EntityLiving> extends LayerEyesAbstract<T> {
	private final ResourceLocation texture;

    public LayerEyes(ResourceLocation texture, RenderLiving<T> render) {
    	super(render);
    	this.texture = texture;
    }

	@Override
	protected ResourceLocation getEyeTexture(T entity) {
		return texture;
	}
}
