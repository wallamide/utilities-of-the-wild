package net.servate.uotw.client.render.entity.model;

import net.minecraft.client.model.ModelPart;
import net.minecraft.client.model.ModelPartBuilder;
import net.minecraft.client.model.ModelPartData;
import net.minecraft.client.model.ModelTransform;
import net.minecraft.client.model.TexturedModelData;
import net.minecraft.client.model.Dilation;
import net.minecraft.client.model.Model;
import net.minecraft.client.model.ModelData;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.util.math.MatrixStack;

public class Lizal_Tri_Boomerang_Entity_Model extends Model {
    private final ModelPart base;

	public Lizal_Tri_Boomerang_Entity_Model(ModelPart base) {
        super(RenderLayer::getEntitySolid); 
        this.base = base.getChild("base");


	}

	public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();

		ModelPartData lizal_tri_boomerang = modelPartData.addChild("base", ModelPartBuilder.create(), ModelTransform.of(0.0F, 16.0F, -1.0F, 0.0F, 0.0F, 0.0F));
		ModelPartData blade3 = lizal_tri_boomerang.addChild("blade3", ModelPartBuilder.create(), ModelTransform.of(0.25F, 9.0F, 1.0F, 0.0F, 0.0F, 0.0F));
		ModelPartData blade3pt2_r1 = blade3.addChild("blade3pt2_r1", ModelPartBuilder.create().uv(0, 0).cuboid(-3.0F, -1.0F, 0.0F, 3.0F, 1.0F, 1.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(-0.5F, -11.25F, 0.0F, 0.0F, 0.0F, 0.2618F));
		ModelPartData blade3pt1_r1 = blade3.addChild("blade3pt1_r1", ModelPartBuilder.create().uv(0, 0).cuboid(-2.0F, -7.0F, 0.0F, 2.0F, 7.0F, 1.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(-2.75F, -12.9F, 0.0F, 0.0F, 0.0F, -1.2654F));
		ModelPartData blade2 = lizal_tri_boomerang.addChild("blade2", ModelPartBuilder.create(), ModelTransform.of(0.0F, 0.75F, 0.0F, 0.0F, 0.0F, 0.0F));
		ModelPartData blade2pt2_r1 = blade2.addChild("blade2pt2_r1", ModelPartBuilder.create().uv(0, 0).cuboid(-1.0F, -2.0F, 0.0F, 1.0F, 3.0F, 1.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(0.2F, -5.0F, 1.0F, 0.0F, 0.0F, 0.4363F));
		ModelPartData blade2pt1_r1 = blade2.addChild("blade2pt1_r1", ModelPartBuilder.create().uv(0, 0).cuboid(-2.0F, -6.0F, 0.0F, 2.0F, 8.0F, 1.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(2.0F, -7.25F, 1.0F, 0.0F, 0.0F, -0.6545F));
		ModelPartData blade1 = lizal_tri_boomerang.addChild("blade1", ModelPartBuilder.create().uv(0, 0).cuboid(-1.0F, -1.0F, 0.0F, 2.0F, 1.0F, 1.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(1.0F, 1.0F, 1.0F, 0.0F, 0.0F, 0.0F));
		ModelPartData bladept2_r1 = blade1.addChild("bladept2_r1", ModelPartBuilder.create().uv(0, 0).cuboid(-1.0F, -2.0F, 0.0F, 6.0F, 2.0F, 1.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(2.0F, -0.25F, 0.0F, 0.0F, 0.0F, -0.6981F));
		ModelPartData curve = lizal_tri_boomerang.addChild("curve", ModelPartBuilder.create().uv(0, 0).cuboid(-1.0F, -4.0F, 1.0F, 1.0F, 4.0F, 1.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F));
		ModelPartData handle = lizal_tri_boomerang.addChild("handle", ModelPartBuilder.create().uv(0, 0).cuboid(-1.0F, 0.0F, 0.0F, 1.0F, 4.0F, 1.0F, new Dilation(0.0F)).mirrored(false)
		.uv(0, 0).cuboid(-2.0F, -1.0F, 0.0F, 2.0F, 1.0F, 1.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(0.0F, 1.0F, 1.0F, 0.0F, 0.0F, 0.0F));

		return TexturedModelData.of(modelData, 32, 32);
	}

    @Override
	public void render(MatrixStack matrixStack, VertexConsumer	buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		
		this.base.render(matrixStack, buffer, packedLight, packedOverlay);
	}
}





