package strumyk;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.scoreboard.Team;

import java.util.Iterator;
import java.util.Set;

public class PlayerChatListener implements Listener {
    @EventHandler
    public void playerChatEvent(AsyncPlayerChatEvent chat) {
        Player player = chat.getPlayer();
        Team team = player.getScoreboard().getEntryTeam(player.getName());
        Set<Player> recipients = chat.getRecipients();
        Iterator<Player> iterator = recipients.iterator();
        if(chat.getMessage().startsWith("!")){
            if(team != null){
                chat.setMessage(chat.getMessage().substring(1));
                String nick = "§9[Team] §r%s ";
                String message = "§7»§r %s";
                chat.setFormat(nick + message);
                while (iterator.hasNext()) {
                    if (team.hasEntry(iterator.next().getName())) {
                    } else {
                        iterator.remove();
                    }
                }
            }else{
                player.sendMessage("Error, you're not in any team");
                chat.setCancelled(true);
            }
        }
    }
}
