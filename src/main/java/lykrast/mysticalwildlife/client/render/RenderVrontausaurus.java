package lykrast.mysticalwildlife.client.render;

import lykrast.mysticalwildlife.client.model.ModelVrontausaurus;
import lykrast.mysticalwildlife.common.entity.EntityVrontausaurus;
import lykrast.mysticalwildlife.common.util.ResourceUtil;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderVrontausaurus extends RenderLiving<EntityVrontausaurus> {
	private static final ResourceLocation TEXTURES = ResourceUtil.getEntityTexture("vrontausaurus");
	
	public RenderVrontausaurus(RenderManager renderManagerIn)
	{
		super(renderManagerIn, new ModelVrontausaurus(), 1.2F);
	}

    @Override
    protected ResourceLocation getEntityTexture(EntityVrontausaurus entity)
    {
        return TEXTURES;
    }

}
