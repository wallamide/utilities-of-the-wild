package net.servate.uotw.material;

import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;

public class LizalMaterial implements ToolMaterial {
    
    private static int durability = 576;
    private static float miningSpeed = 3.0F;
    private static float attackDamage = 2.0F;
    private static int miningLevel = 2;
    private static int enchantability = 15;

    @Override 
    public int getDurability(){
        return durability;
    }

    @Override 
    public float getMiningSpeedMultiplier() {
        return miningSpeed;
    }

    @Override
    public float getAttackDamage() {
        return attackDamage;
    }

    @Override
    public int getMiningLevel() {
        return miningLevel;
    } 

    @Override
    public int getEnchantability() {
        return enchantability;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return null;
    }

}
