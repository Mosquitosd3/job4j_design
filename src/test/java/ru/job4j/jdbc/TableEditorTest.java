package ru.job4j.jdbc;

import org.junit.Ignore;
import org.junit.Test;

import java.sql.SQLException;
import java.util.Properties;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
@Ignore
public class TableEditorTest {
    String tableName = "test_table";
    Properties properties = new Properties();
    TableEditor tableEditor = new TableEditor(properties);
    @Ignore
    @Test
    public void createTable() {
        tableEditor.createTable(tableName);
    }
    @Ignore
    @Test
    public void whenCreateColumn() throws SQLException {
        StringBuilder builder = new StringBuilder();
        builder.append(String.format("%-15s %-15s%n", "column", "type"));
        builder.append(String.format("%-15s %-15s%n", "test", "varchar"));
        tableEditor.addColumn(tableName, "test", "varchar(255)");
        assertThat(tableEditor.getScheme(tableName), is(builder.toString()));
    }
    @Ignore
    @Test
    public void whenRenameColumn() throws SQLException {
        StringBuilder builder = new StringBuilder();
        builder.append(String.format("%-15s %-15s%n", "column", "type"));
        builder.append(String.format("%-15s %-15s%n", "rename", "varchar"));
        tableEditor.renameColumn(tableName, "test", "rename");
        assertThat(tableEditor.getScheme(tableName), is(builder.toString()));
    }
    @Ignore
    @Test
    public void whenDropColumn() throws SQLException {
        StringBuilder builder = new StringBuilder();
        builder.append(String.format("%-15s %-15s%n", "column", "type"));
        builder.append(String.format("%-15s %-15s%n", "rename", "varchar"));
        tableEditor.addColumn(tableName, "test2", "varchar(255)");
        tableEditor.dropColumn(tableName, "test2");
        assertThat(tableEditor.getScheme(tableName), is(builder.toString()));
    }@Ignore
    @Test
    public void dropTable() {
        tableEditor.dropTable(tableName);
    }
}