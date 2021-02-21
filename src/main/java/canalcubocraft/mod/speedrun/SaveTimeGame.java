package canalcubocraft.mod.speedrun;

import java.io.File;
import java.nio.file.Paths;

import canalcubocraft.mod.util.handlers.RegistryHandler;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.fml.common.FMLCommonHandler;
import java.nio.file.Path;
import java.nio.file.Paths;

public class SaveTimeGame {

	private static String dir;

	static long retunrsavetime;

	public static void saveTimeGame(long savetime) {

		File[] caminho = FMLCommonHandler.instance().getSavesDirectory().listFiles();

		MinecraftServer mc = FMLCommonHandler.instance().getMinecraftServerInstance();
		if (mc != null) {
			dir = mc.getFolderName();

			for (File read : caminho) {

				String read2 = read.getName();

				if (read2.equals(dir)) {

					File pathtosavefile = FMLCommonHandler.instance().getSavesDirectory().getAbsoluteFile();
					String pathtosavefile2 = pathtosavefile.getName();

					Path path = Paths.get(pathtosavefile2, dir);

					NBTStorageFile file = new NBTStorageFile(path.toString(), "gametimecompressedfile.dat");

					file.clear();

					file.setLong("gametime", savetime);

					file.write();

				}

			}
		}

		else {

			dir = null;
		}

	}

	public static long var;

	public static long loadTimeGame() {

		File[] caminho = FMLCommonHandler.instance().getSavesDirectory().listFiles();

		MinecraftServer mc = FMLCommonHandler.instance().getMinecraftServerInstance();
		if (mc != null) {
			dir = mc.getFolderName();

			for (File read : caminho) {

				String read2 = read.getName();

				if (read2.equals(dir)) {

					File pathtosavefile = FMLCommonHandler.instance().getSavesDirectory().getAbsoluteFile();
					String pathtosavefile2 = pathtosavefile.getName();

					Path path = Paths.get(pathtosavefile2, dir);

					NBTStorageFile file = new NBTStorageFile(path.toString(), "gametimecompressedfile.dat");

					RegistryHandler reg = new RegistryHandler();

					if (file.read()) {
						var = file.getLong("gametime");

						retunrsavetime = var;
					}

					else {

						var = 0;
					}

				}

			}

		}

		else {
			dir = null;
		}

		return var;

	}
}