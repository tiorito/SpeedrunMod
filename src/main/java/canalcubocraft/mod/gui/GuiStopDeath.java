package canalcubocraft.mod.gui;

import java.io.IOException;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiIngameMenu;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.fml.client.config.GuiButtonExt;

public class GuiStopDeath extends GuiScreen {

	private GuiButtonExt button1;

	@Override
	public void updateScreen() {

	}

	@Override
	public void initGui() {

		button1 = new GuiButtonExt(0, width / 2 - 50, height / 2 + 90, 110, 20, "GAME MENU");
		buttonList.add(button1);

	}

	@Override
	protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {

		super.mouseClicked(mouseX, mouseY, mouseButton);
	}

	@Override
	protected void actionPerformed(GuiButton button) throws IOException {

		if (button == button1) {

			mc.displayGuiScreen(new GuiIngameMenu());
		}

	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {

		drawDefaultBackground();

		drawCenteredString(fontRenderer,
				"You died because you already left or stopped the game before killing the Ender Dragon", width / 2, 21,
				16777045);

		drawCenteredString(fontRenderer, "Or you lost your 5 lives in survivel mode. So, this world is lost ", width / 2, 111, 16777045);

		super.drawScreen(mouseX, mouseY, partialTicks);

	}

}
