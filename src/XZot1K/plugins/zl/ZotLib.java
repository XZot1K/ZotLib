package XZot1K.plugins.zl;

import XZot1K.plugins.zl.commands.ZotLibCommand;
import XZot1K.plugins.zl.libraries.*;
import XZot1K.plugins.zl.libraries.inventorylib.InventoryLibrary;
import XZot1K.plugins.zl.libraries.locationlib.LocationLibrary;
import XZot1K.plugins.zl.listeners.HologramListeners;
import XZot1K.plugins.zl.packets.holograms.Hologram;
import XZot1K.plugins.zl.statistichosts.MCUpdate;
import XZot1K.plugins.zl.statistichosts.Metrics;
import XZot1K.plugins.zl.utils.cooldowns.CoolDownManager;
import XZot1K.plugins.zl.utils.holograms.HologramManager;
import XZot1K.plugins.zl.utils.holograms.HologramTask;
import org.bukkit.plugin.java.JavaPlugin;

public class ZotLib extends JavaPlugin
{

    private static ZotLib instance;
    private String serverVersion = getServer().getClass().getPackage().getName().replace(".", ",").split(",")[3];

    // ZotLib Libraries.
    private InventoryLibrary inventoryLibrary;
    private GeneralLibrary generalLibrary;
    private PacketLibrary packetLibrary;
    private CalculationLibrary calculationLibrary;
    private LocationLibrary locationLibrary;
    private DatabaseLibrary databaseLibrary;
    private PluginManagementLibrary pluginManagementLibrary;

    // ZotLib Managers.
    private CoolDownManager coolDownManager;
    private HologramManager hologramManager;

    // ZotLib Tasks
    private HologramTask hologramTask;

    public static ZotLib getInstance()
    {
        return instance;
    }

    @Override
    public void onEnable()
    {
        long startTime = System.currentTimeMillis();
        instance = this;
        saveDefaultConfig();
        setupLibraries();
        setupManagers();
        connectToStatisticHosts();
        getPacketLibrary().setupPackets();

        // Register Commands.
        getCommand("zotlib").setExecutor(new ZotLibCommand());

        // Register Listeners.
        getServer().getPluginManager().registerEvents(new HologramListeners(), this);

        // Start tasks.
        taskStuff();

        long endTime = System.currentTimeMillis();
        getGeneralLibrary().sendConsoleMessage("&aSuccessfully loaded and enabled &eZotLib&a! (Took &e" + (endTime - startTime) + "ms&a)");
    }

    @Override
    public void onDisable()
    {
        for (Hologram hologram : getHologramManager().getHolograms())
        {
            hologram.hideAll();
            getHologramManager().saveHologram(hologram);
        }
    }

    private void taskStuff()
    {
        long startTime = System.currentTimeMillis();
        if (getConfig().getBoolean("use-hologram-task"))
        {
            int delay = 20 * getConfig().getInt("hologram-task-delay");
            hologramTask = new HologramTask();
            hologramTask.runTaskTimerAsynchronously(this, 0, delay);
        }

        long endTime = System.currentTimeMillis();
        getGeneralLibrary().sendConsoleMessage("&aSuccessfully setup and started all task related things! (Took &e" + (endTime - startTime) + "ms&a)");
    }

    private void setupLibraries()
    {
        inventoryLibrary = new InventoryLibrary();
        generalLibrary = new GeneralLibrary();
        packetLibrary = new PacketLibrary();
        calculationLibrary = new CalculationLibrary();
        locationLibrary = new LocationLibrary();
        databaseLibrary = new DatabaseLibrary();
        pluginManagementLibrary = new PluginManagementLibrary();
    }

    private void setupManagers()
    {
        coolDownManager = new CoolDownManager();
        hologramManager = new HologramManager();
    }

    private void connectToStatisticHosts()
    {
        try
        {
            long startTime = System.currentTimeMillis();
            Metrics metrics = new Metrics(this);
            metrics.start();
            long endTime = System.currentTimeMillis();
            getGeneralLibrary().sendConsoleMessage("&aMetrics has been successfully connected. (Took &e" + (endTime - startTime) + "ms&a)");
        } catch (Exception e)
        {
            getGeneralLibrary().sendConsoleMessage("&cMetrics failed to start. Please check your connection.");
        }

        try
        {
            long startTime = System.currentTimeMillis();
            new MCUpdate(this);
            long endTime = System.currentTimeMillis();
            getGeneralLibrary().sendConsoleMessage("&aMCUpdate has been successfully connected. (Took &e" + (endTime - startTime) + "ms&a)");
        } catch (Exception e)
        {
            getGeneralLibrary().sendConsoleMessage("&cMCUpdate failed to start. Please check your connection.");
        }
    }

    public InventoryLibrary getInventoryLibrary()
    {
        return inventoryLibrary;
    }

    public GeneralLibrary getGeneralLibrary()
    {
        return generalLibrary;
    }

    public PacketLibrary getPacketLibrary()
    {
        return packetLibrary;
    }

    public String getServerVersion()
    {
        return serverVersion;
    }

    public String getPrefix()
    {
        return "&bZotLib &7-> ";
    }

    public CalculationLibrary getCalculationLibrary()
    {
        return calculationLibrary;
    }

    public LocationLibrary getLocationLibrary()
    {
        return locationLibrary;
    }

    public DatabaseLibrary getDatabaseLibrary()
    {
        return databaseLibrary;
    }

    public CoolDownManager getCoolDownManager()
    {
        return coolDownManager;
    }

    public PluginManagementLibrary getPluginManagementLibrary()
    {
        return pluginManagementLibrary;
    }

    public HologramManager getHologramManager()
    {
        return hologramManager;
    }

    public HologramTask getHologramTask()
    {
        return hologramTask;
    }

}
