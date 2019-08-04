package lykrast.mysticalwildlife.client.model;

import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.entity.Entity;

/**
 * dusk_lurker - Lykrast
 * Created using Tabula 7.0.0
 */
public class ModelDuskLurker<T extends Entity> extends ModelBaseQuadruped<T> {
    public RendererModel snout;

    public ModelDuskLurker() {
    	textureWidth = 64;
        textureHeight = 48;
        legLeftFront = new RendererModel(this, 0, 34);
        legLeftFront.setRotationPoint(7.0F, 14.0F, -4.0F);
        legLeftFront.addBox(-2.0F, 0.0F, -2.0F, 4, 10, 4, 0.0F);
        snout = new RendererModel(this, 0, 16);
        snout.setRotationPoint(0.0F, 0.0F, 0.0F);
        snout.addBox(-3.0F, -1.0F, -14.0F, 6, 4, 6, 0.0F);
        legRightBack = new RendererModel(this, 0, 34);
        legRightBack.mirror = true;
        legRightBack.setRotationPoint(-7.0F, 14.0F, 5.0F);
        legRightBack.addBox(-2.0F, 0.0F, -2.0F, 4, 10, 4, 0.0F);
        body = new RendererModel(this, 28, 24);
        body.setRotationPoint(0.0F, 12.0F, 2.0F);
        body.addBox(-5.0F, -10.0F, -7.0F, 10, 16, 8, 0.0F);
        setRotateAngle(body, 1.5707963267948966F, 0.0F, 0.0F);
        head = new RendererModel(this, 0, 0);
        head.setRotationPoint(0.0F, 14.0F, -6.0F);
        head.addBox(-4.0F, -4.0F, -8.0F, 8, 8, 8, 0.0F);
        legRightFront = new RendererModel(this, 0, 34);
        legRightFront.mirror = true;
        legRightFront.setRotationPoint(-7.0F, 14.0F, -4.0F);
        legRightFront.addBox(-2.0F, 0.0F, -2.0F, 4, 10, 4, 0.0F);
        legLeftBack = new RendererModel(this, 0, 34);
        legLeftBack.setRotationPoint(7.0F, 14.0F, 5.0F);
        legLeftBack.addBox(-2.0F, 0.0F, -2.0F, 4, 10, 4, 0.0F);
        head.addChild(snout);
    }
}
