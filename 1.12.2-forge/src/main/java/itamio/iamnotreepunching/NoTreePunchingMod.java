package itamio.iamnotreepunching;

import java.util.Locale;
import java.util.Set;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * No Tree Punching — Itamio Port (1.12.2 Forge).
 *
 * <p>When the harvest tool is known, blocks drop nothing unless the held item
 * is the correct tool with a sufficient harvest level. Silk touch, fortune,
 * unknown-tool blocks and whitelisted blocks keep vanilla behaviour.</p>
 *
 * <p>Derivative of No Tree Punching by alcatrazEscapee (MIT); see CREDITS.md.</p>
 */
@Mod(modid = NoTreePunchingMod.MOD_ID, name = NoTreePunchingMod.MOD_NAME, version = NoTreePunchingMod.VERSION)
public class NoTreePunchingMod {

    public static final String MOD_ID = "itamiotreepunch";
    public static final String MOD_NAME = "No Tree Punching \u2014 Itamio Port";
    public static final String VERSION = "1.0.0";

    private static Set<String> whitelist = java.util.Collections.<String>emptySet();

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        whitelist = HandWhitelist.load();
        MinecraftForge.EVENT_BUS.register(this);
        System.out.println("[NoTreePunching] Loaded " + whitelist.size() + " whitelist entries");
    }

    @SubscribeEvent
    public void onHarvestDrops(BlockEvent.HarvestDropsEvent event) {
        if (event.isCanceled() || event.getDrops().isEmpty()) {
            return;
        }
        EntityPlayer player = event.getHarvester();
        if (player == null || player.capabilities.isCreativeMode) {
            return;
        }
        // Silk touch has its own special drop; never interfere with it.
        if (event.isSilkTouching()) {
            return;
        }

        IBlockState state = event.getState();
        if (state == null) {
            return;
        }
        Block block = state.getBlock();

        ResourceLocation registryName = block.getRegistryName();
        if (registryName != null && whitelist.contains(registryName.toString().toLowerCase(Locale.ROOT))) {
            return;
        }

        // We cannot judge the "correct tool" for a block with no harvest tool;
        // keep vanilla drops rather than making it impossible to obtain.
        String tool = block.getHarvestTool(state);
        if (tool == null) {
            return;
        }

        ItemStack held = player.getHeldItemMainhand();
        if (!held.isEmpty()
                && held.getItem().getHarvestLevel(held, tool, player, state) >= block.getHarvestLevel(state)) {
            return; // correct tool class + tier: fortune and special drops stay intact
        }

        event.getDrops().clear();
        event.setDropChance(0.0F);
    }
}
