package ru.job4j.jdbc;

import org.junit.Test;

import java.sql.SQLException;
import java.util.Properties;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class TableEditorTest {
    String table_name = "test_table";
    Properties properties = new Properties();
    TableEditor tableEditor = new TableEditor(properties);

    @Test
    public void createTable() {
        tableEditor.createTable(table_name);
    }

    @Test
    public void whenCreateColumn() throws SQLException {
        StringBuilder builder = new StringBuilder();
        builder.append(String.format("%-15s %-15s%n", "column", "type"));
        builder.append(String.format("%-15s %-15s%n", "test", "varchar"));
        tableEditor.addColumn(table_name, "test", "varchar(255)");
        assertThat(tableEditor.getScheme(table_name), is(builder.toString()));
    }

    @Test
    public void whenRenameColumn() throws SQLException {
        StringBuilder builder = new StringBuilder();
        builder.append(String.format("%-15s %-15s%n", "column", "type"));
        builder.append(String.format("%-15s %-15s%n", "rename", "varchar"));
        tableEditor.renameColumn(table_name, "test", "rename");
        assertThat(tableEditor.getScheme(table_name), is(builder.toString()));
    }

    @Test
    public void whenDropColumn() throws SQLException {
        StringBuilder builder = new StringBuilder();
        builder.append(String.format("%-15s %-15s%n", "column", "type"));
        builder.append(String.format("%-15s %-15s%n", "rename", "varchar"));
        tableEditor.addColumn(table_name, "test2", "varchar(255)");
        tableEditor.dropColumn(table_name, "test2");
        assertThat(tableEditor.getScheme(table_name), is(builder.toString()));
    }

    @Test
    public void dropTable() {
        tableEditor.dropTable(table_name);
    }
}