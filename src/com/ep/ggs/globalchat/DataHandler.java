/*******************************************************************************
 * Copyright (c) 2012 MCForge.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/gpl.html
 ******************************************************************************/
package com.ep.ggs.globalchat;

import com.ep.ggs.iomodel.Player;

public abstract class DataHandler {
	protected final static String readRules = "gc_readrules";
	protected final static String agreed = "gc_agreed";
	protected final static String ignoring = "gc_ignoring";
	
	public static void preparePlayer(Player p) {
		if (!p.hasAttribute(readRules))
			p.setAttribute(readRules, false);
		if (!p.hasAttribute(agreed))
			p.setAttribute(agreed, false);
		if (!p.hasAttribute(ignoring))
			p.setAttribute(ignoring, false);
	}
	
	public static boolean readGCRules(Player p) {
		if (!p.hasAttribute(readRules))
			p.setAttribute(readRules, false);
		return p.getAttribute(readRules, Boolean.class).booleanValue();
	}
	
	public static boolean agreedToRules(Player p) {
		if (!p.hasAttribute(agreed))
			p.setAttribute(agreed, false);
		
		return p.getAttribute(agreed, Boolean.class).booleanValue();
	}
	
	public static boolean ignoringGC(Player p) {
		if (!p.hasAttribute(ignoring))
			p.setAttribute(ignoring, false);
		
		return p.getAttribute(ignoring, Boolean.class).booleanValue();
	}
	
	public static synchronized void setValue(Player p, String identKey, Object Attribute, boolean save) {
		p.setAttribute(identKey, Attribute);
		if (save)
			saveValue(p, identKey);
	}
	
	public static synchronized void setValue(Player p, String identKey, Object Attribute) {
		setValue(p, identKey, Attribute, false);
	}
	public static void saveValue(Player p, String identKey) {
		if (!p.hasAttribute(identKey))
			return;
		p.saveAttribute(identKey);
	}
}
