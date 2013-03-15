package net.mcforge.globalchat;

import java.util.HashMap;

public class RankHandler {
	private HashMap<String, IRCRank> users = new HashMap<String, IRCRank>(60);
	
	public synchronized void addUser(String name, IRCRank rank) {
		if (!users.containsKey(name)) {
			users.put(name, rank);
		}
	}
	
	public synchronized void removeUser(String name) {
		if (users.containsKey(name)) {
			users.remove(name);
		}
	}
	
	public synchronized void clear() {
		users.clear();
	}
	
	public IRCRank getRank(String user) {
		return users.get(user);
	}
	
	public boolean canSendRules(String user) {
		return getRank(user).getPerm() >= 1;
	}
	
	public HashMap<String, IRCRank> getUsers() {
		return users;
	}
}
