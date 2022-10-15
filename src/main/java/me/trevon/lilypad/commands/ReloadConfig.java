package me.trevon.lilypad.commands;

import me.trevon.lilypad.managers.ConfigurationManager;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

import static me.trevon.lilypad.Main.reloadConfiguration;
import static me.trevon.lilypad.utils.ColorUtils.color;

public class ReloadConfig extends Command {

    public ReloadConfig() {
        super("lilypadreload", "", "lilyreload", "reloadlily", "reloadlilypad", "reloadpad");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {

        if (sender instanceof ProxiedPlayer) {
            ProxiedPlayer p = (ProxiedPlayer) sender;
            if (p.hasPermission("op")) {
                reloadConfiguration();
                p.sendMessage(new TextComponent(color(ConfigurationManager.config.getString("reload"))));
            } else {
                p.sendMessage(new TextComponent(color(ConfigurationManager.config.getString("no-permissions"))));
            }
        } else {

            reloadConfiguration();
            sender.sendMessage(new TextComponent(color(ConfigurationManager.config.getString("reload"))));

        }

    }
}
