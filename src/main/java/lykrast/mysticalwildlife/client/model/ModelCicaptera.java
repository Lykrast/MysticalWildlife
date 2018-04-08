package lykrast.mysticalwildlife.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

/**
 * cicaptera - Lykrast
 * Created using Tabula 7.0.0
 */
public class ModelCicaptera extends ModelBase {
	protected float PI_HALF = ((float)Math.PI / 2F); //90°
	protected float PI_THIRD = ((float)Math.PI / 3F); //60°
	protected float PI_NINTH = ((float)Math.PI / 9F); //20°
	protected float PI_TWELVETH = ((float)Math.PI / 12F); //15°
	protected float WING_BASE = 0.17453292519943295F; //From Tabula
    
    public ModelRenderer body;
    public ModelRenderer head;
    public ModelRenderer wingLeft;
    public ModelRenderer wingRight;
    public ModelRenderer legLeftFront;
    public ModelRenderer legLeftBack;
    public ModelRenderer legRightFront;
    public ModelRenderer legRightBack;
    public ModelRenderer eyeLeft;
    public ModelRenderer eyeRight;

    public ModelCicaptera() {
        this.textureWidth = 32;
        this.textureHeight = 32;
        this.body = new ModelRenderer(this, 12, 18);
        this.body.setRotationPoint(0.0F, 20.2F, -4.0F);
        this.body.addBox(-3.0F, 0.0F, -3.0F, 6, 10, 4, 0.0F);
        this.setRotateAngle(body, 1.5707963267948966F, 0.0F, 0.0F);
        this.legLeftFront = new ModelRenderer(this, 0, 26);
        this.legLeftFront.setRotationPoint(3.5F, 2.0F, 0.0F);
        this.legLeftFront.addBox(-1.0F, 0.0F, -1.0F, 2, 4, 2, 0.0F);
        this.setRotateAngle(legLeftFront, -1.5707963267948966F, -0.2617993877991494F, 0.0F);
        this.legRightFront = new ModelRenderer(this, 0, 26);
        this.legRightFront.mirror = true;
        this.legRightFront.setRotationPoint(-3.5F, 2.0F, 0.0F);
        this.legRightFront.addBox(-1.0F, 0.0F, -1.0F, 2, 4, 2, 0.0F);
        this.setRotateAngle(legRightFront, -1.5707963267948966F, 0.2617993877991494F, 0.0F);
        this.legRightBack = new ModelRenderer(this, 0, 26);
        this.legRightBack.mirror = true;
        this.legRightBack.setRotationPoint(-3.5F, 8.0F, 0.0F);
        this.legRightBack.addBox(-1.0F, 0.0F, -1.0F, 2, 4, 2, 0.0F);
        this.setRotateAngle(legRightBack, -1.5707963267948966F, 0.2617993877991494F, 0.0F);
        this.eyeLeft = new ModelRenderer(this, 0, 0);
        this.eyeLeft.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.eyeLeft.addBox(2.5F, -1.0F, -3.0F, 1, 1, 1, 0.0F);
        this.wingRight = new ModelRenderer(this, 0, 13);
        this.wingRight.mirror = true;
        this.wingRight.setRotationPoint(-0.9F, 1.0F, 1.0F);
        this.wingRight.addBox(-3.0F, 0.0F, 0.0F, 4, 12, 1, 0.0F);
        this.setRotateAngle(wingRight, 0.0F, -0.17453292519943295F, 0.0F);
        this.head = new ModelRenderer(this, 0, 0);
        this.head.setRotationPoint(0.0F, 20.2F, -4.0F);
        this.head.addBox(-3.0F, -2.0F, -4.0F, 6, 4, 4, 0.0F);
        this.legLeftBack = new ModelRenderer(this, 0, 26);
        this.legLeftBack.setRotationPoint(3.5F, 8.0F, 0.0F);
        this.legLeftBack.addBox(-1.0F, 0.0F, -1.0F, 2, 4, 2, 0.0F);
        this.setRotateAngle(legLeftBack, -1.5707963267948966F, -0.2617993877991494F, 0.0F);
        this.wingLeft = new ModelRenderer(this, 0, 13);
        this.wingLeft.setRotationPoint(0.9F, 1.0F, 1.0F);
        this.wingLeft.addBox(-1.0F, 0.0F, 0.0F, 4, 12, 1, 0.0F);
        this.setRotateAngle(wingLeft, 0.0F, 0.17453292519943295F, 0.0F);
        this.eyeRight = new ModelRenderer(this, 0, 0);
        this.eyeRight.mirror = true;
        this.eyeRight.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.eyeRight.addBox(-3.5F, -1.0F, -3.0F, 1, 1, 1, 0.0F);
        this.body.addChild(this.legLeftFront);
        this.body.addChild(this.legRightFront);
        this.body.addChild(this.legRightBack);
        this.head.addChild(this.eyeLeft);
        this.body.addChild(this.wingRight);
        this.body.addChild(this.legLeftBack);
        this.body.addChild(this.wingLeft);
        this.head.addChild(this.eyeRight);
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
        
        if (!entityIn.onGround) //In the air, deploy wings
        {
            this.body.rotateAngleX = PI_THIRD;
            this.legRightBack.rotateAngleX = -PI_THIRD;
            this.legLeftBack.rotateAngleX = -PI_THIRD;
            this.legRightFront.rotateAngleX = -PI_THIRD;
            this.legLeftFront.rotateAngleX = -PI_THIRD;
            
            this.wingLeft.rotateAngleX = PI_THIRD;
            this.wingRight.rotateAngleX = PI_THIRD;
            //TODO: make more noticeable
            this.wingLeft.rotateAngleY = MathHelper.cos(limbSwing * 0.6662F) * PI_TWELVETH * limbSwingAmount + PI_NINTH;
            this.wingRight.rotateAngleY = MathHelper.cos(limbSwing * 0.6662F) * PI_TWELVETH * limbSwingAmount * (-1) - PI_NINTH;
        }
        else //On the ground, do as normal
        {
            this.body.rotateAngleX = PI_HALF;
            this.legRightBack.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount - PI_HALF;
            this.legLeftBack.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount - PI_HALF;
            this.legRightFront.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount - PI_HALF;
            this.legLeftFront.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount - PI_HALF;
            
            this.wingLeft.rotateAngleX = 0.0F;
            this.wingRight.rotateAngleX = 0.0F;
            this.wingLeft.rotateAngleY = WING_BASE;
            this.wingRight.rotateAngleY = -WING_BASE;
        }
        
    }

    @Override
    public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) { 
        this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityIn);

        if (this.isChild)
        {
            GlStateManager.pushMatrix();
            GlStateManager.scale(0.5F, 0.5F, 0.5F);
            GlStateManager.translate(0.0F, 24.0F * scale, 0.0F);
            this.head.render(scale);
            this.body.render(scale);
            GlStateManager.popMatrix();
        }
        else
        {
            this.head.render(scale);
            this.body.render(scale);
        }
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
