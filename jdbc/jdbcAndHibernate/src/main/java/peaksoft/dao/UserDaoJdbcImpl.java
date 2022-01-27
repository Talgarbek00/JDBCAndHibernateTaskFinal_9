package peaksoft.dao;
import peaksoft.model.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import static peaksoft.util.HibernateUtil.connection;

public class UserDaoJdbcImpl implements UserDao {
    List<User> list = new ArrayList<>();
    // constructor ----------------------------------------
    public UserDaoJdbcImpl() {
    }

    // create user table-------------------------------------
    public void createUsersTable() {
        try {
            PreparedStatement preparedStatement =
                    connection().prepareStatement("create table if not exists userTable(id serial not null,name varchar(50)not null,lastName varchar (50)not null ,age int not null)");
            preparedStatement.execute();
            System.out.println("таблица тузулду");
        } catch (SQLException e) {
            System.err.println("таблица уже существует");
            e.printStackTrace();
        }
    }

    // drop user table --------------------------------------
    public void dropUsersTable() {
        try {
            PreparedStatement preparedStatement =
                    connection().prepareStatement("drop table userTable");
            preparedStatement.execute();
            System.out.println("таблица удален");
        } catch (SQLException e) {
            System.err.println("таблица не существует");
        }
    }

    // method for add users------------------------------------------
    public void saveUser(String name, String lastName, byte age) {
        try {
            PreparedStatement preparedStatement =
                    connection().prepareStatement("insert into userTable (name,lastName,age) values(?,?,?)");
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setInt(3, age);
            preparedStatement.executeUpdate();
            System.out.println(name + " базага кoшулду");
        } catch (Exception ex) {
            System.out.println("таблица не существует");
        }
    }

    // remove by id ------------------------------------
    public void removeUserById(long id) {
        try {
            PreparedStatement preparedStatement =
                    connection().prepareStatement("delete from userTable where id = ?");
            preparedStatement.setLong(1, id);
            preparedStatement.execute();
            System.out.println(id + " removed ");
        } catch (SQLException e) {
            System.out.println("таблица не существует");
        }
    }

    //get all users ------------------------------------
    public List<User> getAllUsers() {
        try {
            list.clear();
            PreparedStatement preparedStatement =
                    connection().prepareStatement("select * from userTable");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                Long id = (resultSet.getLong(1));
                String name = (resultSet.getString(2));
                String surname = (resultSet.getString(3));
                Byte age = (resultSet.getByte(4));
                user.setId(id);
                user.setName(name);
                user.setLastName(surname);
                user.setAge(age);
                list.add(user);
            }
            if (list.isEmpty()){
                System.out.println("таблица пустая");
            }
            preparedStatement.executeQuery();
        } catch (SQLException e) {
            System.out.println("Таблица не существует");
        }
        return list;
    }

    //clean user table ----------------------------------
    public void cleanUsersTable() {
        try {
            PreparedStatement preparedStatement =
                    connection().prepareStatement("TRUNCATE TABLE userTable;");
            preparedStatement.execute();
            System.out.println("таблица очищена");
        } catch (SQLException e) {
            System.out.println("таблица уже очищена");
        }
    }

}