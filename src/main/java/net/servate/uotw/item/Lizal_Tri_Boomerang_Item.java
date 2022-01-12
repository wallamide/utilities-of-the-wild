package net.servate.uotw.item;

import java.util.function.Supplier;

import net.servate.uotw.entity.Lizal_Tri_Boomerang_Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.world.World;

public class Lizal_Tri_Boomerang_Item extends SwordItem {

    private final Supplier<EntityType<Lizal_Tri_Boomerang_Entity>> typeSupplier;
    private EntityType<Lizal_Tri_Boomerang_Entity> cachedType = null;

    public Lizal_Tri_Boomerang_Item(ToolMaterial toolMaterial, float attackDamage, float attackSpeed, Supplier<EntityType<Lizal_Tri_Boomerang_Entity>> typeSupplier, Settings settings) {
        super(toolMaterial, (int) attackDamage, attackSpeed, settings);
        this.typeSupplier = typeSupplier;
    }

    public EntityType<Lizal_Tri_Boomerang_Entity> getEntityType() {
        if (cachedType == null) {
            cachedType = typeSupplier.get();
        }
        return cachedType;
    }

    @Override
    public void onStoppedUsing(ItemStack stack, World world, LivingEntity user, int remainingUseTicks) {
        if (user instanceof PlayerEntity) {
            PlayerEntity playerEntity = (PlayerEntity) user;
            int i = this.getMaxUseTime(stack) - remainingUseTicks;
            if (i >= 10) {
                if (!world.isClient) {
                    stack.damage(1, playerEntity, entity -> entity.sendToolBreakStatus(user.getActiveHand()));
                    Lizal_Tri_Boomerang_Entity lizalTriBoomerang_Entity = new Lizal_Tri_Boomerang_Entity(world, playerEntity, this, stack);
                    lizalTriBoomerang_Entity.setVelocity(playerEntity, playerEntity.getPitch(), playerEntity.getYaw(), 1.0F, 2.5F, 1.0F);
                    if (playerEntity.isCreative()) {
                        lizalTriBoomerang_Entity.pickupType = PersistentProjectileEntity.PickupPermission.CREATIVE_ONLY;
                    }
                    world.spawnEntity(lizalTriBoomerang_Entity);
                    world.playSoundFromEntity(null, lizalTriBoomerang_Entity, SoundEvents.ITEM_TRIDENT_THROW, SoundCategory.PLAYERS, 1.0F, 1.0F);
                    if (!playerEntity.isCreative()) {
                        playerEntity.getInventory().removeOne(stack);
                    }
                }

                playerEntity.incrementStat(Stats.USED.getOrCreateStat(this));
            }
        }
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        if (itemStack.getDamage() >= itemStack.getMaxDamage() - 1) {
            return TypedActionResult.fail(itemStack);
        } else {
            user.setCurrentHand(hand);
            return TypedActionResult.consume(itemStack);
        }
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.SPEAR;
    }

    @Override
    public int getMaxUseTime(ItemStack stack) {
        return 72000;
    }
}