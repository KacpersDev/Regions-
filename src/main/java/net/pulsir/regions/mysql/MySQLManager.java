package net.pulsir.regions.mysql;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.Getter;
import net.pulsir.regions.Regions;
import net.pulsir.regions.region.Region;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

@Getter
public class MySQLManager {

    private final HikariDataSource hikariDataSource;

    public MySQLManager() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:mysql://" + Regions.getInstance().getConfig().getString("mysql.host") +
                ":" + Regions.getInstance().getConfig().getInt("mysql.port") + "/" +
                Regions.getInstance().getConfig().getString("mysql.database"));

        config.setUsername(Regions.getInstance().getConfig().getString("mysql.auth.username"));
        config.setPassword(Regions.getInstance().getConfig().getString("mysql.auth.password"));
        hikariDataSource = new HikariDataSource(config);

        init();
    }

    private void init() {
        try {
            Statement statement = hikariDataSource.getConnection().createStatement();
            statement.execute("CREATE TABLE IF NOT EXISTS regions(name varchar(36), locationOne TEXT, locationTwo TEXT, whitelistedPlayers LONGTEXT, regionFlags LONGTEXT)");
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void createRegion(Region region) {
        try {
            PreparedStatement preparedStatement = this.hikariDataSource.getConnection().prepareStatement("INSERT INTO regions VALUES (?,?,?,?,?)");
            preparedStatement.setString(1, region.getName());
            preparedStatement.setString(2, region.databaseLocation(region.getCuboidLocations()[0]));
            preparedStatement.setString(3, region.databaseLocation(region.getCuboidLocations()[1]));
            preparedStatement.setString(4, region.databaseWhitelistedPlayers());
            preparedStatement.setString(5, region.databaseRegionFlags());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
/*
    private String name;
    private Location[] cuboidLocations;
    private Set<UUID> whitelistedPlayers;
    private Map<RegionFlags, FlagState> regionFlags;
 */