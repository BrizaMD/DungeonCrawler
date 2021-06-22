package com.codecool.dungeoncrawl.dao;

import com.codecool.dungeoncrawl.model.PlayerModel;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;

public class PlayerDaoJdbc implements PlayerDao {
    private DataSource dataSource;

    public PlayerDaoJdbc(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void add(PlayerModel player) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "INSERT INTO player (id, save_id, hp, maximum_hp, armor, x, y, selected_item, has_red_key, has_blue_key, player_name) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, 0);
            statement.setInt(2, 1);
            statement.setInt(3, player.getHp());
            statement.setInt(4, player.getMaximumHealth());
            statement.setInt(5, player.getArmor());
            statement.setInt(6, player.getX());
            statement.setInt(7, player.getY());
            statement.setInt(8, player.getSelectedItem());
            statement.setBoolean(9, player.hasRedKey());
            statement.setBoolean(10, player.hasBlueKey());
            statement.setString(11, player.getPlayerName());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            resultSet.next();
            player.setId(resultSet.getInt(1));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(PlayerModel player) {

    }

    @Override
    public PlayerModel get(int id) {
        return null;
    }

    @Override
    public List<PlayerModel> getAll() {
        return null;
    }
}
