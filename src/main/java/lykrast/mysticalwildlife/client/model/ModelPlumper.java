package lykrast.mysticalwildlife.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * plumper - Lykrast
 * Created using Tabula 7.0.0
 */
@SideOnly(Side.CLIENT)
public class ModelPlumper extends ModelBase {
    protected float childYOffset = 0.0F;
    protected float childZOffset = 4.0F;
    
    public ModelRenderer body;
    public ModelRenderer head;
    public ModelRenderer tail;
    public ModelRenderer finLeft;
    public ModelRenderer finRight;

    public ModelPlumper() {
        this.textureWidth = 64;
        this.textureHeight = 48;
        this.tail = new ModelRenderer(this, 32, 16);
        this.tail.setRotationPoint(0.0F, 22.0F, 6.0F);
        this.tail.addBox(-6.0F, 0.0F, -2.0F, 12, 8, 4, 0.0F);
        this.setRotateAngle(tail, 1.5707963267948966F, 0.0F, 0.0F);
        this.finRight = new ModelRenderer(this, 0, 38);
        this.finRight.mirror = true;
        this.finRight.setRotationPoint(-5.0F, 21.0F, 0.0F);
        this.finRight.addBox(-1.0F, 0.0F, -2.0F, 1, 6, 4, 0.0F);
        this.setRotateAngle(finRight, 0.0F, 0.0F, 1.0471975511965976F);
        this.body = new ModelRenderer(this, 28, 28);
        this.body.setRotationPoint(0.0F, 20.0F, 0.0F);
        this.body.addBox(-5.0F, -6.0F, -4.0F, 10, 12, 8, 0.0F);
        this.setRotateAngle(body, 1.5707963267948966F, 0.0F, 0.0F);
        this.head = new ModelRenderer(this, 0, 0);
        this.head.setRotationPoint(0.0F, 19.0F, -5.0F);
        this.head.addBox(-4.0F, -4.0F, -8.0F, 8, 8, 8, 0.0F);
        this.finLeft = new ModelRenderer(this, 0, 38);
        this.finLeft.setRotationPoint(5.0F, 21.0F, 0.0F);
        this.finLeft.addBox(0.0F, 0.0F, -2.0F, 1, 6, 4, 0.0F);
        this.setRotateAngle(finLeft, 0.0F, 0.0F, -1.0471975511965976F);
    }

    @Override
    public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) { 
        this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityIn);

        if (this.isChild)
        {
            GlStateManager.pushMatrix();
            GlStateManager.translate(0.0F, this.childYOffset * scale, this.childZOffset * scale);
            this.head.render(scale);
            GlStateManager.popMatrix();
            GlStateManager.pushMatrix();
            GlStateManager.scale(0.5F, 0.5F, 0.5F);
            GlStateManager.translate(0.0F, 24.0F * scale, 0.0F);
            this.body.render(scale);
            this.tail.render(scale);
            this.finLeft.render(scale);
            this.finRight.render(scale);
            GlStateManager.popMatrix();
        }
        else
        {
            this.head.render(scale);
            this.body.render(scale);
            this.tail.render(scale);
            this.finLeft.render(scale);
            this.finRight.render(scale);
        }
    }

    /**
     * Sets the model's various rotation angles. For bipeds, par1 and par2 are used for animating the movement of arms
     * and legs, where par1 represents the time(so that arms and legs swing back and forth) and par2 represents how
     * "far" arms and legs can swing at most.
     */
    @Override
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn)
    {
        this.head.rotateAngleX = headPitch * 0.017453292F;
        this.head.rotateAngleY = netHeadYaw * 0.017453292F;
//        this.body.rotateAngleX = ((float)Math.PI / 2F);
        this.finLeft.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 2.8F * limbSwingAmount;
        this.finRight.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 2.8F * limbSwingAmount;
    }

    /**
     * This is a helper function from Tabula to set the rotation of model parts
     */
    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
