package com.cheatbreaker.client.util.hologram;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderManager;
import org.lwjgl.opengl.GL11;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Hologram {
    private final UUID uuid;
    private String[] lines;
    private final double x;
    private final double y;
    private final double z;
    private static final List<Hologram> holograms = new ArrayList<>();

    public Hologram(UUID uUID, double x, double y, double z) {
        this.uuid = uUID;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public static void renderHologram() {
        FontRenderer fontRenderer = Minecraft.getMinecraft().fontRenderer;
        RenderManager renderManager = RenderManager.instance;
        for (Hologram hologram : holograms) {
            if (hologram.getLines() == null || hologram.getLines().length <= 0) continue;
            for (int i = hologram.getLines().length - 1; i >= 0; --i) {
                String string = hologram.getLines()[hologram.getLines().length - i - 1];
                float f = (float)(hologram.getX() - (double)((float)RenderManager.renderPosX));
                float f2 = (float)(hologram.getY() + 1.0 + (double)((float)i * (0.16049382f * 1.5576924f)) - (double)((float)RenderManager.renderPosY));
                float f3 = (float)(hologram.getZ() - (double)((float)RenderManager.renderPosZ));
                float f4 = 1.7391304f * 0.92f;
                float f5 = 1.4081633f * 0.011835749f * f4;
                GL11.glPushMatrix();
                GL11.glTranslatef(f, f2, f3);
                GL11.glNormal3f(0.0f, 1.0f, 0.0f);
                GL11.glRotatef(-renderManager.playerViewY, 0.0f, 1.0f, 0.0f);
                GL11.glRotatef(renderManager.playerViewX, 1.0f, 0.0f, 0.0f);
                GL11.glScalef(-f5, -f5, f5);
                GL11.glDisable(GL11.GL_LIGHTING);
                GL11.glDepthMask(false);
                GL11.glDisable(GL11.GL_DEPTH_TEST);
                GL11.glEnable(GL11.GL_BLEND);
                OpenGlHelper.glBlendFunc(770, 771, 1, 0);
                Tessellator tessellator = Tessellator.instance;
                int n = 0;
                GL11.glDisable(GL11.GL_TEXTURE_2D);
                tessellator.startDrawingQuads();
                int n2 = fontRenderer.getStringWidth(string) / 2;
                tessellator.setColorRGBA_F(0.0f, 0.0f, 0.0f, 0.6875f * 0.36363637f);
                tessellator.addVertex(-n2 - 1, -1 + n, 0.0);
                tessellator.addVertex(-n2 - 1, 8 + n, 0.0);
                tessellator.addVertex(n2 + 1, 8 + n, 0.0);
                tessellator.addVertex(n2 + 1, -1 + n, 0.0);
                tessellator.draw();
                GL11.glEnable(GL11.GL_TEXTURE_2D);
                // getBorderList = drawString
                fontRenderer.drawString(string, -fontRenderer.getStringWidth(string) / 2, n, 0x20FFFFFF);
                GL11.glEnable(GL11.GL_DEPTH_TEST);
                GL11.glDepthMask(true);
                fontRenderer.drawString(string, -fontRenderer.getStringWidth(string) / 2, n, -1);
                GL11.glEnable(GL11.GL_LIGHTING);
                GL11.glDisable(GL11.GL_BLEND);
                GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
                GL11.glPopMatrix();
            }
        }
    }

    public UUID getUUID() {
        return this.uuid;
    }

    public String[] getLines() {
        return this.lines;
    }

    public void setLines(String[] newValue) {
        this.lines = newValue;
    }

    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }

    public double getZ() {
        return this.z;
    }

    public static List<Hologram> getHolograms() {
        return holograms;
    }
}