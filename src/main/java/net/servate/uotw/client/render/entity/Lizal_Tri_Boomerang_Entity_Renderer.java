package net.servate.uotw.client.render.entity;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.servate.uotw.entity.Lizal_Tri_Boomerang_Entity;
import net.servate.uotw.client.render.entity.model.Lizal_Tri_Boomerang_Entity_Model;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.Vec3f;
import net.minecraft.entity.EntityType;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.registry.Registry;

import java.util.HashMap;
import java.util.Map;

@Environment(EnvType.CLIENT)
public class Lizal_Tri_Boomerang_Entity_Renderer extends EntityRenderer<Lizal_Tri_Boomerang_Entity> {
    private static final Map<EntityType<?>, Identifier> TEXTURES = new HashMap<>();
    private final Lizal_Tri_Boomerang_Entity_Model model = new Lizal_Tri_Boomerang_Entity_Model(Lizal_Tri_Boomerang_Entity_Model.getTexturedModelData().createModel());

    public Lizal_Tri_Boomerang_Entity_Renderer(EntityRendererFactory.Context context) {
        super(context);
    }

    @Override
    public void render(Lizal_Tri_Boomerang_Entity lizal_Tri_Boomerang_Entity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
        matrixStack.push();
        matrixStack.multiply(Vec3f.POSITIVE_Y.getDegreesQuaternion(MathHelper.lerp(g, lizal_Tri_Boomerang_Entity.prevYaw, lizal_Tri_Boomerang_Entity.getYaw()) - 90.0F));
        matrixStack.multiply(Vec3f.POSITIVE_Z.getDegreesQuaternion(MathHelper.lerp(g, lizal_Tri_Boomerang_Entity.prevPitch, lizal_Tri_Boomerang_Entity.getPitch()) + 90.0F));
        VertexConsumer vertexConsumer = ItemRenderer.getItemGlintConsumer(vertexConsumerProvider, model.getLayer(this.getTexture(lizal_Tri_Boomerang_Entity)), false, lizal_Tri_Boomerang_Entity.enchantingGlint());

        matrixStack.translate(0.0D, -0.75D, 0.0D);
        model.render(matrixStack, vertexConsumer, i, OverlayTexture.DEFAULT_UV, 1.0F, 1.0F, 1.0F, 1.0F);
        matrixStack.scale(1.0F, -1.0F, 1.0F);
        matrixStack.pop();
        super.render(lizal_Tri_Boomerang_Entity, f, g, matrixStack, vertexConsumerProvider, i);
    }

    @Override
    public Identifier getTexture(Lizal_Tri_Boomerang_Entity lizalTriBoomerang_Entity) {
        return getTexture(lizalTriBoomerang_Entity.getType());
    }

    public static Identifier getTexture(EntityType<?> type) {
        if (!TEXTURES.containsKey(type)) {
            TEXTURES.put(type, new Identifier("uotw", "textures/entity/boomerang/" + Registry.ENTITY_TYPE.getId(type).getPath() + ".png"));
        }
        return TEXTURES.get(type);
    }
}