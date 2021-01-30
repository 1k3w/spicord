package spicord.spicord;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.TextChannel;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import spicord.spicord.JDAEvents.DiscordToServer;
import spicord.spicord.JDAEvents.events;

import javax.security.auth.login.LoginException;
import java.io.File;

public final class Spicord extends JavaPlugin {
    public static long CID = 0;
    public static TextChannel channel;
    public static boolean killLogging;
    public static boolean chatLogging;
    public static boolean joinLogging;

    public static String joinString = "";
    public static String leaveString = "";
    public static String messageString = "";

    private File messagefile = new File(getDataFolder(), "messages.yml");
    private FileConfiguration messageConfig = YamlConfiguration.loadConfiguration(messagefile);
    @Override
    public void onEnable(){
        if (!messagefile.exists()){
            saveResource("messages.yml",false);
        }
        joinString = messageConfig.getString("joinmessage");
        leaveString = messageConfig.getString("leavemessage");
        messageString = messageConfig.getString("chatFormatting");
        System.out.println(joinString);
        System.out.println(leaveString);
        System.out.println(messageString);
        this.saveDefaultConfig();
        if(this.getConfig().getString("token") == ""){
            System.out.println("you must provide a discord bot token in the config.yml!");
            this.getConfig().options().copyDefaults();
            this.saveDefaultConfig();
            Bukkit.getPluginManager().disablePlugin(this);
            return;
        }
        String token = this.getConfig().getString("token");
        CID = this.getConfig().getLong("channelID");
        killLogging = this.getConfig().getBoolean("killLogging");
        chatLogging = this.getConfig().getBoolean("chatLogging");
        joinLogging = this.getConfig().getBoolean("joinLogging");
        boolean discordChatToGameChat = this.getConfig().getBoolean("discordChatToGameChat");
        if (CID == 0){
            System.out.println("you must provide a channel id (to send the messages)!");
            Bukkit.getPluginManager().disablePlugin(this);
            return;
        }

        System.out.println("enabling plugin and bot...");

        try {
            JDA jda = JDABuilder.createDefault(token).build().awaitReady();
            channel = jda.getTextChannelById(CID);
            if (discordChatToGameChat){
                jda.addEventListener(new DiscordToServer());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (LoginException e) {
            e.printStackTrace();
        }
        getServer().getPluginManager().registerEvents(new events(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }




}
