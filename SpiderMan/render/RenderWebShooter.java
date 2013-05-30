package colossali.SpiderMan.render;
import org.lwjgl.opengl.GL11;

import colossali.SpiderMan.models.ModelWebShooter;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.item.ItemStack;
import net.minecraft.src.*;
import net.minecraftforge.client.ForgeHooksClient;
import net.minecraftforge.client.IItemRenderer;

public class RenderWebShooter implements IItemRenderer 
{
	protected ModelWebShooter modelsaber;
	public static int weapontype = 1;
	
	public RenderWebShooter()
	{
		modelsaber = new ModelWebShooter();
	}
	
	public boolean handleRenderType(ItemStack item, ItemRenderType type) 
	{
		if (type == ItemRenderType.EQUIPPED) { return true; }
		return false;
	}

	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) 
	{
		return false;
	}

	public void renderItem(ItemRenderType type, ItemStack item, Object... data) 
	{
		
		
		switch(type)
		{
		case EQUIPPED:
		{
			GL11.glPushMatrix();
			

				Minecraft.getMinecraft().renderEngine.bindTexture("/spiderman/webshooter.png");


			GL11.glRotatef(187, 1F, 0F, 0F);
			GL11.glRotatef(51, 0F, 0F, 1F);		
			GL11.glRotatef(97, 0F, 1F, 0F);
			GL11.glTranslatef(-0.072F, -2.26F, 0.264F);
			GL11.glScalef(1.2F, 1.2F, 1.2F);
			modelsaber.render((Entity)data[1], 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
			
			
			Minecraft.getMinecraft().renderEngine.resetBoundTexture();
			
			GL11.glPopMatrix();
		}

		default:
			break;
		}
	}


}