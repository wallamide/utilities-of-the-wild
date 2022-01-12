package net.servate.uotw.client.render.entity.model;

import net.minecraft.client.model.Dilation;
import net.minecraft.client.model.Model;
import net.minecraft.client.model.ModelData;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.model.ModelPartBuilder;
import net.minecraft.client.model.ModelPartData;
import net.minecraft.client.model.ModelTransform;
import net.minecraft.client.model.TexturedModelData;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.util.math.MatrixStack;

public class LizalForkedBoomerangEntityModel extends Model{
   private final ModelPart base;

   public LizalForkedBoomerangEntityModel(ModelPart base){ super(RenderLayer::getEntitySolid); this.base = base.getChild("base");}

   public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();

		ModelPartData bb_main = modelPartData.addChild("base", ModelPartBuilder.create().uv(0, 0).cuboid(-1.0F, -7.0F, 0.0F, 1.0F, 4.0F, 1.0F, new Dilation(0.0F)).mirrored(false)
		.uv(0, 0).cuboid(-2.1F, -8.0F, 0.0F, 3.0F, 1.0F, 1.0F, new Dilation(0.0F)).mirrored(false)
		.uv(0, 0).cuboid(-1.6F, -9.5F, 0.0F, 2.0F, 2.0F, 1.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(0.0F, 24.0F, 0.0F, 0.0F, 0.0F, 0.0F));
		ModelPartData cube_r1 = bb_main.addChild("cube_r1", ModelPartBuilder.create().uv(0, 0).cuboid(-1.0F, -6.0F, 0.0F, 1.0F, 7.0F, 1.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(-3.5F, -13.0F, 0.0F, 0.0F, 0.0F, -0.5236F));
		ModelPartData cube_r2 = bb_main.addChild("cube_r2", ModelPartBuilder.create().uv(0, 0).cuboid(-3.0F, -6.0F, 0.0F, 3.0F, 6.0F, 1.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(1.25F, -11.0F, 0.0F, 0.0F, 0.0F, 0.8639F));
		ModelPartData cube_r3 = bb_main.addChild("cube_r3", ModelPartBuilder.create().uv(0, 0).cuboid(-1.0F, -6.0F, 0.0F, 1.0F, 6.0F, 1.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(0.0F, -9.0F, 0.0F, 0.0F, 0.0F, -0.5672F));
		ModelPartData cube_r4 = bb_main.addChild("cube_r4", ModelPartBuilder.create().uv(0, 0).cuboid(-1.0F, -5.0F, 0.0F, 2.0F, 5.0F, 1.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(-0.75F, -8.25F, 0.0F, 0.0F, 0.0F, 0.8639F));
         
      
      return TexturedModelData.of(modelData, 32, 32);
         
   }

   @Override
   public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
      this.base.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
   }
}