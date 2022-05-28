# Pin a Discord message to the bottom of a channel.
[![CodeFactor](https://www.codefactor.io/repository/github/flappythebats/discordmessagealwaysatthebottom/badge)](https://www.codefactor.io/repository/github/flappythebats/discordmessagealwaysatthebottom)
[![Releases](https://img.shields.io/github/v/release/FlappyTheBats/DiscordMessageAlwaysAtTheBottom?logo=github)](https://github.com/FlappyTheBats/DiscordMessageAlwaysAtTheBottom/releases)
![Heroku](https://pyheroku-badge.herokuapp.com/?app=discord-message-pins&style=flat)

[![](https://img.shields.io/badge/discord-add%20to%20server-blue?style=for-the-badge&logo=discord)](https://discord.com/api/oauth2/authorize?client_id=979827675361337384&permissions=216064&scope=bot%20applications.commands)

Need users to read a message before they post in a channel?
This bot allows you to keep a message always at the bottom of a channel (or multiple channels).

![](https://lh3.googleusercontent.com/pw/AM-JKLWoff5pBrJlBMmmEY7jGvaMrEv1ymNFZqrD2obCmgQi_iz3vWIQEbwOtMMu8P53cc0RHXP4xCKyHdwkvpbpHmm9Y5_gUBE3KCXokM-jlhey2ksY5jBilAE2g6o3e5nBumiZK3mRaylm9BJGKiZlWj3D=w1280-h720-no?authuser=0)

# Deployment
The bot token is taken from the `BOT_TOKEN` environment variable.

To deploy, simple compile and run the bot with the `BOT_TOKEN` set.

# Compilation
**Requires Java 17**

Pre-compiled Java archives are avalible on the (https://github.com/FlappyTheBats/DiscordMessageAlwaysAtTheBottom/releases)[Releases] tab.

**If you'd rather compile it yourself:**

Windows: `gradlew.bat jar`

Mac / Linux: `./gradlew jar`

Output file will be located in `build\libs`.
