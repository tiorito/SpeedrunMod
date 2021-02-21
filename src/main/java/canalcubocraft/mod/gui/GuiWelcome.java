package canalcubocraft.mod.gui;

import java.io.IOException;

import canalcubocraft.mod.util.Reference;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiLabel;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.config.GuiButtonExt;

public class GuiWelcome extends GuiScreen {

	private static ResourceLocation pictureEntry = new ResourceLocation(Reference.MOD_ID,
			"textures/gui/artjuanminecraftmod.png");
	private GuiButtonExt button1;

	@Override
	public void initGui() {

		button1 = new GuiButtonExt(0, width / 2 - 50, height / 2 + 90, 70, 20, "MENU");
		buttonList.add(button1);

	}

	@Override
	protected void keyTyped(char typedChar, int keyCode) throws IOException {

	}

	@Override
	public void updateScreen() {

	}

	@Override
	protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {

		super.mouseClicked(mouseX, mouseY, mouseButton);
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {

		drawDefaultBackground();
		mc.renderEngine.bindTexture(pictureEntry);

		drawTexturedModalRect(0, 0, width, height, 1280, 570);

		drawCenteredString(fontRenderer, "Welcome to the SPEEDRUN mod. Challenge your friends and yourself", width / 2,
				21, 16777045);
		drawCenteredString(fontRenderer, "in a non stopping Minecraft game. Insist, persist and never desist.",
				width / 2, 31, 16777045);

		drawCenteredString(fontRenderer, "Instructions: Attention, never try to pause, stop, save or leave the game,",
				width / 2, 111, 16777045);
		drawCenteredString(fontRenderer, "until you defeat the Ender Dragon Boss or you will lose instantly", width / 2,
				121, 16777045);
		drawCenteredString(fontRenderer, "the game and the game save backup. After beating the Dragon then you",
				width / 2, 131, 16777045);
		drawCenteredString(fontRenderer, "will be able freely to stop or save the game and gain by the way", width / 2,
				141, 16777045);
		drawCenteredString(fontRenderer, "Ender Dragon drops new rewards.", width / 2, 151, 16777045);

		super.drawScreen(mouseX, mouseY, partialTicks);

	}

	@Override
	protected void actionPerformed(GuiButton button) throws IOException {

		if (button == button1) {
			mc.displayGuiScreen(new GuiMainMenu());

		}

	}

}
