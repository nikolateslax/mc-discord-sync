package net.nikolateslax.mods.discordsync;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

class DiscordHelper {

    static String getAuthCode(String guildID, String role, String username) throws MalformedURLException, IOException {
        URL url = new URL("http://nikolateslax.net/discord/discordhelper.php?guild=" + guildID + "&role=" + role + "&user=" + username);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.getResponseCode();
        StringBuilder content = new StringBuilder();
        try (BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()))) {
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
        }
        con.disconnect();
        return content.toString();
    }
}
