package fr.silenthill99.restrictionpl.manager;

import java.sql.Timestamp;
import java.util.UUID;

public class ShowRestrict {
    private final String pseudo;
    private final UUID uuid;
    private final String metier;
    private final Timestamp date_added;
    private final Timestamp date_end;

    public ShowRestrict(String pseudo, UUID uuid, String metier, Timestamp date_added, Timestamp date_end) {
        this.pseudo = pseudo;
        this.uuid = uuid;
        this.metier = metier;
        this.date_added = date_added;
        this.date_end = date_end;
    }

    public String getPseudo() {
        return pseudo;
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getMetier() {
        return metier;
    }

    public Timestamp getDate_added() {
        return date_added;
    }

    public Timestamp getDate_end() {
        return date_end;
    }
}
