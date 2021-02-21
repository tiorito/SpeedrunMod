package canalcubocraft.mod;

import org.lwjgl.input.Keyboard;
import canalcubocraft.mod.init.ModRecipes;
import canalcubocraft.mod.proxy.CommonProxy;
import canalcubocraft.mod.speedrun.EnderDragonKillFinishOnPause;
import canalcubocraft.mod.speedrun.NewKeyBinding;
import canalcubocraft.mod.util.Reference;
import canalcubocraft.mod.util.handlers.RegistryHandler;
import canalcubocraft.mod.world.ModWorldGen;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import scala.reflect.internal.Trees.New;

@Mod(modid = Reference.MOD_ID, name = Reference.NAME, version = Reference.VERSION)
public class Main {

	@Instance
	public static Main instance;

	@SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.COMMON_PROXY_CLASS)
	public static CommonProxy proxy;

	@EventHandler

	public static void PreInit(FMLPreInitializationEvent event) {

		GameRegistry.registerWorldGenerator(new ModWorldGen(), 3);

	}

	@EventHandler

	public static void init(FMLInitializationEvent event) {
		ModRecipes.init();
		NewKeyBinding meuk = new NewKeyBinding();

		meuk.meuKbinding();

		FMLCommonHandler.instance().bus().register(new RegistryHandler());

		MinecraftForge.EVENT_BUS.register(new EnderDragonKillFinishOnPause());

	}

	@EventHandler

	public static void Postinit(FMLPostInitializationEvent event) {

	}

}
