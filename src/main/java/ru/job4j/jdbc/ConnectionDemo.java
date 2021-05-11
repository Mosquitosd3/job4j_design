package ru.job4j.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionDemo {
    public static void main(String[] args) throws ClassNotFoundException, IOException, SQLException {
      Class.forName("org.postgresql.Driver");
      Properties props = new Properties();
      ClassLoader loader = ConnectionDemo.class.getClassLoader();
      try (InputStream io = loader.getResourceAsStream("app.properties")) {
          props.load(io);
      }
      String url = props.getProperty("url");
      String login = props.getProperty("login");
      String password = props.getProperty("password");
      try (Connection connection = DriverManager.getConnection(url, login, password)){
          DatabaseMetaData metaData = connection.getMetaData();
          System.out.println(metaData.getURL());
          System.out.println(metaData.getUserName());
      }
    }
}
