package com.redstonedev.midnighttransit.item;

import com.redstonedev.midnighttransit.init.ModSounds;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundSetSubtitleTextPacket;
import net.minecraft.network.protocol.game.ClientboundSetTitleTextPacket;
import net.minecraft.network.protocol.game.ClientboundSetTitlesAnimationPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

/**
 * Message [Audio] item - right-click to play the audio and show the message text as a
 * subtitle. The full transcript fades in/out across several seconds via the vanilla
 * title/subtitle protocol.
 */
public class MessageAudioItem extends Item {

    public MessageAudioItem(Properties props) {
        super(props);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        if (!level.isClientSide) {
            // Play the audio at the player's position.
            level.playSound(null, player.getX(), player.getY(), player.getZ(),
                    ModSounds.MESSAGE_AUDIO.get(), SoundSource.VOICE, 1.0F, 1.0F);

            if (player instanceof ServerPlayer) {
                ServerPlayer sp = (ServerPlayer) player;
                // Long display so the player can actually read it (fade-in 10t, stay 200t, fade-out 20t = ~11.5s).
                sp.connection.send(new ClientboundSetTitlesAnimationPacket(10, 200, 20));
                sp.connection.send(new ClientboundSetTitleTextPacket(
                        Component.literal("Message [Audio]").withStyle(ChatFormatting.RED, ChatFormatting.BOLD)));
                sp.connection.send(new ClientboundSetSubtitleTextPacket(
                        Component.literal("Whatever you do, do not go outside when dark...")));
            }
        }
        return InteractionResultHolder.success(stack);
    }
}
