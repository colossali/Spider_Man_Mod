package colossali.SpiderMan.client;

import colossali.SpiderMan.common.mod_spiderman;
import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.network.PacketDispatcher;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.EnumSet;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.living.LivingFallEvent;

import org.lwjgl.input.Keyboard;

public class ClientTickHandler implements ITickHandler
{
    private final EnumSet ticksToGet;
    private int attackTime = 0;
    private int hoverTime = 10;
    private static int webKey = mod_spiderman.webKey;
    private static boolean webKeyDown = false;
    private static int pulseboltKey = mod_spiderman.stringKey;
    private static boolean pulseboltKeyDown = false;
    
    private static boolean hasSpiderSuit = false;    
    private static boolean hasShoes = false;
    private static boolean hasMask = false;
    private static boolean hasPants = false;
    
    private static int hasSuit = 0;

    private static boolean jumpKeyDown = false;
    private int canJump = 0;
    private int webTimer = 0;
    private int explosionTimer = 0;

    public ClientTickHandler(EnumSet var1)
    {
        this.ticksToGet = var1;
    }

    @ForgeSubscribe
    public void livingFall(LivingFallEvent event)
    {
        if (!(event.entityLiving instanceof EntityPlayer)) return;
        EntityPlayer eventPlayer = (EntityPlayer)event.entityLiving;
        //Check if the damage should be removed
        
        if (hasSpiderSuit == true || hasSuit > 0 || hasPants == true)
        {   	
        event.distance = 0F;

    }
    }
    
    public void tickStart(EnumSet var1, Object ... var2)
    {
        EntityClientPlayerMP var3 = Minecraft.getMinecraft().thePlayer;

        if (var3 != null)
        {
            if (hasSuit > 0)
            {
                byte var4 = 6;

                if (hasSuit > 1)
                {
                    var4 = 6;
                }

                if (var3.getHealth() < 20 && var3.ticksExisted % 100 * 1 == 0)
                {
                    var3.heal(var4);
                }
            }

            if (var3.inventory.armorItemInSlot(0) != null)
            {
                this.handleBoots(var3.inventory.armorItemInSlot(0).itemID);
            }
            else
            {
                Block.blocksList[Block.ice.blockID].slipperiness = 0.98F;
            }

            if (var3.inventory.armorItemInSlot(1) != null)
            {
                this.handleLegs(var3.inventory.armorItemInSlot(1).itemID);
            }
            else
            {
                hasPants = false;
            }

            if (var3.inventory.armorItemInSlot(2) != null)
            {
                this.handlePlate(var3.inventory.armorItemInSlot(2).itemID);
            }
            else
            {
                hasSuit = 0;
            }

            if (var3.inventory.armorItemInSlot(3) != null)
            {
                this.handleHelmet(var3.inventory.armorItemInSlot(3).itemID);
            }

            if( hasSpiderSuit = true && hasPants == true && hasMask == true && hasShoes == true)
            {

            }
            
            pulseboltKeyDown = Keyboard.isKeyDown(pulseboltKey);
            webKeyDown = Keyboard.isKeyDown(webKey);
            jumpKeyDown = Keyboard.isKeyDown(Minecraft.getMinecraft().gameSettings.keyBindJump.keyCode);
        }
    }


    private void handleBoots(int var1)
    {
        Minecraft var2 = Minecraft.getMinecraft();
        EntityClientPlayerMP var3 = var2.thePlayer;
        WorldClient var4 = var2.theWorld;

        
        
       if (var1 == mod_spiderman.SpiderManShoes.itemID)
        {
    	   
    	   hasShoes = true;
    	   
            if (!hasSpiderSuit && Keyboard.isKeyDown(var2.gameSettings.keyBindJump.keyCode) && var3.motionY > 0.0D)
            {
                var3.motionY += 0.06699999910593032D;
            }

            if (!var3.onGround)
            {
                if (Keyboard.isKeyDown(var2.gameSettings.keyBindJump.keyCode) && !jumpKeyDown && this.canJump == 1)
                {
                    var3.motionY = 0.41999998688697815D;
                    this.canJump = 2;
                }

                if (Keyboard.isKeyDown(var2.gameSettings.keyBindJump.keyCode) && this.canJump == 0)
                {
                    this.canJump = 1;
                }
            }
            else
            {
                this.canJump = 0;

                if (Keyboard.isKeyDown(var2.gameSettings.keyBindJump.keyCode))
                {
                    this.canJump = 1;
                }
            }
            
            var3.fallDistance = 0.0F;

        }
    }

    private void handleLegs(int var1)
    {
        Minecraft var2 = Minecraft.getMinecraft();
        EntityClientPlayerMP var3 = var2.thePlayer;
        WorldClient var4 = var2.theWorld;
        int var12 = var3.inventory.getDamageVsEntity(var3);

        if (var1 == mod_spiderman.SpiderManPants.itemID)
        {
            int var5 = MathHelper.floor_double(var3.posX);
            int var6 = MathHelper.floor_double(var3.posY - 2.0D);
            int var7 = MathHelper.floor_double(var3.posZ);
            
            var12 += 3;

            if (Keyboard.isKeyDown(var2.gameSettings.keyBindForward.keyCode) || Keyboard.isKeyDown(var2.gameSettings.keyBindBack.keyCode) || Keyboard.isKeyDown(var2.gameSettings.keyBindLeft.keyCode) || Keyboard.isKeyDown(var2.gameSettings.keyBindRight.keyCode))
            {
                if (var4.getBlockId(var5, var6, var7) != Block.ice.blockID)
                {
                    if (var3.onGround)
                    {
                        if (!var3.isInWater())
                        {
                            var3.motionX *= 1.4500000476837158D;
                            var3.motionZ *= 1.4500000476837158D;
                        }
                        else
                        {
                            var3.motionX *= 1.149999976158142D;
                            var3.motionZ *= 1.149999976158142D;
                        }
                    }
                    else if (var3.isInWater())
                    {
                        var3.motionX *= 1.149999976158142D;
                        var3.motionZ *= 1.149999976158142D;
                    }
                }
                else
                {
                    var3.motionX *= 1.100000023841858D;
                    var3.motionZ *= 1.100000023841858D;
                }
            }

            hasPants = true;
            
        }
        

        
    }

    private void handlePlate(int var1)
    {
        Minecraft var2 = Minecraft.getMinecraft();
        EntityClientPlayerMP var3 = var2.thePlayer;
        WorldClient var4 = var2.theWorld;
        

        if (var1 == mod_spiderman.SpiderManSuit.itemID)
        {

            if (var2.currentScreen == null && Keyboard.isKeyDown(webKey) && !webKeyDown)
            {
                this.webTimer = 1;
            }


            if (this.webTimer > 0)
            {
                ++this.webTimer;

                if (this.webTimer > 100)
                {
                    this.webTimer = 100;
                }
            }
            
                        

            if (var2.currentScreen == null && !Keyboard.isKeyDown(webKey) && webKeyDown)
            {               
            	
            	
            	MovingObjectPosition pos = var3.rayTrace(200, 1.0f);
            	
                if (pos == null) 
                {
                	
                var3.sendChatToPlayer("Out Of Range");
                return;
                
                }
            	
                int var5 = pos.blockX;
                int var6 = pos.blockY + 1;
                int var7 = pos.blockZ;
                                
                
                this.sendweb(var5, var6, var7, this.webTimer);
                this.webTimer = 0;
            }
            

            if (var2.currentScreen == null && !Keyboard.isKeyDown(pulseboltKey) && pulseboltKeyDown)
            {
            	this.sendBullet(1);
            }

            if (!var3.onGround)
            {
                if (hasPants)
                {
                    var3.motionX *= 1.0800000429153442D;
                    var3.motionZ *= 1.0800000429153442D;
                }
                else
                {
                    var3.motionX *= 1.0399999618530273D;
                    var3.motionZ *= 1.0399999618530273D;
                }
            }

            hasSpiderSuit = true;
            hasSuit = 2;
            

            
            

            var3.fallDistance = 0.0F;
        }
    }

    private void sendExplosion(int var1, int var2, int var3, int time)
    {
        ByteArrayOutputStream var4 = new ByteArrayOutputStream();
        DataOutputStream var5 = new DataOutputStream(var4);
        int[] var6 = new int[] {1, var1, var2, var3, time};

        try
        {
            for (int var7 = 0; var7 < var6.length; ++var7)
            {
                var5.writeInt(var6[var7]);
            }
        }
        catch (IOException var8)
        {
            ;
        }

        Packet250CustomPayload var9 = new Packet250CustomPayload();
        var9.channel = "SpiderMan_C";
        var9.data = var4.toByteArray();
        var9.length = var4.size();
        PacketDispatcher.sendPacketToServer(var9);
    }

    private void sendweb(int var1, int var2, int var3, int var4)
    {
        ByteArrayOutputStream var5 = new ByteArrayOutputStream();
        DataOutputStream var6 = new DataOutputStream(var5);
        int[] var7 = new int[] {0, var1, var2, var3, var4};

        try
        {
            for (int var8 = 0; var8 < var7.length; ++var8)
            {
                var6.writeInt(var7[var8]);
            }
        }
        catch (IOException var9)
        {
            ;
        }

        Packet250CustomPayload var10 = new Packet250CustomPayload();
        var10.channel = "SpiderMan_C";
        var10.data = var5.toByteArray();
        var10.length = var5.size();
        PacketDispatcher.sendPacketToServer(var10);
    }

    private void sendBolt(int var2, int var3, int var4)
    {
        ByteArrayOutputStream var6 = new ByteArrayOutputStream();
        DataOutputStream var7 = new DataOutputStream(var6);
        int[] var8 = new int[] {2, var2, var3, var4};

        try
        {
            for (int var9 = 0; var9 < var8.length; ++var9)
            {
                var7.writeInt(var8[var9]);
            }
        }
        catch (IOException var10)
        {
            ;
        }

        Packet250CustomPayload var11 = new Packet250CustomPayload();
        var11.channel = "SpiderMan_C";
        var11.data = var6.toByteArray();
        var11.length = var6.size();
        PacketDispatcher.sendPacketToServer(var11);
    }

    private void sendBullet(int var2)
    {
        ByteArrayOutputStream var7 = new ByteArrayOutputStream();
        DataOutputStream var8 = new DataOutputStream(var7);
        int[] var9 = new int[] {3, var2};

        try
        {
            for (int var10 = 0; var10 < var9.length; ++var10)
            {
                var8.writeInt(var9[var10]);
            }
        }
        catch (IOException var11)
        {
            ;
        }

        Packet250CustomPayload var12 = new Packet250CustomPayload();
        var12.channel = "SpiderMan_C";
        var12.data = var7.toByteArray();
        var12.length = var7.size();
        PacketDispatcher.sendPacketToServer(var12);
    }
    
  
    private void handleHelmet(int var1)
    {
        Minecraft var2 = Minecraft.getMinecraft();
        EntityClientPlayerMP var3 = var2.thePlayer;
        WorldClient var4 = var2.theWorld;

        if (var1 == mod_spiderman.SpiderManMask.itemID)
        {
            int var5 = MathHelper.floor_double(var3.posX);
            int var6 = MathHelper.floor_double(var3.posY + 1.0D);
            int var7 = MathHelper.floor_double(var3.posZ);

            if (var4.getBlockMaterial(var5, var6, var7).isSolid())
            {
                if (Keyboard.isKeyDown(var2.gameSettings.keyBindJump.keyCode))
                {
                    var3.motionY = 0.1D;
                }
                else
                {
                    var3.motionY *= 0.6D;
                }
            }
            
            if (var3.isCollidedHorizontally)
            {
                var3.motionY = 0.3D;
            }
            
            boolean var71 = var3.isSneaking() && var3 instanceof EntityPlayer && var3.isCollidedHorizontally;

            if (var71)
            {
                var3.motionY = 0.0D;
            }
        }
        

    }

    public void tickEnd(EnumSet var1, Object ... var2) {}

    public EnumSet ticks()
    {
        return this.ticksToGet;
    }

    public String getLabel()
    {
        return "SpiderManClientTick";
    }
}