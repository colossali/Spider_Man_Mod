package colossali.SpiderMan.common;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import colossali.SpiderMan.entities.EntityWebBall;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.server.MinecraftServer;
import net.minecraft.src.*;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class ItemWebShooter extends Item
{
    private int weaponDamage;
    private final EnumToolSpiderMan toolMaterial;
    protected float efficiencyOnProperMaterial = 7.0F;
    public static final Block[] blocksEffectiveAgainst = new Block[] {Block.grass, Block.dirt, Block.sand, Block.gravel, Block.snow, Block.blockSnow, Block.blockClay, Block.tilledField, Block.slowSand, Block.mycelium, Block.cobblestone, Block.stoneDoubleSlab, Block.stoneSingleSlab, Block.stone, Block.sandStone, Block.cobblestoneMossy, Block.oreIron, Block.blockIron, Block.oreCoal, Block.blockGold, Block.oreGold, Block.oreDiamond, Block.blockDiamond, Block.ice, Block.netherrack, Block.oreLapis, Block.blockLapis, Block.oreRedstone, Block.oreRedstoneGlowing, Block.rail, Block.railDetector, Block.railPowered, Block.planks, Block.bookShelf, Block.wood, Block.chest, Block.stoneDoubleSlab, Block.stoneSingleSlab, Block.pumpkin, Block.pumpkinLantern, Block.web};
    protected Random rand;
    public static int hammerthunder = 0;
    public static boolean toggleflying = false;
    public static int togglehammerability = 1;
    public static int hammertimer = 0;
    private String itemTexture;

    public static boolean throwhammer = false;


    public ItemWebShooter(int var1, EnumToolSpiderMan var2)
    {
        super(var1);
        this.toolMaterial = var2;
        this.maxStackSize = 1;
        this.setMaxDamage(var2.getMaxUses());
        this.setCreativeTab(CreativeTabs.tabCombat);
        this.weaponDamage = 10 + var2.getDamageVsEntity();
    }

    /**
     * Returns the strength of the stack against a given block. 1.0F base, (Quality+1)*2 if correct blocktype, 1.5F if
     * sword
     */
    public float getStrVsBlock(ItemStack var1, Block var2)
    {
        Block[] var3 = this.blocksEffectiveAgainst;
        int var4 = var3.length;
        
        if (var2.blockID == Block.web.blockID)
        {
            return 30.0F;
        }
        
        for (int var5 = 0; var5 < var4; ++var5)
        {
            Block var6 = var3[var5];

            if (var6 == var2)
            {
                return this.efficiencyOnProperMaterial;
            }
        }

        return 1.0F;
    }

    /**
     * Current implementations of this method in child classes do not use the entry argument beside ev. They just raise
     * the damage on the stack.
     */
    public boolean hitEntity(ItemStack var1, EntityLiving var2, EntityLiving var3)
    {


        var1.damageItem(1, var3);
         Vec3 var21 = var3.getLookVec();

        double var31 = (var3.boundingBox.maxX + var3.boundingBox.minX) / 2.0D + var21.xCoord / 4.0D;
        double var51 = (var3.boundingBox.maxZ + var3.boundingBox.minZ) / 2.0D + var21.zCoord / 4.0D;
        double var7 = var3.posY + (double)var3.getEyeHeight() + var21.yCoord / 4.0D;

        return true;
    }

    public boolean onBlockDestroyed(ItemStack var1, int var2, int var3, int var4, int var5, EntityLiving var6)
    {
        var1.damageItem(2, var6);
        return true;
    }

    /**
     * Returns the damage against a given entity.
     */
    public int getDamageVsEntity(Entity var1)
    {
        return this.weaponDamage;
    }

    /**
     * Returns True is the item is renderer in full 3D when hold.
     */
    public boolean isFull3D()
    {
        return true;
    }

    /**
     * returns the action that specifies what animation to play when the items is being used
     */
    public EnumAction getItemUseAction(ItemStack var1)
    {
        return EnumAction.bow;
    }

    /**
     * How long it takes to use or consume an item
     */
    public int getMaxItemUseDuration(ItemStack var1)
    {
        return 72000;
    }

    /**
     * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
     */
    public ItemStack onItemRightClick(ItemStack var1, World var2, EntityPlayer var3)
    {
    	if (var3.inventory.hasItem(mod_spiderman.WebBallItem.itemID))
    	{
        	var2.spawnEntityInWorld(new EntityWebBall(var2, var3));
        	var3.inventory.consumeInventoryItem(mod_spiderman.WebBallItem.itemID);
    	}
        return var1;
    }

    /**
     * Returns if the item (tool) can harvest results from the block type.
     */
    public boolean canHarvestBlock(Block var1)
    {
    	 return var1.blockID == Block.web.blockID;
    }

    /**
     * Return the enchantability factor of the item, most of the time is based on material.
     */
    public int getItemEnchantability()
    {
        return this.toolMaterial.getEnchantability();
    }
    
   
    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister iconRegister)
    {
    	
             itemIcon = iconRegister.registerIcon("SpiderMan:webshooter");
             
    }
}
