package net.servate.uotw.entity;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.jetbrains.annotations.Nullable;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.ProjectileDamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtList;
import net.minecraft.network.Packet;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.servate.uotw.client.EntitySpawnPacket;
import net.servate.uotw.item.LizalForkedBoomerang_Item;

public class LizalForkedBoomerang_Entity extends PersistentProjectileEntity {
    private static final TrackedData<Byte> LOYALTY;
    private static final TrackedData<Boolean> ENCHANTMENT_GLINT;
    private ItemStack lizalForkedBoomerang;
    private final Set<UUID> piercedEntities = new HashSet<>();
    public int returnTimer;
    private boolean dealtDamage;

    public LizalForkedBoomerang_Entity(EntityType<? extends LizalForkedBoomerang_Entity> entityType, World world, LizalForkedBoomerang_Item item) {
        super(entityType, world);
        this.lizalForkedBoomerang = new ItemStack(item);
    }

    public LizalForkedBoomerang_Entity(World world, LivingEntity owner, LizalForkedBoomerang_Item item, ItemStack stack) {
        super(item.getEntityType(), owner, world);
        this.lizalForkedBoomerang = new ItemStack(item);
        this.lizalForkedBoomerang = stack.copy();
        this.dataTracker.set(ENCHANTMENT_GLINT, stack.hasGlint());
        this.dataTracker.set(LOYALTY, (byte) EnchantmentHelper.getLoyalty(stack));
    }

    @Environment(EnvType.CLIENT)
    public LizalForkedBoomerang_Entity(World world, double x, double y, double z, LizalForkedBoomerang_Item item) {
        super(item.getEntityType(), x, y, z, world);
        this.lizalForkedBoomerang = new ItemStack(item);
    }

    @Override
    protected void initDataTracker() {
        super.initDataTracker();
        this.dataTracker.startTracking(LOYALTY, (byte) 0);
        this.dataTracker.startTracking(ENCHANTMENT_GLINT, false);
    }

    @Override
    public Packet<?> createSpawnPacket() {
        return EntitySpawnPacket.createPacket(this);
    }

    @Override
    protected ItemStack asItemStack() {
        return this.lizalForkedBoomerang.copy();
    }

    @Environment(EnvType.CLIENT)
    public boolean enchantingGlint() {
        return this.dataTracker.get(ENCHANTMENT_GLINT);
    }

    @Override
    protected void onEntityHit(EntityHitResult entityHitResult) {
        int level = EnchantmentHelper.getLevel(Enchantments.PIERCING, this.lizalForkedBoomerang);
        Entity hitEntity = entityHitResult.getEntity();
        if (this.piercedEntities.contains(hitEntity.getUuid()) || this.piercedEntities.size() > level) {
            return;
        }
        this.piercedEntities.add(hitEntity.getUuid());
        float damage = ((LizalForkedBoomerang_Item) this.lizalForkedBoomerang.getItem()).getAttackDamage() * 2;
        if (hitEntity instanceof AnimalEntity) {
            int impalingLevel = EnchantmentHelper.getLevel(Enchantments.IMPALING, this.lizalForkedBoomerang);
            if (impalingLevel > 0) {
                damage += impalingLevel * 1.5F;
            }
        }
        this.dealtDamage = true;
        Entity owner = this.getOwner();
        DamageSource damageSource = createDamageSource(this, owner == null ? this : owner);
        SoundEvent soundEvent = SoundEvents.ITEM_TRIDENT_HIT;

        if (hitEntity.damage(damageSource, damage)) {
            if (hitEntity.getType() == EntityType.ENDERMAN) {
                return;
            }
            if (hitEntity instanceof LivingEntity) {
                LivingEntity hitLivingEntity = (LivingEntity) hitEntity;
                if (owner instanceof LivingEntity) {
                    EnchantmentHelper.onUserDamaged(hitLivingEntity, owner);
                    EnchantmentHelper.onTargetDamaged((LivingEntity) owner, hitLivingEntity);
                }
                this.playSound(soundEvent, 1.0F, 1.0F);
                this.onHit(hitLivingEntity);
            }
        }

        if (this.piercedEntities.size() > level) {
            this.setVelocity(this.getVelocity().multiply(-0.01D, -0.1D, -0.01D));
        } else {
            this.setVelocity(this.getVelocity().multiply(0.75));
        }

    }

    @Override
    @Nullable
    protected EntityHitResult getEntityCollision(Vec3d currentPosition, Vec3d nextPosition) {
        return this.dealtDamage ? null : super.getEntityCollision(currentPosition, nextPosition);
    }

    @Override
    public void tick() {
        if (this.inGroundTime > 4) {
            this.dealtDamage = true;
        }

        Entity entity = this.getOwner();
        if ((this.dealtDamage || this.isNoClip()) && entity != null) {
            int i = (Byte) this.dataTracker.get(LOYALTY);
            if (i > 0 && !this.isOwnerAlive()) {
                if (!this.world.isClient && this.pickupType == PersistentProjectileEntity.PickupPermission.ALLOWED) {
                    this.dropStack(this.asItemStack(), 0.1F);
                }

                this.discard();
            } else if (i > 0) {
                this.setNoClip(true);
                Vec3d vec3d = new Vec3d(entity.getX() - this.getX(), entity.getEyeY() - this.getY(), entity.getZ() - this.getZ());
                this.setPos(this.getX(), this.getY() + vec3d.y * 0.015D * (double) i, this.getZ());
                if (this.world.isClient) {
                    this.lastRenderY = this.getY();
                }

                double d = 0.05D * (double) i;
                this.setVelocity(this.getVelocity().multiply(0.95D).add(vec3d.normalize().multiply(d)));
                if (this.returnTimer == 0) {
                    this.playSound(SoundEvents.ITEM_TRIDENT_RETURN, 10.0F, 1.0F);
                }

                ++this.returnTimer;
            }
        }

        super.tick();
    }

    private boolean isOwnerAlive() {
        Entity entity = this.getOwner();
        if (entity != null && entity.isAlive()) {
            return !(entity instanceof ServerPlayerEntity) || !entity.isSpectator();
        } else {
            return false;
        }
    }

    @Override
    public void onPlayerCollision(PlayerEntity player) {
        Entity entity = this.getOwner();
        if (entity == null || entity.getUuid() == player.getUuid()) {
            super.onPlayerCollision(player);
        }
    }

    @Override
    public void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
        if (nbt.contains("lizal_forked_boomerang", 10)) {
            this.lizalForkedBoomerang = ItemStack.fromNbt(nbt.getCompound("lizal_forked_boomerang"));
            this.dataTracker.set(ENCHANTMENT_GLINT, this.lizalForkedBoomerang.hasGlint());
        }

        this.piercedEntities.clear();
        if (nbt.contains("lizal_forked_boomerang_hit", 9)) {
            for (NbtElement hitEntity : nbt.getList("lizal_forked_boomerang_hit", 10)) {
                this.piercedEntities.add(((NbtCompound) hitEntity).getUuid("UUID"));
            }
        }
        this.dealtDamage = nbt.getBoolean("DealtDamage");
        this.dataTracker.set(LOYALTY, (byte) EnchantmentHelper.getLoyalty(this.lizalForkedBoomerang));
    }

    @Override
    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        nbt.put("lizal_forked_boomerang", this.lizalForkedBoomerang.writeNbt(new NbtCompound()));

        NbtList tags = new NbtList();
        for (UUID uuid : this.piercedEntities) {
            NbtCompound c = new NbtCompound();
            c.putUuid("UUID", uuid);
            tags.add(c);
        }
        nbt.putBoolean("DealtDamage", this.dealtDamage);
        nbt.put("lizal_forked_boomerang_hit", tags);
    }

    @Override
    public void age() {
        int i = (Byte) this.dataTracker.get(LOYALTY);
        if (this.pickupType != PersistentProjectileEntity.PickupPermission.ALLOWED || i <= 0) {
            super.age();
        }

    }

    @Override
    @Environment(EnvType.CLIENT)
    public boolean shouldRender(double cameraX, double cameraY, double cameraZ) {
        return true;
    }

    static {
        LOYALTY = DataTracker.registerData(LizalForkedBoomerang_Entity.class, TrackedDataHandlerRegistry.BYTE);
        ENCHANTMENT_GLINT = DataTracker.registerData(LizalForkedBoomerang_Entity.class, TrackedDataHandlerRegistry.BOOLEAN);
    }

    public static DamageSource createDamageSource(Entity entity, Entity owner) {
        return new ProjectileDamageSource("lizal_forked_boomerang", entity, owner).setProjectile();
    }

}