package colossali.SpiderMan.common;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ItemSpiderSilk extends Item
{
    protected ItemSpiderSilk(int var1)
    {
        super(var1);
        this.setCreativeTab(CreativeTabs.tabMaterials);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister iconRegister)
    {
    	
             itemIcon = iconRegister.registerIcon("Slender:childsoul");
             
    }
    
    public String getTextureFile()
    {
        return mod_spiderman.itemsPath;
    }
}
