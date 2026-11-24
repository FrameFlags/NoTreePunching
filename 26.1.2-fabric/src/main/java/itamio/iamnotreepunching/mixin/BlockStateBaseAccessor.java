package itamio.iamnotreepunching.mixin;

import net.minecraft.world.level.block.state.BlockBehaviour;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.gen.Accessor;

/**
 * Mojang-mapped accessor for the private-final field on BlockStateBase.
 * The mapped field name is confirmed from the 1.20.1 Mojang mappings in the
 * local Gradle cache: {@code private final boolean requiresCorrectToolForDrops}.
 * {@code @Mutable} is required because the field is final.
 */
@Mixin(BlockBehaviour.BlockStateBase.class)
public interface BlockStateBaseAccessor {

    @Mutable
    @Accessor("requiresCorrectToolForDrops")
    void iamntp$setRequiresCorrectToolForDrops(boolean value);

    @Accessor("destroySpeed")
    float iamntp$getDestroySpeed();
}
