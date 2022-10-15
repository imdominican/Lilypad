package me.trevon.lilypad.commands;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.TabExecutor;

import java.util.ArrayList;
import java.util.List;

import static me.trevon.lilypad.Main.getInstance;

import static me.trevon.lilypad.managers.ConfigurationManager.config;
import static me.trevon.lilypad.utils.ColorUtils.color;

public class Command extends net.md_5.bungee.api.plugin.Command implements TabExecutor {

    public Command() {
        super("joinserver", "", "connect", "hop", "jump", "join");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {

        if (!(sender instanceof ProxiedPlayer)) {
            sender.sendMessage(new TextComponent("You can't run this command from the console fucking idiot!"));
            return;
        }

        ProxiedPlayer p = (ProxiedPlayer) sender;

        if (args.length == 0) {
            if (p.hasPermission("lilypad.command.join")) {
                p.sendMessage(new TextComponent(color(config.getString("usage"))));
            } else {
                p.sendMessage(new TextComponent(color( config.getString("no-permissions"))));
            }
        } else if (args.length == 1) {

            if (p.hasPermission("lilypad.command.join")) {
                ServerInfo server = ProxyServer.getInstance().getServerInfo(args[0]);

                if(server != null) {
                    p.connect(server);
                } else {
                    p.sendMessage(color(config.getString("not-found")));
                }
            } else {
                p.sendMessage(new TextComponent(color(config.getString("no-permissions"))));
            }

        }

    }

    @Override
    public Iterable<String> onTabComplete(CommandSender sender, String[] args) {

        if (!(sender instanceof ProxiedPlayer)) {
            sender.sendMessage(new TextComponent("You can't run this command from the console fucking idiot!"));
            return new ArrayList<>();
        }


        if (sender.hasPermission("lilypad.command.join")) {

            List<String> servers = new ArrayList<>();

            for (String s : getInstance().getProxy().getServers().keySet()) {

                servers.add(s);

            }

            return servers;
        }

        return new ArrayList<>();
    }
}
