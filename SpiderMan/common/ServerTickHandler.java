package colossali.SpiderMan.common;

import cpw.mods.fml.common.ITickHandler;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import org.lwjgl.input.Keyboard;

import colossali.SpiderMan.client.ClientTickHandler;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.WorldServer;

public class ServerTickHandler implements ITickHandler
{
    private final EnumSet ticksToGet;
    protected Random rand;
    private int attackTime;

    public ServerTickHandler(EnumSet var1)
    {
        this.ticksToGet = var1;
        this.rand = new Random();
        this.attackTime = 0;
    }

    public void tickStart(EnumSet var1, Object ... var2)
    {
        --this.attackTime;

        if (this.attackTime < 0)
        {
            this.attackTime = 25;
        }

        ArrayList var3 = (ArrayList)MinecraftServer.getServer().getConfigurationManager().playerEntityList;
        Iterator var4 = var3.iterator();

        while (var4.hasNext())
        {
            EntityPlayerMP var5 = (EntityPlayerMP)var4.next();

            for (int var6 = 0; var6 < 4; ++var6)
            {
                if (var5.inventory.armorInventory[var6] != null)
                {
                    int var7 = var5.inventory.armorInventory[var6].itemID;

                    if (this.affectFallDistance(var7))
                    {
                        var5.fallDistance = 0.0F;
                    }

                    WorldServer var8;


                    int var10;
                    List var25;
                    Entity var26;

                   
                        int var9;
                        int var11;
                        

                      
                    }
                }
            }
        }
    

    private boolean affectFallDistance(int var1)
    {
        return var1 == mod_spiderman.SpiderManShoes.itemID || var1 == mod_spiderman.SpiderManSuit.itemID || var1 == mod_spiderman.SpiderManPants.itemID;
    }

    public void tickEnd(EnumSet var1, Object ... var2) {}

    public EnumSet ticks()
    {
        return this.ticksToGet;
    }

    public String getLabel()
    {
        return "ServerTick";
    }
}
