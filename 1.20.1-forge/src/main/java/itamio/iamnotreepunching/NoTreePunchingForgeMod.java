package itamio.iamnotreepunching;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;

/**
 * No Tree Punching — Itamio Port (1.20.1 Forge).
 *
 * <p>Derivative of No Tree Punching by alcatrazEscapee (MIT); see CREDITS.md.</p>
 */
@Mod(NoTreePunchingForgeMod.MOD_ID)
public class NoTreePunchingForgeMod {

    public static final String MOD_ID = "itamiotreepunch";
    public static final String MOD_NAME = "No Tree Punching \u2014 Itamio Port";
    public static final String VERSION = "1.0.0";

    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static final class Events {

        private Events() {}

        @SubscribeEvent
        public static void onLoadComplete(FMLLoadCompleteEvent event) {
            ToolRequirements.apply();
        }
    }
}
