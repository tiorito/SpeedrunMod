package canalcubocraft.mod.gui;

import java.io.IOException;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.client.gui.GuiIngameMenu;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.fml.client.config.GuiButtonExt;

public class GuiEnderDragonDeath extends GuiScreen {

	private GuiButtonExt button1;

	@Override
	public void updateScreen() {
		// TODO Auto-generated method stub
		// textfield1.updateCursorCounter();

	}

	@Override
	public void initGui() {

		button1 = new GuiButtonExt(0, width / 2 - 50, height / 2 + 90, 110, 20, "BACK TO GAME");
		buttonList.add(button1);

	}

	@Override
	protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
		// TODO Auto-generated method stub

		// textfield1.mouseClicked(mouseX, mouseY, mouseButton);
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
		// TODO Auto-generated method stub
		drawDefaultBackground();

		drawCenteredString(fontRenderer, "Congratulations you defeat the Ender Dragon Boss", width / 2, 21, 16777045);

		drawCenteredString(fontRenderer,
				"Attention, some of the Ender Dragon drops new rewards can pass through the portal,", width / 2, 111,
				16777045);
		drawCenteredString(fontRenderer, "to the OverWorld and the other part can drop in the End, go and take them.",
				width / 2, 121, 16777045);
		drawCenteredString(fontRenderer, "Now that you beat the Dragon, you are free to stop or save the game",
				width / 2, 131, 16777045);

		super.drawScreen(mouseX, mouseY, partialTicks);

	}

}
