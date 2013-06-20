package colossali.SpiderMan.entities;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntityWebBall extends EntityThrowable {
 public EntityWebBall(World par1World) {
  super(par1World);
 }

 public EntityWebBall(World par1World,
   EntityLiving par2EntityLiving) {
  super(par1World, par2EntityLiving);
 }

 public EntityWebBall(World par1World, double par2,
   double par4, double par6) {
  super(par1World, par2, par4, par6);
 }

 /**
  * Gets the amount of gravity to apply to the thrown entity with each tick.
  */
 protected float getGravityVelocity() {
  return 0.07F;
 }

 protected float func_70182_d() {
  return 1.5F;
 }

 protected float func_70183_g() {
  return -20.0F;
 }

 /**
  * Called when this EntityThrowable hits a block or entity.
  */
 protected void onImpact(MovingObjectPosition par1MovingObjectPosition) {
    
   int var10 = 3;
   
   if(!this.worldObj.isRemote){
   for (int var11 = -(2 + var10 / 10); var11 < 1 + var10 / 10; ++var11)
   {
       for (int var12 = -(2 + var10 / 10); var12 < 1 + var10 / 10; ++var12)
       {                     
               this.worldObj.setBlock((int)(posX + var11),(int)posY,(int)(posZ + var12), Block.web.blockID);
           
       }
   }
   }

   this.setDead();
  
 }
}