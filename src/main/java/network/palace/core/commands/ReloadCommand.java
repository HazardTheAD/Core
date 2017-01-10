package network.palace.core.commands;

import network.palace.core.command.CommandException;
import network.palace.core.command.CommandMeta;
import network.palace.core.command.CoreCommand;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

/**
 * The type Reload command.
 */
@CommandMeta(aliases = {"rl"}, description = "Safely stop the server.")
public class ReloadCommand extends CoreCommand {

    /**
     * Instantiates a new Reload command.
     */
    public ReloadCommand() {
        super("reload");
    }

    @Override
    protected void handleCommandUnspecific(CommandSender sender, String[] args) throws CommandException {
        sender.sendMessage(ChatColor.RED + "Disabled");
    }
}
