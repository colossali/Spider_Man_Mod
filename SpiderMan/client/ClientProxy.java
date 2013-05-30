package colossali.SpiderMan.client;

import colossali.SpiderMan.common.CommonProxy;
import colossali.SpiderMan.common.RadioactiveSpider;
import colossali.SpiderMan.common.ServerTickHandler;
import colossali.SpiderMan.common.mod_spiderman;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.TickType;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;

import java.util.EnumSet;
import java.util.Map;

import net.minecraft.client.renderer.entity.RenderSpider;
import net.minecraft.world.World;
import net.minecraftforge.client.MinecraftForgeClient;

public class ClientProxy extends CommonProxy
{
    public void preInit()
    {
        MinecraftForgeClient.preloadTexture(mod_spiderman.itemsPath);
        MinecraftForgeClient.preloadTexture("/spiderman/spiderman_1.png");
        MinecraftForgeClient.preloadTexture("/spiderman/spiderman_2.png");
        MinecraftForgeClient.preloadTexture("/spiderman/radioactivespider.png");

    }

    public void load()
    {
        TickRegistry.registerTickHandler(new ClientTickHandler(EnumSet.of(TickType.CLIENT)), Side.CLIENT);
        LanguageRegistry.addName(mod_spiderman.SpiderManMask, "Spider Man Mask");
        LanguageRegistry.addName(mod_spiderman.SpiderManSuit, "Spider Man Chest");
        LanguageRegistry.addName(mod_spiderman.SpiderManPants, "Spider Man Pants");
        LanguageRegistry.addName(mod_spiderman.SpiderManShoes, "Spider Man Shoes");
        LanguageRegistry.addName(mod_spiderman.SpiderSilkCloth, "Spider Silk Cloth");
        LanguageRegistry.addName(mod_spiderman.RadioactiveSpider, "Radioactive Spider");
        

    }

    public void addRenderer(Map var1)
    {
        var1.put(RadioactiveSpider.class, new RenderSpider());
    }
    
    public World getClientWorld()
    {
        return FMLClientHandler.instance().getClient().theWorld;
    }
}
