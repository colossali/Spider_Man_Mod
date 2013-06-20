package colossali.SpiderMan.render;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.entity.projectile.EntityFishHook;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;
import colossali.SpiderMan.entities.EntityWebSwing;

@SideOnly(Side.CLIENT)
public class RenderWebString extends Render
{
    /**
     * Actually renders the fishing line and hook
     */
	public void doRenderFishHook(EntityWebSwing par1EntityGrapHook, double par2, double par4, double par6, float par8, float par9)
	{
	         GL11.glPushMatrix();
	         GL11.glTranslatef((float)par2, (float)par4, (float)par6);
	         GL11.glEnable(GL12.GL_RESCALE_NORMAL);
	         GL11.glScalef(0.5F, 0.5F, 0.5F);
	         byte var10 = 1;
	         byte var11 = 2;
	         this.loadTexture("/mods/SpiderMan/textures/items/webball.png");
	         Tessellator var12 = Tessellator.instance;
	         float var13 = (float)(var10 * 8 + 0) / 128.0F;
	         float var14 = (float)(var10 * 8 + 8) / 128.0F;
	         float var15 = (float)(var11 * 8 + 0) / 128.0F;
	         float var16 = (float)(var11 * 8 + 8) / 128.0F;
	         float var17 = 1.0F;
	         float var18 = 0.5F;
	         float var19 = 0.5F;
	         GL11.glRotatef(180.0F - this.renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
	         GL11.glRotatef(-this.renderManager.playerViewX, 1.0F, 0.0F, 0.0F);
	         var12.startDrawingQuads();
	         var12.setNormal(0.0F, 1.0F, 0.0F);
	         var12.addVertexWithUV((double)(0.0F - var18), (double)(0.0F - var19), 0.0D, (double)var13, (double)var16);
	         var12.addVertexWithUV((double)(var17 - var18), (double)(0.0F - var19), 0.0D, (double)var14, (double)var16);
	         var12.addVertexWithUV((double)(var17 - var18), (double)(1.0F - var19), 0.0D, (double)var14, (double)var15);
	         var12.addVertexWithUV((double)(0.0F - var18), (double)(1.0F - var19), 0.0D, (double)var13, (double)var15);
	         var12.draw();
	         GL11.glDisable(GL12.GL_RESCALE_NORMAL);
	         GL11.glPopMatrix();
	        
	         if (par1EntityGrapHook.shootingEntity != null)
	         {	                
	                 Vec3 var22 = par1EntityGrapHook.worldObj.getWorldVec3Pool().getVecFromPool(-0.5D, 0.03D, 0.8D);
	                 var22.rotateAroundX(-(par1EntityGrapHook.shootingEntity.prevRotationPitch + (par1EntityGrapHook.shootingEntity.rotationPitch - par1EntityGrapHook.shootingEntity.prevRotationPitch) * par9) * (float)Math.PI / 180.0F);
	                 var22.rotateAroundY(-(par1EntityGrapHook.shootingEntity.prevRotationYaw + (par1EntityGrapHook.shootingEntity.rotationYaw - par1EntityGrapHook.shootingEntity.prevRotationYaw) * par9) * (float)Math.PI / 180.0F);
	                 double var23 = par1EntityGrapHook.shootingEntity.prevPosX + (par1EntityGrapHook.shootingEntity.posX - par1EntityGrapHook.shootingEntity.prevPosX) * (double)par9 + var22.xCoord;
	                 double var25 = par1EntityGrapHook.shootingEntity.prevPosY + (par1EntityGrapHook.shootingEntity.posY - par1EntityGrapHook.shootingEntity.prevPosY) * (double)par9 + var22.yCoord;
	                 double var27 = par1EntityGrapHook.shootingEntity.prevPosZ + (par1EntityGrapHook.shootingEntity.posZ - par1EntityGrapHook.shootingEntity.prevPosZ) * (double)par9 + var22.zCoord;
	                 double var29 = par1EntityGrapHook.shootingEntity != Minecraft.getMinecraft().thePlayer ? (double)0.85 : 0.0D;
	                 if (this.renderManager.options.thirdPersonView > 0 || par1EntityGrapHook.shootingEntity != Minecraft.getMinecraft().thePlayer)
	                 {
	                         float var31 = (par1EntityGrapHook.shootingEntity.prevRenderYawOffset + (par1EntityGrapHook.shootingEntity.renderYawOffset - par1EntityGrapHook.shootingEntity.prevRenderYawOffset) * par9) * (float)Math.PI / 180.0F;
	                         double var32 = (double)MathHelper.sin(var31);
	                         double var34 = (double)MathHelper.cos(var31);
	                         var23 = par1EntityGrapHook.shootingEntity.prevPosX + (par1EntityGrapHook.shootingEntity.posX - par1EntityGrapHook.shootingEntity.prevPosX) * (double)par9 - var34 * 0.35D - var32 * 0.85D;
	                         var25 = par1EntityGrapHook.shootingEntity.prevPosY + var29 + (par1EntityGrapHook.shootingEntity.posY - par1EntityGrapHook.shootingEntity.prevPosY) * (double)par9 - 0.45D;
	                         var27 = par1EntityGrapHook.shootingEntity.prevPosZ + (par1EntityGrapHook.shootingEntity.posZ - par1EntityGrapHook.shootingEntity.prevPosZ) * (double)par9 - var32 * 0.35D + var34 * 0.85D;
	                 }
	                 double var46 = par1EntityGrapHook.prevPosX + (par1EntityGrapHook.posX - par1EntityGrapHook.prevPosX) * (double)par9;
	                 double var33 = par1EntityGrapHook.prevPosY + (par1EntityGrapHook.posY - par1EntityGrapHook.prevPosY) * (double)par9 + 0.25D;
	                 double var35 = par1EntityGrapHook.prevPosZ + (par1EntityGrapHook.posZ - par1EntityGrapHook.prevPosZ) * (double)par9;
	                 double var37 = (double)((float)(var23 - var46));
	                 double var39 = (double)((float)(var25 - var33));
	                 double var41 = (double)((float)(var27 - var35));
	                 GL11.glDisable(GL11.GL_TEXTURE_2D);
	                 GL11.glDisable(GL11.GL_LIGHTING);
	                 var12.startDrawing(3);
	                 var12.setColorOpaque(255, 255, 255);
	                 byte var43 = 16;
	                 for (int var44 = 0; var44 <= var43; ++var44)
	                 {
	                         float var45 = (float)var44 / (float)var43;
	                         var12.addVertex(par2 + var37 * (double)var45 - 0.11D, par4 + var39 * (double)(var45 * var45 + var45) * 0.5D - 0.16D, (par6 + var41 * (double)var45) + 0.5D);
	                 }
	                 var12.draw();
	                 GL11.glEnable(GL11.GL_LIGHTING);
	                 GL11.glEnable(GL11.GL_TEXTURE_2D);
	         }
	}
    /**
     * Actually renders the given argument. This is a synthetic bridge method, always casting down its argument and then
     * handing it off to a worker function which does the actual work. In all probabilty, the class Render is generic
     * (Render<T extends Entity) and this method has signature public void doRender(T entity, double d, double d1,
     * double d2, float f, float f1). But JAD is pre 1.5 so doesn't do that.
     */
    public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9)
    {
        this.doRenderFishHook((EntityWebSwing)par1Entity, par2, par4, par6, par8, par9);
    }
}
