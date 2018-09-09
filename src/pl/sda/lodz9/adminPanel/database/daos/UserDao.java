package pl.sda.lodz9.adminPanel.database.daos;

import pl.sda.lodz9.adminPanel.database.DatabaseConnector;
import pl.sda.lodz9.adminPanel.models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {

    public static User getUserByLogin(String login) {


        String query = "SELECT * from user where login = ?";
        User user = null;
        try (Connection connection = DatabaseConnector.getConection()){
if(connection == null){
    System.out.println("connection never exist");
}

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, login);
            ResultSet rs = preparedStatement.executeQuery();


            user = createUserFromResultSet(rs);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    private static User createUserFromResultSet(ResultSet rs) throws SQLException {

        User user = new User();

        while ((rs.next())) {
            user.setId(rs.getInt("id"));
            user.setLogin(rs.getString("login"));
            user.setPassword(rs.getString("password"));
            user.setName(rs.getString("name"));
            user.setSurname(rs.getString("surname"));
            user.setIsAdmin(rs.getInt("isAdmin"));
        }
        return user;

    }
}
