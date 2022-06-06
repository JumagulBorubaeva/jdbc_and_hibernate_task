package peaksoft.dao;

import peaksoft.model.User;
import peaksoft.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJdbcImpl implements UserDao {

    public UserDaoJdbcImpl() {

    }

    public void createUsersTable() {

        String SQL = "CREATE TABLE users(" +
                "id BIGSERIAL PRIMARY KEY ,"+
                "name varchar(50),"+
                "lastname varchar(50), "+
                "age int2)";
        try (Connection connection = Util.connection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(SQL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    public void dropUsersTable() {
        String sql="drop table users";
        try (Connection connection=Util.connection();
             Statement statement=connection.createStatement()) {
            statement.executeUpdate(sql);
        }catch (SQLException e){
            e.printStackTrace();


        }
    }

    public void saveUser(String name, String lastName, byte age) {
  String sql="insert into users(name,lastname,age) values(?,?,?)";
  try ( Connection connection=Util.connection();
        PreparedStatement statement=connection.prepareStatement(sql) ){
      statement.setString(1,name);
      statement.setString(2,lastName);
      statement.setByte(3,age);
      statement.executeUpdate();
  }
  catch (SQLException e){
      e.printStackTrace();
  }

    }

    public void removeUserById(long id) {
        String sql="delete from users where id =?";
        try (Connection connection= Util.connection();
             PreparedStatement statement=connection.prepareStatement(sql) ){
            statement.executeUpdate();

        }
        catch (SQLException e){
            e.printStackTrace();
        }

    }

    public List<User> getAllUsers() {
        String sgl="select * from users";
        List<User>user=new ArrayList<>();
        try (Connection connection=Util.connection();
             Statement statement=connection.createStatement();
             ResultSet resultSet=statement.executeQuery(sgl)){
            while (resultSet.next()){
                User user1=new User();
                user1.setId(resultSet.getLong("id"));
                user1.setName(resultSet.getString("name"));
                user1.setLastName(resultSet.getString("lastname"));
                user1.setAge(resultSet.getByte("age"));
                user.add(user1);

            }

        } catch (SQLException e) {
           e.printStackTrace();
        }
        return user;

    }

    public void cleanUsersTable() {
        String sgl="truncate table users";
        try (Connection connection=Util.connection();
            Statement statement=connection.createStatement()){
            statement.executeUpdate(sgl);

        }catch (SQLException e){
            e.printStackTrace();
        }

    }
}