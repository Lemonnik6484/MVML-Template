package net.template.mod_id;

//? if fabric {

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//?}
//? if forge {
/*import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.loading.FMLLoader;
*/
//?}
//? if neoforge {
/*import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.loading.FMLLoader;
*/
//?}
//? if >=1.21.11 {
import net.minecraft.resources.Identifier;
//?} else {
/*import net.minecraft.resources.ResourceLocation;
 */
//?}
//? 1.18.2
//import net.minecraft.network.chat.TextComponent;

//? if forge || neoforge
//@Mod(MOD_ID)

@SuppressWarnings("removal")
public class MainClass
		//? if fabric
		implements ModInitializer

		{
	public static final String MOD_ID = /*$ mod_id */"mod_id";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public static final String VERSION = /*$ mod_version */"0.1.0";

	//? if fabric
	@Override public void onInitialize()

	//? if neoforge
	 //public MainClass(IEventBus modBus)

	//? if forge
	//public MainClass()

	{
		LOGGER.info("Starting " + MOD_ID);
	}

	//? >=1.21.11 {
	public static Identifier id(String namespace, String path) {
	//?} else {
	/*public static ResourceLocation id(String namespace, String path) {
	*///?}
		//? if >=1.21.11 {
		return Identifier.tryBuild(namespace, path);
		 //?} else if >1.18.2 {
		/*return ResourceLocation.tryBuild(namespace, path);
		 *///?} else {
		/*return new ResourceLocation(namespace, path);
		*///?}
	}
}