package com.github.x3rmination.common.enchantments;

import com.github.x3rmination.init.EnchantmentInit;
import com.github.x3rmination.pitchants;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.living.LivingKnockBackEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber(modid=pitchants.MODID)
public class EnchantmentSpammerAndProud extends Enchantment {

    public EnchantmentSpammerAndProud() {
        super(Rarity.UNCOMMON, EnumEnchantmentType.BOW, new EntityEquipmentSlot[] {EntityEquipmentSlot.MAINHAND});
        this.setName("spammer_and_proud");
        this.setRegistryName(new ResourceLocation(pitchants.MODID + ":spammer_and_proud"));

        EnchantmentInit.ENCHANTMENTS.add(this);
    }

    @Override
    public int getMinEnchantability(int enchantmentLevel) {
        return 20 * enchantmentLevel;
    }

    @Override
    public int getMaxEnchantability(int enchantmentLevel) {
        return this.getMinEnchantability(enchantmentLevel) + 10;
    }

    @Override
    public int getMaxLevel() {
        return 3;
    }

    @SubscribeEvent
    public void onDamage(LivingHurtEvent event) {
        if (event.getSource().getTrueSource() instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) event.getSource().getTrueSource();
            int level = EnchantmentHelper.getEnchantmentLevel(EnchantmentInit.SPAMMER_AND_PROUD, player.getItemStackFromSlot(EntityEquipmentSlot.MAINHAND));
            if (level > 0 && event.getEntity().getDistance(event.getSource().getTrueSource()) < 8) {
                float totalAmount = event.getAmount() + ((event.getAmount()*((6*level) + 3))/100);
                event.setAmount(totalAmount);
            }
        }
    }
}