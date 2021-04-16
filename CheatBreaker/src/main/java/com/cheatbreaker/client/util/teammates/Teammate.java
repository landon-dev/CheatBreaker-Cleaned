package com.cheatbreaker.client.util.teammates;

import net.minecraft.util.Vec3;

import java.awt.*;

public class Teammate {
    private String teammateName;
    private boolean isLeader = false;
    private Vec3 vector3D;
    private long IIIIllIlIIIllIlllIlllllIl;
    private Color color;
    private long lastMS;

    public void setColor(Color color) {
        this.color = color;
    }

    public Teammate(String teammateName, boolean isLeader) {
        this.teammateName = teammateName;
        this.isLeader = isLeader;
        this.IIIIllIlIIIllIlllIlllllIl = System.currentTimeMillis();
    }

    public void lIIIIlIIllIIlIIlIIIlIIllI(double x, double y, double z, long lastMS) {
        this.vector3D = new Vec3(x, y, z);
        this.IIIIllIlIIIllIlllIlllllIl = System.currentTimeMillis();
        this.lastMS = lastMS;
    }

    public Vec3 getVector3D() {
        return this.vector3D;
    }

    public long lIIIIIIIIIlIllIIllIlIIlIl() {
        return this.IIIIllIlIIIllIlllIlllllIl;
    }

    public String getTeammateName() {
        return this.teammateName;
    }

    public long getLastMS() {
        return this.lastMS;
    }

    public boolean isLeader() {
        return this.isLeader;
    }

    public Color getColor() {
        return this.color;
    }
}
 