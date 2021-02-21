package canalcubocraft.mod.speedrun;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import canalcubocraft.mod.gui.GuiCheatingOrCreative;
import canalcubocraft.mod.gui.GuiEnderDragonDeath;
import canalcubocraft.mod.init.ModItems;
import canalcubocraft.mod.util.handlers.RegistryHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.DamageSource;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.GameType;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class EnderDragonKillFinishOnPause {

	public RegistryHandler reg;

	private static boolean flag = false;

	private static boolean ishardcore = false;
	private static boolean issurvivel = false;
	private static boolean iscreative = false;
	private static int deathcount = 0;
	private static NBTStorageFile file;
	private static boolean dragondeath = false;

	@SubscribeEvent
	public void entityDeathDrops(LivingDropsEvent event) {
		if (event.getEntity() instanceof EntityDragon ) {

			final List<ItemStack> ITEMS = new ArrayList<ItemStack>();

			if (Minecraft.getMinecraft().player != null) {

				GameType mc = Minecraft.getMinecraft().playerController.getCurrentGameType();

				if (mc.isCreative()) {

					iscreative = true;
					Certificate cert = new Certificate();
					cert.certifitation();

					Minecraft.getMinecraft().displayGuiScreen(new GuiCheatingOrCreative());

				}

				EnumDifficulty mcc = FMLCommonHandler.instance().getMinecraftServerInstance().getDifficulty();

				if (mc.isSurvivalOrAdventure() && mcc != EnumDifficulty.HARD && reg.compareTime() <= 360000) { // 6
																												// minutes
																												// tolerance

					flag = true;
					issurvivel = true;
					Certificate cert = new Certificate();
					cert.certifitation();

					ItemStack itemStackToDrop1 = new ItemStack(Items.DIAMOND, 128);
					ItemStack itemStackToDrop2 = new ItemStack(Items.EMERALD, 128);
					ItemStack itemStackToDrop3 = new ItemStack(ModItems.RUBY_SWORD, 1);

					ITEMS.add(itemStackToDrop1);
					ITEMS.add(itemStackToDrop2);
					ITEMS.add(itemStackToDrop3);

					for (int i = 0; i < ITEMS.size(); i++) {

						event.getDrops()
								.add(new EntityItem(event.getEntity().getEntityWorld(), event.getEntity().lastTickPosX,
										event.getEntity().lastTickPosY, event.getEntity().lastTickPosZ, ITEMS.get(i)));

					}

				}

				else {

					Minecraft.getMinecraft().displayGuiScreen(new GuiCheatingOrCreative());

				}

				if (mc.isSurvivalOrAdventure() && mcc == EnumDifficulty.HARD && reg.compareTime() <= 360000) {// 6
																												// minutes
																												// tolerance

					flag = true;
					ishardcore = true;
					Certificate cert = new Certificate();
					cert.certifitation();

					ItemStack itemStackToDrop1 = new ItemStack(Items.DIAMOND, 128);
					ItemStack itemStackToDrop2 = new ItemStack(Items.EMERALD, 128);
					ItemStack itemStackToDrop3 = new ItemStack(ModItems.SPEEDRUN_SWORD, 1);
					ItemStack itemStackToDrop4 = new ItemStack(Items.TOTEM_OF_UNDYING, 4);

					ITEMS.add(itemStackToDrop1);
					ITEMS.add(itemStackToDrop2);
					ITEMS.add(itemStackToDrop3);
					ITEMS.add(itemStackToDrop4);

					for (int i = 0; i < ITEMS.size(); i++) {

						event.getDrops()
								.add(new EntityItem(event.getEntity().getEntityWorld(), event.getEntity().lastTickPosX,
										event.getEntity().lastTickPosY, event.getEntity().lastTickPosZ, ITEMS.get(i)));

					}

				}

				else {

					Minecraft.getMinecraft().displayGuiScreen(new GuiCheatingOrCreative());

				}

				if (event.getEntity() instanceof EntityDragon ) {

					if (flag)
						Minecraft.getMinecraft().displayGuiScreen(new GuiEnderDragonDeath());

				}

			}
		}

	}

	public static boolean isIshardcore() {
		return ishardcore;
	}

	public static void setIshardcore(boolean ishardcore) {
		EnderDragonKillFinishOnPause.ishardcore = ishardcore;
	}

	public static boolean isIssurvivel() {
		return issurvivel;
	}

	public static void setIssurvivel(boolean issurvivel) {
		EnderDragonKillFinishOnPause.issurvivel = issurvivel;
	}

	public static boolean isIscreative() {
		return iscreative;
	}

	public static void setIscreative(boolean iscreative) {
		EnderDragonKillFinishOnPause.iscreative = iscreative;
	}

	@SubscribeEvent(priority = EventPriority.HIGHEST)
	public void onLivingDeathEvent(LivingDeathEvent event) {

		if (event.getEntity() instanceof EntityPlayer && event.getEntity() != null
				&& !event.getEntity().world.isRemote) {

			dragondeath = RegistryHandler.isDragonDeath();

			System.out.println("valor do dragondeath" + dragondeath);

			if (dragondeath) {

				System.out.println("zerou dragondeath");
				deathcount = 0;

			}

			else {

				System.out.println("não zerou dragondeath");
				deathcount++;

			}

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

						file.read();

						if (deathcount > 5 && !dragondeath) {

							System.out.println("dentro do deathcount > 5");

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

					}
				}
			}
		}

	

		if (event.getEntity() instanceof EntityDragon ) {

			File[] caminho3 = FMLCommonHandler.instance().getSavesDirectory().listFiles();

			String dir3;

			MinecraftServer mc3 = FMLCommonHandler.instance().getMinecraftServerInstance();
			if (mc3 != null) {
				dir3 = mc3.getFolderName();

				for (File read5 : caminho3) {

					String read6 = read5.getName();

					if (read6.equals(dir3)) {

						File caminhoarquivoparagravar = FMLCommonHandler.instance().getSavesDirectory()
								.getAbsoluteFile();
						String caminhoarquivoparagravar2 = caminhoarquivoparagravar.getName();

						Path path = Paths.get(caminhoarquivoparagravar2, dir3);

						NBTStorageFile file = new NBTStorageFile(path.toString(), "dragoncompressedFile.dat");

						file.clear();

						file.setBoolean("dragondead", true);

						file.write();

					}

				}

			}

			else {
				dir3 = null;
			}

		}
	}

	public static int getDeathcount() {
		return deathcount;
	}

	public static void setDeathcount(int deathcount) {
		EnderDragonKillFinishOnPause.deathcount = deathcount;
	}

}
