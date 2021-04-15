package com.cheatbreaker.client.module.type.notifications;

import com.cheatbreaker.client.CheatBreaker;
import com.cheatbreaker.client.ui.util.RenderUtil;
import com.cheatbreaker.client.ui.util.font.CBFontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

class Notification {
    public CBNotificationType type;
    public String content;
    public long duration;
    public long startTime = System.currentTimeMillis();
    public int IIIIllIIllIIIIllIllIIIlIl = 2;
    public int IlIlIIIlllIIIlIlllIlIllIl;
    public int iconSize = 20;
    public int tick = 0;
    final CBNotificationsModule notificationsModule;

    Notification(CBNotificationsModule notificationsModule, ScaledResolution scaledResolution, CBNotificationType notificationType, String content, long duration) {
        this.notificationsModule = notificationsModule;
        this.type = notificationType;
        this.content = content;
        this.duration = duration;
        this.iconSize = notificationType == CBNotificationType.DEFAULT ? 16 : 20;
        this.IlIlIIIlllIIIlIlllIlIllIl = scaledResolution.getScaledHeight() - 14 - this.iconSize;
        this.IIIIllIIllIIIIllIllIIIlIl = scaledResolution.getScaledHeight() + this.iconSize;
    }

    public void tick() {
        if (this.IlIlIIIlllIIIlIlllIlIllIl != -1) {
            ++this.tick;
            float f = (float)this.tick * ((float)this.tick / (float)5) / (float)7;
            if (this.IIIIllIIllIIIIllIllIIIlIl > this.IlIlIIIlllIIIlIlllIlIllIl) {
                if ((float)this.IIIIllIIllIIIIllIllIIIlIl - f < (float)this.IlIlIIIlllIIIlIlllIlIllIl) {
                    this.IIIIllIIllIIIIllIllIIIlIl = this.IlIlIIIlllIIIlIlllIlIllIl;
                    this.IlIlIIIlllIIIlIlllIlIllIl = -1;
                } else {
                    this.IIIIllIIllIIIIllIllIIIlIl = (int)((float)this.IIIIllIIllIIIIllIllIIIlIl - f);
                }
            } else if (this.IIIIllIIllIIIIllIllIIIlIl < this.IlIlIIIlllIIIlIlllIlIllIl) {
                if ((float)this.IIIIllIIllIIIIllIllIIIlIl + f > (float)this.IlIlIIIlllIIIlIlllIlIllIl) {
                    this.IIIIllIIllIIIIllIllIIIlIl = this.IlIlIIIlllIIIlIlllIlIllIl;
                    this.IlIlIIIlllIIIlIlllIlIllIl = -1;
                } else {
                    this.IIIIllIIllIIIIllIllIIIlIl = (int)((float)this.IIIIllIIllIIIIllIllIIIlIl + f);
                }
            } else {
                this.IlIlIIIlllIIIlIlllIlIllIl = -1;
            }
        }
    }

    public void drawNotification(int scaledWidth) {
        CBFontRenderer cbFontRenderer = CheatBreaker.getInstance().playRegular16px;
        int n2 = this.IIIIllIIllIIIIllIllIIIlIl;
        float f = cbFontRenderer.getStringWidth(this.content);
        int n3 = (int)(this.type == CBNotificationType.DEFAULT ? f + (float)10 : f + (float)30);
        Gui.drawRect(scaledWidth - 5 - n3, n2, scaledWidth - 5, n2 + this.iconSize, -1358954496);
        switch (this.type) {
            case ERROR: {
                GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
                RenderUtil.lIIIIlIIllIIlIIlIIIlIIllI(new ResourceLocation("client/icons/error-64.png"), (float)6, (float)(scaledWidth - 10 - n3 + 9), (float)(n2 + 4));
                Gui.drawRect((float)(scaledWidth - 10) - f - 7.8428574f * 0.57377046f, n2 + 4, (float)(scaledWidth - 10) - f - (float)4, n2 + this.iconSize - 4, -1342177281);
                break;
            }
            case INFO: {
                GL11.glColor4f(1.0f, 1.0f, 1.0f, 0.7955224f * 0.81707317f);
                RenderUtil.lIIIIlIIllIIlIIlIIIlIIllI(new ResourceLocation("client/icons/info-64.png"), (float)6, (float)(scaledWidth - 10 - n3 + 9), (float)(n2 + 4));
                Gui.drawRect((float)(scaledWidth - 10) - f - 11.142858f * 0.40384614f, n2 + 4, (float)(scaledWidth - 10) - f - (float)4, n2 + this.iconSize - 4, -1342177281);
            }
        }
        long l = this.duration - (this.startTime + this.duration - System.currentTimeMillis());
        if (l > this.duration) {
            l = this.duration;
        }
        if (l < 0L) {
            l = 0L;
        }
        float f2 = f * ((float)l / (float)this.duration * (float)100 / (float)100);
        Gui.drawRect((float)(scaledWidth - 10) - f, (float)(n2 + this.iconSize) - 56.46667f * 0.077922076f, (float)(scaledWidth - 10) - f + f, n2 + this.iconSize - 4, 0x30666666);
        Gui.drawRect((float)(scaledWidth - 10) - f, (float)(n2 + this.iconSize) - 2.2f * 2.0f, (float)(scaledWidth - 10) - f + f2, n2 + this.iconSize - 4, -1878982912);
        cbFontRenderer.drawString(this.content, (float)(scaledWidth - 10) - f, (float)(n2 + (this.type == CBNotificationType.DEFAULT ? 2 : 4)), -1);
    }
}
