package lykrast.mysticalwildlife.client.model;

import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.entity.Entity;

/**
 * vrontausurus2 - Lykrast Created using Tabula 7.0.0
 */
public class ModelVrontausaurus<T extends Entity> extends ModelBaseQuadruped<T> {
	public RendererModel plateLowerFront;
	public RendererModel plateLowerBack;
	public RendererModel plateUpperFront;
	public RendererModel plateUpperBack;
	public RendererModel snout;

	public ModelVrontausaurus() {
	    childYOffset = 8.0F;
	    childZOffset = 7.0F;
	    
		textureWidth = 128;
		textureHeight = 64;
		legRightFront = new RendererModel(this, 0, 38);
		legRightFront.mirror = true;
		legRightFront.setRotationPoint(-12.0F, 6.0F, -4.0F);
		legRightFront.addBox(-4.0F, 0.0F, -4.0F, 8, 18, 8, 0.0F);
		body = new RendererModel(this, 68, 18);
		body.setRotationPoint(0.0F, 8.0F, 0.0F);
		body.addBox(-8.0F, -12.0F, -9.0F, 16, 32, 14, 0.0F);
		setRotateAngle(body, 1.5707963267948966F, 0.0F, 0.0F);
		legLeftFront = new RendererModel(this, 0, 38);
		legLeftFront.setRotationPoint(12.0F, 6.0F, -4.0F);
		legLeftFront.addBox(-4.0F, 0.0F, -4.0F, 8, 18, 8, 0.0F);
		plateLowerBack = new RendererModel(this, 58, 55);
		plateLowerBack.setRotationPoint(0.0F, 12.0F, 5.0F);
		plateLowerBack.addBox(-2.0F, -4.0F, 0.0F, 4, 8, 1, 0.0F);
		legLeftBack = new RendererModel(this, 0, 38);
		legLeftBack.setRotationPoint(12.0F, 6.0F, 12.0F);
		legLeftBack.addBox(-4.0F, 0.0F, -4.0F, 8, 18, 8, 0.0F);
		plateLowerFront = new RendererModel(this, 58, 55);
		plateLowerFront.setRotationPoint(0.0F, -4.0F, 5.0F);
		plateLowerFront.addBox(-2.0F, -4.0F, 0.0F, 4, 8, 1, 0.0F);
		legRightBack = new RendererModel(this, 0, 38);
		legRightBack.mirror = true;
		legRightBack.setRotationPoint(-12.0F, 6.0F, 12.0F);
		legRightBack.addBox(-4.0F, 0.0F, -4.0F, 8, 18, 8, 0.0F);
		head = new RendererModel(this, 0, 0);
		head.setRotationPoint(0.0F, 7.0F, -12.0F);
		head.addBox(-6.0F, -5.0F, -8.0F, 12, 10, 8, 0.0F);
		plateUpperFront = new RendererModel(this, 60, 46);
		plateUpperFront.setRotationPoint(0.0F, 0.0F, 1.0F);
		plateUpperFront.addBox(-0.5F, -3.0F, 0.0F, 1, 6, 3, 0.0F);
		plateUpperBack = new RendererModel(this, 60, 46);
		plateUpperBack.setRotationPoint(0.0F, 0.0F, 1.0F);
		plateUpperBack.addBox(-0.5F, -3.0F, 0.0F, 1, 6, 3, 0.0F);
		snout = new RendererModel(this, 0, 18);
		snout.setRotationPoint(0.0F, 0.0F, 0.0F);
		snout.addBox(-4.0F, -2.0F, -16.0F, 8, 6, 8, 0.0F);
		body.addChild(plateLowerBack);
		body.addChild(plateLowerFront);
		plateLowerFront.addChild(plateUpperFront);
		plateLowerBack.addChild(plateUpperBack);
		head.addChild(snout);
	}
}
