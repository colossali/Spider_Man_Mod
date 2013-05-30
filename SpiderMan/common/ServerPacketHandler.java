package colossali.SpiderMan.common;

import colossali.SpiderMan.entities.EntityWebBall;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;
import net.minecraft.block.Block;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityFishHook;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.Vec3;
import net.minecraft.world.WorldServer;

public class ServerPacketHandler implements IPacketHandler
{
    public void onPacketData(INetworkManager var1, Packet250CustomPayload var2, Player var3)
    {
        if (var2.data != null)
        {
            EntityPlayer var4 = (EntityPlayer)var3;
            ByteArrayDataInput var5 = ByteStreams.newDataInput(var2.data);
            WorldServer var6;
            int var7;
            int var8;
            int var9;

            switch (var5.readInt())
            {
                case 0:
                    var6 = MinecraftServer.getServer().worldServerForDimension(var4.dimension);
                    var7 = var5.readInt();

                    var6.spawnEntityInWorld(new EntityWebBall(var6, var4));                   
                    	
                    return;
                	

            }
        }
    }
}
