package canalcubocraft.mod.gui;

import java.io.IOException;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.fml.client.config.GuiButtonExt;

public class GuiCheatingOrCreative extends GuiScreen {

	private GuiButtonExt button1;

	@Override
	public void updateScreen() {

	}

	@Override
	public void initGui() {

		button1 = new GuiButtonExt(0, width / 2 - 50, height / 2 + 90, 110, 20, "BACK TO GAME");
		buttonList.add(button1);

	}

	@Override
	protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {

		super.mouseClicked(mouseX, mouseY, mouseButton);
	}

	@Override
	protected void actionPerformed(GuiButton button) throws IOException {

		if (button == button1) {

			mc.displayGuiScreen(null);

		}

	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {

		drawDefaultBackground();

		drawCenteredString(fontRenderer, "Congratulations you defeat the Ender Dragon Boss", width / 2, 21, 16777045);

		drawCenteredString(fontRenderer,
				"In this Speedrun Mod, for reward you must play a nonstop game. You don't gain a reward if you played some time ",
				width / 2, 111, 16777045);
		drawCenteredString(fontRenderer,
				"in creative mode,  install this Mod not at the begin of this game (this minecraft world) or stop the game.",
				width / 2, 121, 16777045);

		super.drawScreen(mouseX, mouseY, partialTicks);

	}

}
