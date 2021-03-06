package com.ep.ggs.plugin.commands;

import com.ep.ggs.API.CommandExecutor;
import com.ep.ggs.API.ManualLoad;
import com.ep.ggs.API.help.HelpItem;
import com.ep.ggs.API.plugin.Command;
import com.ep.ggs.iomodel.Player;
import com.ep.ggs.server.Server;
@ManualLoad
public class Title extends Command implements HelpItem {

	@Override
	public String getName() {
		return "title";
	}

	@Override
	public String[] getShortcuts() {
		return new String[0];
	}

	@Override
	public int getDefaultPermissionLevel() {
		return 0;
	}

	@Override
	public boolean isOpCommandDefault() {
		return false;
	}

	@Override
	public void execute(CommandExecutor executor, String[] args) {
		if (args.length == 0) {
			executor.sendMessage("Please specify a title!");
			return;
		}
		Server s = executor.getServer();
		Player who;
		if (args.length == 1) {
			if (executor instanceof Player) {
				who = (Player)executor;
			}
			else {
				executor.sendMessage("You need to specify a player!");
				return;
			}
		}
		else {
			who = s.findPlayer(args[0]);
			if (who == null) {
				executor.sendMessage("Player not found!");
				return;
			}
		}
		String specified = args.length == 1 ? args[0] : args[1];
		specified = specified.replaceAll("%", "&");
		String prefix = who.getPrefix();
		if (prefix == null) {
			prefix = "[" + specified + "] ";
		}
		else {
			if (prefix.startsWith("[")) {
				prefix = prefix.substring(1);
			}
			else if (prefix.startsWith("&") && prefix.charAt(2) == '[') {
				prefix = prefix.substring(0, 2) + prefix.substring(3, prefix.length());
			}
			if (prefix.endsWith("] ")) {
				prefix = prefix.substring(0, prefix.length() - 2);
			}
			if (!prefix.startsWith("&") || prefix.length() <= 1) {
				prefix = "[" + specified + "] ";
			}
			else {
				String color = prefix.substring(0, 2);
				if (prefix.charAt(prefix.length() - 2) == '&') {
					prefix = prefix.substring(0, prefix.length() - 2);
				}
				prefix = color + "[" + specified + color + "] ";
			}
		}
		who.setRawPrefix(prefix);
		s.sendGlobalMessage(who.getDisplayName() + s.defaultColor + " got the title " + who.getPrefix());
	}
	
	@Override
	public void help(CommandExecutor executor) {
		executor.sendMessage("/title <player> <title> - changes the player's title");
		if (executor instanceof Player)
			executor.sendMessage("/title <title> - changes your title");
	}

    @Override
    public String getType() {
        return "personalization";
    }
}
