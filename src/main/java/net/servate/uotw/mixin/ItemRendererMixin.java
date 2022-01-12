package net.servate.uotw.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.render.model.json.ModelTransformation;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
//import net.minecraft.util.math.Vec3f;
import net.minecraft.world.World;
import net.servate.uotw.client.render.item.LizalBoomerangItemRenderer;
import net.servate.uotw.client.render.item.LizalForkedBoomerangItemRenderer;
import net.servate.uotw.client.render.item.LizalTriBoomerangItemRenderer;
import net.servate.uotw.item.LizalBoomerang_Item;
import net.servate.uotw.item.LizalForkedBoomerang_Item;
import net.servate.uotw.item.Lizal_Tri_Boomerang_Item;
//import net.servate.uotw.item.tools.BeanzBroadsword;

@Environment(EnvType.CLIENT)
@Mixin(ItemRenderer.class)
public class ItemRendererMixin {
    @Inject(method = "Lnet/minecraft/client/render/item/ItemRenderer;renderItem(Lnet/minecraft/entity/LivingEntity;Lnet/minecraft/item/ItemStack;Lnet/minecraft/client/render/model/json/ModelTransformation$Mode;ZLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;Lnet/minecraft/world/World;III)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/item/ItemRenderer;renderItem(Lnet/minecraft/item/ItemStack;Lnet/minecraft/client/render/model/json/ModelTransformation$Mode;ZLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;IILnet/minecraft/client/render/model/BakedModel;)V"), locals = LocalCapture.CAPTURE_FAILSOFT)
    public void renderItemMixin(LivingEntity entity, ItemStack stack, ModelTransformation.Mode renderMode, boolean leftHanded, MatrixStack matrices, VertexConsumerProvider vertexConsumers,
            World world, int light, int overlay, int seed, CallbackInfo info, BakedModel bakedModel) {
        if ((stack.getItem() instanceof LizalBoomerang_Item && LizalBoomerangItemRenderer.INSTANCE.render(entity, stack, renderMode, leftHanded, matrices, vertexConsumers, light, overlay, bakedModel)) || (stack.getItem() instanceof LizalForkedBoomerang_Item && LizalForkedBoomerangItemRenderer.INSTANCE.render(entity, stack, renderMode, leftHanded, matrices, vertexConsumers, light, overlay, bakedModel)) || (stack.getItem() instanceof Lizal_Tri_Boomerang_Item && LizalTriBoomerangItemRenderer.INSTANCE.render(entity, stack, renderMode, leftHanded, matrices, vertexConsumers, light, overlay, bakedModel))) {
            matrices.scale(0, 0, 0);
        }
    }

    /*@Inject(method = "Lnet/minecraft/client/render/item/ItemRenderer;renderItem(Lnet/minecraft/entity/LivingEntity;Lnet/minecraft/item/ItemStack;Lnet/minecraft/client/render/model/json/ModelTransformation$Mode;ZLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;Lnet/minecraft/world/World;III)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/item/ItemRenderer;renderItem(Lnet/minecraft/item/ItemStack;Lnet/minecraft/client/render/model/json/ModelTransformation$Mode;ZLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;IILnet/minecraft/client/render/model/BakedModel;)V"))
    public void renderItemMixinTwo(LivingEntity entity, ItemStack stack, ModelTransformation.Mode renderMode, boolean leftHanded, MatrixStack matrices, VertexConsumerProvider vertexConsumers,
            World world, int light, int overlay, int seed, CallbackInfo info) {
        if (entity != null && (renderMode == ModelTransformation.Mode.FIRST_PERSON_LEFT_HAND || renderMode == ModelTransformation.Mode.FIRST_PERSON_RIGHT_HAND)
                && stack.getItem() instanceof BeanzBroadsword && entity.isBlocking()) {
            matrices.multiply(Vec3f.POSITIVE_Z.getDegreesQuaternion(20F));
        }
    }*/

}
