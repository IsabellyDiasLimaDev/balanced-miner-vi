package br.com.isis.balanced_miner_vi;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;

@EventBusSubscriber(modid = BalancedMinerVI.MODID)
public final class MiningSpeedEvents {
    private static final ResourceKey<Enchantment> EFFICIENCY_VI = ResourceKey.create(
        Registries.ENCHANTMENT,
        Identifier.fromNamespaceAndPath(BalancedMinerVI.MODID, "efficiency_vi")
    );
    private static final TagKey<Block> MINEABLE_STANDARD = TagKey.create(
        Registries.BLOCK,
        Identifier.fromNamespaceAndPath(BalancedMinerVI.MODID, "mineable_standard")
    );
    private static final float EFFICIENCY_V_BONUS = 26.0F;
    private static final float OBSIDIAN_EFFICIENCY_VI_BONUS = EFFICIENCY_V_BONUS * 15F;
    private static final float BALANCED_EFFICIENCY_VI_BONUS = 34.0F;
    private static final float STANDARD_MINEABLE_FAST_SPEED = 132.0F;
    private static final float MIN_SAFE_HARDNESS = 0.5F;

    private MiningSpeedEvents() {
    }

    @SubscribeEvent
    public static void onBreakSpeed(PlayerEvent.BreakSpeed event) {
        Player player = event.getEntity();
        ItemStack tool = player.getMainHandItem();
        BlockState state = event.getState();

        if (!tool.is(ItemTags.PICKAXES) || !state.is(BlockTags.MINEABLE_WITH_PICKAXE)) {
            return;
        }

        Level level = player.level();
        float hardness = state.getDestroySpeed(level, event.getPosition().orElse(player.blockPosition()));
        if (hardness < MIN_SAFE_HARDNESS || isInstamineBlacklisted(state)) {
            return;
        }

        Holder<Enchantment> enchantment = level.registryAccess()
            .lookupOrThrow(Registries.ENCHANTMENT)
            .getOrThrow(EFFICIENCY_VI);
        if (tool.getEnchantmentLevel(enchantment) <= 0) {
            return;
        }

        if (state.is(Blocks.OBSIDIAN)) {
            event.setNewSpeed(event.getNewSpeed() + OBSIDIAN_EFFICIENCY_VI_BONUS);
            return;
        }

        if (!state.is(MINEABLE_STANDARD)) {
            return;
        }

        Holder<Enchantment> vanillaEfficiency = level.registryAccess()
            .lookupOrThrow(Registries.ENCHANTMENT)
            .getOrThrow(Enchantments.EFFICIENCY);
        float vanillaBonus = getEfficiencyBonus(tool.getEnchantmentLevel(vanillaEfficiency));
        float balancedBonus = Math.max(0.0F, BALANCED_EFFICIENCY_VI_BONUS - vanillaBonus);
        float balancedSpeed = event.getNewSpeed() + balancedBonus;
        balancedSpeed = Math.max(balancedSpeed, STANDARD_MINEABLE_FAST_SPEED);

        event.setNewSpeed(balancedSpeed);
    }

    private static boolean isInstamineBlacklisted(BlockState state) {
        return state.is(Blocks.NETHERRACK)
            || state.is(Blocks.MELON)
            || state.is(Blocks.PUMPKIN)
            || state.is(Blocks.QUARTZ_BLOCK)
            || state.is(Blocks.CHISELED_QUARTZ_BLOCK)
            || state.is(Blocks.QUARTZ_PILLAR)
            || state.is(Blocks.QUARTZ_BRICKS)
            || state.is(Blocks.SMOOTH_QUARTZ);
    }

    private static float getEfficiencyBonus(int level) {
        return level > 0 ? level * level + 1.0F : 0.0F;
    }
}
