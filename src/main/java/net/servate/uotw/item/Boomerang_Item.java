package net.servate.uotw.item;

import java.util.function.Supplier;

import net.servate.uotw.entity.Boomerang_Entity;
import net.servate.uotw.reg.UotWSounds;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.sound.SoundCategory;
//import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.world.World;

public class Boomerang_Item extends SwordItem{

    private final Supplier<EntityType<Boomerang_Entity>> typeSupplier;
    private EntityType<Boomerang_Entity> cachedType = null;

    public Boomerang_Item(ToolMaterial toolMaterial, float attackDamage, float attackSpeed, Supplier<EntityType<Boomerang_Entity>> typeSupplier, Settings settings) {
        super(toolMaterial, (int) attackDamage, attackSpeed, settings);
        this.typeSupplier = typeSupplier;
    }

    public EntityType<Boomerang_Entity> getEntityType() {
        if (cachedType == null) {cachedType = typeSupplier.get();}
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
                    Boomerang_Entity BoomerangEntity = new Boomerang_Entity(world, playerEntity, this, stack);
                    BoomerangEntity.setVelocity(playerEntity, playerEntity.getPitch(), playerEntity.getYaw(), 0.0F, 2.5F, 1.0F);
                    if (playerEntity.isCreative()) {
                        BoomerangEntity.pickupType = PersistentProjectileEntity.PickupPermission.CREATIVE_ONLY;
                    }
                    world.spawnEntity(BoomerangEntity);
                    world.playSoundFromEntity(null, BoomerangEntity, UotWSounds.BOOMERANG_THROW_EVENT, SoundCategory.PLAYERS, 1.0F, 1.0F);
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
