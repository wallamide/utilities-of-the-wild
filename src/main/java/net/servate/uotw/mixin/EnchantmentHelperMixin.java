package net.servate.uotw.mixin;

import java.util.ArrayList;
import java.util.List;

import net.servate.uotw.item.Boomerang_Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentLevelEntry;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.ItemStack;


@Mixin(EnchantmentHelper.class)
public class EnchantmentHelperMixin {

@Inject(method = "getPossibleEntries(ILnet/minecraft/item/ItemStack;Z)Ljava/util/List;", at = @At("RETURN"), cancellable = true)
private static void getPossibleEntriesMixin(int i, ItemStack stack, boolean treasureAllowed, CallbackInfoReturnable<List<EnchantmentLevelEntry>> info) {
    if (stack.getItem() instanceof Boomerang_Item) {
        List<EnchantmentLevelEntry> currentEnchantments = info.getReturnValue();
        List<EnchantmentLevelEntry> enchantments = new ArrayList<>();
        currentEnchantments.forEach(enchantment -> {
            if (!(enchantment.enchantment.type == EnchantmentTarget.TRIDENT) || enchantment.enchantment == Enchantments.IMPALING) {
                enchantments.add(enchantment);
            }
        });
        Enchantment piercing = Enchantments.PIERCING;
        for (int level = piercing.getMaxLevel(); level > piercing.getMinLevel() - 1; --level) {
            if (i >= piercing.getMinPower(level) && i <= piercing.getMaxPower(level)) {
                enchantments.add(new EnchantmentLevelEntry(piercing, level));
                break;
            }
        }
        info.setReturnValue(enchantments);
    }
  }
}

