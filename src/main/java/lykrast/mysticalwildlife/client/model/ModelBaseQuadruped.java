package lykrast.mysticalwildlife.client.model;

import com.mojang.blaze3d.platform.GlStateManager;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

/**
 * This is to simplify animation on my Tabula models. If you're wondering why not directly use ModelQuadruped, then I don't know either.
 */
public class ModelBaseQuadruped<T extends Entity> extends EntityModel<T> {
    protected float childYOffset = 8.0F;
    protected float childZOffset = 4.0F;
    
	public RendererModel body;
	public RendererModel head;
	public RendererModel legRightBack;
	public RendererModel legLeftBack;
	public RendererModel legLeftFront;
	public RendererModel legRightFront;
	
    /**
     * Sets the models various rotation angles then renders the model.
     */
    @Override
	public void render(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        setRotationAngles(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);

        if (isChild) {
            GlStateManager.pushMatrix();
            GlStateManager.translatef(0.0F, childYOffset * scale, childZOffset * scale);
            head.render(scale);
            GlStateManager.popMatrix();
            GlStateManager.pushMatrix();
            GlStateManager.scalef(0.5F, 0.5F, 0.5F);
            GlStateManager.translatef(0.0F, 24.0F * scale, 0.0F);
            body.render(scale);
            legRightBack.render(scale);
            legLeftBack.render(scale);
            legRightFront.render(scale);
            legLeftFront.render(scale);
            GlStateManager.popMatrix();
        }
        else {
            head.render(scale);
            body.render(scale);
            legRightBack.render(scale);
            legLeftBack.render(scale);
            legRightFront.render(scale);
            legLeftFront.render(scale);
        }
    }

    @Override
	public void setRotationAngles(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor) {
        head.rotateAngleX = headPitch * 0.017453292F;
        head.rotateAngleY = netHeadYaw * 0.017453292F;
        body.rotateAngleX = ((float)Math.PI / 2F);
        legRightBack.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        legLeftBack.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
        legRightFront.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
        legLeftFront.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
    }

	/**
	 * This is a helper function from Tabula to set the rotation of model parts
	 */
	public void setRotateAngle(RendererModel RendererModel, float x, float y, float z) {
		RendererModel.rotateAngleX = x;
		RendererModel.rotateAngleY = y;
		RendererModel.rotateAngleZ = z;
	}
}
