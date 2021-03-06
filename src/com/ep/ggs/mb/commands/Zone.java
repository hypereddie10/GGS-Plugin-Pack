/*******************************************************************************
 * Copyright (c) 2012 MCForge.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/gpl.html
 ******************************************************************************/
package com.ep.ggs.mb.commands;

import com.ep.ggs.API.CommandExecutor;
import com.ep.ggs.API.ManualLoad;
import com.ep.ggs.API.action.Action;
import com.ep.ggs.API.action.BlockChangeAction;
import com.ep.ggs.API.plugin.PlayerCommand;
import com.ep.ggs.chat.ChatColor;
import com.ep.ggs.iomodel.Player;
import com.ep.ggs.mb.blocks.ZoneBlock;
import com.ep.ggs.world.blocks.classicmodel.ClassicBlock;


@ManualLoad
public class Zone extends PlayerCommand {
	@Override
	public void execute(Player player, String[] arg1) {
		if (arg1.length == 0) { help(player); return; }
		player.sendMessage("Place two blocks to determine the edges.");
		Thread t = new Run(player, arg1);
		t.start();
	}

	@Override
	public int getDefaultPermissionLevel() {
		return 50;
	}

	@Override
	public String getName() {
		return "zone";
	}

	@Override
	public String[] getShortcuts() {
		return new String[] { };
	}

	@Override
	public void help(CommandExecutor arg0) {
		arg0.sendMessage("/zone <owner1>, [owner2], [owner3] ... - Add a zone with the owners being owner1, owner2, owner3 and ect..");
		arg0.sendMessage("You can have as many owners as you like!");
	}

	@Override
	public boolean isOpCommandDefault() {
		return false;
	}
	
	private class Run extends Thread {
		String[] owner;
		Player player;
		public Run(Player player, String[] message) { this.player = player; this.owner = message; }
		
		@Override
		public void run() {
			int x1, y1, z1, x2, y2, z2;
			Action<BlockChangeAction> action = new BlockChangeAction();
			action.setPlayer(player);
			try {
				BlockChangeAction response = action.waitForResponse();
				x1 = response.getX();
				y1 = response.getY();
				z1 = response.getZ();
				action = new BlockChangeAction();
				action.setPlayer(player);
				response = action.waitForResponse();
				x2 = response.getX();
				y2 = response.getY();
				z2 = response.getZ();
				for (int xx = Math.min(x1, x2); xx <= Math.max(x1, x2); ++xx) {
					for (int yy = Math.min(y1, y2); yy <= Math.max(y1, y2); ++yy) {
						for (int zz = Math.min(z1, z2); zz <= Math.max(z1, z2); ++zz) {
							ZoneBlock zb = new ZoneBlock(owner, (ClassicBlock)player.getLevel().getTile(xx, yy, zz));
							Player.GlobalBlockChange((short)xx, (short)yy, (short)zz, zb, player.getLevel(), player.getServer());
						}
					}
				}
				player.sendMessage(ChatColor.Bright_Green + "Zone placed!");
			} catch (IllegalAccessException e) {
				e.printStackTrace();
				player.sendMessage("An error has occured..");
				return;
			} catch (InterruptedException e) {
				e.printStackTrace();
				player.sendMessage("An error has occured..");
				return;
			}
		}
	}
}

