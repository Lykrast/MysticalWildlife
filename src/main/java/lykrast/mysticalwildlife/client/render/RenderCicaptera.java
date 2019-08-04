package lykrast.mysticalwildlife.client.render;

import lykrast.mysticalwildlife.client.model.ModelCicaptera;
import lykrast.mysticalwildlife.common.entity.EntityCicaptera;
import lykrast.mysticalwildlife.common.util.ResourceUtil;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.entity.MobEntity;
import net.minecraft.util.ResourceLocation;

public abstract class RenderCicaptera<T extends MobEntity> extends MobRenderer<T, ModelCicaptera<T>> {	
	public RenderCicaptera(EntityRendererManager renderManagerIn) {
		super(renderManagerIn, new ModelCicaptera<>(), 0.4F);
	}

	@Override
	protected float getDeathMaxRotation(T entityLivingBaseIn) {
		return 180.0F;
	}
    
	public static class Azure extends RenderCicaptera<EntityCicaptera.Azure> {
		private static final ResourceLocation TEXTURES = ResourceUtil.getEntityTexture("cicaptera_azure");

		public Azure(EntityRendererManager renderManagerIn) {
			super(renderManagerIn);
		}

		@Override
		protected ResourceLocation getEntityTexture(EntityCicaptera.Azure entity) {
			return TEXTURES;
		}
	}

	public static class Verdant extends RenderCicaptera<EntityCicaptera.Verdant> {
		private static final ResourceLocation TEXTURES = ResourceUtil.getEntityTexture("cicaptera_verdant");

		public Verdant(EntityRendererManager renderManagerIn) {
			super(renderManagerIn);
		}

		@Override
		protected ResourceLocation getEntityTexture(EntityCicaptera.Verdant entity) {
			return TEXTURES;
		}
	}

	public static class Crimson extends RenderCicaptera<EntityCicaptera.Crimson> {
		private static final ResourceLocation TEXTURES = ResourceUtil.getEntityTexture("cicaptera_crimson");

		public Crimson(EntityRendererManager renderManagerIn) {
			super(renderManagerIn);
		}

		@Override
		protected ResourceLocation getEntityTexture(EntityCicaptera.Crimson entity) {
			return TEXTURES;
		}
	}

	public static class Sandy extends RenderCicaptera<EntityCicaptera.Sandy> {
		private static final ResourceLocation TEXTURES = ResourceUtil.getEntityTexture("cicaptera_sandy");

		public Sandy(EntityRendererManager renderManagerIn) {
			super(renderManagerIn);
		}

		@Override
		protected ResourceLocation getEntityTexture(EntityCicaptera.Sandy entity) {
			return TEXTURES;
		}
	}

	public static class Wintry extends RenderCicaptera<EntityCicaptera.Wintry> {
		private static final ResourceLocation TEXTURES = ResourceUtil.getEntityTexture("cicaptera_wintry");

		public Wintry(EntityRendererManager renderManagerIn) {
			super(renderManagerIn);
		}

		@Override
		protected ResourceLocation getEntityTexture(EntityCicaptera.Wintry entity) {
			return TEXTURES;
		}
	}

	public static class Lovely extends RenderCicaptera<EntityCicaptera.Lovely> {
		private static final ResourceLocation TEXTURES = ResourceUtil.getEntityTexture("cicaptera_lovely");

		public Lovely(EntityRendererManager renderManagerIn) {
			super(renderManagerIn);
		}

		@Override
		protected ResourceLocation getEntityTexture(EntityCicaptera.Lovely entity) {
			return TEXTURES;
		}
	}
}
