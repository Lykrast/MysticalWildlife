package lykrast.mysticalwildlife.client.model;

import net.minecraft.client.model.ModelRenderer;

/**
 * dusk_lurker - Lykrast
 * Created using Tabula 7.0.0
 */
public class ModelDuskLurker extends ModelBaseQuadruped {
    public ModelRenderer snout;

    public ModelDuskLurker() {
    	this.textureWidth = 64;
        this.textureHeight = 48;
        this.legLeftFront = new ModelRenderer(this, 0, 34);
        this.legLeftFront.setRotationPoint(7.0F, 14.0F, -4.0F);
        this.legLeftFront.addBox(-2.0F, 0.0F, -2.0F, 4, 10, 4, 0.0F);
        this.snout = new ModelRenderer(this, 0, 16);
        this.snout.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.snout.addBox(-3.0F, -1.0F, -14.0F, 6, 4, 6, 0.0F);
        this.legRightBack = new ModelRenderer(this, 0, 34);
        this.legRightBack.mirror = true;
        this.legRightBack.setRotationPoint(-7.0F, 14.0F, 5.0F);
        this.legRightBack.addBox(-2.0F, 0.0F, -2.0F, 4, 10, 4, 0.0F);
        this.body = new ModelRenderer(this, 28, 24);
        this.body.setRotationPoint(0.0F, 12.0F, 2.0F);
        this.body.addBox(-5.0F, -10.0F, -7.0F, 10, 16, 8, 0.0F);
        this.setRotateAngle(body, 1.5707963267948966F, 0.0F, 0.0F);
        this.head = new ModelRenderer(this, 0, 0);
        this.head.setRotationPoint(0.0F, 14.0F, -6.0F);
        this.head.addBox(-4.0F, -4.0F, -8.0F, 8, 8, 8, 0.0F);
        this.legRightFront = new ModelRenderer(this, 0, 34);
        this.legRightFront.mirror = true;
        this.legRightFront.setRotationPoint(-7.0F, 14.0F, -4.0F);
        this.legRightFront.addBox(-2.0F, 0.0F, -2.0F, 4, 10, 4, 0.0F);
        this.legLeftBack = new ModelRenderer(this, 0, 34);
        this.legLeftBack.setRotationPoint(7.0F, 14.0F, 5.0F);
        this.legLeftBack.addBox(-2.0F, 0.0F, -2.0F, 4, 10, 4, 0.0F);
        this.head.addChild(this.snout);
    }
}
