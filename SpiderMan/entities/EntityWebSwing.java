package colossali.SpiderMan.entities;

import java.util.List;

import net.minecraft.util.AxisAlignedBB;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.entity.Entity;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class EntityWebSwing extends Entity
{
	private int xTile;
	private int yTile;
	private int zTile;
	private int inTile;
	private boolean inGround;
	public int arrowShake;
	public EntityLiving shootingEntity;
	private int timeTillDeath;
	private int flyTime;
	private EntityPlayerMP owner;
	private int damage;
	private final float size = 1.0F;
	private int knockbackStrength = 4;
	private double distToWebSwingX;
	private double distToWebSwingY;
	private double distToWebSwingZ;
	public double newPlayerMotionX;
	public double newPlayerMotionY;
	public double newPlayerMotionZ;
	private boolean hitBlock = true;
	private boolean doSwing = true;
	public static boolean swingDead = false;
	private double newMotionX;
	private double newMotionY;
	private double newMotionZ;


	public EntityWebSwing(World var1)
	{
		super(var1);
		this.xTile = -1;
		this.yTile = -1;
		this.zTile = -1;
		this.inTile = 0;
		this.inGround = false;
		this.arrowShake = 0;
		this.flyTime = 0;
		this.setSize(1.0F, 1.0F);
	}

	public EntityWebSwing(World var1, double var2, double var4, double var6, double var8, double var10, double var12, EntityPlayerMP var14)
	{
		super(var1);
		this.xTile = -1;
		this.yTile = -1;
		this.zTile = -1;
		this.inTile = 0;
		this.inGround = false;
		this.arrowShake = 0;
		this.flyTime = 0;
		this.setSize(1.0F, 1.0F);
		this.setPosition(var2, var4, var6);
		this.yOffset = 0.0F;
		this.setVelocity(var8, var10, var12);
		this.owner = var14;
	}

	public EntityWebSwing(World var1, double var2, double var4, double var6)
	{
		super(var1);
		this.xTile = -1;
		this.yTile = -1;
		this.zTile = -1;
		this.inTile = 0;
		this.inGround = false;
		this.arrowShake = 0;
		this.flyTime = 0;
		this.setSize(1.0F, 1.0F);
		this.setPosition(var2, var4, var6);
		this.yOffset = 0.0F;
	}

	public EntityWebSwing(World var1, EntityLiving var2, int var3, int var4)
	{
		super(var1);
		this.damage = var3;
		this.xTile = -1;
		this.yTile = -1;
		this.zTile = -1;
		this.inTile = 0;
		this.inGround = false;
		this.arrowShake = 0;
		this.flyTime = 0;
		this.shootingEntity = var2;
		this.setSize(1.0F, 1.0F);
		this.setLocationAndAngles(var2.posX, var2.posY + (double)var2.getEyeHeight() + 1D, var2.posZ, var2.rotationYaw, var2.rotationPitch);
		this.posX -= (double)(MathHelper.cos(this.rotationYaw / 180.0F * (float)Math.PI) * 0.16F);
		this.posY -= 0.10000000149011612D;
		this.posZ -= (double)(MathHelper.sin(this.rotationYaw / 180.0F * (float)Math.PI) * 0.16F);
		this.setPosition(this.posX, this.posY, this.posZ);
		this.yOffset = 0.0F;
		this.newMotionX = (double)(-MathHelper.sin(this.rotationYaw / 180.0F * (float)Math.PI) * MathHelper.cos(this.rotationPitch / 180.0F * (float)Math.PI));
		this.motionX = newMotionX;
		this.newMotionZ = (double)(MathHelper.cos(this.rotationYaw / 180.0F * (float)Math.PI) * MathHelper.cos(this.rotationPitch / 180.0F * (float)Math.PI));
		this.motionZ = newMotionZ;
		this.newMotionY = (double)(-MathHelper.sin(this.rotationPitch / 180.0F * (float)Math.PI));
		this.motionY = newMotionY;
		this.setArrowHeading(this.motionX, this.motionY, this.motionZ, 2.0F, 2.5F, var4);
	}

	protected void entityInit() {}

	public boolean isInRangeToRenderDist(double par1)
	{
		double var3 = this.boundingBox.getAverageEdgeLength() * 4.0D;
		var3 *= 76.0D;
		return par1 < var3 * var3;
	}

	public void setArrowHeading(double var1, double var3, double var5, float var7, float var8, int var9)
	{
		float var10 = MathHelper.sqrt_double(var1 * var1 + var3 * var3 + var5 * var5);
		var1 /= (double)var10;
		var3 /= (double)var10;
		var5 /= (double)var10;
		var1 += this.rand.nextGaussian() * 0.0034999998323619365D * (double)var8 * (double)var9 / 5.0D;
		var3 += this.rand.nextGaussian() * 0.0034999998323619365D * (double)var8 * (double)var9 / 5.0D;
		var5 += this.rand.nextGaussian() * 0.0034999998323619365D * (double)var8 * (double)var9 / 5.0D;
		var1 *= (double)var7;
		var3 *= (double)var7;
		var5 *= (double)var7;
		this.motionX = var1;
		this.motionY = var3;
		this.motionZ = var5;
		float var11 = MathHelper.sqrt_double(var1 * var1 + var5 * var5);
		this.prevRotationYaw = this.rotationYaw = (float)(Math.atan2(var1, var5) * 180.0D / Math.PI);
		this.prevRotationPitch = this.rotationPitch = (float)(Math.atan2(var3, (double)var11) * 180.0D / Math.PI);
		this.timeTillDeath = 0;
	}

	/**
	 * Sets the velocity to the args. Args: x, y, z
	 */
	public void setVelocity(double var1, double var3, double var5)
	{
		this.motionX = var1;
		this.motionY = var3;
		this.motionZ = var5;

		if (this.prevRotationPitch == 0.0F && this.prevRotationYaw == 0.0F)
		{
			float var7 = MathHelper.sqrt_double(var1 * var1 + var5 * var5);
			this.prevRotationYaw = this.rotationYaw = (float)(Math.atan2(var1, var5) * 180.0D / Math.PI);
			this.prevRotationPitch = this.rotationPitch = (float)(Math.atan2(var3, (double)var7) * 180.0D / Math.PI);
		}
	}

	/**
	 * Called to update the entity's position/logic.
	 */
	public void onUpdate()
	{
		super.onUpdate();

		this.worldObj.spawnParticle("smoke", this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D);

		if(this.getDistanceToEntity(shootingEntity) > 100)
		{
			this.setDead();
		}

		if (this.flyTime > 1000)
		{
			this.setDead();
		}

		if (this.prevRotationPitch == 0.0F && this.prevRotationYaw == 0.0F)
		{
			float var1 = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionZ * this.motionZ);
			this.prevRotationYaw = this.rotationYaw = (float)(Math.atan2(this.motionX, this.motionZ) * 180.0D / Math.PI);
			this.prevRotationPitch = this.rotationPitch = (float)(Math.atan2(this.motionY, (double)var1) * 180.0D / Math.PI);
		}

		int i = this.worldObj.getBlockId(this.xTile, this.yTile, this.zTile);

		if (i > 0)
		{
			Block.blocksList[i].setBlockBoundsBasedOnState(this.worldObj, this.xTile, this.yTile, this.zTile);
			AxisAlignedBB axisalignedbb = Block.blocksList[i].getCollisionBoundingBoxFromPool(this.worldObj, this.xTile, this.yTile, this.zTile);

			if (axisalignedbb != null && axisalignedbb.isVecInside(this.worldObj.getWorldVec3Pool().getVecFromPool(this.posX, this.posY, this.posZ)))
			{
				this.inGround = true;
			}
		}

		if (this.inGround)
		{
			int j = this.worldObj.getBlockId(this.xTile, this.yTile, this.zTile);
			
			if (j == this.inTile)
			{
				++this.timeTillDeath;

				if (this.timeTillDeath == 1200)
				{
					this.setDead();
				}
			}
			
			else
			{
				this.inGround = false;
				this.motionX *= (double)(this.rand.nextFloat() * 0.2F);
				this.motionY *= (double)(this.rand.nextFloat() * 0.2F);
				this.motionZ *= (double)(this.rand.nextFloat() * 0.2F);
				this.timeTillDeath = 0;
				this.flyTime = 0;
			}
			
			/*if(hitBlock)
			{
				this.distToWebSwingX = this.getDistance(this.shootingEntity.posX, this.posY, this.posZ);
				this.distToWebSwingY = this.getDistance(this.posX, this.shootingEntity.posY, this.posZ);
				this.distToWebSwingZ = this.getDistance(this.posX, this.posY, this.shootingEntity.posZ);
				hitBlock = false;
				System.out.println("hitblock: "+hitBlock);
				System.out.println("Player to swing distance X: " + distToWebSwingX);
				System.out.println("Player to swing distance Y: "+ distToWebSwingY);
				System.out.println("Player to swing distance Z: " + this.distToWebSwingZ);
			}*/

			
			//this.doWebSwing();
			//     if(this.newPlayerMotionY > 0)
			//     System.out.println("newMotionY " + newPlayerMotionY);
		}
		else
		{
			++this.flyTime;
		}

		Vec3 var16 = Vec3.fakePool.getVecFromPool(this.posX, this.posY, this.posZ);
		Vec3 var2 = Vec3.fakePool.getVecFromPool(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);
		MovingObjectPosition var3 = this.worldObj.rayTraceBlocks(var16, var2);
		var16 = Vec3.fakePool.getVecFromPool(this.posX, this.posY, this.posZ);
		var2 = Vec3.fakePool.getVecFromPool(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);

		if (var3 != null)
		{
			var2 = Vec3.fakePool.getVecFromPool(var3.hitVec.xCoord, var3.hitVec.yCoord, var3.hitVec.zCoord);
		}

		Entity var4 = null;
		List var5 = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.boundingBox.addCoord(this.motionX, this.motionY, this.motionZ).expand(1.0D, 1.0D, 1.0D));
		double var6 = 0.0D;
		float var10;

		for (int var8 = 0; var8 < var5.size(); ++var8)
		{
			Entity var9 = (Entity)var5.get(var8);

			if (var9.canBeCollidedWith() && (var9 != this.shootingEntity || this.flyTime >= 5))
			{
				var10 = 0.3F;
				AxisAlignedBB var11 = var9.boundingBox.expand((double)var10, (double)var10, (double)var10);
				MovingObjectPosition var12 = var11.calculateIntercept(var16, var2);

				if (var12 != null)
				{
					double var13 = var16.distanceTo(var12.hitVec);

					if (var13 < var6 || var6 == 0.0D)
					{
						var4 = var9;
						var6 = var13;
					}
				}
			}
		}

		if (var4 != null)
		{
			var3 = new MovingObjectPosition(var4);
		}

		float var17;

		if (var3 != null)
		{
			this.onImpact(var3);

			if (var3.entityHit != null)
			{
								
				if (var3.entityHit.attackEntityFrom(new EntityDamageSource("player", shootingEntity), this.damage))
				{
					if (this.knockbackStrength > 0)
					{
						float var25 = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionZ * this.motionZ);

						if (var25 > 0.0F)
						{
							var3.entityHit.addVelocity(-(this.motionX * (double)this.knockbackStrength * 0.6000000238418579D / (double)var25), 0.2D, -(this.motionZ * (double)this.knockbackStrength * 0.6000000238418579D / (double)var25));
						}
					}

					//    this.worldObj.playSoundAtEntity(this, "BulletHit", 1.0F, 1.2F / (this.rand.nextFloat() * 0.2F + 0.9F));
					//   this.worldObj.createExplosion(null, posX, posY, posZ, 4F, true);

					//     this.setDead();
				}
				else
				{
					this.motionX *= 0.10000000149011612D;
					this.motionY *= 0.10000000149011612D;
					this.motionZ *= 0.10000000149011612D;
					this.flyTime = 0;
					//    this.setDead();
				}
			}
			else
			{
				this.xTile = var3.blockX;
				this.yTile = var3.blockY;
				this.zTile = var3.blockZ;
				this.inTile = this.worldObj.getBlockId(this.xTile, this.yTile, this.zTile);

				this.motionX = 0;
				this.motionY = 0;
				this.motionZ = 0;

				var17 = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionY * this.motionY + this.motionZ * this.motionZ);
				this.posX -= this.motionX / (double)var17 * 0.05000000074505806D;
				this.posY -= this.motionY / (double)var17 * 0.05000000074505806D;
				this.posZ -= this.motionZ / (double)var17 * 0.05000000074505806D;

				// this.setDead();

			}
		}

		this.posX += this.motionX * 3.0D;
		this.posY += this.motionY * 3.0D;
		this.posZ += this.motionZ * 3.0D;
		var17 = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionZ * this.motionZ);
		this.rotationYaw = (float)(Math.atan2(this.motionX, this.motionZ) * 180.0D / Math.PI);

		for (this.rotationPitch = (float)(Math.atan2(this.motionY, (double)var17) * 180.0D / Math.PI); this.rotationPitch - this.prevRotationPitch < -180.0F; this.prevRotationPitch -= 360.0F)
		{
			;
		}

		while (this.rotationPitch - this.prevRotationPitch >= 180.0F)
		{
			this.prevRotationPitch += 360.0F;
		}

		while (this.rotationYaw - this.prevRotationYaw < -180.0F)
		{
			this.prevRotationYaw -= 360.0F;
		}

		while (this.rotationYaw - this.prevRotationYaw >= 180.0F)
		{
			this.prevRotationYaw += 360.0F;
		}

		this.rotationPitch = this.prevRotationPitch + (this.rotationPitch - this.prevRotationPitch) * 0.2F;
		this.rotationYaw = this.prevRotationYaw + (this.rotationYaw - this.prevRotationYaw) * 0.2F;
		float var18 = 0.99F;
		var10 = 0.03F;

		if (this.handleWaterMovement())
		{
			for (int var19 = 0; var19 < 4; ++var19)
			{
				float var20 = 0.25F;
				this.worldObj.spawnParticle("bubble", this.posX - this.motionX * (double)var20, this.posY - this.motionY * (double)var20, this.posZ - this.motionZ * (double)var20, this.motionX, this.motionY, this.motionZ);
			}

			var18 = 0.8F;
		}

		this.motionX *= (double)var18;
		this.motionY *= (double)var18;
		this.motionZ *= (double)var18;
		this.setPosition(this.posX, this.posY, this.posZ);

		//	 newPlayerMotionX
		//	 newPlayerMotionZ

	}

	/**
	 * (abstract) Protected helper method to write subclass entity data to NBT.
	 */
	public void writeEntityToNBT(NBTTagCompound var1)
	{
		var1.setShort("xTile", (short)this.xTile);
		var1.setShort("yTile", (short)this.yTile);
		var1.setShort("zTile", (short)this.zTile);
		var1.setByte("inTile", (byte)this.inTile);
		var1.setByte("shake", (byte)this.arrowShake);
		var1.setByte("inGround", (byte)(this.inGround ? 1 : 0));
	}

	/**
	 * (abstract) Protected helper method to read subclass entity data from NBT.
	 */
	public void readEntityFromNBT(NBTTagCompound var1)
	{
		this.xTile = var1.getShort("xTile");
		this.yTile = var1.getShort("yTile");
		this.zTile = var1.getShort("zTile");
		this.inTile = var1.getByte("inTile") & 255;
		this.arrowShake = var1.getByte("shake") & 255;
		this.inGround = var1.getByte("inGround") == 1;
	}

	@Override
	public void setDead()
	{
		this.isDead = true;
		this.swingDead = false;
		this.hitBlock = true;
		this.doSwing = true;
	}

	/*public void doWebSwing()
	{
		double tempDistToPlayerX = this.getDistance(this.shootingEntity.posX, this.posY, this.posZ);
		double tempDistToPlayerY = this.getDistance(this.posX, this.shootingEntity.posY, this.posZ);
		double tempDistToPlayerZ = this.getDistance(this.posX, this.posY, this.shootingEntity.posZ);

		newPlayerMotionY = 10 * Math.sin(Math.toRadians(tempDistToPlayer/40));

		if(doSwing)
		{
		this.shootingEntity.motionX = (double)(5.00F * -MathHelper.sin(this.shootingEntity.rotationYaw / 180.0F * (float)Math.PI) * MathHelper.cos(this.shootingEntity.rotationPitch / 180.0F * (float)Math.PI));
		this.shootingEntity.motionZ = (double)(5.00F * MathHelper.cos(this.shootingEntity.rotationYaw / 180.0F * (float)Math.PI) * MathHelper.cos(this.shootingEntity.rotationPitch / 180.0F * (float)Math.PI));
		this.shootingEntity.motionY = (double)(6.00F * -MathHelper.sin(this.shootingEntity.rotationPitch / 180.0F * (float)Math.PI));
		doSwing = false;
		
		this.setDead();
		}
		if(tempDistToPlayerX >= this.distToWebSwingX)
		{
			shootingEntity.motionX = 0;
		}
		if(tempDistToPlayerY >= this.distToWebSwingY)
		{
			shootingEntity.motionY = 0;
		}
		if(tempDistToPlayerZ >= this.distToWebSwingZ)
		{
			shootingEntity.motionZ = 0;
		}
		
		else if(this.distToWebSwing < tempDistToPlayer)	
			shootingEntity.posY -= newPlayerMotionY;
		System.out.println("temp dist to player: " + tempDistToPlayer + " disttoplayer: " + this.distToWebSwing);
	}*/


	/**
	 * Called by a player entity when they collide with an entity
	 */

	protected void onImpact(MovingObjectPosition par1MovingObjectPosition)
	{
		/*
		 Launch the player into the air
		 */
		this.shootingEntity.motionX = 2.5F * this.newMotionX;
		this.shootingEntity.motionZ = 2.5F * this.newMotionZ;
		this.shootingEntity.motionY = (double)(4.0F * -MathHelper.sin(this.shootingEntity.rotationPitch / 180.0F * (float)Math.PI));
		this.setDead();
	}

	public float getShadowSize()
	{
		return 0.0F;
	}
}
