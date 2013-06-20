package colossali.SpiderMan.client;

import colossali.SpiderMan.common.CommonProxy;
import colossali.SpiderMan.common.ServerTickHandler;
import colossali.SpiderMan.common.mod_spiderman;
import colossali.SpiderMan.entities.EntityWebBall;
import colossali.SpiderMan.entities.EntityWebSwing;
import colossali.SpiderMan.entities.RadioactiveSpider;
import colossali.SpiderMan.render.RenderWebBall;
import colossali.SpiderMan.render.RenderWebShooter;
import colossali.SpiderMan.render.RenderWebString;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.TickType;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;

import java.util.EnumSet;
import java.util.Map;

import net.minecraft.client.renderer.entity.RenderSpider;
import net.minecraft.world.World;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.MinecraftForgeClient;

public class ClientProxy extends CommonProxy
{
    public void preInit()
    {
    }

    public void load()
    {
        TickRegistry.registerTickHandler(new ClientTickHandler(EnumSet.of(TickType.CLIENT)), Side.CLIENT);
        LanguageRegistry.addName(mod_spiderman.SpiderManMask, "\u00a74 Spider Man Mask");
        LanguageRegistry.addName(mod_spiderman.SpiderManSuit, "\u00a71 Spider Man Chest");
        LanguageRegistry.addName(mod_spiderman.SpiderManPants, "\u00a71 Spider Man Pants");
        LanguageRegistry.addName(mod_spiderman.SpiderManShoes, "Spider Man Shoes");
        LanguageRegistry.addName(mod_spiderman.SpiderSilkCloth, "Spider Silk Cloth");
        LanguageRegistry.addName(mod_spiderman.RadioactiveSpider, "Radioactive Spider");
        LanguageRegistry.addName(mod_spiderman.WebBallItem, "Web Ball");
        LanguageRegistry.addName(mod_spiderman.WebString, "Web String");
        LanguageRegistry.addName(mod_spiderman.WebShooter, "Web Shooter");
        
  	    RenderingRegistry.registerEntityRenderingHandler(EntityWebBall.class, new RenderWebBall(mod_spiderman.WebBallItem));
  	    RenderingRegistry.registerEntityRenderingHandler(EntityWebSwing.class, new RenderWebString());
        MinecraftForgeClient.registerItemRenderer(mod_spiderman.WebShooter.itemID,((IItemRenderer) new RenderWebShooter()));
        

        

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
