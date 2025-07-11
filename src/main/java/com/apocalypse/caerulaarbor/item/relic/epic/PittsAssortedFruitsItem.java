package com.apocalypse.caerulaarbor.item.relic.epic;

import com.apocalypse.caerulaarbor.capability.Relic;
import com.apocalypse.caerulaarbor.init.ModItems;
import com.apocalypse.caerulaarbor.item.relic.RelicItem;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Sheep;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;

public class PittsAssortedFruitsItem extends RelicItem {

    public PittsAssortedFruitsItem() {
        super(new Item.Properties().stacksTo(64).rarity(Rarity.EPIC)
                .food((new FoodProperties.Builder()).nutrition(6).saturationMod(0.5f)
                        .effect(() -> new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 280, 1), 1.0f)
                        .effect(() -> new MobEffectInstance(MobEffects.DAMAGE_BOOST, 280, 1), 1.0f)
                        .effect(() -> new MobEffectInstance(MobEffects.HEALTH_BOOST, 560, 1), 1.0f)
                        .alwaysEat().build())
        );
    }

    @Override
    @ParametersAreNonnullByDefault
    public @NotNull InteractionResult interactLivingEntity(ItemStack pStack, Player pPlayer, LivingEntity pInteractionTarget, InteractionHand pUsedHand) {
        if (pInteractionTarget instanceof Sheep) {
            pInteractionTarget.setCustomName(Component.literal("jeb_"));
            pStack.shrink(1);
            return InteractionResult.SUCCESS;
        }
        return super.interactLivingEntity(pStack, pPlayer, pInteractionTarget, pUsedHand);
    }

    @Override
    public void appendHoverText(ItemStack itemstack, Level level, @NotNull List<Component> list, @NotNull TooltipFlag flag) {
        list.add(Component.translatable("item.caerula_arbor.pitts_assorted_fruits.des_1").withStyle(ChatFormatting.AQUA));
        list.add(Component.translatable("item.caerula_arbor.pitts_assorted_fruits.des_2").withStyle(ChatFormatting.GRAY));
    }

    @Override
    public @Nullable Relic getRelic() {
        return Relic.PITTS_ASSORTED_FRUITS;
    }

    @Override
    @ParametersAreNonnullByDefault
    public @NotNull ItemStack finishUsingItem(ItemStack pStack, Level world, LivingEntity entity) {
        ItemStack rt = new ItemStack(ModItems.PAPER_BAG.get());
        if (super.finishUsingItem(pStack, world, entity).isEmpty()) {
            return rt;
        } else {
            if (entity instanceof Player player && !player.getAbilities().instabuild) {
                if (!player.getInventory().add(rt)) {
                    player.drop(rt, false);
                }
            }
            return pStack;
        }
    }
}
