/*******************************************************************************
* Copyright (c) 2012 MCForge.
* All rights reserved. This program and the accompanying materials
* are made available under the terms of the GNU Public License v3.0
* which accompanies this distribution, and is available at
* http://www.gnu.org/licenses/gpl.html
******************************************************************************/
package com.ep.ggs.plugin.commands;

import java.io.IOException;

import com.ep.ggs.API.CommandExecutor;
import com.ep.ggs.API.ManualLoad;
import com.ep.ggs.API.help.HelpItem;
import com.ep.ggs.API.plugin.PlayerCommand;
import com.ep.ggs.iomodel.Player;
import com.ep.ggs.util.FileUtils;


@ManualLoad
public class Rules extends PlayerCommand implements HelpItem {
	@Override
	public String[] getShortcuts() {
		return new String[0];
	}

	@Override
	public String getName() {
		return "rules";
	}

	@Override
	public boolean isOpCommandDefault() {
		return false;
	}

	@Override
	public int getDefaultPermissionLevel() {
		return 0;
	}

	@Override
	public void execute(Player executor, String[] args) {
		try {
			FileUtils.createIfNotExist("text", "rules.txt", "No rules added yet!");
		} catch (IOException e) {
			e.printStackTrace();
			executor.sendMessage("Error while displaying rules!");
			return;
		}
		String[] rules;
		try {
			rules = FileUtils.readAllLines("text/rules.txt");
		} catch (IOException e) {
			e.printStackTrace();
			executor.sendMessage("Error while displaying rules!");
			return;
		}
		for (int i = 0; i < rules.length; i++) {
			executor.sendMessage(rules[i]);
		}
	}

	@Override
	public void help(CommandExecutor executor) {
		executor.sendMessage("/rules - shows the server rules");
	}

    @Override
    public String getType() {
        return "information";
    }
}
