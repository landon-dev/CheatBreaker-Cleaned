package com.cheatbreaker.client.util.title;

import com.cheatbreaker.client.CheatBreaker;
import com.cheatbreaker.client.event.type.GuiDrawEvent;
import com.cheatbreaker.client.event.type.TickEvent;
import com.google.common.collect.Lists;
import net.minecraft.client.Minecraft;
import org.lwjgl.opengl.GL11;

import java.awt.*;
import java.util.List;

public class TitleManager {
    private final Minecraft minecraft = Minecraft.getMinecraft();
    private final CheatBreaker cheatBreaker = CheatBreaker.getInstance();
    private final List<Title> titles = Lists.newArrayList();

    public void onDraw(GuiDrawEvent event) {
        GL11.glEnable(3042);
        for (Title title : this.titles) {
            boolean bl = Title.getTitleTitleEnum(title) == Title.TitleType.title;
            float f = bl ? (float)4 : 1.875f * 0.8f;
            float f2 = bl ? (float)-30 : (float)10;
            GL11.glScalef(f *= Title.getTitleScale(title), f, f);
            float f3 = 255;
            if (title.lIIIIlIIllIIlIIlIIIlIIllI()) {
                long var8_8 = Title.getTitleFadeTimeMs(title) - (System.currentTimeMillis() - title.startTimeMillis);
                f3 = 1.0f - var8_8 / (float)Title.getTitleFadeTimeMs(title);
            } else if (title.lIIIIIIIIIlIllIIllIlIIlIl()) {
                long var8_8 = Title.getTitleDisplayTimeMs(title) - (System.currentTimeMillis() - title.startTimeMillis);
                f3 = var8_8 <= 0.0f ? 0.0f : var8_8 / (float)Title.getTitleFadeOutTimeMs(title);
            }
            f3 = Math.min(1.0f, Math.max(0.0f, f3));
            if ((double)f3 <= 0.8611111044883728 * 0.17419354972680576) {
                f3 = 1.6f * 0.09375f;
            }
            this.minecraft.fontRenderer.drawCenteredStringWithShadow(Title.getTitleMessage(title), (int)((float)(event.getResolution().getScaledWidth() / 2) / f), (int)(((float)(event.getResolution().getScaledHeight() / 2 - this.minecraft.fontRenderer.FONT_HEIGHT / 2) + f2) / f), new Color(1.0f, 1.0f, 1.0f, f3).getRGB());
            GL11.glScalef(1.0f / f, 1.0f / f, 1.0f / f);
        }
        GL11.glDisable(3042);
    }

    public void onTick(TickEvent cBTickEvent) {
        if (!this.titles.isEmpty()) {
            this.titles.removeIf(title -> title.startTimeMillis + Title.getTitleDisplayTimeMs(title) < System.currentTimeMillis());
        }
    }

    public List<Title> getTitles() {
        return this.titles;
    }
}
 