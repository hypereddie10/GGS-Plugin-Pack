/*******************************************************************************
 * Copyright (c) 2012 MCForge.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/gpl.html
 ******************************************************************************/
package com.ep.ggs.mb.blocks;

import com.ep.ggs.groups.Group;
import com.ep.ggs.iomodel.Player;
import com.ep.ggs.world.blocks.classicmodel.ClassicBlock;

public class ZoneBlock extends ClassicBlock {
	private static final long serialVersionUID = -1429204508778865220L;
	
	private String[] owners;
	public ZoneBlock() { super(); }
	
	public ZoneBlock(byte ID, String name) {
		super(ID, name);
	}
	
	public ZoneBlock(String[] owners, ClassicBlock b) {
		this(b.getVisibleBlock(), b.name);
		this.owners = owners;
	}
	
	public String[] getOwners() {
		return owners;
	}
	
	public boolean canBuild(Player p) {
		Group g;
		for (String s : owners) {
			if (p.getName().equals(s)) //Check if name
				return true;
			if ((g = Group.find(s)) != null) { //Check if group
				if (Group.getGroup(p) == g)
					return true;
			}
			g = null; //Reset just in case
		}
		return false;
	}
	
	/**
	 * Create a new ZoneBlock for the block
	 * the player is placing
	 * @param b
	 *         The block the placing
	 * @return
	 *        The new ZoneBlock
	 */
	public ZoneBlock clone(ClassicBlock b) {
		ZoneBlock bb = new ZoneBlock(owners, b);
		return bb;
	}

	public String getOwnersString() {
		String finalstring = "";
		for (String s : getOwners()) {
			finalstring += " " + s;
		}
		return finalstring.trim();
	}
}

