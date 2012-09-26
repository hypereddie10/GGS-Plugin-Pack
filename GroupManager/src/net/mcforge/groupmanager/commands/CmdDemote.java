/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.mcforge.groupmanager.commands;

import net.mcforge.API.CommandExecutor;
import net.mcforge.API.plugin.Command;
import net.mcforge.groupmanager.API.GroupManagerAPI;
import net.mcforge.groupmanager.main.MainPlugin;
import net.mcforge.iomodel.Player;

/**
 *
 * @author Wouter Gerarts
 */
public class CmdDemote extends Command {

    @Override
    public String[] getShortcuts() {
        return new String[0];
    }

    @Override
    public String getName() {
        return "demote";
    }

    @Override
    public boolean isOpCommand() {
        return true;
    }

    @Override
    public int getDefaultPermissionLevel() {
        return 100;
    }

    @Override
    public void execute(CommandExecutor player, String[] args) {
        if (args.length == 1)
        {
            if (GroupManagerAPI.DemotePlayer(args[0]))
            {
                player.sendMessage("Demoted '" + Player.find(MainPlugin.server, args[0]).getName() + "'");
                Player.find(MainPlugin.server, args[0]).sendMessage("You have been demoted!");
            }
            else
            {
                player.sendMessage("Couldn't demote '" + args[0] + "'");
            }
        }
        else
        {
            help(player);
            return;
        }
    }

    @Override
    public void help(CommandExecutor executor) {
        executor.sendMessage("/demote <player> - demotes a player");
    }
    
}
