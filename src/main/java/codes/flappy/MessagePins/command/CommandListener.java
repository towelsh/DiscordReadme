package codes.flappy.MessagePins.command;

import codes.flappy.MessagePins.exception.MessageAlreadyPinnedException;
import codes.flappy.MessagePins.exception.NoMessagePinnedException;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class CommandListener extends ListenerAdapter {
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent e) {
        // if there is no channel argument
        try {
            if (e.getOption("channel").getAsTextChannel() == null) {
                e.replyEmbeds(new EmbedBuilder()
                        .setTitle("Error")
                        .setDescription("You didn't provide a valid text channel.").build()
                ).setEphemeral(true).queue();
                return;
            }
        } catch(NullPointerException ex) {
            e.replyEmbeds(new EmbedBuilder()
                    .setTitle("Error")
                    .setDescription("You didn't provide a valid text channel.").build()
            ).setEphemeral(true).queue();
            return;
        }

        try {
            switch (e.getName()) {
                case "pin" -> {
                    CommandExecutor.pinMessage(e,
                            Objects.requireNonNull(Objects.requireNonNull(e.getOption("channel")).getAsMessageChannel()),
                            e.getOption("message").getAsString());
                }
                case "unpin" -> CommandExecutor.unpinMessage(e, Objects.requireNonNull(Objects.requireNonNull(e.getOption("channel")).getAsMessageChannel()));
                default -> {
                    e.replyEmbeds(new EmbedBuilder()
                            .setTitle("Error")
                            .setDescription("Unrecognised slash command... How did you do that?").build()
                    ).setEphemeral(true).queue();
                }
            }
        } catch(MessageAlreadyPinnedException ex) {
            e.replyEmbeds(new EmbedBuilder()
                    .setTitle("Error")
                    .setDescription("There is already a message pinned in that channel. Unpin it first.").build()
            ).setEphemeral(true).queue();
        } catch(NoMessagePinnedException ex) {
            e.replyEmbeds(new EmbedBuilder()
                    .setTitle("Error")
                    .setDescription("There is no message pinned in that channel.").build()
            ).setEphemeral(true).queue();
        } catch(NullPointerException ex) {
            e.replyEmbeds(new EmbedBuilder()
                    .setTitle("Error")
                    .setDescription("You didn't provide a message.").build()
            ).setEphemeral(true).queue();
        }
    }
}
