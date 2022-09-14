package io.myosotisdev.minestom.module;

import com.google.common.base.Strings;
import io.myosotisdev.minestom.Minestom;
import net.minestom.server.command.builder.Command;
import net.minestom.server.event.Event;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class Module
{
    private String name;
    private Logger logger;

    public Module(String name)
    {
        this.name = name;
        this.logger = Logger.getLogger(name);
    }

    public String getName()
    {
        return this.name;
    }

    public File getDataFolder()
    {
        File file;
        (file = new File(name)).mkdir();
        return file;
    }

    public Logger getLogger()
    {
        return this.logger;
    }

    public File saveConfig() throws IOException
    {
        return savePackagedResource("configuration.json", false);
    }

    public File savePackagedResource(String path) throws IOException
    {
        return savePackagedResource(path, false);
    }

    public File savePackagedResource(String path, boolean override) throws IOException
    {
        if (Strings.isNullOrEmpty(path))
            throw new IllegalArgumentException("Path cannot null or empty");

        File fileInDestination = new File(getDataFolder(), path);
        InputStream packageFileStream;

        if (!fileInDestination.exists() || override)
        {
            fileInDestination.delete();
            fileInDestination.createNewFile();

            if ((packageFileStream = getPackagedResource(path)) != null)
            {
                FileOutputStream out = new FileOutputStream(fileInDestination);
                byte[] bytes = new byte[1];
                while (packageFileStream.read(bytes) != -1)
                    out.write(bytes);
                out.flush();
            }
            else
                return null;
        }

        return fileInDestination;
    }

    public InputStream getPackagedResource(String path)
    {
        try {
            URL url = getClass().getClassLoader().getResource(name + "/" + path);
            if (url == null) {
                return null;
            } else {
                return url.openConnection().getInputStream();
            }
        } catch (IOException var3) {
            this.getLogger().log(Level.WARNING, String.format("Failed to load resource {}.", path));
            return null;
        }
    }

    public void addCommand(Command command)
    {
        Minestom.registerCommand(command);
    }

    public <T extends Event> void addListener(Class<T> clazz, Consumer<T> consumer)
    {
        Minestom.registerListener(clazz, consumer);
    }

    public abstract void onEnable();

    public abstract void onDisable();
}
