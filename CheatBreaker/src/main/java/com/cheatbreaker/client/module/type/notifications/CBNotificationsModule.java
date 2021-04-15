package com.cheatbreaker.client.module.type.notifications;

import com.cheatbreaker.client.event.type.GuiDrawEvent;
import com.cheatbreaker.client.event.type.KeepAliveEvent;
import com.cheatbreaker.client.event.type.TickEvent;
import com.cheatbreaker.client.module.AbstractModule;
import net.minecraft.client.gui.ScaledResolution;

import java.util.Iterator;
import java.util.ArrayList;
import java.util.List;

public class CBNotificationsModule extends AbstractModule
{
    public long time;
    private List<Notification> notifications;

    public CBNotificationsModule() {
        super("Notifications");
        this.time = System.currentTimeMillis();
        this.notifications = new ArrayList<>();
        this.addEvent(KeepAliveEvent.class, this::onKeepAlive);
        this.addEvent(TickEvent.class, this::onTick);
        this.addEvent(GuiDrawEvent.class, this::onDraw);
        this.setDefaultState(true);
    }

    private void onKeepAlive(final KeepAliveEvent time) {
        this.time = System.currentTimeMillis();
    }

    private void onTick(final TickEvent cbTickEvent) {
        final Iterator<Notification> iterator = this.notifications.iterator();
        while (iterator.hasNext()) {
            final Notification notification = iterator.next();
            notification.tick();
            if (notification.startTime + notification.duration - System.currentTimeMillis() <= 0L) {
                int ilIlIIIlllIIIlIlllIlIllIl = notification.IIIIllIIllIIIIllIllIIIlIl;
                for (final Notification notif : this.notifications) {
                    if (notif.IIIIllIIllIIIIllIllIIIlIl < notification.IIIIllIIllIIIIllIllIIIlIl) {
                        notif.tick = 0;
                        notif.IlIlIIIlllIIIlIlllIlIllIl = ilIlIIIlllIIIlIlllIlIllIl;
                        ilIlIIIlllIIIlIlllIlIllIl = notif.IIIIllIIllIIIIllIllIIIlIl;
                    }
                }
                iterator.remove();
            }
        }
    }

    private void onDraw(final GuiDrawEvent event) {
        for (Notification notification : this.notifications) {
            notification.drawNotification(event.getResolution().getScaledWidth());
        }
    }

    public void queueNotification(final String type, String content, long duration) {
        final ScaledResolution scaledResolution = new ScaledResolution(this.minecraft, this.minecraft.displayWidth, this.minecraft.displayHeight);
        if (duration < 2000L) duration = 2000L;
        content = content.replaceAll("&([abcdefghijklmrABCDEFGHIJKLMNR0-9])|(&$)", "§$1");
        final String lowerCase = type.toLowerCase();
        CBNotificationType resolvedType;
        switch (lowerCase) {
            case "info": {
                resolvedType = CBNotificationType.INFO;
                break;
            }
            case "error": {
                resolvedType = CBNotificationType.ERROR;
                break;
            }
            default: {
                resolvedType = CBNotificationType.DEFAULT;
                break;
            }
        }
        final Notification notification1 = new Notification(this, scaledResolution, resolvedType, content, duration);
        int ilIlIIIlllIIIlIlllIlIllIl = notification1.IlIlIIIlllIIIlIlllIlIllIl - notification1.iconSize - 2;
        for (int i = this.notifications.size() - 1; i >= 0; --i) {
            final Notification notification = this.notifications.get(i);
            notification.tick = 0;
            notification.IlIlIIIlllIIIlIlllIlIllIl = ilIlIIIlllIIIlIlllIlIllIl;
            ilIlIIIlllIIIlIlllIlIllIl -= 2 + notification.iconSize;
        }
        this.notifications.add(notification1);
    }
}
