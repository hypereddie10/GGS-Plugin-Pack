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
public class Afk extends PlayerCommand implements HelpItem
{
	@Override
	public String[] getShortcuts()
	{
		return new String[0];
	}

	@Override
	public String getName()
	{
		return "afk";
	}

	@Override
	public boolean isOpCommandDefault()
	{
		return false;
	}

	@Override
	public int getDefaultPermissionLevel()
	{
		return 0;
	}

	@Override
	public void execute(Player player, String[] args)
	{
		if(player.isAfk())
		{
			player.setAfk(false);

			player.getChat().serverBroadcast(player.username + " is no longer afk.");
		} else {
			player.setAfk(true);

			player.getChat().serverBroadcast(player.username + " is now afk...");
		}
	}

	@Override
	public void help(CommandExecutor executor) {
		executor.sendMessage("/afk - marks you as afk or back");
	}

    @Override
    public String getType() {
        return "information";
    }
}

