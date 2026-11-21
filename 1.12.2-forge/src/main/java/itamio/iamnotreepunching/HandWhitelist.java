package itamio.iamnotreepunching;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Locale;
import java.util.Set;

/** Loads the user-editable hand-harvest whitelist from config/. */
public final class HandWhitelist {

    private static final String FILE_NAME = "itamiotreepunch-whitelist.txt";

    private HandWhitelist() {}

    public static Set<String> load() {
        File dir = new File("config");
        if (!dir.exists() && !dir.mkdirs()) {
            System.err.println("[NoTreePunching] Could not create config directory");
        }
        File file = new File(dir, FILE_NAME);
        if (!file.exists()) {
            try {
                Files.write(file.toPath(), DEFAULTS.getBytes(StandardCharsets.UTF_8));
            } catch (IOException e) {
                System.err.println("[NoTreePunching] Could not write default whitelist: " + e.getMessage());
            }
        }

        Set<String> ids = new LinkedHashSet<String>();
        if (file.exists()) {
            try {
                for (String line : Files.readAllLines(file.toPath(), StandardCharsets.UTF_8)) {
                    String id = line.trim();
                    if (id.isEmpty() || id.startsWith("#")) {
                        continue;
                    }
                    ids.add(id.toLowerCase(Locale.ROOT));
                }
            } catch (IOException e) {
                System.err.println("[NoTreePunching] Could not read whitelist: " + e.getMessage());
            }
        }
        return Collections.unmodifiableSet(ids);
    }

    private static final String DEFAULTS = "# No Tree Punching - Itamio Port : hand-harvest whitelist (1.12.2)\n# One block registry name per line. Blank lines and lines starting with # are ignored.\n# Whitelisted blocks keep vanilla drops even when broken empty-handed.\nminecraft:cactus\nminecraft:reeds\nminecraft:vine\nminecraft:leaves\nminecraft:leaves2\n";
}
