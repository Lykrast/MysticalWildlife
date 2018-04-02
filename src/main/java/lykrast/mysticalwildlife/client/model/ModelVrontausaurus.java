package lykrast.mysticalwildlife.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

/**
 * vrontausurus2 - Lykrast Created using Tabula 7.0.0
 */
public class ModelVrontausaurus extends ModelBase {
    protected float childYOffset = 8.0F;
    protected float childZOffset = 7.0F;
    
	public ModelRenderer body;
	public ModelRenderer legRightBack;
	public ModelRenderer legLeftBack;
	public ModelRenderer head;
	public ModelRenderer legLeftFront;
	public ModelRenderer leftRightFront;
	public ModelRenderer plateLowerFront;
	public ModelRenderer plateLowerBack;
	public ModelRenderer plateUpperFront;
	public ModelRenderer plateUpperBack;
	public ModelRenderer snout;

	public ModelVrontausaurus() {
		this.textureWidth = 128;
		this.textureHeight = 64;
		this.leftRightFront = new ModelRenderer(this, 0, 38);
		this.leftRightFront.mirror = true;
		this.leftRightFront.setRotationPoint(-12.0F, 6.0F, -4.0F);
		this.leftRightFront.addBox(-4.0F, 0.0F, -4.0F, 8, 18, 8, 0.0F);
		this.body = new ModelRenderer(this, 68, 18);
		this.body.setRotationPoint(0.0F, 8.0F, 0.0F);
		this.body.addBox(-8.0F, -12.0F, -9.0F, 16, 32, 14, 0.0F);
		this.setRotateAngle(body, 1.5707963267948966F, 0.0F, 0.0F);
		this.legLeftFront = new ModelRenderer(this, 0, 38);
		this.legLeftFront.setRotationPoint(12.0F, 6.0F, -4.0F);
		this.legLeftFront.addBox(-4.0F, 0.0F, -4.0F, 8, 18, 8, 0.0F);
		this.plateLowerBack = new ModelRenderer(this, 58, 55);
		this.plateLowerBack.setRotationPoint(0.0F, 12.0F, 5.0F);
		this.plateLowerBack.addBox(-2.0F, -4.0F, 0.0F, 4, 8, 1, 0.0F);
		this.legLeftBack = new ModelRenderer(this, 0, 38);
		this.legLeftBack.setRotationPoint(12.0F, 6.0F, 12.0F);
		this.legLeftBack.addBox(-4.0F, 0.0F, -4.0F, 8, 18, 8, 0.0F);
		this.plateLowerFront = new ModelRenderer(this, 58, 55);
		this.plateLowerFront.setRotationPoint(0.0F, -4.0F, 5.0F);
		this.plateLowerFront.addBox(-2.0F, -4.0F, 0.0F, 4, 8, 1, 0.0F);
		this.legRightBack = new ModelRenderer(this, 0, 38);
		this.legRightBack.mirror = true;
		this.legRightBack.setRotationPoint(-12.0F, 6.0F, 12.0F);
		this.legRightBack.addBox(-4.0F, 0.0F, -4.0F, 8, 18, 8, 0.0F);
		this.head = new ModelRenderer(this, 0, 0);
		this.head.setRotationPoint(0.0F, 7.0F, -12.0F);
		this.head.addBox(-6.0F, -5.0F, -8.0F, 12, 10, 8, 0.0F);
		this.plateUpperFront = new ModelRenderer(this, 60, 46);
		this.plateUpperFront.setRotationPoint(0.0F, 0.0F, 1.0F);
		this.plateUpperFront.addBox(-0.5F, -3.0F, 0.0F, 1, 6, 3, 0.0F);
		this.plateUpperBack = new ModelRenderer(this, 60, 46);
		this.plateUpperBack.setRotationPoint(0.0F, 0.0F, 1.0F);
		this.plateUpperBack.addBox(-0.5F, -3.0F, 0.0F, 1, 6, 3, 0.0F);
		this.snout = new ModelRenderer(this, 0, 18);
		this.snout.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.snout.addBox(-4.0F, -2.0F, -16.0F, 8, 6, 8, 0.0F);
		this.body.addChild(this.plateLowerBack);
		this.body.addChild(this.plateLowerFront);
		this.plateLowerFront.addChild(this.plateUpperFront);
		this.plateLowerBack.addChild(this.plateUpperBack);
		this.head.addChild(this.snout);
	}

//	@Override
//	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
//		this.leftRightFront.render(f5);
//		this.body.render(f5);
//		this.legLeftFront.render(f5);
//		this.legLeftBack.render(f5);
//		this.legRightBack.render(f5);
//		this.head.render(f5);
//	}

	//Copied from ModelQuadruped
	//TODO: generalize this shit or fucking use ModelQuadruped
    /**
     * Sets the models various rotation angles then renders the model.
     */
    public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale)
    {
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
            this.legRightBack.render(scale);
            this.legLeftBack.render(scale);
            this.leftRightFront.render(scale);
            this.legLeftFront.render(scale);
            GlStateManager.popMatrix();
        }
        else
        {
            this.head.render(scale);
            this.body.render(scale);
            this.legRightBack.render(scale);
            this.legLeftBack.render(scale);
            this.leftRightFront.render(scale);
            this.legLeftFront.render(scale);
        }
    }

    /**
     * Sets the model's various rotation angles. For bipeds, par1 and par2 are used for animating the movement of arms
     * and legs, where par1 represents the time(so that arms and legs swing back and forth) and par2 represents how
     * "far" arms and legs can swing at most.
     */
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn)
    {
        this.head.rotateAngleX = headPitch * 0.017453292F;
        this.head.rotateAngleY = netHeadYaw * 0.017453292F;
        this.body.rotateAngleX = ((float)Math.PI / 2F);
        this.legRightBack.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        this.legLeftBack.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
        this.leftRightFront.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
        this.legLeftFront.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
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
