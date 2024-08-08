package fr.silenthill99.restrictionpl;

import java.io.File;

public enum CustomFiles {
    RESTRICTION(new File(Main.getInstance().getDataFolder(), "Restriction.yml"))
    ;

    private final File file;

    CustomFiles(File file) {
        this.file = file;
    }

    public File getFile() {
        return file;
    }
}
