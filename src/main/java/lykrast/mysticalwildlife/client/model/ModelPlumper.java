package lykrast.mysticalwildlife.client.model;

import com.mojang.blaze3d.platform.GlStateManager;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

/**
 * plumper - Lykrast
 * Created using Tabula 7.0.0
 */
public class ModelPlumper<T extends Entity> extends EntityModel<T> {
    protected float childYOffset = 0.0F;
    protected float childZOffset = 4.0F;
    
    public RendererModel body;
    public RendererModel head;
    public RendererModel tail;
    public RendererModel finLeft;
    public RendererModel finRight;

    public ModelPlumper() {
        textureWidth = 64;
        textureHeight = 48;
        tail = new RendererModel(this, 32, 16);
        tail.setRotationPoint(0.0F, 22.0F, 6.0F);
        tail.addBox(-6.0F, 0.0F, -2.0F, 12, 8, 4, 0.0F);
        setRotateAngle(tail, 1.5707963267948966F, 0.0F, 0.0F);
        finRight = new RendererModel(this, 0, 38);
        finRight.mirror = true;
        finRight.setRotationPoint(-5.0F, 21.0F, 0.0F);
        finRight.addBox(-1.0F, 0.0F, -2.0F, 1, 6, 4, 0.0F);
        setRotateAngle(finRight, 0.0F, 0.0F, 1.0471975511965976F);
        body = new RendererModel(this, 28, 28);
        body.setRotationPoint(0.0F, 20.0F, 0.0F);
        body.addBox(-5.0F, -6.0F, -4.0F, 10, 12, 8, 0.0F);
        setRotateAngle(body, 1.5707963267948966F, 0.0F, 0.0F);
        head = new RendererModel(this, 0, 0);
        head.setRotationPoint(0.0F, 19.0F, -5.0F);
        head.addBox(-4.0F, -4.0F, -8.0F, 8, 8, 8, 0.0F);
        finLeft = new RendererModel(this, 0, 38);
        finLeft.setRotationPoint(5.0F, 21.0F, 0.0F);
        finLeft.addBox(0.0F, 0.0F, -2.0F, 1, 6, 4, 0.0F);
        setRotateAngle(finLeft, 0.0F, 0.0F, -1.0471975511965976F);
    }

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
            tail.render(scale);
            finLeft.render(scale);
            finRight.render(scale);
            GlStateManager.popMatrix();
        }
        else {
            head.render(scale);
            body.render(scale);
            tail.render(scale);
            finLeft.render(scale);
            finRight.render(scale);
        }
    }
    
    @Override
	public void setRotationAngles(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor) {
        head.rotateAngleX = headPitch * 0.017453292F;
        head.rotateAngleY = netHeadYaw * 0.017453292F;
//        body.rotateAngleX = ((float)Math.PI / 2F);
        finLeft.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 2.8F * limbSwingAmount;
        finRight.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 2.8F * limbSwingAmount;
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
