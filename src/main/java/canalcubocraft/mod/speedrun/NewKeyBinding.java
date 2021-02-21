package canalcubocraft.mod.speedrun;

import org.lwjgl.input.Keyboard;

import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;

public class NewKeyBinding {

	public static KeyBinding[] keyBindings;

	public NewKeyBinding() {

	}

	public static void meuKbinding() {

		keyBindings = new KeyBinding[2];

		keyBindings[0] = new KeyBinding("No stopping allowed", Keyboard.KEY_ESCAPE, "speedrun_mod");
		keyBindings[1] = new KeyBinding("key.hud.desc", Keyboard.KEY_H, "key.magicbeans.category");

		for (int i = 0; i < keyBindings.length; ++i) {
			ClientRegistry.registerKeyBinding(keyBindings[i]);
		}

	}

}
