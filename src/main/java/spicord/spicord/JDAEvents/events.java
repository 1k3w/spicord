package spicord.spicord.JDAEvents;

import net.dv8tion.jda.api.EmbedBuilder;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import spicord.spicord.Messages;
import spicord.spicord.Spicord;

import java.awt.*;

public class events implements Listener {
    @EventHandler
    public void mesageSent(AsyncPlayerChatEvent e){
        if (Spicord.chatLogging == true){
            if (e.getPlayer() instanceof Player){
                new Messages().sendMessage((e.getPlayer().getDisplayName()) , e.getMessage());
                return;
            }
        }
    }


    @EventHandler
    public void onDeath(PlayerDeathEvent e){
        if(Spicord.killLogging == true){
            EmbedBuilder builder = new EmbedBuilder();
            builder.setThumbnail("http://minotar.net/helm/"+(e.getEntity()).getName());
            builder.setTitle("Death:");
            builder.setColor(Color.RED);
            builder.setDescription(e.getDeathMessage());
            new Messages().sendEmbed(builder);
        }

    }
    @EventHandler
    public void  onJoin(PlayerJoinEvent e){
        if (Spicord.joinLogging == true){
            EmbedBuilder builder = new EmbedBuilder().setColor(Color.GREEN);
            builder.setTitle(e.getPlayer().getName());
            builder.setThumbnail("http://minotar.net/helm/"+(e.getPlayer()).getName());
            builder.setDescription(Spicord.joinString.replace("%player%",e.getPlayer().getName()));
            new Messages().sendEmbed(builder);
        }
    }
    @EventHandler
    public void onLeave(PlayerQuitEvent e){
        if (Spicord.joinLogging == true){
            EmbedBuilder builder = new EmbedBuilder().setColor(Color.RED);
            builder.setTitle(e.getPlayer().getName());
            builder.setThumbnail("http://minotar.net/helm/"+(e.getPlayer()).getName());
            builder.setDescription(Spicord.leaveString.replace("%player%",e.getPlayer().getName()));
            new Messages().sendEmbed(builder);
        }
    }
}
