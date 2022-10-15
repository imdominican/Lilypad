package me.trevon.lilypad;

import me.trevon.lilypad.commands.Command;
import me.trevon.lilypad.commands.ReloadConfig;
import me.trevon.lilypad.managers.ConfigurationManager;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.api.plugin.PluginManager;

public final class Main extends Plugin {

    private static Main Instance;

    public static void setInstance(Main instance) {
        Instance = instance;
    }

    public static Main getInstance() {
        return Instance;
    }

    @Override
    public void onEnable() {
        // Plugin startup logic
        setInstance(this);

        ConfigurationManager.createMainConfig(this);
        ConfigurationManager.registerMainConfig(this);

        getProxy().getLogger().info("\n" +
                "[Lilypad] v1.0 Enabled \nMade by TV (tv#5909)");
        PluginManager pm = ProxyServer.getInstance().getPluginManager();
        pm.registerCommand(this, new Command());
        pm.registerCommand(this, new ReloadConfig());



        };



    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getProxy().getLogger().info("\n" +
                "[Lilypad] v1.0 Disabled \nMade by TV (tv#5909)\n");
    }


    public static void reloadConfiguration() {
        getInstance().getProxy().getPluginManager().getPlugin("Lilypad").onDisable();
        getInstance().getProxy().getPluginManager().getPlugin("Lilypad").onEnable();
    }
}
