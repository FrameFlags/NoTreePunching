package itamio.iamnotreepunching;

import itamio.iamnotreepunching.mixin.BlockStateBaseAccessor;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Set;

/** Applies the tool requirement to every block state via the Mixin accessor. */
public final class ToolRequirements {

    private ToolRequirements() {}

    public static void apply() {
        Set<String> whitelist = HandWhitelist.load();
        int changed = 0;
        for (Block block : BuiltInRegistries.BLOCK) {
            Identifier name = BuiltInRegistries.BLOCK.getKey(block);
            if (name == null || whitelist.contains(name.toString())) {
                continue;
            }
            for (BlockState state : block.getStateDefinition().getPossibleStates()) {
                BlockStateBaseAccessor access = (BlockStateBaseAccessor) state;
                // Instant-break blocks (hardness <= 0) keep their vanilla hand drops.
                if (access.iamntp$getDestroySpeed() <= 0.0F) {
                    continue;
                }
                access.iamntp$setRequiresCorrectToolForDrops(true);
                changed++;
            }
        }
        System.out.println("[NoTreePunching] Applied tool requirement to " + changed
                + " block states; whitelist entries=" + whitelist.size());
    }
}
