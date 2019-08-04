package lykrast.mysticalwildlife.client.render;

import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class LayerEyes<T extends Entity, M extends EntityModel<T>> extends LayerEyesAbstract<T, M> {
	private final ResourceLocation texture;

    public LayerEyes(ResourceLocation texture, IEntityRenderer<T, M> render) {
    	super(render);
    	this.texture = texture;
    }

	@Override
	protected ResourceLocation getEyeTexture(T entity) {
		return texture;
	}
}
