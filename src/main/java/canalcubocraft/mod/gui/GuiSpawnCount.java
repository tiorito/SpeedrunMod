package canalcubocraft.mod.gui;

import java.io.IOException;

import canalcubocraft.mod.speedrun.EnderDragonKillFinishOnPause;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiIngameMenu;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.fml.client.config.GuiButtonExt;

public class GuiSpawnCount extends GuiScreen {

	EnderDragonKillFinishOnPause end;
	private GuiButtonExt button1;

	@Override
	public void updateScreen() {

	}

	@Override
	public void initGui() {

		button1 = new GuiButtonExt(0, width / 2 - 50, height / 2 + 90, 110, 20, "GAME");
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

		drawCenteredString(fontRenderer, "You died so you have more" + " " + (5 - end.getDeathcount()) + " "
				+ "live(s) left until you kill the Ender Dragon", width / 2, 21, 16777045);

		drawCenteredString(fontRenderer, "Warning, don't leave this world until Ender Dragons death ", width / 2, 111,
				16777045);
		drawCenteredString(fontRenderer, "or you will lost this game(this world) permanently", width / 2, 121,
				16777045);

		super.drawScreen(mouseX, mouseY, partialTicks);

	}

}
