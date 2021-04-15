package com.cheatbreaker.client.util.dash;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import javazoom.jl.decoder.JavaLayerUtils;
import javazoom.jl.player.Player;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DashUtil {
    private static final String apiURL = "https://dash-api.com/api/v3/allData.php";
    private static Player player;
    private static boolean IlllIIIlIlllIllIlIIlllIlI;
    private static DashPlayer dashPlayer;

    public static List<Station> dashHelpers() {
        JavaLayerUtils.setHook(new DashHook());
        ArrayList<Station> arrayList = new ArrayList<>();
        try {
            JsonObject DashApiJson = new JsonParser().parse(DashUtil.dashHelpers(apiURL)).getAsJsonObject();
            if (DashApiJson.has("stations")) {
                JsonArray jsonArray = DashApiJson.getAsJsonArray("stations");
                for (JsonElement jsonElement : jsonArray) {
                    JsonObject jsonObject2 = jsonElement.getAsJsonObject();
                    String name = jsonObject2.get("name").getAsString();
                    String genre = jsonObject2.get("genre").getAsString();
                    String squareLogoURL = jsonObject2.get("square_logo_url").getAsString();
                    String currentSongURL = jsonObject2.get("current_song_url").getAsString();
                    String streamURL = jsonObject2.get("stream_url").getAsString();
                    Station station = new Station(name, squareLogoURL, genre, currentSongURL, streamURL);
                    arrayList.add(station);
                }
            }
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
        return arrayList;
    }

    public static String dashHelpers(String string) {
        try {
            URLConnection uRLConnection = new URL(string).openConnection();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(uRLConnection.getInputStream()));
            return bufferedReader.readLine();
        }
        catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }
    }

    public static void end() {
        if (player != null) {
            player.close();
            player = null;
        }
        IlllIIIlIlllIllIlIIlllIlI = false;
    }

    public static boolean isPlayerNotNull() {
        return player != null;
    }

    public static void end(String string) {
        if (IlllIIIlIlllIllIlIIlllIlI) {
            return;
        }
        IlllIIIlIlllIllIlIIlllIlI = true;
        if (player != null) {
            player.close();
            player = null;
            return;
        }
        new Thread(() -> {
            try {
                URL uRL = new URL(string);
                InputStream inputStream = uRL.openStream();
                dashPlayer = new DashPlayer();
                player = new Player(inputStream, dashPlayer);
                player.play();
            }
            catch (Exception exception) {
                exception.printStackTrace();
            }
        }).start();
    }

    public static DashPlayer getDashPlayer() {
        return dashPlayer;
    }

    static {
        dashPlayer = new DashPlayer();
    }
}
 