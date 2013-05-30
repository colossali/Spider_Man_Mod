package colossali.SpiderMan.common;

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
                    var8 = var5.readInt();
                    var9 = var5.readInt();
                    int var10 = var5.readInt();
                    

                    for (int var11 = -(2 + var10 / 10); var11 < 1 + var10 / 10; ++var11)
                    {
                        for (int var12 = -(2 + var10 / 10); var12 < 1 + var10 / 10; ++var12)
                        {
                            if (var6.getBlockId(var7 + var11, var8, var9 + var12) == 0)
                            
                                var6.setBlock(var7 + var11, var8, var9 + var12, Block.web.blockID);
                            
                        }
                    }
                    	
                    return;

                case 1:
                    var6 = MinecraftServer.getServer().worldServerForDimension(var4.dimension);
                    var7 = var5.readInt();
                    var8 = var5.readInt();
                    var9 = var5.readInt();
                    var10 = var5.readInt();

                    var6.createExplosion(var4, (double)var7, (double)var8, (double)var9, var10, false);
                    var4.inventory.damageArmor(40);

                    return;

                case 2:
                    var6 = MinecraftServer.getServer().worldServerForDimension(var4.dimension);
                    var7 = var5.readInt();
                    var8 = var5.readInt();
                    var9 = var5.readInt();

                    var6.spawnEntityInWorld(new EntityLightningBolt(var6, (double)var7 , (double)var8 , (double)var9 ));

                    return;
                    
                case 3:
                	
                    var6 = MinecraftServer.getServer().worldServerForDimension(var4.dimension);
                    var7 = var5.readInt();

                    EntityFishHook var16 = new EntityFishHook(var6, var4);

                    Vec3 look = var4.getLookVec();
                    var16.motionX = look.xCoord * 0.1;
                    var16.motionY = look.yCoord * 0.1;
                    var16.motionZ = look.zCoord * 0.1;
                    
                    var6.spawnEntityInWorld(var16);
                    
                    if (var16.isCollided)
                    {
                        var4.setPositionAndUpdate(var16.posX, var16.posY, var16.posZ);
                    }

                    return;
                    
                	

            }
        }
    }
}
