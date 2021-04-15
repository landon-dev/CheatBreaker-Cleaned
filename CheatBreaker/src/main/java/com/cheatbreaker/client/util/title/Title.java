package com.cheatbreaker.client.util.title;

public class Title {
    private final TitleType titleEnum;
    private final String message;
    private final float scale;
    private final long displayTimeMs;
    private final long fadeInTimeMs;
    private final long fadeOutTimeMs;
    protected final long startTimeMillis = System.currentTimeMillis();

    public Title(String message, TitleType titleEnum, float scale, long displayTimeMs, long fadeInTimeMs, long fadeOutTimeMs) {
        this.message = message;
        this.scale = scale;
        this.titleEnum = titleEnum;
        this.displayTimeMs = displayTimeMs == 0L ? 5000L : displayTimeMs;
        this.fadeInTimeMs = fadeInTimeMs;
        this.fadeOutTimeMs = fadeOutTimeMs;
    }

    public boolean lIIIIlIIllIIlIIlIIIlIIllI() {
        return System.currentTimeMillis() < this.startTimeMillis + this.fadeInTimeMs;
    }

    public boolean lIIIIIIIIIlIllIIllIlIIlIl() {
        return System.currentTimeMillis() > this.startTimeMillis + this.displayTimeMs - this.fadeOutTimeMs;
    }

    public TitleType getTitleEnum() {
        return this.titleEnum;
    }

    public String getMessage() {
        return this.message;
    }

    public float getScale() {
        return this.scale;
    }

    public long getDisplayTimeMs() {
        return this.displayTimeMs;
    }

    public long getFadeInTimeMs() {
        return this.fadeInTimeMs;
    }

    public long getFadeOutTimeMs() {
        return this.fadeOutTimeMs;
    }

    static TitleType getTitleTitleEnum(Title title) {
        return title.titleEnum;
    }

    static float getTitleScale(Title title) {
        return title.scale;
    }

    static long getTitleFadeTimeMs(Title title) {
        return title.fadeInTimeMs;
    }

    static long getTitleDisplayTimeMs(Title title) {
        return title.displayTimeMs;
    }

    static long getTitleFadeOutTimeMs(Title title) {
        return title.fadeOutTimeMs;
    }

    static String getTitleMessage(Title title) {
        return title.message;
    }

    public enum TitleType {
        subtitle,
        title;

    }
}