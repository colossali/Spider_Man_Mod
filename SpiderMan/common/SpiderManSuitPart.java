package colossali.SpiderMan.common;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.IArmorTextureProvider;

public class SpiderManSuitPart extends ItemArmor implements IArmorTextureProvider
{
	private String itemTexture;
	
    public SpiderManSuitPart(int var1, EnumArmorMaterial var2, int var3, int var4, String texture)
    {
        super(var1, var2, 0, var4);
        itemTexture = texture;
        this.setCreativeTab(CreativeTabs.tabCombat);
    }

    public String getTextureFile()
    {
        return mod_spiderman.itemsPath;
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister iconRegister)
    {
    	
             itemIcon = iconRegister.registerIcon("Spiderman:"+itemTexture);
             
    }

    @Override
    public String getArmorTextureFile(ItemStack itemstack)
    {
                    if(itemstack.itemID == mod_spiderman.SpiderManMask.itemID || itemstack.itemID == mod_spiderman.SpiderManSuit.itemID || itemstack.itemID == mod_spiderman.SpiderManShoes.itemID)
                    {
                                    return "/spiderman/spiderman_1.png";
                    }
                    if(itemstack.itemID == mod_spiderman.SpiderManPants.itemID)
                    {
                                    return "/spiderman/spiderman_2.png";
                    }

                    else
                    {
                    	return null;
                    }
    }
}
