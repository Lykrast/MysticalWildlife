package lykrast.mysticalwildlife.client.render;

import lykrast.mysticalwildlife.client.model.ModelPlumper;
import lykrast.mysticalwildlife.common.entity.EntityPlumper;
import lykrast.mysticalwildlife.common.util.ResourceUtil;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderPlumper extends RenderLiving<EntityPlumper> {
	private static final ResourceLocation TEXTURES = ResourceUtil.getEntityTexture("plumper");
	
	public RenderPlumper(RenderManager renderManagerIn)
	{
		super(renderManagerIn, new ModelPlumper(), 0.7F);
	}

    @Override
    protected ResourceLocation getEntityTexture(EntityPlumper entity)
    {
        return TEXTURES;
    }

}
