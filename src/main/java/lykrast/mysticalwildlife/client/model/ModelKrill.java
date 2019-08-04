package lykrast.mysticalwildlife.client.model;

import com.mojang.blaze3d.platform.GlStateManager;

import lykrast.mysticalwildlife.common.entity.EntityKrill;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.util.math.MathHelper;

/**
 * Krill - Lykrast
 * Created using Tabula 7.0.0
 */
public class ModelKrill<T extends EntityKrill> extends EntityModel<T> {
	protected static final float LEG_ANGLE = 2.356194490192345F; //From Tabula
	protected static final float FOOT_ANGLE = 2.1816615649929116F; //From Tabula
	protected static final float HEAD_ANGLE = 0.2617993877991494F; //From Tabula
	protected static final float FORAGE_ANGLE = (float)Math.PI / 3; //60°
	
    public RendererModel body;
    public RendererModel head;
    public RendererModel legFL;
    public RendererModel legML;
    public RendererModel legBL;
    public RendererModel legFR;
    public RendererModel legMR;
    public RendererModel legBR;
    public RendererModel tentacleUL;
    public RendererModel tentacleUR;
    public RendererModel tentacleLL;
    public RendererModel tentacleLR;
    public RendererModel tentacleM;
    public RendererModel footFL;
    public RendererModel footML;
    public RendererModel footBL;
    public RendererModel footFR;
    public RendererModel footMR;
    public RendererModel footBR;

    public ModelKrill() {
        textureWidth = 32;
        textureHeight = 32;
        footML = new RendererModel(this, 0, 23);
        footML.setRotationPoint(0.0F, 4.0F, 0.0F);
        footML.addBox(-0.5F, 0.0F, -0.5F, 1, 8, 1, 0.0F);
        setRotateAngle(footML, 0.0F, 0.0F, FOOT_ANGLE);
        footMR = new RendererModel(this, 0, 23);
        footMR.mirror = true;
        footMR.setRotationPoint(0.0F, 4.0F, 0.0F);
        footMR.addBox(-0.5F, 0.0F, -0.5F, 1, 8, 1, 0.0F);
        setRotateAngle(footMR, 0.0F, 0.0F, -FOOT_ANGLE);
        legBR = new RendererModel(this, 0, 18);
        legBR.mirror = true;
        legBR.setRotationPoint(-1.5F, 19.0F, 6.0F);
        legBR.addBox(-0.5F, 0.0F, -0.5F, 1, 4, 1, -0.1F);
        setRotateAngle(legBR, 0.0F, 0.0F, LEG_ANGLE);
        legBL = new RendererModel(this, 0, 18);
        legBL.setRotationPoint(1.5F, 19.0F, 6.0F);
        legBL.addBox(-0.5F, 0.0F, -0.5F, 1, 4, 1, -0.1F);
        setRotateAngle(legBL, 0.0F, 0.0F, -LEG_ANGLE);
        footFR = new RendererModel(this, 0, 23);
        footFR.mirror = true;
        footFR.setRotationPoint(0.0F, 4.0F, 0.0F);
        footFR.addBox(-0.5F, 0.0F, -0.5F, 1, 8, 1, 0.0F);
        setRotateAngle(footFR, 0.0F, 0.0F, -FOOT_ANGLE);
        legFR = new RendererModel(this, 0, 18);
        legFR.mirror = true;
        legFR.setRotationPoint(-1.5F, 19.0F, 0.0F);
        legFR.addBox(-0.5F, 0.0F, -0.5F, 1, 4, 1, -0.1F);
        setRotateAngle(legFR, 0.0F, 0.0F, LEG_ANGLE);
        tentacleLR = new RendererModel(this, 0, 9);
        tentacleLR.setRotationPoint(0.75F, 0.75F, -4.5F);
        tentacleLR.addBox(-0.5F, -0.5F, -3.0F, 1, 1, 3, 0.0F);
        setRotateAngle(tentacleLR, 0.8726646259971648F, 0.0F, 0.0F);
        tentacleM = new RendererModel(this, 0, 9);
        tentacleM.setRotationPoint(0.0F, 0.0F, -4.5F);
        tentacleM.addBox(-0.5F, -0.5F, -3.0F, 1, 1, 3, 0.0F);
        setRotateAngle(tentacleM, 0.7853981633974483F, 0.0F, 0.0F);
        footFL = new RendererModel(this, 0, 23);
        footFL.setRotationPoint(0.0F, 4.0F, 0.0F);
        footFL.addBox(-0.5F, 0.0F, -0.5F, 1, 8, 1, 0.0F);
        setRotateAngle(footFL, 0.0F, 0.0F, FOOT_ANGLE);
        footBL = new RendererModel(this, 0, 23);
        footBL.setRotationPoint(0.0F, 4.0F, 0.0F);
        footBL.addBox(-0.5F, 0.0F, -0.5F, 1, 8, 1, 0.0F);
        setRotateAngle(footBL, 0.0F, 0.0F, FOOT_ANGLE);
        legMR = new RendererModel(this, 0, 18);
        legMR.mirror = true;
        legMR.setRotationPoint(-1.5F, 19.0F, 3.0F);
        legMR.addBox(-0.5F, 0.0F, -0.5F, 1, 4, 1, -0.1F);
        setRotateAngle(legMR, 0.0F, 0.0F, LEG_ANGLE);
        body = new RendererModel(this, 16, 20);
        body.setRotationPoint(0.0F, 19.0F, 0.0F);
        body.addBox(-2.0F, -1.0F, -2.0F, 4, 8, 4, 0.0F);
        setRotateAngle(body, 1.5707963267948966F, 0.0F, 0.0F);
        head = new RendererModel(this, 0, 0);
        head.setRotationPoint(0.0F, 19.0F, -1.0F);
        head.addBox(-1.5F, -1.5F, -5.0F, 3, 3, 6, 0.0F);
        setRotateAngle(head, HEAD_ANGLE, 0.0F, 0.0F);
        legFL = new RendererModel(this, 0, 18);
        legFL.setRotationPoint(1.5F, 19.0F, 0.0F);
        legFL.addBox(-0.5F, 0.0F, -0.5F, 1, 4, 1, -0.1F);
        setRotateAngle(legFL, 0.0F, 0.0F, -LEG_ANGLE);
        legML = new RendererModel(this, 0, 18);
        legML.setRotationPoint(1.5F, 19.0F, 3.0F);
        legML.addBox(-0.5F, 0.0F, -0.5F, 1, 4, 1, -0.1F);
        setRotateAngle(legML, 0.0F, 0.0F, -LEG_ANGLE);
        footBR = new RendererModel(this, 0, 23);
        footBR.mirror = true;
        footBR.setRotationPoint(0.0F, 4.0F, 0.0F);
        footBR.addBox(-0.5F, 0.0F, -0.5F, 1, 8, 1, 0.0F);
        setRotateAngle(footBR, 0.0F, 0.0F, -FOOT_ANGLE);
        tentacleLL = new RendererModel(this, 0, 9);
        tentacleLL.setRotationPoint(-0.75F, 0.75F, -4.5F);
        tentacleLL.addBox(-0.5F, -0.5F, -3.0F, 1, 1, 3, 0.0F);
        setRotateAngle(tentacleLL, 0.8726646259971648F, 0.0F, 0.0F);
        tentacleUL = new RendererModel(this, 0, 9);
        tentacleUL.setRotationPoint(-0.75F, -0.75F, -4.5F);
        tentacleUL.addBox(-0.5F, -0.5F, -3.0F, 1, 1, 3, 0.0F);
        setRotateAngle(tentacleUL, 0.6981317007977318F, 0.0F, 0.0F);
        tentacleUR = new RendererModel(this, 0, 9);
        tentacleUR.setRotationPoint(0.75F, -0.75F, -4.5F);
        tentacleUR.addBox(-0.5F, -0.5F, -3.0F, 1, 1, 3, 0.0F);
        setRotateAngle(tentacleUR, 0.6981317007977318F, 0.0F, 0.0F);
        legML.addChild(footML);
        legMR.addChild(footMR);
        legFR.addChild(footFR);
        head.addChild(tentacleLR);
        head.addChild(tentacleM);
        legFL.addChild(footFL);
        legBL.addChild(footBL);
        legBR.addChild(footBR);
        head.addChild(tentacleLL);
        head.addChild(tentacleUL);
        head.addChild(tentacleUR);
    }
    
    private float headRotateX = -1;


    @Override
	public void setRotationAngles(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor) {
        head.rotateAngleX = headRotateX <= 0 ? headPitch * 0.017453292F + HEAD_ANGLE : headRotateX;
        head.rotateAngleY = netHeadYaw * 0.017453292F;
        
        float forwards = MathHelper.cos(limbSwing) * 0.523599F * limbSwingAmount;
        float backwards = -forwards;
        
        legFL.rotateAngleX = forwards;
        legFL.rotateAngleY = backwards;
        legML.rotateAngleX = backwards;
        legML.rotateAngleY = forwards;
        legBL.rotateAngleX = forwards;
        legBL.rotateAngleY = backwards;
        
        legFR.rotateAngleX = backwards;
        legFR.rotateAngleY = forwards;
        legMR.rotateAngleX = forwards;
        legMR.rotateAngleY = backwards;
        legBR.rotateAngleX = backwards;
        legBR.rotateAngleY = forwards;
    }
    
    @Override
    public void setLivingAnimations(T entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTickTime) {
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
    public void render(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        setRotationAngles(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);

        if (isChild)
        {
            GlStateManager.pushMatrix();
            GlStateManager.scalef(0.5F, 0.5F, 0.5F);
            GlStateManager.translatef(0.0F, 24.0F * scale, 0.0F);
            legBR.render(scale);
            legBL.render(scale);
            legFR.render(scale);
            legMR.render(scale);
            body.render(scale);
            head.render(scale);
            legFL.render(scale);
            legML.render(scale);
            GlStateManager.popMatrix();
        }
        else
        {
            legBR.render(scale);
            legBL.render(scale);
            legFR.render(scale);
            legMR.render(scale);
            body.render(scale);
            head.render(scale);
            legFL.render(scale);
            legML.render(scale);
        }
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
