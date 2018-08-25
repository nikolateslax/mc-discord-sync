package net.nikolateslax.mods.discordsync;

import cpw.mods.fml.common.Mod;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
@Mod(modid = "DiscordSync", version = "0.0.1.0", useMetadata = true, serverSideOnly = true, acceptedMinecraftVersions =  "[1.6.4,1.7.10]")
public class ModMain {

    String[] getDiscordMembers(String guildID, String role, String auth) throws MalformedURLException, IOException {
        URL url = new URL("https://discordapp.com/api/guilds/" + guildID + "/members");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestProperty("Authorization", auth);
        con.setRequestMethod("GET");
        int status = con.getResponseCode();
        StringBuilder content = new StringBuilder();
        try (BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()))) {
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
        }
        con.disconnect();
        return content.toString().split("\n");
    }
}
