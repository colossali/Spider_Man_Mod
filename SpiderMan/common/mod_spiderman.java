package colossali.SpiderMan.common;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.Mod.ServerStarted;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.TickType;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartedEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkMod.SidedPacketHandler;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;

import java.util.EnumSet;
import java.util.Random;
import java.util.logging.Level;
import colossali.SpiderMan.client.ClientTickHandler;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.src.ModLoader;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.client.EnumHelperClient;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.Property;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

@Mod(
        modid = "colossali_SpiderMan",
        name = "Spider Man",
        version = "1.3.2"
)
@NetworkMod(
        clientSideRequired = true,
        serverSideRequired = false,
        serverPacketHandlerSpec =       @NetworkMod.SidedPacketHandler(
                channels = {"SpiderMan_C"},
                packetHandler = ServerPacketHandler.class
                )
)
public class mod_spiderman
{
    @SidedProxy(
            clientSide = "colossali.SpiderMan.client.ClientProxy",
            serverSide = "colossali.SpiderMan.common.CommonProxy"
            
    )

    
    public static CommonProxy proxy;

    public static String itemsPath = "/spiderman/items.png";

    public static int SpiderManMaskID = 4060;
    public static int SpiderManSuitID = 4210;
    public static int SpiderManPantsID = 4080;
    public static int SpiderManShoesID = 4090;
    public static int SpiderSilkClothID = 4120;
    public static int RadioactiveSpiderID = 4100;
    public static int WebBallID = 4101;
    public static int RadSpiID = 411;
    

    
    public static int webKey = 44;
    public static int stringKey = 45;
      

    public static Item SpiderManMask;
    public static Item SpiderManSuit;
    public static Item SpiderManPants;
    public static Item SpiderManShoes;
    public static Item RadioactiveSpider;
    public static Item SpiderSilkCloth;
    public static Item WebBall;
    
    @Mod.PreInit
    public void preInit(FMLPreInitializationEvent var1)
    {
        proxy.preInit();
       
        Configuration var2 = new Configuration(var1.getSuggestedConfigurationFile());

        try
        {
            var2.load();
            Property var3 = var2.get("Spider Man Mask", "item", 4060);
            SpiderManMaskID = var3.getInt(4060);
            
            var3 = var2.get("Spider Man Suit", "item", 4210);
            SpiderManSuitID = var3.getInt(4210);
            
            var3 = var2.get("Spider Man Pants", "item", 4080);
            SpiderManPantsID = var3.getInt(4080);
            
            var3 = var2.get("Spider Man Shoes", "item", 4090);
            SpiderManShoesID = var3.getInt(4090);
            
            var3 = var2.get("Radioactive Spider Item", "item", 4100);
            RadioactiveSpiderID = var3.getInt(4100);
            
            var3 = var2.get("Radioactive Spider", "mob", 411);
            RadSpiID = var3.getInt(411);
            
            var3 = var2.get("Spider Silk Cloth", "item", 4120);
            SpiderSilkClothID = var3.getInt(4120);
            
            var3 = var2.get("Web Ball ID", "item", 4101);
            WebBallID = var3.getInt(4101);
            
            var3 = var2.get("webKey", "keybinding", 44);
            webKey = var3.getInt(44);
            var3 = var2.get("stringKey", "keybinding", 44);
            stringKey = var3.getInt(42);

   	 
      	 
        }
        catch (Exception var7)
        {
            FMLLog.log(Level.SEVERE, var7, "PROBLEMS n SHIT", new Object[0]);
            FMLLog.severe(var7.getMessage(), new Object[0]);
        }
        finally
        {
            var2.save();
        }
    }


    @Mod.Init
    public void load(FMLInitializationEvent var1)
    {
    	
     SpiderManMask = (new SpiderManSuitPart(SpiderManMaskID, EnumArmorMaterial.DIAMOND, 1, 0)).setUnlocalizedName("Spider Man Mask");
     SpiderManSuit = (new SpiderManSuitPart(SpiderManSuitID, EnumArmorMaterial.DIAMOND, 1, 1).setUnlocalizedName("Spider Man Suit"));
     SpiderManPants = (new SpiderManSuitPart(SpiderManPantsID, EnumArmorMaterial.DIAMOND, 1, 2)).setUnlocalizedName("Spider Man Pants");
     SpiderManShoes = (new SpiderManSuitPart(SpiderManShoesID, EnumArmorMaterial.DIAMOND, 1, 3)).setUnlocalizedName("Spider Man Shoes");
     SpiderSilkCloth = new ItemSpiderSilk(SpiderSilkClothID).setUnlocalizedName("Spider Silk Cloth");
     RadioactiveSpider = new ItemSpider(RadioactiveSpiderID, 4, false).setUnlocalizedName("Radioactive Spider");
	
   	 proxy.load();
   	 
   	 
      TickRegistry.registerTickHandler(new ServerTickHandler(EnumSet.of(TickType.SERVER)), Side.SERVER);

      
      EntityRegistry.registerGlobalEntityID(RadioactiveSpider.class, "Radioactive Spider", RadSpiID, 96210, 11020332);
      EntityRegistry.registerModEntity(RadioactiveSpider.class, "Radioactive Spider", RadSpiID, this, 1000, 1, false);
      LanguageRegistry.instance().addStringLocalization("entity.RadioactiveSpider.name", "en_US", "Radioactive Spider");
      ModLoader.addSpawn(RadioactiveSpider.class, 1, 1, 1, EnumCreatureType.monster, BiomeGenBase.jungle);
      
     LanguageRegistry.addName(mod_spiderman.SpiderManMask, "Spider Man Mask");
     LanguageRegistry.addName(mod_spiderman.SpiderManSuit, "Spider Man Suit");
     LanguageRegistry.addName(mod_spiderman.SpiderManPants, "Spider Man Pants");
     LanguageRegistry.addName(mod_spiderman.SpiderManShoes, "Spider Man Shoes");
     LanguageRegistry.addName(mod_spiderman.SpiderSilkCloth, "Spider Silk Cloth");
     LanguageRegistry.addName(mod_spiderman.RadioactiveSpider, "Radioactive Spider");
     

  	  
  	MinecraftForge.EVENT_BUS.register(new ClientTickHandler(EnumSet.of(TickType.CLIENT)));
		//Mask
        ModLoader.addRecipe(new ItemStack(SpiderManMask, 1), new Object[]
        		{"sss", "s s", "   ", 's', mod_spiderman.SpiderSilkCloth});
        
        ModLoader.addRecipe(new ItemStack(SpiderManPants, 1), new Object[]
        		{"sss", "s s", "s s", 's', mod_spiderman.SpiderSilkCloth});

        ModLoader.addRecipe(new ItemStack(SpiderManSuit, 1), new Object[]
        		{"s s", "sss", "sss", 's', mod_spiderman.SpiderSilkCloth});
        
        ModLoader.addRecipe(new ItemStack(SpiderManShoes, 1), new Object[]
        		{"s s", "s s", "   ", 's', mod_spiderman.SpiderSilkCloth});
        
        ModLoader.addRecipe(new ItemStack(SpiderSilkCloth, 3), new Object[]
        		{"rwr", "wsw", "bwb", Character.valueOf('r'), new ItemStack(Item.dyePowder, 1, 1), Character.valueOf('b'), new ItemStack(Item.dyePowder, 1, 0), 'w', Item.silk, 's', mod_spiderman.RadioactiveSpider});

    }
    
    public void livingHurt(LivingHurtEvent event){
    	
    }


    @Mod.PostInit
    public void modsLoaded(FMLPostInitializationEvent var1) {}

    @Mod.ServerStarted
    public void serverStarted(FMLServerStartedEvent var1) {}
}
