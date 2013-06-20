package colossali.SpiderMan.entities;

import colossali.SpiderMan.common.mod_spiderman;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class RadioactiveSpider extends EntitySpider
{
    public RadioactiveSpider(World par1World)
    {
        super(par1World);
        this.texture = "/spiderman/radioactivespider.png";
        this.setSize(0.7F, 0.5F);
    }

    public int getMaxHealth()
    {
        return 12;
    }

    @SideOnly(Side.CLIENT)

    /**
     * How large the spider should be scaled.
     */
    public float spiderScaleAmount()
    {
        return 0.7F;
    }

    protected int getDropItemId()
    {
        return mod_spiderman.RadioactiveSpider.itemID;
    }
    
    public boolean attackEntityAsMob(EntityLiving par1Entity)
    {
        if (super.attackEntityAsMob(par1Entity))
        {
            if (par1Entity instanceof EntityLiving)
            {
                byte var2 = 0;

                if (this.worldObj.difficultySetting > 1)
                {
                    if (this.worldObj.difficultySetting == 2)
                    {
                        var2 = 7;
                    }
                    else if (this.worldObj.difficultySetting == 3)
                    {
                        var2 = 15;
                    }
                }

                if (var2 > 0)
                {
                    ((EntityLiving)par1Entity).addPotionEffect(new PotionEffect(Potion.poison.id, var2 * 20, 0));
                    ((EntityLiving)par1Entity).addPotionEffect(new PotionEffect(Potion.blindness.id, var2 * 20, 0));
                    ((EntityLiving)par1Entity).addPotionEffect(new PotionEffect(Potion.jump.id, (var2 + 5) * 20, 5));
                }
            }

            return true;
        }
        else
        {
            return false;
        }
    }
}
