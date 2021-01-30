package spicord.spicord;

import net.dv8tion.jda.api.EmbedBuilder;

public class Messages {
    public void sendEmbed(EmbedBuilder builder){
        Spicord.channel.sendMessage(builder.build()).queue();
    }
    public void sendMessage(String name, String text){
        String finalString = name + " >> "+text;
        Spicord.channel.sendMessage(finalString).queue();
    }
}
