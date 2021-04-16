package com.cheatbreaker.client.util.worldborder;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.cheatbreaker.client.CheatBreaker;
import com.cheatbreaker.client.event.type.CollisionEvent;
import com.cheatbreaker.client.event.type.RenderWorldEvent;
import com.cheatbreaker.client.event.type.TickEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

import javax.vecmath.Vector2d;

public class WorldBorderManager {

    private final Minecraft minecraft = Minecraft.getMinecraft();
    private final CheatBreaker cheatbreaker = CheatBreaker.getInstance();
    private static final ResourceLocation forceFieldTexture = new ResourceLocation("textures/misc/forcefield.png");
    private final List<WorldBorder> borderList = new ArrayList<>();

    public WorldBorderManager() {
        CheatBreaker.getInstance().getEventBus().addEvent(TickEvent.class, this::onTick);
        CheatBreaker.getInstance().getEventBus().addEvent(RenderWorldEvent.class, this::onWorldRender);
        CheatBreaker.getInstance().getEventBus().addEvent(CollisionEvent.class, this::onCollision);
    }

    private void onCollision(CollisionEvent cbCollisionEvent) {
        for (WorldBorder border : this.borderList) {
            if (border.lIIIIlIIllIIlIIlIIIlIIllI(cbCollisionEvent.getX(), cbCollisionEvent.getZ())) continue;
            cbCollisionEvent.getBoundingBoxes().add(new AxisAlignedBB(cbCollisionEvent.getX(), cbCollisionEvent.getY(), cbCollisionEvent.getZ(), cbCollisionEvent.getX() + 1.0, cbCollisionEvent.getY() + 1.0, cbCollisionEvent.getZ() + 1.0));
        }
    }

    private void onTick(TickEvent cBTickEvent) {
        this.borderList.forEach(WorldBorder::ting);
    }

    private void onWorldRender(RenderWorldEvent renderWorldEvent) {
        if (!this.borderList.isEmpty()) {
            EntityClientPlayerMP entityClientPlayerMP = this.minecraft.thePlayer;
            float f = renderWorldEvent.getPartialTicks();
            this.borderList.stream().filter(WorldBorder::worldEqualsWorld).forEach(border -> {
                Tessellator tessellator = Tessellator.instance;
                double d = this.minecraft.gameSettings.renderDistanceChunks * 16;
                if (entityClientPlayerMP.posX >= border.IIIIllIlIIIllIlllIlllllIl() - d || entityClientPlayerMP.posX <= border.IlIlIIIlllIIIlIlllIlIllIl() + d || entityClientPlayerMP.posZ >= border.IIIIllIIllIIIIllIllIIIlIl() - d || entityClientPlayerMP.posZ <= border.IIIllIllIlIlllllllIlIlIII() + d) {
                    float f2;
                    double d2;
                    double d3;
                    float f3;
                    double d4 = 1.0 - border.lIIIIlIIllIIlIIlIIIlIIllI(entityClientPlayerMP) / d;
                    d4 = Math.pow(d4, 4);
                    double d5 = entityClientPlayerMP.lastTickPosX + (entityClientPlayerMP.posX - entityClientPlayerMP.lastTickPosX) * (double)f;
                    double d6 = entityClientPlayerMP.lastTickPosY + (entityClientPlayerMP.posY - entityClientPlayerMP.lastTickPosY) * (double)f;
                    double d7 = entityClientPlayerMP.lastTickPosZ + (entityClientPlayerMP.posZ - entityClientPlayerMP.lastTickPosZ) * (double)f;
                    GL11.glEnable(3042);
                    GL11.glBlendFunc(770, 1);
                    this.minecraft.getTextureManager().bindTexture(forceFieldTexture);
                    GL11.glDepthMask(false);
                    GL11.glPushMatrix();
                    boolean bl = true;
                    float f4 = (float)(WorldBorder.getWorldBorderColor(border).getRed() & 0xFF) / (float)255;
                    float f5 = (float)(WorldBorder.getWorldBorderColor(border).getGreen() & 0xFF) / (float)255;
                    float f6 = (float)(WorldBorder.getWorldBorderColor(border).getBlue() & 0xFF) / (float)255;
                    GL11.glPolygonOffset(-3, -3);
                    GL11.glEnable(32823);
                    GL11.glAlphaFunc(516, 0.097260274f * 1.028169f);
                    GL11.glEnable(3008);
                    GL11.glDisable(2884);
                    float f7 = (float)(Minecraft.getSystemTime() % 3000L) / (float)3000;
                    tessellator.startDrawingQuads();
                    GL11.glTranslated(-d5, -d6, -d7);
                    tessellator.setColorRGBA_F(f4, f5, f6, 1.0f);
                    double d8 = Math.max(MathHelper.floor_double(d7 - d), border.IIIllIllIlIlllllllIlIlIII());
                    double d9 = Math.min(MathHelper.ceiling_double_int(d7 + d), border.IIIIllIIllIIIIllIllIIIlIl());
                    if (d5 > border.IIIIllIlIIIllIlllIlllllIl() - d) {
                        f3 = 0.0f;
                        d3 = d8;
                        while (d3 < d9) {
                            d2 = Math.min(1.0, d9 - d3);
                            f2 = (float)d2 * (0.04054054f * 12.333334f);
                            tessellator.addVertexWithUV(border.IIIIllIlIIIllIlllIlllllIl(), 256, d3, f7 + f3, f7 + 0.0f);
                            tessellator.addVertexWithUV(border.IIIIllIlIIIllIlllIlllllIl(), 256, d3 + d2, f7 + f2 + f3, f7 + 0.0f);
                            tessellator.addVertexWithUV(border.IIIIllIlIIIllIlllIlllllIl(), 0.0, d3 + d2, f7 + f2 + f3, f7 + (float)128);
                            tessellator.addVertexWithUV(border.IIIIllIlIIIllIlllIlllllIl(), 0.0, d3, f7 + f3, f7 + (float)128);
                            d3 += 1.0;
                            f3 += 0.16463414f * 3.0370371f;
                        }
                    }
                    if (d5 < border.IlIlIIIlllIIIlIlllIlIllIl() + d) {
                        f3 = 0.0f;
                        d3 = d8;
                        while (d3 < d9) {
                            d2 = Math.min(1.0, d9 - d3);
                            f2 = (float)d2 * (0.3611111f * 1.3846154f);
                            tessellator.addVertexWithUV(border.IlIlIIIlllIIIlIlllIlIllIl(), 256, d3, f7 + f3, f7 + 0.0f);
                            tessellator.addVertexWithUV(border.IlIlIIIlllIIIlIlllIlIllIl(), 256, d3 + d2, f7 + f2 + f3, f7 + 0.0f);
                            tessellator.addVertexWithUV(border.IlIlIIIlllIIIlIlllIlIllIl(), 0.0, d3 + d2, f7 + f2 + f3, f7 + (float)128);
                            tessellator.addVertexWithUV(border.IlIlIIIlllIIIlIlllIlIllIl(), 0.0, d3, f7 + f3, f7 + (float)128);
                            d3 += 1.0;
                            f3 += 1.25f * 0.4f;
                        }
                    }
                    d8 = Math.max(MathHelper.floor_double(d5 - d), border.IlIlIIIlllIIIlIlllIlIllIl());
                    d9 = Math.min( MathHelper.ceiling_double_int(d5 + d), border.IIIIllIlIIIllIlllIlllllIl());
                    if (d7 > border.IIIIllIIllIIIIllIllIIIlIl() - d) {
                        f3 = 0.0f;
                        d3 = d8;
                        while (d3 < d9) {
                            d2 = Math.min(1.0, d9 - d3);
                            f2 = (float)d2 * (0.3115942f * 1.6046512f);
                            tessellator.addVertexWithUV(d3, 256, border.IIIIllIIllIIIIllIllIIIlIl(), f7 + f3, f7 + 0.0f);
                            tessellator.addVertexWithUV(d3 + d2, 256, border.IIIIllIIllIIIIllIllIIIlIl(), f7 + f2 + f3, f7 + 0.0f);
                            tessellator.addVertexWithUV(d3 + d2, 0.0, border.IIIIllIIllIIIIllIllIIIlIl(), f7 + f2 + f3, f7 + (float)128);
                            tessellator.addVertexWithUV(d3, 0.0, border.IIIIllIIllIIIIllIllIIIlIl(), f7 + f3, f7 + (float)128);
                            d3 += 1.0;
                            f3 += 1.5882353f * 0.31481484f;
                        }
                    }
                    if (d7 < border.IIIllIllIlIlllllllIlIlIII() + d) {
                        f3 = 0.0f;
                        d3 = d8;
                        while (d3 < d9) {
                            d2 = Math.min(1.0, d9 - d3);
                            f2 = (float)d2 * (1.6071428f * 0.31111112f);
                            tessellator.addVertexWithUV(d3, 256, border.IIIllIllIlIlllllllIlIlIII(), f7 + f3, f7 + 0.0f);
                            tessellator.addVertexWithUV(d3 + d2, 256, border.IIIllIllIlIlllllllIlIlIII(), f7 + f2 + f3, f7 + 0.0f);
                            tessellator.addVertexWithUV(d3 + d2, 0.0, border.IIIllIllIlIlllllllIlIlIII(), f7 + f2 + f3, f7 + (float)128);
                            tessellator.addVertexWithUV(d3, 0.0, border.IIIllIllIlIlllllllIlIlIII(), f7 + f3, f7 + (float)128);
                            d3 += 1.0;
                            f3 += 2.2820513f * 0.21910112f;
                        }
                    }
                    tessellator.draw();
                    GL11.glTranslated(0.0, 0.0, 0.0);
                    GL11.glEnable(2884);
                    GL11.glDisable(3008);
                    GL11.glPolygonOffset(0.0f, 0.0f);
                    GL11.glDisable(32823);
                    GL11.glEnable(3008);
                    GL11.glDisable(3042);
                    GL11.glPopMatrix();
                    GL11.glDepthMask(true);
                }
            });
        }
    }

    public void addWordBorder(String player, String world, int packetColor, double minX, double minZ, double maxX, double maxZ, boolean isCanShrinkExpand, boolean isCancelsExit) {
        this.borderList.add(new WorldBorder(this, player, world, packetColor, minX, minZ, maxX, maxZ, isCanShrinkExpand, isCancelsExit));
    }

    public void updateWorldBorder(String string, double minX, double minZ, double maxX, double maxZ, int durationTick) {
        this.borderList.stream().filter(borders -> Objects.equals(WorldBorder.getPlayer(borders), string) && WorldBorder.worldBorderIsCanShrinkExpand(borders)).findFirst().ifPresent(border -> {
            WorldBorder.lIIIIlIIllIIlIIlIIIlIIllI(border, new Vector2d(minX, minZ));
            WorldBorder.lIIIIIIIIIlIllIIllIlIIlIl(border, new Vector2d(maxX, maxZ));
            WorldBorder.lIIIIlIIllIIlIIlIIIlIIllI(border, 0);
            WorldBorder.lIIIIIIIIIlIllIIllIlIIlIl(border, durationTick);
        });
    }

    public void removeWorldBorder(String string) {
        this.borderList.removeIf(border -> Objects.equals(WorldBorder.getPlayer(border), string));
    }

    public void clearBorder() {
        this.borderList.clear();
    }

    public List<WorldBorder> getBorderList() {
        return this.borderList;
    }

    static CheatBreaker getCBInstance(WorldBorderManager cBWorldBorder) {
        return cBWorldBorder.cheatbreaker;
    }
}
 