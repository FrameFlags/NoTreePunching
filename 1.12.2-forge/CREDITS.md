# Credits

## No Tree Punching — Itamio Port

A clean-room port of the *drop-gating* mechanic from **No Tree Punching** to
Minecraft 1.12.2 Forge, 1.20.1 Fabric and 1.20.1 Forge.

* **Original mod:** No Tree Punching by **alcatrazEscapee**
* **Original repository:** https://github.com/alcatrazEscapee/no-tree-punching
* **Original licence:** MIT
* **Port, configurable whitelist and maintenance:** Sekai0NI0itamio
* **This port's licence:** MIT (see `LICENSE`)

### What was adapted

The upstream idea — force `requiresCorrectToolForDrops` on block states and
gate drops on the player's held tool — is reimplemented here with:

* 1.20.1: a Mixin `@Accessor` + `@Mutable` setter for the private-final
  `requiresCorrectToolForDrops` field on `BlockBehaviour.BlockStateBase`,
  applied to every block state at startup;
* 1.12.2: Forge's `BlockEvent.HarvestDropsEvent`, which clears drops when the
  harvester has no correct tool (no MixinBooter needed);
* a plain-text, user-editable hand-harvest whitelist at
  `config/itamiotreepunch-whitelist.txt` — this port's differentiator.

Harvest level / tool tier is respected (`Item#getHarvestLevel` on 1.12.2,
vanilla `ItemStack#isCorrectToolForDrops` on 1.20.1). Silk touch and fortune
loot tables are left untouched.

The upstream project also adds new tools, knapping and other machinery. This
port deliberately implements only the drop-gating mechanic; no items, GUI or
rendering were added.

All names, trademarks and copyrights remain the property of their respective
owners.
