package fishnetwork.akeome.task;

import java.util.Random;

import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.entity.item.EntityFirework;
import cn.nukkit.item.Item;
import cn.nukkit.item.ItemFirework.FireworkExplosion;
import cn.nukkit.item.ItemID;
import cn.nukkit.level.Level;
import cn.nukkit.nbt.NBTIO;
import cn.nukkit.nbt.tag.CompoundTag;
import cn.nukkit.nbt.tag.DoubleTag;
import cn.nukkit.nbt.tag.FloatTag;
import cn.nukkit.nbt.tag.ListTag;
import cn.nukkit.scheduler.Task;
import cn.nukkit.utils.DyeColor;

public class SpawnFireworkTask extends Task {


    private static final Random random = new Random();


    private int count = 10;


    @Override
    public void onRun(int currentTick) {
        if(count <= 0) cancel();
        for(Player player: Server.getInstance().getOnlinePlayers().values()) spawnFirework(player);
        count--;
    }


    private void spawnFirework(Player player) {
        Level level = player.getLevel();
        Item firework = Item.get(ItemID.FIREWORKS).setNamedTag(new CompoundTag()
            .putCompound("Fireworks", new CompoundTag("Fireworks")
                .putList(new ListTag<CompoundTag>("Explosions")
                    .add(new CompoundTag()
                        .putByteArray("FireworkColor", new byte[]{(byte)DyeColor.values()[random.nextInt(FireworkExplosion.ExplosionType.values().length)].getDyeData()})
                        .putByteArray("FireworkFade", new byte[]{})
                        .putBoolean("FireworkFlicker", random.nextBoolean())
                        .putBoolean("FireworkTrail", random.nextBoolean())
                        .putByte("FireworkType", FireworkExplosion.ExplosionType.values()[random.nextInt(FireworkExplosion.ExplosionType.values().length)].ordinal())))
                .putByte("Flight", 1)));
        CompoundTag nbt = new CompoundTag()
            .putList(new ListTag<DoubleTag>("Pos")
                .add(new DoubleTag("", player.x + 0.5))
                .add(new DoubleTag("", player.y + 0.5))
                .add(new DoubleTag("", player.z + 0.5)))
            .putList(new ListTag<DoubleTag>("Motion")
                .add(new DoubleTag("", 0))
                .add(new DoubleTag("", 0))
                .add(new DoubleTag("", 0)))
            .putList(new ListTag<FloatTag>("Rotation")
                .add(new FloatTag("", 0))
                .add(new FloatTag("", 0)))
            .putCompound("FireworkItem", NBTIO.putItemHelper(firework));
        EntityFirework fireworkEntity = new EntityFirework(level.getChunk((int)player.x >> 4, (int)player.z >> 4), nbt);
        fireworkEntity.spawnToAll();
    }

    
}
