package codes.flappy.MessagePins;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Logger;

public class MessageListener extends ListenerAdapter {
    private static HashMap<MessageChannel, Message> watches = new HashMap<>();

    /**
     * Get a HashMap of the currently active watches
     * @return HashMap of the currently active watches in the format MessageChannel, Message
     */
    public static HashMap<MessageChannel, Message> getWatches()
    { return watches; }

    /**
     * Watch a channel for messages to keep a message at the bottom
     * Will not overwrite a watch if already present
     * @param toWatch The MessageChannel to watch
     * @param message The Message to keep at the bottom
     */
    public static void addWatch(@NotNull MessageChannel toWatch, @NotNull Message message)
    { watches.putIfAbsent(toWatch, message); }

    /**
     * Watch a channel for messages to keep a message at the bottom
     * Will overwrite a watch if already present
     */
    public static void forceAddWatch(@NotNull MessageChannel toWatch, @NotNull Message message)
    { watches.put(toWatch, message); }

    /**
     * Stop a channel from being watched
     * @param watchToDel The MessageChannel to stop watching
     */
    public static void delWatch(@Nullable MessageChannel watchToDel)
    { watches.remove(watchToDel); }

    public void onMessageReceived(MessageReceivedEvent e) {
        if (!watches.containsKey(e.getChannel())) {
            return;
        }

        Logger logger = Logger.getLogger("MessageListener");
        logger.info("Message sent in channel with pinned message.");

        if (e.getAuthor().isBot()) {
            logger.info("Sent by bot, ignoring.");
            return;
        }

        watches.get(e.getChannel()).delete().queue();
        watches.put(e.getChannel(),
                e.getChannel().sendMessage(watches.get(e.getChannel()).getContentRaw()).complete()
        );

        logger.info("Message updated to be at the bottom.");

    }
}
