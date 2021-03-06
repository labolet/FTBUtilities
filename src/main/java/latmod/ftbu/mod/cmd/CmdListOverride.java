package latmod.ftbu.mod.cmd;

import ftb.lib.FTBLib;
import latmod.ftbu.cmd.*;
import latmod.ftbu.world.*;
import latmod.lib.*;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.IChatComponent;

public class CmdListOverride extends CommandLM
{
	public CmdListOverride()
	{ super("list", CommandLevel.ALL); }
	
	public void printHelp(ICommandSender ics)
	{
		printHelpLine(ics, "[uuid]");
	}
	
	public IChatComponent onCommand(ICommandSender ics, String[] args)
	{
		FastList<EntityPlayerMP> players = FTBLib.getAllOnlinePlayers(null);
		boolean printUUID = args.length > 0 && args[0].equals("uuid");
		
		FTBLib.printChat(ics, "Players currently online: [ " + players.size() + " ]");
		for(int i = 0; i < players.size(); i++)
		{
			EntityPlayerMP ep = players.get(i);
			LMPlayer p = LMWorldServer.inst.getPlayer(ep);
			
			if(printUUID)
				FTBLib.printChat(ics, p.getName() + " :: " + LMStringUtils.fromUUID(ep.getUniqueID()));
			else
				FTBLib.printChat(ics, p.getName());
		}
		
		return null;
	}
}