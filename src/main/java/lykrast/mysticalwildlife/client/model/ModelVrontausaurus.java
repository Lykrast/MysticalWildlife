package lykrast.mysticalwildlife.client.model;

import net.minecraft.client.renderer.entity.model.ModelRenderer;

/**
 * vrontausurus2 - Lykrast Created using Tabula 7.0.0
 */
public class ModelVrontausaurus extends ModelBaseQuadruped {
	public ModelRenderer plateLowerFront;
	public ModelRenderer plateLowerBack;
	public ModelRenderer plateUpperFront;
	public ModelRenderer plateUpperBack;
	public ModelRenderer snout;

	public ModelVrontausaurus() {
	    childYOffset = 8.0F;
	    childZOffset = 7.0F;
	    
		this.textureWidth = 128;
		this.textureHeight = 64;
		this.legRightFront = new ModelRenderer(this, 0, 38);
		this.legRightFront.mirror = true;
		this.legRightFront.setRotationPoint(-12.0F, 6.0F, -4.0F);
		this.legRightFront.addBox(-4.0F, 0.0F, -4.0F, 8, 18, 8, 0.0F);
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
}
