package net.nikolateslax.mods.discordsync;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;

@Mod(modid = "DiscordSync", version = "0.0.1.0", useMetadata = true, serverSideOnly = true, acceptedMinecraftVersions =  "[1.6.4,1.7.10]")
public class ModMain {
	@Config(modid = "DiscordSync", name = "ServerID", category = "Discord");
	public String server;
	@Config(modid = "DiscordSync", name = "RoleID", category = "Discord");
	public String role;
	@Config(modid = "DiscordSync", name = "AccessToken", category = "Discord");
	public String token;
	
	@EventHandler
	public void preinit(FMLPreInitializationEvent event) {
		
	}

	@EventHandler
	public void ServerStarting(FMLServerStartingEvent event) {
		event.registerServerCommand(new CommandHandler());
		
	}
	
	@SubscribeEvent
	public void OnLogin(PlayerLoggedInEvent event) {
		
	}
}

public class CommandHandler implements ICommand {
	private List<String> aliases;
	public CommandHandler()
	{
		this.aliases = new ArrayList<String>();
		this.aliases.add("discord_auth");
		this.aliases.add("discordauth");
	}
	
	@Override
	public String getCommandName()
	{
		return "discord-auth";
	}
	
	@Override
	public String getCommandUsage(ICommandSender icommandsender)
	{
		return "discord-auth [DiscordTag]";
	}
	
	@Override
	public List<String> getCommandAliases()
	{
		return this.aliases;
	}
	
	@Override
	public void processCommand(ICommandSender icommandsender, String[] astring)
	{
		if(astring.length == 0)
		{
			icommandsender.addChatMessage(new ChatComponentText("Invalid Arguments. Usage: " + this.getCommandUsage(icommandsender)));
			return;
		}
		if (astring[0] == "help")
		{
			icommandsender.addChatMessage(new ChatComponentText("Usage: " + this.getCommandUsage(icommandsender)));
			return;
		} else {
			ChatComponentText msg = new ChatComponentText("Output: [");
			for (int i = 0;i < astring.length; ++i)
			{
				msg.appendText(" " + astring[i]);
			}
			msg.appendText(" ]");
			icommandsender.addChatMessage(msg);	
		}
	}
	
	@Override
	public boolean canCommandSenderUseCommand(ICommandSender icommandsender) {
		return true;
	}
	
	@Override
	public List<?> addTabCompletionOptions(ICommandSender icommandsender, String[] astring) {
		return null;
	}
	
	@Override
	public boolean isUsernameIndex(String[] astring, int i) {
		return false;
	}
	
	@Override
	public int compareTo(Object o) {
		return 0;
	}
}