package canalcubocraft.mod.util.handlers;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;
import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAmount;
import java.time.temporal.TemporalUnit;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import com.mojang.authlib.GameProfile;

import canalcubocraft.mod.gui.GuiEnderDragonDeath;
import canalcubocraft.mod.gui.GuiSpawnCount;
import canalcubocraft.mod.gui.GuiStopDeath;
import canalcubocraft.mod.gui.GuiWelcome;
import canalcubocraft.mod.init.ModBlocks;
import canalcubocraft.mod.init.ModItems;
import canalcubocraft.mod.items.ItemBase;
import canalcubocraft.mod.speedrun.Certificate;
import canalcubocraft.mod.speedrun.EnderDragonKillFinishOnPause;
import canalcubocraft.mod.speedrun.NBTStorageFile;
import canalcubocraft.mod.speedrun.NewKeyBinding;
import canalcubocraft.mod.speedrun.SaveTimeGame;

import canalcubocraft.mod.util.IHasModel;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.GuiCreateWorld;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.client.gui.GuiIngameMenu;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.management.PlayerInteractionManager;
import net.minecraft.stats.StatList;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.GameType;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent.KeyInputEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import scala.tools.nsc.interactive.CompilerControl.NoWorkScheduler;

@EventBusSubscriber

public class RegistryHandler

{

	private static SaveTimeGame savetime;

	private static boolean showonetime = true;

	private static volatile boolean flag = false;

	private static volatile long startgametimeloaded = 0;
	private static volatile long currentgametime = 0;
	private static volatile long currentgamestarttime = 0;

	private static long diference = 0;
	private static long diference2 = 0;

	private static long playtimegamemodtogether = 0;

	private static boolean flag2 = true;

	private static boolean onetime2 = true;

	private static volatile long somatime = 0;

	private static NBTStorageFile file;

	private static LocalDateTime filecreationtime;

	@SubscribeEvent
	public static void onItemRegister(RegistryEvent.Register<Item> event) {
		event.getRegistry().registerAll(ModItems.ITEMS.toArray(new Item[0]));

	}

	@SubscribeEvent
	public static void onBlockRegister(RegistryEvent.Register<Block> event) {
		event.getRegistry().registerAll(ModBlocks.BLOCKS.toArray(new Block[0]));

	}

	@SubscribeEvent
	public static void onModelRegister(ModelRegistryEvent event) {
		for (Item item : ModItems.ITEMS) {

			if (item instanceof IHasModel) {
				((IHasModel) item).registerModels();
			}
		}
		for (Block block : ModBlocks.BLOCKS) {

			if (block instanceof IHasModel) {
				((IHasModel) block).registerModels();
			}

		}

	}

	@SideOnly(Side.CLIENT)
	@SubscribeEvent(priority = EventPriority.NORMAL, receiveCanceled = true)

	public static void onEvent(KeyInputEvent event)

	{

		NewKeyBinding meuk = new NewKeyBinding();

		KeyBinding[] keyBindings = meuk.keyBindings;

		if (keyBindings[0].isPressed()) {

			File[] caminho = FMLCommonHandler.instance().getSavesDirectory().listFiles();

			String dir;

			MinecraftServer mc = FMLCommonHandler.instance().getMinecraftServerInstance();
			if (mc != null) {
				dir = mc.getFolderName();

				for (File read : caminho) {

					String read2 = read.getName();

					if (read2.equals(dir)) {

						File caminhoarquivoparagravar = FMLCommonHandler.instance().getSavesDirectory()
								.getAbsoluteFile();
						String caminhoarquivoparagravar2 = caminhoarquivoparagravar.getName();

						Path path = Paths.get(caminhoarquivoparagravar2, dir);
						file = new NBTStorageFile(path.toString(), "dragoncompressedFile.dat");

						file.clear();

						file.read();

						if (file.getBoolean("dragondead")) {

							flag = false;

						}

						else {

							flag = true;

						}

					}

				}

			}

			else {
				dir = null;
			}

		}

		if (keyBindings[1].isKeyDown()) {

			somatime = currentgametime + startgametimeloaded;
			Minecraft.getMinecraft().player.sendChatMessage(
					"The value of time played without stopping is:  " + getDurationBreakdown(somatime));

		}

	}

	@SubscribeEvent(priority = EventPriority.LOWEST)

	public static void onTick(TickEvent.PlayerTickEvent event) throws InterruptedException {

		long finish = System.currentTimeMillis();
		currentgametime = (finish - currentgamestarttime);
		somatime = currentgametime + startgametimeloaded;

		savetime.saveTimeGame(somatime);

		MinecraftServer mc = FMLCommonHandler.instance().getMinecraftServerInstance();
		mc.commandManager.getCommands().clear();

		if (flag && !isDragonDeath()) {
			Minecraft.getMinecraft().displayGuiScreen(new GuiStopDeath());

			File[] caminho2 = FMLCommonHandler.instance().getSavesDirectory().listFiles();

			String dir2;

			MinecraftServer mc2 = FMLCommonHandler.instance().getMinecraftServerInstance();
			if (mc2 != null) {
				dir2 = mc2.getFolderName();

				for (File read3 : caminho2) {

					String read4 = read3.getName();

					if (read4.equals(dir2)) {

						File caminhoarquivoparagravar8 = FMLCommonHandler.instance().getSavesDirectory()
								.getAbsoluteFile();
						String caminhoarquivoparagravar9 = caminhoarquivoparagravar8.getName();

						Path path2 = Paths.get(caminhoarquivoparagravar9, dir2);

						NBTStorageFile file = new NBTStorageFile(path2.toString(), "surviveldeath.dat");

						file.clear();

						file.setBoolean("surviveldeath", true);

						file.write();

					}

				}

			}

			else {
				dir2 = null;
			}

			event.player.attackEntityFrom(DamageSource.OUT_OF_WORLD, Float.MAX_VALUE);

		}

	}

	private static boolean showwelcomepage = true;

	@SubscribeEvent(priority = EventPriority.NORMAL)
	public static void onGuiOpened(GuiOpenEvent e) {
		if (showwelcomepage && e.getGui() instanceof GuiMainMenu) {

			e.setGui(new GuiWelcome());

			showwelcomepage = false;
		}

	}

	@SubscribeEvent
	public static void beginnPlayToSave(RenderLivingEvent.Specials.Post<EntityLivingBase> event) {

		if (survivelDeath()) {

			flag = true;

		}

		if (onetime2) {

			savetime.saveTimeGame(somatime);

			onetime2 = false;

		}

	}

	@SubscribeEvent

	public void openWorld(PlayerEvent.PlayerRespawnEvent event) {

		if (!isDragonDeath()) {
			Minecraft.getMinecraft().displayGuiScreen(new GuiSpawnCount());
		}
		if (survivelDeath() && !isDragonDeath()) {

			event.player.attackEntityFrom(DamageSource.OUT_OF_WORLD, Float.MAX_VALUE);

		}

	}

	@SubscribeEvent(priority = EventPriority.NORMAL)

	public static void playerConnected(PlayerEvent.PlayerLoggedInEvent event) {

		currentgamestarttime = 0;
		flag = false;

		if (savetime.loadTimeGame() == 0) {

			File[] caminho = FMLCommonHandler.instance().getSavesDirectory().listFiles();

			String dir;

			MinecraftServer mc = FMLCommonHandler.instance().getMinecraftServerInstance();
			if (mc != null) {
				dir = mc.getFolderName();

				for (File read : caminho) {

					String read2 = read.getName();

					if (read2.equals(dir)) {

						File caminhoarquivoparagravar3 = FMLCommonHandler.instance().getSavesDirectory()
								.getAbsoluteFile();
						String caminhoarquivoparagravar4 = caminhoarquivoparagravar3.getName();

						File file2 = new File(caminhoarquivoparagravar4);

						try {

							filecreationtime = getCreateTime(file2);

						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();

						}

					}

				}

			}
		}

		else {

			File[] caminho = FMLCommonHandler.instance().getSavesDirectory().listFiles();

			String dir;

			MinecraftServer mc = FMLCommonHandler.instance().getMinecraftServerInstance();
			if (mc != null) {
				dir = mc.getFolderName();

				for (File read : caminho) {

					String read2 = read.getName();

					if (read2.equals(dir)) {

						File caminhoarquivoparagravar = FMLCommonHandler.instance().getSavesDirectory()
								.getAbsoluteFile();
						String caminhoarquivoparagravar2 = caminhoarquivoparagravar.getName();

						Path path = Paths.get(caminhoarquivoparagravar2, dir);
						file = new NBTStorageFile(path.toString(), "dragoncompressedFile.dat");

						file.clear();

						file.read();

					}

				}

			}

			else {
				dir = null;
			}

			if (!file.getBoolean("dragondead")) {

				flag = true;

			}

		}

		boolean onetime = true;
		if (true) {

			startgametimeloaded = savetime.loadTimeGame();

			currentgamestarttime = System.currentTimeMillis();

			somatime = currentgametime + startgametimeloaded;

			savetime.saveTimeGame(somatime);

		}

	}

	@SubscribeEvent(priority = EventPriority.NORMAL)
	public static void playerDisconnected(PlayerEvent.PlayerLoggedOutEvent event) {

		EnderDragonKillFinishOnPause.setDeathcount(0);

		if (!file.getBoolean("dragondead")) {

			File[] caminho2 = FMLCommonHandler.instance().getSavesDirectory().listFiles();

			String dir2;

			MinecraftServer mc2 = FMLCommonHandler.instance().getMinecraftServerInstance();
			if (mc2 != null) {
				dir2 = mc2.getFolderName();

				for (File read3 : caminho2) {

					String read4 = read3.getName();

					if (read4.equals(dir2)) {

						File caminhoarquivoparagravar8 = FMLCommonHandler.instance().getSavesDirectory()
								.getAbsoluteFile();
						String caminhoarquivoparagravar9 = caminhoarquivoparagravar8.getName();

						Path path2 = Paths.get(caminhoarquivoparagravar9, dir2);

						NBTStorageFile file = new NBTStorageFile(path2.toString(), "surviveldeath.dat");

						file.clear();

						file.setBoolean("surviveldeath", true);

						file.write();

					}

				}

			}

			else {
				dir2 = null;
			}

		}

		flag = false;

		somatime = currentgametime + startgametimeloaded;

		boolean onetime = true;
		if (true) {

			onetime = false;

		}

	}

	/**
	 * Convert a millisecond duration to a string format
	 * 
	 * @param millis A duration to convert to a string form
	 * @return A string of the form "X Days Y Hours Z Minutes A Seconds".
	 */
	public static String getDurationBreakdown(long millis) {
		if (millis < 0) {
			throw new IllegalArgumentException("Duration must be greater than zero!");
		}

		long days = TimeUnit.MILLISECONDS.toDays(millis);
		millis -= TimeUnit.DAYS.toMillis(days);
		long hours = TimeUnit.MILLISECONDS.toHours(millis);
		millis -= TimeUnit.HOURS.toMillis(hours);
		long minutes = TimeUnit.MILLISECONDS.toMinutes(millis);
		millis -= TimeUnit.MINUTES.toMillis(minutes);
		long seconds = TimeUnit.MILLISECONDS.toSeconds(millis);

		StringBuilder sb = new StringBuilder(64);
		sb.append(days);
		sb.append(" Days ");
		sb.append(hours);
		sb.append(" Hours ");
		sb.append(minutes);
		sb.append(" Minutes ");
		sb.append(seconds);
		sb.append(" Seconds");

		return (sb.toString());
	}

	private static LocalDateTime getCreateTime(File file) throws IOException {

		String dir;
		MinecraftServer mc = FMLCommonHandler.instance().getMinecraftServerInstance();
		if (mc != null) {
			dir = mc.getFolderName();

			String caminhoarquivoparagravar2 = file.getName();

			Path path = Paths.get(caminhoarquivoparagravar2, dir);
			BasicFileAttributeView basicfile = Files.getFileAttributeView(path, BasicFileAttributeView.class,
					LinkOption.NOFOLLOW_LINKS);
			BasicFileAttributes attr = basicfile.readAttributes();
			long date = attr.creationTime().toMillis();
			Instant instant = Instant.ofEpochMilli(date);

			return LocalDateTime.ofInstant(instant, ZoneId.systemDefault());

		}

		return null;

	}

	public static long compareTime() {

		File[] caminho = FMLCommonHandler.instance().getSavesDirectory().listFiles();

		String dir;

		MinecraftServer mc = FMLCommonHandler.instance().getMinecraftServerInstance();
		if (mc != null) {
			dir = mc.getFolderName();

			for (File read : caminho) {

				String read2 = read.getName();

				if (read2.equals(dir)) {

					File caminhoarquivoparagravar3 = FMLCommonHandler.instance().getSavesDirectory().getAbsoluteFile();
					String caminhoarquivoparagravar4 = caminhoarquivoparagravar3.getName();

					File file2 = new File(caminhoarquivoparagravar4);

					try {

						filecreationtime = getCreateTime(file2);

					} catch (IOException e) {

						e.printStackTrace();

					}

				}

			}

			ZoneOffset zone = ZoneOffset.of("Z");
			diference = LocalDateTime.now().toEpochSecond(zone);
			diference2 = filecreationtime.toEpochSecond(zone);

		}

		playtimegamemodtogether = (diference - diference2) - (somatime / 1000);

		return playtimegamemodtogether;

	}

	public static boolean survivelDeath() {

		File[] caminho = FMLCommonHandler.instance().getSavesDirectory().listFiles();

		String dir;

		MinecraftServer mc = FMLCommonHandler.instance().getMinecraftServerInstance();
		if (mc != null) {
			dir = mc.getFolderName();

			for (File read : caminho) {

				String read2 = read.getName();

				if (read2.equals(dir)) {

					File caminhoarquivoparagravar = FMLCommonHandler.instance().getSavesDirectory().getAbsoluteFile();
					String caminhoarquivoparagravar2 = caminhoarquivoparagravar.getName();

					Path path = Paths.get(caminhoarquivoparagravar2, dir);
					file = new NBTStorageFile(path.toString(), "surviveldeath.dat");

					file.clear();

					file.read();

					if (file.getBoolean("surviveldeath")) {

						return true;

					}

					else {

						return false;

					}

				}

			}

		}

		else {
			dir = null;
		}

		return false;

	}

	public static boolean isDragonDeath() {

		File[] caminho = FMLCommonHandler.instance().getSavesDirectory().listFiles();

		String dir;

		MinecraftServer mc = FMLCommonHandler.instance().getMinecraftServerInstance();
		if (mc != null) {
			dir = mc.getFolderName();

			for (File read : caminho) {

				String read2 = read.getName();

				if (read2.equals(dir)) {

					File caminhoarquivoparagravar = FMLCommonHandler.instance().getSavesDirectory().getAbsoluteFile();
					String caminhoarquivoparagravar2 = caminhoarquivoparagravar.getName();

					Path path = Paths.get(caminhoarquivoparagravar2, dir);
					file = new NBTStorageFile(path.toString(), "dragoncompressedFile.dat");

					file.clear();

					if (file.read()) {
						return true;

					}

				}

			}

		}

		else {
			dir = null;
		}

		return false;

	}

	public static long getSomatime() {
		return somatime;
	}

	public static void setSomatime(long somatime) {
		RegistryHandler.somatime = somatime;
	}

}
