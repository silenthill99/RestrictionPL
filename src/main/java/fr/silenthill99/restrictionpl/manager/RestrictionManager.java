package fr.silenthill99.restrictionpl.manager;

import fr.silenthill99.restrictionpl.Main;
import fr.silenthill99.restrictionpl.inventory.Metiers;
import fr.silenthill99.restrictionpl.inventory.hook.modo.DurationInventory;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

public class RestrictionManager {
    private final Main main;
    private final Connection conn;

    public RestrictionManager() {
        main = Main.getInstance();
        try {
            conn = main.getManager().getConnection().getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void restrictPlayer(OfflinePlayer target, Metiers metiers, DurationInventory.Duree duree) {
        try {
            PreparedStatement sts = conn.prepareStatement("INSERT INTO restrict_anamara(player_uuid, player_name, metier, created_at, end) VALUES (?, ?, ?, ?, ?)");
            sts.setString(1, target.getUniqueId().toString());
            sts.setString(2, target.getName());
            sts.setString(3, metiers.name().toLowerCase(Locale.ROOT));
            sts.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
            sts.setTimestamp(5, new Timestamp(System.currentTimeMillis() + duree.getEndToMillis()));
            sts.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean isRestricted(OfflinePlayer target) {
        try {
            PreparedStatement sts = conn.prepareStatement("SELECT * from restrict_anamara WHERE player_uuid = ?");
            sts.setString(1, target.getUniqueId().toString());
            ResultSet rs = sts.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void remove(OfflinePlayer target) {
        try {
            PreparedStatement sts = conn.prepareStatement("DELETE FROM restrict_anamara WHERE player_uuid = ?");
            sts.setString(1, target.getUniqueId().toString());
            sts.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<ShowRestrict> showRestrictions(Player player) {
        List<ShowRestrict> restrict = new ArrayList<>();
        try {
            PreparedStatement sts = conn.prepareStatement("SELECT * FROM restrict_anamara WHERE player_uuid = ?");
            sts.setString(1, player.getUniqueId().toString());
            ResultSet rs = sts.executeQuery();
            while (rs.next()) {
                restrict.add(new ShowRestrict(rs.getString("player_name"),
                        UUID.fromString(rs.getString("player_uuid")), rs.getString("metier"),
                        rs.getTimestamp("created_at"), rs.getTimestamp("end")));
            }
            return restrict;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
