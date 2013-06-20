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
import colossali.SpiderMan.entities.EntityWebBall;
import colossali.SpiderMan.entities.EntityWebSwing;
import colossali.SpiderMan.entities.RadioactiveSpider;
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
        version = "1.5.2"
)
@NetworkMod(
        clientSideRequired = true,
        serverSideRequired = false,
        serverPacketHandlerSpec =  @NetworkMod.SidedPacketHandler(
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

    public static int SpiderManMaskID = 6123;
    public static int SpiderManSuitID = 6124;
    public static int SpiderManPantsID = 6125;
    public static int SpiderManShoesID = 6126;
    public static int SpiderSilkClothID = 6127;
    public static int RadioactiveSpiderID = 6128;
    public static int WebShooterID = 6129;
    public static int WebBallItemID = 6130;
    public static int WebStringID = 6131;
    public static int WebBallID = 6132;
    public static int WebSwingID = 412;
    public static int RadSpiID = 411; 
    public static int RadSpawnChance = 5;
    
    public static int webKey = 19;

    public static Item SpiderManMask;
    public static Item SpiderManSuit;
    public static Item SpiderManPants;
    public static Item SpiderManShoes;
    public static Item RadioactiveSpider;
    public static Item SpiderSilkCloth;
    public static Item WebShooter;
    public static Item WebBallItem;
    public static Item WebString;
    
    @Mod.PreInit
    public void preInit(FMLPreInitializationEvent var1)
    {
        proxy.preInit();
       
        Configuration var2 = new Configuration(var1.getSuggestedConfigurationFile());

        try
        {
            var2.load();
            Property var3 = var2.get("Spider Man Mask", "item", 6123);
            SpiderManMaskID = var3.getInt(6123);
            
            var3 = var2.get("Spider Man Suit", "item", 6124);
            SpiderManSuitID = var3.getInt(6124);
            
            var3 = var2.get("Spider Man Pants", "item", 6125);
            SpiderManPantsID = var3.getInt(6125);
            
            var3 = var2.get("Spider Man Shoes", "item", 6126);
            SpiderManShoesID = var3.getInt(6126);
            
            var3 = var2.get("Radioactive Spider Item", "item", 6128);
            RadioactiveSpiderID = var3.getInt(6128);
            
            var3 = var2.get("Radioactive Spider", "mob", 411);
            RadSpiID = var3.getInt(411);
            
            var3 = var2.get("Web Swing Entity", "mob", 412);
            WebSwingID = var3.getInt(412);
            
            var3 = var2.get("Spider Silk Cloth", "item", 6127);
            SpiderSilkClothID = var3.getInt(6127);
            
            var3 = var2.get("Web Shooter", "item", 6129);
            WebShooterID = var3.getInt(6129);
            
            var3 = var2.get("Web Ball ID", "item", 6132);
            WebBallID = var3.getInt(6132);
            
            var3 = var2.get("Web Ball Item ID", "item", 6130);
            WebBallItemID = var3.getInt(6130);
            
            var3 = var2.get("Web String ID", "item", 6131);
            WebStringID = var3.getInt(6131);
            
            var3 = var2.get("webKey", "keybinding", 19);
            webKey = var3.getInt(19);           
  	 
      	 
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
    	
     SpiderManMask = new SpiderManSuitPart(SpiderManMaskID, EnumArmorMaterial.DIAMOND, 1, 0, "mask").setUnlocalizedName("Spider Man Mask");
     SpiderManSuit = new SpiderManSuitPart(SpiderManSuitID, EnumArmorMaterial.DIAMOND, 1, 1, "chest").setUnlocalizedName("Spider Man Suit");
     SpiderManPants = new SpiderManSuitPart(SpiderManPantsID, EnumArmorMaterial.DIAMOND, 1, 2, "pants").setUnlocalizedName("Spider Man Pants");
     SpiderManShoes = new SpiderManSuitPart(SpiderManShoesID, EnumArmorMaterial.DIAMOND, 1, 3, "shoes").setUnlocalizedName("Spider Man Shoes");
     SpiderSilkCloth = new ItemSpiderSilk(SpiderSilkClothID).setUnlocalizedName("Spider Silk Cloth");
     RadioactiveSpider = new ItemSpider(RadioactiveSpiderID, 4, false).setUnlocalizedName("Radioactive Spider");
     WebShooter = new ItemWebShooter(WebShooterID, EnumToolSpiderMan.SLENDER).setUnlocalizedName("Web Shooter").setFull3D();
     WebBallItem = new ItemWebBall(WebBallItemID).setUnlocalizedName("WebBallItem");
     WebString = new ItemWebString(WebStringID).setUnlocalizedName("WebString");

   	 proxy.load();
   	 
   	 
      TickRegistry.registerTickHandler(new ServerTickHandler(EnumSet.of(TickType.SERVER)), Side.SERVER);

      
      EntityRegistry.registerGlobalEntityID(RadioactiveSpider.class, "Radioactive Spider", RadSpiID, 96210, 11020332);
      EntityRegistry.registerModEntity(RadioactiveSpider.class, "Radioactive Spider", RadSpiID, this, 1000, 1, false);
      LanguageRegistry.instance().addStringLocalization("entity.RadioactiveSpider.name", "en_US", "Radioactive Spider");
      ModLoader.addSpawn(RadioactiveSpider.class, RadSpawnChance, 1, 3, EnumCreatureType.monster, BiomeGenBase.jungle);
      ModLoader.addSpawn(RadioactiveSpider.class, RadSpawnChance, 1, 3, EnumCreatureType.monster, BiomeGenBase.forest);
      ModLoader.addSpawn(RadioactiveSpider.class, RadSpawnChance, 1, 3, EnumCreatureType.monster, BiomeGenBase.taiga);

  	
		//Mask
        ModLoader.addRecipe(new ItemStack(SpiderManMask, 1), new Object[]
        		{"sss", "s s", "   ", 's', mod_spiderman.SpiderSilkCloth});
        
        ModLoader.addRecipe(new ItemStack(SpiderManPants, 1), new Object[]
        		{"sss", "s s", "s s", 's', mod_spiderman.SpiderSilkCloth});

        ModLoader.addRecipe(new ItemStack(SpiderManSuit, 1), new Object[]
        		{"s s", "sss", "sss", 's', mod_spiderman.SpiderSilkCloth});
        
        ModLoader.addRecipe(new ItemStack(SpiderManShoes, 1), new Object[]
        		{"s s", "s s", "   ", 's', mod_spiderman.SpiderSilkCloth});
        
        ModLoader.addRecipe(new ItemStack(WebBallItem, 1), new Object[]
        		{"sss", "sss", "sss", 's', Item.silk});
        
        ModLoader.addRecipe(new ItemStack(WebString, 1), new Object[]
        		{"  s", " s ", "s  ", 's', Item.silk});
        
        ModLoader.addRecipe(new ItemStack(WebString, 9), new Object[]
        		{"  s", " r ", "s  ", 's', Item.silk, 'r', RadioactiveSpider});
        
        ModLoader.addRecipe(new ItemStack(WebBallItem, 9), new Object[]
        		{"sss", "srs", "sss", 's', Item.silk, 'r', RadioactiveSpider});
        
        ModLoader.addRecipe(new ItemStack(WebShooter, 1), new Object[]
        		{"iii", "grg", "ddd", 'i', Item.ingotIron, 'g', Item.ingotGold, 'r', RadioactiveSpider, 'd', Item.diamond } );
        
        ModLoader.addRecipe(new ItemStack(SpiderSilkCloth, 3), new Object[]
        		{"rwr", "wsw", "bwb", Character.valueOf('r'), new ItemStack(Item.dyePowder, 1, 1), Character.valueOf('b'), new ItemStack(Item.dyePowder, 1, 0), 'w', Item.silk, 's', mod_spiderman.RadioactiveSpider});

	//	EntityRegistry.registerModEntity(EntityWebBall.class,"Web Swing", this.WebBallID, this, 64, 20, true);

        
    }
    
    public void livingHurt(LivingHurtEvent event){
    	
    }


    @Mod.PostInit
    public void modsLoaded(FMLPostInitializationEvent var1) {}

    @Mod.ServerStarted
    public void serverStarted(FMLServerStartedEvent var1) {}
}
