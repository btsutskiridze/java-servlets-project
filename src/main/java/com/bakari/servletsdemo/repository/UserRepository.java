package com.bakari.servletsdemo.repository;

import com.bakari.servletsdemo.entity.User;
import com.bakari.servletsdemo.config.DB;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

public class UserRepository {

    private static final DB db = DB.getInstance();

    public int saveUser(User user) {
        try (PreparedStatement preparedStatement = db.getConnection()
                .prepareStatement("INSERT INTO users (username, email) VALUES (?, ?)")) {
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getEmail());
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            Logger.getLogger("UserRepository").severe("Failed to save user. " + e.getMessage());
            return -1;
        }
    }

    public int updateUser(User user) {
        try (PreparedStatement preparedStatement = db.getConnection()
                .prepareStatement("UPDATE users SET username = ?, email = ? WHERE id = ?")) {
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setLong(3, user.getId());
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            Logger.getLogger("UserRepository").severe("Failed to update user. " + e.getMessage());
            return 0;
        }
    }

    public int deleteUserById(Long id) {
        try (PreparedStatement statement = db.prepareStatement("DELETE FROM users WHERE id = ?")) {
            statement.setLong(1, id);
            return statement.executeUpdate();
        } catch (SQLException e) {
            Logger.getLogger("UserRepository").severe("Failed to delete user. " + e.getMessage());
            return -1;
        }
    }

    public List<User> getUsers() {
        try (ResultSet result = db.getConnection().createStatement().executeQuery("SELECT * FROM users ORDER BY created_on")) {
            List<User> users = new ArrayList<>();
            while (result.next()) {
                int id = result.getInt("id");
                String uname = result.getString("username");
                String email = result.getString("email");

                users.add(new User(id, uname, email));
            }

            return Collections.unmodifiableList(users);
        }
        catch (SQLException e) {
            Logger.getLogger("UserRepository").severe("Failed to get users. " + e.getMessage());
            return null;
        }
    }

    public User getUserById(long id) {
        User user = null;
        try (PreparedStatement statement = db.prepareStatement("SELECT * FROM users WHERE id = ?")) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                user = new User(
                        resultSet.getLong("id"),
                        resultSet.getString("username"),
                        resultSet.getString("email")
                );
            }

        }
        catch (SQLException e) {
            Logger.getLogger("UserRepository").severe("Failed to get user. " + e.getMessage());
        }

        return user;
    }
}
