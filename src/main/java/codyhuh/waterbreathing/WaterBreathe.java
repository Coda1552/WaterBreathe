package codyhuh.waterbreathing;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;

@Mod(WaterBreathe.MOD_ID)
public class WaterBreathe {
    public static final String MOD_ID = "waterbreathe";

    public WaterBreathe() {
        IEventBus bus = MinecraftForge.EVENT_BUS;

        bus.addListener(this::useHeart);
    }

    private void useHeart(PlayerInteractEvent.EntityInteract e) {
        Player player = e.getEntity();
        ItemStack item = player.getItemInHand(e.getHand());

        if (item.is(Items.HEART_OF_THE_SEA) && e.getTarget() instanceof LivingEntity living && !living.hasEffect(MobEffects.WATER_BREATHING)) {
            living.addEffect(new MobEffectInstance(MobEffects.WATER_BREATHING, -1, 0, true, false));
            living.addEffect(new MobEffectInstance(MobEffects.GLOWING, 20, 0, true, false));
            living.playSound(SoundEvents.CONDUIT_ACTIVATE, 0.5F, 1.0F);

            player.swing(e.getHand());

            if (!player.getAbilities().instabuild) {
                item.shrink(1);
            }
        }
    }
}
