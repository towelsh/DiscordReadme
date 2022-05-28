package codes.flappy.MessagePins.command;

import codes.flappy.MessagePins.MessageListener;
import codes.flappy.MessagePins.exception.MessageAlreadyPinnedException;
import codes.flappy.MessagePins.exception.NoMessagePinnedException;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

/**
 * The class to execute commands
 */
public class CommandExecutor {
    /**
     * Pin a message to a channel
     * @param command The slash command to respond to
     * @param channel The channel to pin the message to
     * @param message The message to pin
     * @throws MessageAlreadyPinnedException If there is already a message pinned to the channel
     */
    public static void pinMessage(
            @NotNull SlashCommandInteractionEvent command,
            @NotNull MessageChannel channel,
            @NotNull String message) throws MessageAlreadyPinnedException {
        if (MessageListener.getWatches().get(channel) != null) {
            throw new MessageAlreadyPinnedException("Message already pinned in "+channel.getId());
        }

        command.replyEmbeds(new EmbedBuilder()
                .setTitle("Message Pinned")
                .setDescription("I've pinned your message.")
                .setColor(Color.CYAN).build()
        ).setEphemeral(true).complete();

        Message toPin = channel.sendMessage(message).complete();
        MessageListener.addWatch(channel, toPin);
    }

    /**
     * Unpin a message from a channel
     * @param command The slash command to respond to
     * @param channel The channel to unpin the message from
     * @throws NoMessagePinnedException If there is no message pinned to the channel
     */
    public static void unpinMessage(
            @NotNull SlashCommandInteractionEvent command,
            @NotNull MessageChannel channel) throws NoMessagePinnedException {
        if (!MessageListener.getWatches().containsKey(channel)) {
            throw new NoMessagePinnedException("No message pinned in "+channel.getId());
        }

        MessageListener.getWatches().get(channel).delete().queue();
        MessageListener.delWatch(channel);

        command.replyEmbeds(new EmbedBuilder()
                .setTitle("Message Unpinned")
                .setDescription("I've unpinned your message.")
                .setColor(Color.CYAN).build()
        ).setEphemeral(true).queue();
    }
}
