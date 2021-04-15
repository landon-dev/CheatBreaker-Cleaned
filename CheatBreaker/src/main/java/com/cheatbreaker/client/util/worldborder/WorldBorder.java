package com.cheatbreaker.client.util.worldborder;

import com.cheatbreaker.client.CheatBreaker;
import net.minecraft.entity.Entity;

import javax.vecmath.Vector2d;
import java.awt.Color;

public class WorldBorder {
    private String player;
    private String world;
    private Color packetColor;
    private Vector2d IIIIllIIllIIIIllIllIIIlIl;
    private Vector2d IlIlIIIlllIIIlIlllIlIllIl;
    private Vector2d IIIllIllIlIlllllllIlIlIII;
    private Vector2d IllIIIIIIIlIlIllllIIllIII;
    private Vector2d lIIIIllIIlIlIllIIIlIllIlI;
    private Vector2d IlllIllIlIIIIlIIlIIllIIIl;
    private boolean isCanShrinkExpand;
    private boolean isCancelsExit;
    private int lIIlIlIllIIlIIIlIIIlllIII;
    private int IIIlllIIIllIllIlIIIIIIlII;
    final WorldBorderManager worldBorder;

    public WorldBorder(WorldBorderManager cBWorldBorder, String player, String world, int packetColor, double minX, double minZ, double maxX, double maxZ, boolean isCanShrinkExpand, boolean isCancelsExit) {
        this.worldBorder = cBWorldBorder;
        this.player = player;
        this.world = world;
        this.packetColor = new Color(packetColor, true);
        this.isCanShrinkExpand = isCanShrinkExpand;
        this.isCancelsExit = isCancelsExit;
        this.IIIllIllIlIlllllllIlIlIII = this.IIIIllIIllIIIIllIllIIIlIl = new Vector2d(minX, minZ);
        this.IllIIIIIIIlIlIllllIIllIII = this.IlIlIIIlllIIIlIlllIlIllIl = new Vector2d(maxX, maxZ);
    }

    public boolean lIIIIlIIllIIlIIlIIIlIIllI() {
        return this.isCanShrinkExpand && this.IIIlllIIIllIllIlIIIIIIlII != 0 && this.lIIlIlIllIIlIIIlIIIlllIII < this.IIIlllIIIllIllIlIIIIIIlII;
    }

    public boolean lIIIIlIIllIIlIIlIIIlIIllI(double d, double d2) {
        return !this.isCancelsExit || !this.world.equals(WorldBorderManager.getCBInstance(this.worldBorder).getNetHandler().getWorld()) || d + 1.0 > this.IlIlIIIlllIIIlIlllIlIllIl() && d < this.IIIIllIlIIIllIlllIlllllIl() && d2 + 1.0 > this.IIIllIllIlIlllllllIlIlIII() && d2 < this.IIIIllIIllIIIIllIllIIIlIl();
    }

    public void ting() {
        if (this.lIIIIlIIllIIlIIlIIIlIIllI()) {
            double d = this.IIIIllIIllIIIIllIllIIIlIl.x - this.lIIIIllIIlIlIllIIIlIllIlI.x;
            double d2 = this.IlIlIIIlllIIIlIlllIlIllIl.x - this.IlllIllIlIIIIlIIlIIllIIIl.x;
            double d3 = this.IIIIllIIllIIIIllIllIIIlIl.y - this.lIIIIllIIlIlIllIIIlIllIlI.y;
            double d4 = this.IlIlIIIlllIIIlIlllIlIllIl.y - this.IlllIllIlIIIIlIIlIIllIIIl.y;
            double d5 = (float)this.lIIlIlIllIIlIIIlIIIlllIII / (float)this.IIIlllIIIllIllIlIIIIIIlII;
            double d6 = this.IIIIllIIllIIIIllIllIIIlIl.x - d * d5;
            double d7 = this.IlIlIIIlllIIIlIlllIlIllIl.x - d2 * d5;
            double d8 = this.IIIIllIIllIIIIllIllIIIlIl.y - d3 * d5;
            double d9 = this.IlIlIIIlllIIIlIlllIlIllIl.y - d4 * d5;
            this.IIIllIllIlIlllllllIlIlIII = new Vector2d(d6, d8);
            this.IllIIIIIIIlIlIllllIIllIII = new Vector2d(d7, d9);
            ++this.lIIlIlIllIIlIIIlIIIlllIII;
        } else if (this.IlIlIIIlllIIIlIlllIlIllIl != this.IllIIIIIIIlIlIllllIIllIII || this.IIIIllIIllIIIIllIllIIIlIl != this.IIIllIllIlIlllllllIlIlIII) {
            this.IIIIllIIllIIIIllIllIIIlIl = this.IIIllIllIlIlllllllIlIlIII;
            this.IlIlIIIlllIIIlIlllIlIllIl = this.IllIIIIIIIlIlIllllIIllIII;
            this.IllIIIIIIIlIlIllllIIllIII = this.IlllIllIlIIIIlIIlIIllIIIl;
            this.IIIllIllIlIlllllllIlIlIII = this.lIIIIllIIlIlIllIIIlIllIlI;
            this.IIIlllIIIllIllIlIIIIIIlII = 0;
            this.lIIlIlIllIIlIIIlIIIlllIII = 0;
        }
    }

    public boolean worldEqualsWorld() {
        return CheatBreaker.getInstance().getNetHandler().getWorld().equals(this.world);
    }

    public double IIIIllIlIIIllIlllIlllllIl() {
        return this.IllIIIIIIIlIlIllllIIllIII.x;
    }

    public double IIIIllIIllIIIIllIllIIIlIl() {
        return this.IllIIIIIIIlIlIllllIIllIII.y;
    }

    public double IlIlIIIlllIIIlIlllIlIllIl() {
        return this.IIIllIllIlIlllllllIlIlIII.x;
    }

    public double IIIllIllIlIlllllllIlIlIII() {
        return this.IIIllIllIlIlllllllIlIlIII.y;
    }

    public double lIIIIlIIllIIlIIlIIIlIIllI(Entity entity) {
        return this.lIIIIIIIIIlIllIIllIlIIlIl(entity.posX, entity.posZ);
    }

    public double lIIIIIIIIIlIllIIllIlIIlIl(double d, double d2) {
        double d3 = d2 - this.IIIllIllIlIlllllllIlIlIII();
        double d4 = this.IIIIllIIllIIIIllIllIIIlIl() - d2;
        double d5 = d - this.IlIlIIIlllIIIlIlllIlIllIl();
        double d6 = this.IIIIllIlIIIllIlllIlllllIl() - d;
        double d7 = Math.min(d5, d6);
        d7 = Math.min(d7, d3);
        return Math.min(d7, d4);
    }

    public double IlllIIIlIlllIllIlIIlllIlI(double d, double d2) {
        double d3 = d2 - this.IIIllIllIlIlllllllIlIlIII();
        double d4 = this.IIIIllIIllIIIIllIllIIIlIl() - d2;
        return Math.min(d3, d4);
    }

    public double IIIIllIlIIIllIlllIlllllIl(double d, double d2) {
        double d3 = d - this.IlIlIIIlllIIIlIlllIlIllIl();
        double d4 = this.IIIIllIlIIIllIlllIlllllIl() - d;
        return Math.min(d3, d4);
    }

    public String getPlayer() {
        return this.player;
    }

    public String getWorld() {
        return this.world;
    }

    public Color getPacketColor() {
        return this.packetColor;
    }

    public Vector2d IlIlllIIIIllIllllIllIIlIl() {
        return this.IIIIllIIllIIIIllIllIIIlIl;
    }

    public Vector2d llIIlllIIIIlllIllIlIlllIl() {
        return this.IlIlIIIlllIIIlIlllIlIllIl;
    }

    public Vector2d lIIlIlIllIIlIIIlIIIlllIII() {
        return this.IIIllIllIlIlllllllIlIlIII;
    }

    public Vector2d IIIlllIIIllIllIlIIIIIIlII() {
        return this.IllIIIIIIIlIlIllllIIllIII;
    }

    public Vector2d llIlIIIlIIIIlIlllIlIIIIll() {
        return this.lIIIIllIIlIlIllIIIlIllIlI;
    }

    public Vector2d IIIlIIllllIIllllllIlIIIll() {
        return this.IlllIllIlIIIIlIIlIIllIIIl;
    }

    public boolean isCanShrinkExpand() {
        return this.isCanShrinkExpand;
    }

    public boolean isCancelsExit() {
        return this.isCancelsExit;
    }

    public int IIIIIIlIlIlIllllllIlllIlI() {
        return this.lIIlIlIllIIlIIIlIIIlllIII;
    }

    public int IllIllIIIlIIlllIIIllIllII() {
        return this.IIIlllIIIllIllIlIIIIIIlII;
    }

    public static String getPlayer(WorldBorder worldBorder) {
        return worldBorder.player;
    }

    public static Vector2d lIIIIlIIllIIlIIlIIIlIIllI(WorldBorder worldBorder, Vector2d vec2d) {
        worldBorder.lIIIIllIIlIlIllIIIlIllIlI = vec2d;
        return worldBorder.lIIIIllIIlIlIllIIIlIllIlI;
    }

    public static Vector2d lIIIIIIIIIlIllIIllIlIIlIl(WorldBorder worldBorder, Vector2d vec2d) {
        worldBorder.IlllIllIlIIIIlIIlIIllIIIl = vec2d;
        return worldBorder.IlllIllIlIIIIlIIlIIllIIIl;
    }

    public static int lIIIIlIIllIIlIIlIIIlIIllI(WorldBorder worldBorder, int n) {
        worldBorder.lIIlIlIllIIlIIIlIIIlllIII = n;
        return worldBorder.lIIlIlIllIIlIIIlIIIlllIII;
    }

    public static int lIIIIIIIIIlIllIIllIlIIlIl(WorldBorder worldBorder, int n) {
        worldBorder.IIIlllIIIllIllIlIIIIIIlII = n;
        return worldBorder.IIIlllIIIllIllIlIIIIIIlII;
    }

    public static boolean worldBorderIsCanShrinkExpand(WorldBorder worldBorder) {
        return worldBorder.isCanShrinkExpand;
    }

    public static Color getWorldBorderColor(WorldBorder worldBorder) {
        return worldBorder.packetColor;
    }
}