package canalcubocraft.mod.speedrun;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;

public class NBTStorageFile {

	// the file to use
	private final File file;
	// the TagCompound containing the files content
	private NBTTagCompound tagCompound;

	public NBTStorageFile(File file) {
		this.file = file;
	}

	// two optional constructors:
	public NBTStorageFile(String folder, String name) {
		this(new File(folder, name + ".dat"));
	}

	public NBTStorageFile(String path) {
		this(new File(path));
	}

	public boolean read() {
		try {
			// if the file exists we read it
			if (file.exists()) {
				FileInputStream fileinputstream = new FileInputStream(file);
				tagCompound = CompressedStreamTools.readCompressed(fileinputstream);
				fileinputstream.close();
				return true;

			} else {
				// else we create an empty TagCompound
				clear();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return false;
	}

	public void clear() {
		tagCompound = new NBTTagCompound();
	}

	public void write() {
		try {

			if (!file.exists()) {
				file.createNewFile();
			}

			FileOutputStream fileoutputstream = new FileOutputStream(file);
			CompressedStreamTools.writeCompressed(tagCompound, fileoutputstream);

			fileoutputstream.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public boolean hasKey(String key) {
		return tagCompound.hasKey(key);
	}

	public void remove(String key) {
		tagCompound.removeTag(key);
	}

	public boolean getBoolean(String key) {
		return tagCompound.getBoolean(key);
	}

	public double getDouble(String key) {
		return tagCompound.getDouble(key);
	}

	public float getFloat(String key) {
		return tagCompound.getFloat(key);
	}

	public int getInt(String key) {
		return tagCompound.getInteger(key);
	}

	public int[] getIntArray(String key) {
		return tagCompound.getIntArray(key);
	}

	public long getLong(String key) {
		return tagCompound.getLong(key);
	}

	public short getShort(String key) {
		return tagCompound.getShort(key);
	}

	public String getString(String key) {
		return tagCompound.getString(key);
	}

	public void setBoolean(String key, boolean value) {
		tagCompound.setBoolean(key, value);
	}

	public void setDouble(String key, double value) {
		tagCompound.setDouble(key, value);
	}

	public void setFloat(String key, float value) {
		tagCompound.setFloat(key, value);
	}

	public void setInt(String key, int value) {
		tagCompound.setInteger(key, value);
	}

	public void setIntArray(String key, int[] value) {
		tagCompound.setIntArray(key, value);
	}

	public void setLong(String key, long value) {
		tagCompound.setLong(key, value);
	}

	public void setShort(String key, short value) {
		tagCompound.setShort(key, value);
	}

	public void setString(String key, String value) {
		tagCompound.setString(key, value);
	}

}