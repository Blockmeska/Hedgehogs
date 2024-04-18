package blockmeska.mod.entity.custom;

import blockmeska.mod.entity.ModEntities;
import net.minecraft.entity.AnimationState;
import net.minecraft.entity.EntityPose;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class HedgehogEntity extends AnimalEntity {

    public final AnimationState idleAnimationState = new AnimationState();
    private int idleAnimationTimeout = 0;

    public HedgehogEntity(EntityType<? extends AnimalEntity> entityType, World world) {super(entityType, world);}

    private void setupAnimationStates() {
        if (this.idleAnimationTimeout <= 0) {
            this.idleAnimationTimeout = this.random.nextInt(40) + 80;
            this.idleAnimationState.start(this.age);
        } else {
            --this.idleAnimationTimeout;
        }
    }

    @Override
    protected void updateLimbs(float posDelta) {
        float f = this.getPose() == EntityPose.STANDING ? Math.min(posDelta * 6.0f, 1.0f) : 0.0f;
        this.limbAnimator.updateLimbs(f, 0.2f);
    }

    @Override
    public void tick() {
        super.tick();
        if(this.getWorld().isClient) {
            setupAnimationStates();
        }
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundEvents.ENTITY_BAT_HURT;
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(0, new SwimGoal(this));
        this.goalSelector.add(1, new EscapeDangerGoal(this, 0.9D));
        this.goalSelector.add(2, new AnimalMateGoal(this, 0.4D));
        this.goalSelector.add(3, new TemptGoal(this, 0.6D, Ingredient.ofItems(Items.MELON_SLICE), false));
        this.goalSelector.add(4, new FollowParentGoal(this, 0.6D));
        this.goalSelector.add(5, new WanderAroundFarGoal(this, 0.4D));
        this.goalSelector.add(6, new LookAtEntityGoal(this, PlayerEntity.class, 4f));
        this.goalSelector.add(7, new LookAroundGoal(this));
    }

    public static DefaultAttributeContainer.Builder createHedgehogAttributes() {
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 10)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.4f)
                .add(EntityAttributes.GENERIC_ARMOR, 0.7f)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 0.2f);
    }

    @Override
    public boolean isBreedingItem(ItemStack stack) {
        return stack.isOf(Items.MELON_SLICE);
    }

    @Override
    public ActionResult interactMob(PlayerEntity player, Hand hand) {
        ItemStack itemStack = player.getStackInHand(hand);
        Item item = itemStack.getItem();
        ActionResult actionResult;

        if (this.isBreedingItem(itemStack) && this.getHealth() < this.getMaxHealth()) {
            if (!player.getAbilities().creativeMode) {
                itemStack.decrement(1);
            }
            this.heal(item.getFoodComponent().getHunger());
            return ActionResult.SUCCESS;
        }
        if ((actionResult = super.interactMob(player, hand)).isAccepted() && !this.isBaby()) return actionResult;
        return ActionResult.SUCCESS;
    }

    @Nullable
    @Override
    public PassiveEntity createChild(ServerWorld world, PassiveEntity entity) {
        if(entity.getType() == ModEntities.FOREST_HEDGEHOG) {
            return ModEntities.FOREST_HEDGEHOG.create(world);
        } else if(entity.getType() == ModEntities.DESERT_HEDGEHOG) {
            return ModEntities.DESERT_HEDGEHOG.create(world);
        } else if(entity.getType() == ModEntities.SNOW_HEDGEHOG) {
            return ModEntities.SNOW_HEDGEHOG.create(world);
        } else {
            return ModEntities.DESERT_HEDGEHOG.create(world);
        }
    }
}
