package fr.silenthill99.restrictionpl.inventory;

public enum Metiers {
    MAIRE("Maire"),
    POLICE("Policier"),
    GENDARME("Gendarme"),
    MILITAIRE("Militaire"),
    MILITAIREUNSC("Militaire UNSC");

    private final String name;

    Metiers(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
