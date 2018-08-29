package lykrast.mysticalwildlife.client.render;

import lykrast.mysticalwildlife.client.model.ModelKrill;
import lykrast.mysticalwildlife.common.entity.EntityKrill;
import lykrast.mysticalwildlife.common.util.ResourceUtil;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderKrill extends RenderLiving<EntityKrill> {
	private static final ResourceLocation TEXTURES = ResourceUtil.getEntityTexture("krill");
	
	public RenderKrill(RenderManager renderManagerIn) {
		super(renderManagerIn, new ModelKrill(), 0.45F);
	}

    protected float getDeathMaxRotation(EntityKrill entityLivingBaseIn) {
        return 180.0F;
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityKrill entity) {
        return TEXTURES;
    }

}
