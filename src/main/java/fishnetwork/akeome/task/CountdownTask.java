package fishnetwork.akeome.task;

import java.util.Date;
import java.util.function.Consumer;

import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.scheduler.Task;
import fishnetwork.akeome.Akeome;
import fishnetwork.userapi.UserAPI;

public class CountdownTask extends Task {


    @Override
    public void onRun(int currentTick) {
        int timeLeft = (int)((Akeome.NEW_YEAR.getTime() - new Date().getTime()) / 1000);
        if(timeLeft > 0) {
            broadcast(player -> player.sendTip(String.format("§7新年まであと§e%s秒§7...", timeLeft)));
            if(timeLeft <= 10) {
                broadcast(player -> player.sendTitle(String.format("§7> §6%s §7<", timeLeft)));
            }
            return;
        }
        broadcast(player -> {
            player.sendMessage(String.format("§7» §3Akeome §7| §b新年あけましておめでとうございます!"));
            player.sendMessage(String.format("§7» §3Akeome §7| §bお年玉§e$%s§bをお受け取りください!", Akeome.REWARD));
            player.sendTitle("§l§4H§ca§6p§ep§ay §2N§be§3w §9Y§5e§da§7r §f!", "", 20, 5 * 20, 20);
            UserAPI.getUser(player).addMoney(Akeome.REWARD);
        });
        Server.getInstance().getScheduler().scheduleRepeatingTask(Akeome.getInstance(), new SpawnFireworkTask(), 20);
        cancel();
    }


    private void broadcast(Consumer<Player> consumer) {
        for(Player player: Server.getInstance().getOnlinePlayers().values()) consumer.accept(player);
    }

    
}
