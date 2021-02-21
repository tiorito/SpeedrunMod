package canalcubocraft.mod.speedrun;

import java.io.File;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.DosFileAttributes;
import java.util.UUID;

import canalcubocraft.mod.util.handlers.RegistryHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.fml.common.FMLCommonHandler;

public class Certificate {

	RegistryHandler reg;
	EnderDragonKillFinishOnPause ender;

	public void certifitation() {

		String dir;
		MinecraftServer mc = FMLCommonHandler.instance().getMinecraftServerInstance();

		File[] caminho = FMLCommonHandler.instance().getSavesDirectory().listFiles();

		if (mc != null) {

			dir = mc.getFolderName();

			for (File read : caminho) {

				String read2 = read.getName();

				if (read2.equals(dir)) {

					File caminhoarquivoparagravar = FMLCommonHandler.instance().getSavesDirectory().getAbsoluteFile();
					String caminhoarquivoparagravar2 = caminhoarquivoparagravar.getName();

					Path path = Paths.get(caminhoarquivoparagravar2, dir);

					File diretory = new File(path.toString(), "canalcubocraft@gmail.com");

					diretory.mkdir();

					String caminhoarquivoparagravar5 = diretory.getName();

					Path path2 = Paths.get(path.toString(), caminhoarquivoparagravar5);

					File diretory2 = new File(path2.toString(), "CertificationSpeedrun");

					Path path3 = Paths.get(path2.toString(), "CertificationSpeedrun");

					diretory2.mkdir();

					NBTStorageFile file = new NBTStorageFile(path3.toString(), "Certification.dat");

					file.clear();

					UUID uuid = Minecraft.getMinecraft().player.getUniqueID();
					String uuidstring = uuid.toString();

					String playername = Minecraft.getMinecraft().player.getName();

					file.setString("Player name", playername);
					file.setString("Valor do uuid", uuidstring);
					file.setString("gametimetoenderdragondeath", reg.getDurationBreakdown(reg.getSomatime()));
					file.setBoolean("iscreative", ender.isIscreative());
					file.setBoolean("issurvivel", ender.isIssurvivel());
					file.setBoolean("isIshardcore", ender.isIshardcore());

					file.write();

					System.out.println("path3.getFileName()" + path3.getFileName());
					try {
						Files.setAttribute(path3, "dos:readonly", true);

						Files.setAttribute(path3, "dos:hidden", true);

						DosFileAttributes dos = Files.readAttributes(path3, DosFileAttributes.class);

					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}

				else {
					dir = null;
				}

			}
		}
	}
}
