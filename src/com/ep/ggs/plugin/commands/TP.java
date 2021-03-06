/*******************************************************************************
 * Copyright (c) 2012 MCForge.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/gpl.html
 ******************************************************************************/
package com.ep.ggs.plugin.commands;

import com.ep.ggs.API.CommandExecutor;
import com.ep.ggs.API.ManualLoad;
import com.ep.ggs.API.help.HelpItem;
import com.ep.ggs.API.plugin.PlayerCommand;
import com.ep.ggs.iomodel.Player;

@ManualLoad
public class TP extends PlayerCommand implements HelpItem {
	@Override
	public String[] getShortcuts() {
		return new String[0];
	}

	@Override
	public String getName() {
		return "tp";
	}

	@Override
	public boolean isOpCommandDefault() {
		return true;
	}

	@Override
	public int getDefaultPermissionLevel() {
		return 100;
	}

	@Override
	public void execute(Player player, String[] args) {
		if (args.length == 1) {
			Player other = player.getServer().findPlayer(args[0]);
			if (other != null) {
				if (player.getLevel() != other.getLevel())
					player.changeLevel(other.getLevel());
				player.setPos(other.getX(), other.getY(), other.getZ());
			}
			else {
				player.sendMessage(args[1] + " is not online.");
			}
		}
		else if (args.length == 2) {
			Player otherTPing = player.getServer().findPlayer(args[0]);
			Player otherTPto = player.getServer().findPlayer(args[1]);

			if (otherTPing != null && otherTPto != null) {
				if (otherTPing.getLevel() != otherTPto.getLevel())
					otherTPing.changeLevel(otherTPto.getLevel());
				otherTPing.setPos(otherTPto.getX(), otherTPto.getY(),
						otherTPto.getZ());
			}
			else {
				player.sendMessage("Either " + args[0] + " or " + args[1]
						+ " is not online.");
			}
		}
		else {
			help(player);
		}
	}

	@Override
	public void help(CommandExecutor executor) {
		executor.sendMessage("/tp <player> - teleports you to a player");
		executor.sendMessage("/tp <player1> <player2> - teleports player1 to player2");
	}

    @Override
    public String getType() {
        return "other";
    }
}

