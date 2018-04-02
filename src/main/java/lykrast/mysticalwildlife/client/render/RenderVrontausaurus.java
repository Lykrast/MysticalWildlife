package lykrast.mysticalwildlife.client.render;

import lykrast.mysticalwildlife.client.model.ModelVrontausaurus;
import lykrast.mysticalwildlife.common.entity.EntityVrontausaurus;
import lykrast.mysticalwildlife.core.MysticalWildlife;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderVrontausaurus extends RenderLiving<EntityVrontausaurus> {
	private static final ResourceLocation TEXTURES = new ResourceLocation(MysticalWildlife.MODID, "textures/entity/vrontausaurus.png");
    public static final Factory FACTORY = new Factory();
	
	public RenderVrontausaurus(RenderManager renderManagerIn)
	{
		super(renderManagerIn, new ModelVrontausaurus(), 1.2F);
	}
	
    protected ResourceLocation getEntityTexture(EntityVrontausaurus entity)
    {
        return TEXTURES;
    }

    public static class Factory implements IRenderFactory<EntityVrontausaurus> {

        @Override
        public Render<? super EntityVrontausaurus> createRenderFor(RenderManager manager) {
            return new RenderVrontausaurus(manager);
        }

    }

}
