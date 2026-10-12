package dev.lemonnik.circuits;
import net.fabricmc.api.ModInitializer;
import net.minecraft.resources.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Circuits implements ModInitializer {
	public static final String MOD_ID = /*$ mod_id */"circuits";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public static final String VERSION = /*$ mod_version */"0.1.0";

	@Override public void onInitialize() {
		LOGGER.info("Starting " + MOD_ID);
	}

	public static Identifier id(String path) {
		return Identifier.tryBuild(MOD_ID, path);
	}
}