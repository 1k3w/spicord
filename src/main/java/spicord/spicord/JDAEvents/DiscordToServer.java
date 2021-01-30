package spicord.spicord.JDAEvents;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import spicord.spicord.Spicord;

public class DiscordToServer extends ListenerAdapter {
    @Override
    public void onMessageReceived(MessageReceivedEvent e) {
        if (e.getAuthor().isBot()){
            return;
        }
        if (e.getChannel().getIdLong() == Spicord.CID){
            String toSend = Spicord.messageString.replace("%player%", e.getAuthor().getName());
            toSend = toSend.replace("%message%", e.getMessage().getContentRaw());
            for (Player player : Bukkit.getOnlinePlayers()){
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', toSend));
            }
        }
    }
}
