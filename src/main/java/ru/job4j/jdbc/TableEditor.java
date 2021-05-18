package ru.job4j.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class TableEditor implements AutoCloseable {
    private Connection connection;
    private Properties properties;

    public TableEditor(Properties properties) {
        this.properties = properties;
        initConnection();
    }


    public void createTable(String tableName) {
        String creteTable = String.format("create table if not exists %s();", tableName);
        execute(creteTable);
    }

    public void dropTable(String tableName) {
        String dropTable = String.format("drop table if exists %s;", tableName);
        execute(dropTable);
    }

    public void addColumn(String tableName, String columnName, String type) {
        String addColumn = String.format("alter table if exists %s add %s %s;", tableName, columnName, type);
        execute(addColumn);
    }

    public void dropColumn(String tableName, String columnName) {
        String dropColumn = String.format("alter table if exists %s drop column %s;", tableName, columnName);
        execute(dropColumn);
    }

    public void renameColumn(String tableName, String columnName, String newNameColumn) {
        String renameColumn = String.format("alter table if exists %s rename column %s to %s;", tableName, columnName, newNameColumn);
        execute(renameColumn);
    }

    public String getScheme(String tableName) throws SQLException {
        StringBuilder scheme = new StringBuilder();
        DatabaseMetaData metaData = connection.getMetaData();
        try (ResultSet columns = metaData.getColumns(null, null, tableName, null)) {
            scheme.append(String.format("%-15s %-15s%n", "column", "type"));
            while (columns.next()) {
                scheme.append(String.format("%-15s %-15s%n",
                        columns.getString("COLUMN_NAME"),
                        columns.getString("TYPE_NAME")));
            }
        }
        return scheme.toString();
    }

    private void initConnection() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        ClassLoader loader = this.getClass().getClassLoader();
        try (InputStream io = loader.getResourceAsStream("app.properties")) {
            properties.load(io);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String url = properties.getProperty("url");
        String login = properties.getProperty("login");
        String password = properties.getProperty("password");
        try {
            connection = DriverManager.getConnection(url, login, password);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private void execute(String sql) {
        try (Statement statement = connection.createStatement()) {
            statement.execute(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }
}
