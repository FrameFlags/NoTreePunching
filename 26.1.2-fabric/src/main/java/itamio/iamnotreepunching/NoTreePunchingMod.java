package itamio.iamnotreepunching;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLevelEvents;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * No Tree Punching — Itamio Port (1.20.1 Fabric).
 *
 * <p>Derivative of No Tree Punching by alcatrazEscapee (MIT); see CREDITS.md.</p>
 */
public class NoTreePunchingMod implements ModInitializer {

    public static final String MOD_ID = "itamiotreepunch";
    public static final String MOD_NAME = "No Tree Punching \u2014 Itamio Port";
    public static final String VERSION = "1.0.0";

    private static final AtomicBoolean APPLIED = new AtomicBoolean(false);

    @Override
    public void onInitialize() {
        // Registries are frozen by the time a world loads; apply once.
        ServerLevelEvents.LOAD.register((server, level) -> {
            if (APPLIED.compareAndSet(false, true)) {
                ToolRequirements.apply();
            }
        });
    }
}
