package fishnetwork.akeome;

import java.util.Date;

import cn.nukkit.Server;
import cn.nukkit.plugin.PluginBase;
import fishnetwork.akeome.task.CountdownTask;
import lombok.Getter;

public class Akeome extends PluginBase {


    public static final Date NEW_YEAR = new Date(1640962800000L);
    public static final int REWARD = 30000;


    @Getter
    private static Akeome instance;


    @Override
    public void onEnable() {
        instance = this;
        Server server = getServer();
        server.getScheduler().scheduleRepeatingTask(this, new CountdownTask(), 20);
    }


}
