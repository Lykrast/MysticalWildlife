package lykrast.mysticalwildlife.client.model;

import lykrast.mysticalwildlife.common.entity.EntityKrill;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.model.ModelBase;
import net.minecraft.client.renderer.entity.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.MathHelper;

/**
 * Krill - Lykrast
 * Created using Tabula 7.0.0
 */
public class ModelKrill extends ModelBase {
	protected static final float LEG_ANGLE = 2.356194490192345F; //From Tabula
	protected static final float FOOT_ANGLE = 2.1816615649929116F; //From Tabula
	protected static final float HEAD_ANGLE = 0.2617993877991494F; //From Tabula
	protected static final float FORAGE_ANGLE = (float)Math.PI / 3; //60°
	
    public ModelRenderer body;
    public ModelRenderer head;
    public ModelRenderer legFL;
    public ModelRenderer legML;
    public ModelRenderer legBL;
    public ModelRenderer legFR;
    public ModelRenderer legMR;
    public ModelRenderer legBR;
    public ModelRenderer tentacleUL;
    public ModelRenderer tentacleUR;
    public ModelRenderer tentacleLL;
    public ModelRenderer tentacleLR;
    public ModelRenderer tentacleM;
    public ModelRenderer footFL;
    public ModelRenderer footML;
    public ModelRenderer footBL;
    public ModelRenderer footFR;
    public ModelRenderer footMR;
    public ModelRenderer footBR;

    public ModelKrill() {
        this.textureWidth = 32;
        this.textureHeight = 32;
        this.footML = new ModelRenderer(this, 0, 23);
        this.footML.setRotationPoint(0.0F, 4.0F, 0.0F);
        this.footML.addBox(-0.5F, 0.0F, -0.5F, 1, 8, 1, 0.0F);
        this.setRotateAngle(footML, 0.0F, 0.0F, FOOT_ANGLE);
        this.footMR = new ModelRenderer(this, 0, 23);
        this.footMR.mirror = true;
        this.footMR.setRotationPoint(0.0F, 4.0F, 0.0F);
        this.footMR.addBox(-0.5F, 0.0F, -0.5F, 1, 8, 1, 0.0F);
        this.setRotateAngle(footMR, 0.0F, 0.0F, -FOOT_ANGLE);
        this.legBR = new ModelRenderer(this, 0, 18);
        this.legBR.mirror = true;
        this.legBR.setRotationPoint(-1.5F, 19.0F, 6.0F);
        this.legBR.addBox(-0.5F, 0.0F, -0.5F, 1, 4, 1, -0.1F);
        this.setRotateAngle(legBR, 0.0F, 0.0F, LEG_ANGLE);
        this.legBL = new ModelRenderer(this, 0, 18);
        this.legBL.setRotationPoint(1.5F, 19.0F, 6.0F);
        this.legBL.addBox(-0.5F, 0.0F, -0.5F, 1, 4, 1, -0.1F);
        this.setRotateAngle(legBL, 0.0F, 0.0F, -LEG_ANGLE);
        this.footFR = new ModelRenderer(this, 0, 23);
        this.footFR.mirror = true;
        this.footFR.setRotationPoint(0.0F, 4.0F, 0.0F);
        this.footFR.addBox(-0.5F, 0.0F, -0.5F, 1, 8, 1, 0.0F);
        this.setRotateAngle(footFR, 0.0F, 0.0F, -FOOT_ANGLE);
        this.legFR = new ModelRenderer(this, 0, 18);
        this.legFR.mirror = true;
        this.legFR.setRotationPoint(-1.5F, 19.0F, 0.0F);
        this.legFR.addBox(-0.5F, 0.0F, -0.5F, 1, 4, 1, -0.1F);
        this.setRotateAngle(legFR, 0.0F, 0.0F, LEG_ANGLE);
        this.tentacleLR = new ModelRenderer(this, 0, 9);
        this.tentacleLR.setRotationPoint(0.75F, 0.75F, -4.5F);
        this.tentacleLR.addBox(-0.5F, -0.5F, -3.0F, 1, 1, 3, 0.0F);
        this.setRotateAngle(tentacleLR, 0.8726646259971648F, 0.0F, 0.0F);
        this.tentacleM = new ModelRenderer(this, 0, 9);
        this.tentacleM.setRotationPoint(0.0F, 0.0F, -4.5F);
        this.tentacleM.addBox(-0.5F, -0.5F, -3.0F, 1, 1, 3, 0.0F);
        this.setRotateAngle(tentacleM, 0.7853981633974483F, 0.0F, 0.0F);
        this.footFL = new ModelRenderer(this, 0, 23);
        this.footFL.setRotationPoint(0.0F, 4.0F, 0.0F);
        this.footFL.addBox(-0.5F, 0.0F, -0.5F, 1, 8, 1, 0.0F);
        this.setRotateAngle(footFL, 0.0F, 0.0F, FOOT_ANGLE);
        this.footBL = new ModelRenderer(this, 0, 23);
        this.footBL.setRotationPoint(0.0F, 4.0F, 0.0F);
        this.footBL.addBox(-0.5F, 0.0F, -0.5F, 1, 8, 1, 0.0F);
        this.setRotateAngle(footBL, 0.0F, 0.0F, FOOT_ANGLE);
        this.legMR = new ModelRenderer(this, 0, 18);
        this.legMR.mirror = true;
        this.legMR.setRotationPoint(-1.5F, 19.0F, 3.0F);
        this.legMR.addBox(-0.5F, 0.0F, -0.5F, 1, 4, 1, -0.1F);
        this.setRotateAngle(legMR, 0.0F, 0.0F, LEG_ANGLE);
        this.body = new ModelRenderer(this, 16, 20);
        this.body.setRotationPoint(0.0F, 19.0F, 0.0F);
        this.body.addBox(-2.0F, -1.0F, -2.0F, 4, 8, 4, 0.0F);
        this.setRotateAngle(body, 1.5707963267948966F, 0.0F, 0.0F);
        this.head = new ModelRenderer(this, 0, 0);
        this.head.setRotationPoint(0.0F, 19.0F, -1.0F);
        this.head.addBox(-1.5F, -1.5F, -5.0F, 3, 3, 6, 0.0F);
        this.setRotateAngle(head, HEAD_ANGLE, 0.0F, 0.0F);
        this.legFL = new ModelRenderer(this, 0, 18);
        this.legFL.setRotationPoint(1.5F, 19.0F, 0.0F);
        this.legFL.addBox(-0.5F, 0.0F, -0.5F, 1, 4, 1, -0.1F);
        this.setRotateAngle(legFL, 0.0F, 0.0F, -LEG_ANGLE);
        this.legML = new ModelRenderer(this, 0, 18);
        this.legML.setRotationPoint(1.5F, 19.0F, 3.0F);
        this.legML.addBox(-0.5F, 0.0F, -0.5F, 1, 4, 1, -0.1F);
        this.setRotateAngle(legML, 0.0F, 0.0F, -LEG_ANGLE);
        this.footBR = new ModelRenderer(this, 0, 23);
        this.footBR.mirror = true;
        this.footBR.setRotationPoint(0.0F, 4.0F, 0.0F);
        this.footBR.addBox(-0.5F, 0.0F, -0.5F, 1, 8, 1, 0.0F);
        this.setRotateAngle(footBR, 0.0F, 0.0F, -FOOT_ANGLE);
        this.tentacleLL = new ModelRenderer(this, 0, 9);
        this.tentacleLL.setRotationPoint(-0.75F, 0.75F, -4.5F);
        this.tentacleLL.addBox(-0.5F, -0.5F, -3.0F, 1, 1, 3, 0.0F);
        this.setRotateAngle(tentacleLL, 0.8726646259971648F, 0.0F, 0.0F);
        this.tentacleUL = new ModelRenderer(this, 0, 9);
        this.tentacleUL.setRotationPoint(-0.75F, -0.75F, -4.5F);
        this.tentacleUL.addBox(-0.5F, -0.5F, -3.0F, 1, 1, 3, 0.0F);
        this.setRotateAngle(tentacleUL, 0.6981317007977318F, 0.0F, 0.0F);
        this.tentacleUR = new ModelRenderer(this, 0, 9);
        this.tentacleUR.setRotationPoint(0.75F, -0.75F, -4.5F);
        this.tentacleUR.addBox(-0.5F, -0.5F, -3.0F, 1, 1, 3, 0.0F);
        this.setRotateAngle(tentacleUR, 0.6981317007977318F, 0.0F, 0.0F);
        this.legML.addChild(this.footML);
        this.legMR.addChild(this.footMR);
        this.legFR.addChild(this.footFR);
        this.head.addChild(this.tentacleLR);
        this.head.addChild(this.tentacleM);
        this.legFL.addChild(this.footFL);
        this.legBL.addChild(this.footBL);
        this.legBR.addChild(this.footBR);
        this.head.addChild(this.tentacleLL);
        this.head.addChild(this.tentacleUL);
        this.head.addChild(this.tentacleUR);
    }
    
    private float headRotateX = -1;

    /**
     * Sets the model's various rotation angles. For bipeds, par1 and par2 are used for animating the movement of arms
     * and legs, where par1 represents the time(so that arms and legs swing back and forth) and par2 represents how
     * "far" arms and legs can swing at most.
     */
    @Override
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn)
    {
        this.head.rotateAngleX = headRotateX <= 0 ? headPitch * 0.017453292F + HEAD_ANGLE : headRotateX;
        this.head.rotateAngleY = netHeadYaw * 0.017453292F;
        
        float forwards = MathHelper.cos(limbSwing) * 0.523599F * limbSwingAmount;
        float backwards = -forwards;
        
        this.legFL.rotateAngleX = forwards;
        this.legFL.rotateAngleY = backwards;
        this.legML.rotateAngleX = backwards;
        this.legML.rotateAngleY = forwards;
        this.legBL.rotateAngleX = forwards;
        this.legBL.rotateAngleY = backwards;
        
        this.legFR.rotateAngleX = backwards;
        this.legFR.rotateAngleY = forwards;
        this.legMR.rotateAngleX = forwards;
        this.legMR.rotateAngleY = backwards;
        this.legBR.rotateAngleX = backwards;
        this.legBR.rotateAngleY = forwards;
    }
    
    @Override
    public void setLivingAnimations(EntityLivingBase entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTickTime) {
        super.setLivingAnimations(entitylivingbaseIn, limbSwing, limbSwingAmount, partialTickTime);

    	int timer = ((EntityKrill)entitylivingbaseIn).forageTimer;
    	if (timer > 0)
    	{
    		if (timer <= 4)
    			headRotateX = (float)MathHelper.clampedLerp(HEAD_ANGLE, FORAGE_ANGLE, (timer - partialTickTime)/4);
    		else if (timer > 36)
    			headRotateX = (float)MathHelper.clampedLerp(HEAD_ANGLE, FORAGE_ANGLE, (40 - timer + partialTickTime)/4);
    		else
    			headRotateX = FORAGE_ANGLE;
    	}
    	else headRotateX = -1;
    }

    @Override
    public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityIn);

        if (this.isChild)
        {
            GlStateManager.pushMatrix();
            GlStateManager.scalef(0.5F, 0.5F, 0.5F);
            GlStateManager.translatef(0.0F, 24.0F * scale, 0.0F);
            this.legBR.render(scale);
            this.legBL.render(scale);
            this.legFR.render(scale);
            this.legMR.render(scale);
            this.body.render(scale);
            this.head.render(scale);
            this.legFL.render(scale);
            this.legML.render(scale);
            GlStateManager.popMatrix();
        }
        else
        {
            this.legBR.render(scale);
            this.legBL.render(scale);
            this.legFR.render(scale);
            this.legMR.render(scale);
            this.body.render(scale);
            this.head.render(scale);
            this.legFL.render(scale);
            this.legML.render(scale);
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
