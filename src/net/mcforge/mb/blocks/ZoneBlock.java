package net.mcforge.mb.blocks;

import net.mcforge.groups.Group;
import net.mcforge.iomodel.Player;
import net.mcforge.world.Block;

public class ZoneBlock extends Block {
	private static final long serialVersionUID = -1429204508778865220L;
	
	private String[] owners;
	
	public ZoneBlock(byte ID, String name) {
		super(ID, name);
	}
	
	public ZoneBlock(String[] owners, Block b) {
		this(b.getVisableBlock(), b.name);
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
	public ZoneBlock clone(Block b) {
		ZoneBlock bb = new ZoneBlock(owners, b);
		return bb;
	}
}