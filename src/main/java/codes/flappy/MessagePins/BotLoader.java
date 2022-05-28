package codes.flappy.MessagePins;

import codes.flappy.MessagePins.command.CommandListener;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import org.jetbrains.annotations.NotNull;

import javax.security.auth.login.LoginException;
import java.util.logging.Logger;

/**
 * Main class which builds the bot
 */
public class BotLoader extends ListenerAdapter {

    /**
     * The global logger
     */
    public static final Logger gLogger = Logger.getGlobal();

    private static final Logger logger = Logger.getLogger("BotLoader");

    /**
     * Main method
     * @param args Application arguments
     */
    public static void main(String[] args) {
        gLogger.info("Starting...");

        JDABuilder builder = JDABuilder.createLight(System.getenv("BOT_TOKEN"))
                .addEventListeners(new BotLoader(), new CommandListener(), new MessageListener())
                .setActivity(Activity.watching("messages | /pin"));

        try {
            builder.build();
        } catch(LoginException e) {
            logger.severe("LoginException when building the bot: " + e.getCause());
            e.printStackTrace();
            System.exit(1);
        }

        logger.info("Bot built.");
    }

    public void onReady(@NotNull ReadyEvent e) {
        logger.info("Registering commands...");

        // TODO: Change this to global commands
        Guild g = e.getJDA().getGuildById(952834115533676545L);
        assert g != null;
        g.updateCommands().addCommands(
                Commands.slash("pin", "Pin a message to the bottom of a channel")
                        .addOption(OptionType.CHANNEL, "channel", "The channel to pin to", true)
                        .addOption(OptionType.STRING, "message" , "The message to pin", true),
                Commands.slash("unpin", "Unpin a message from a channel")
                        .addOption(OptionType.CHANNEL, "channel", "The channel to unpin from", true)
        ).complete();

        logger.info("Commands registered.");
        gLogger.info("Bot is ready.");
    }


}
