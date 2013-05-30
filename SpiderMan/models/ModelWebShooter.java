package colossali.SpiderMan.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelWebShooter extends ModelBase
{
  //fields
    ModelRenderer Base;
    ModelRenderer Spinerrete;
    ModelRenderer Trigger;
  
  public ModelWebShooter()
  {
    textureWidth = 64;
    textureHeight = 32;
    
      Base = new ModelRenderer(this, 0, 0);
      Base.addBox(-2.5F, -1F, -2.5F, 5, 4, 5);
      Base.setRotationPoint(0F, 21F, 0F);
      Base.setTextureSize(64, 32);
      Base.mirror = true;
      setRotation(Base, 0F, 0F, 0F);
      Spinerrete = new ModelRenderer(this, 20, 0);
      Spinerrete.addBox(0F, -2F, 0F, 1, 5, 1);
      Spinerrete.setRotationPoint(-0.5F, 19F, -3F);
      Spinerrete.setTextureSize(64, 32);
      Spinerrete.mirror = true;
      setRotation(Spinerrete, 0F, 0F, 0F);
      Trigger = new ModelRenderer(this, 0, 9);
      Trigger.addBox(-1F, -1F, 0F, 2, 2, 1);
      Trigger.setRotationPoint(0F, 17F, -2F);
      Trigger.setTextureSize(64, 32);
      Trigger.mirror = true;
      setRotation(Trigger, 0F, 0F, 0F);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5);
    Base.render(f5);
    Spinerrete.render(f5);
    Trigger.render(f5);
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z)
  {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }
  
  public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.setRotationAngles(f, f1, f2, f3, f4, f5, null);
  }

}
