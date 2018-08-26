package net.nikolateslax.mods.discordsync;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

class DiscordHelper {
	public String[] getDiscordMembers(String guildID, String role, String auth) throws MalformedURLException, IOException {
	    URL url = new URL("https://discordapp.com/api/guilds/" + guildID + "/members");
	    HttpURLConnection con = (HttpURLConnection) url.openConnection();
	    con.setRequestProperty("User-Agent", "MCDiscordSync");
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