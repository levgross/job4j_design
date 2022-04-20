package ru.job4j.jdbc;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;
import java.util.StringJoiner;

public class TableEditor implements AutoCloseable {

    private Connection connection;

    private Properties properties;

    public TableEditor(Properties properties) throws SQLException, ClassNotFoundException {
        this.properties = properties;
        initConnection();
    }

    private void initConnection() throws ClassNotFoundException, SQLException {
        Class.forName(properties.getProperty("driver_class"));
        String url = properties.getProperty("url");
        String login = properties.getProperty("username");
        String password = properties.getProperty("password");
        connection = DriverManager.getConnection(url, login, password);
    }

    public void createTable(String tableName) throws SQLException {
            String sql = String.format(
                    "create table if not exists %s(%s, %s);",
                    tableName,
                    "id serial primary key",
                    "name text"
            );
            action(sql);
    }

    public void dropTable(String tableName) throws SQLException {
            String sql = String.format(
                    "drop table if exists %s;",
                    tableName
            );
            action(sql);
    }

    public void addColumn(String tableName, String columnName, String type) throws SQLException {
            String sql = String.format(
                    "alter table %s add column %s %s;",
                    tableName,
                    columnName,
                    type
            );
            action(sql);
    }

    public void dropColumn(String tableName, String columnName) throws SQLException {
            String sql = String.format(
                    "alter table %s drop column %s;",
                    tableName,
                    columnName
            );
            action(sql);
    }

    public void renameColumn(String tableName, String columnName, String newColumnName) throws SQLException {
            String sql = String.format(
                    "alter table %s rename column %s to %s;",
                    tableName,
                    columnName,
                    newColumnName
            );
            action(sql);
    }

    private void action(String str) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            statement.execute(str);
        }
    }


    public static String getTableScheme(Connection connection, String tableName) throws Exception {
        var rowSeparator = "-".repeat(30).concat(System.lineSeparator());
        var header = String.format("%-15s|%-15s%n", "NAME", "TYPE");
        var buffer = new StringJoiner(rowSeparator, rowSeparator, rowSeparator);
        buffer.add(header);
        try (var statement = connection.createStatement()) {
            var selection = statement.executeQuery(String.format(
                    "select * from %s limit 1", tableName
            ));
            var metaData = selection.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                buffer.add(String.format("%-15s|%-15s%n",
                        metaData.getColumnName(i), metaData.getColumnTypeName(i))
                );
            }
        }
        return buffer.toString();
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }

    public static void main(String[] args) throws Exception {
        Properties config = new Properties();
        try (InputStream in = TableEditor.class.getClassLoader().getResourceAsStream("app.properties")) {
            config.load(in);
        }
        try (TableEditor editor = new TableEditor(config)) {
            editor.createTable("people");
            System.out.println(getTableScheme(editor.connection, "people"));
            editor.dropTable("people");
            editor.createTable("people");
            editor.addColumn("people", "Age", "varchar(255)");
            System.out.println(getTableScheme(editor.connection, "people"));
            editor.dropColumn("people", "Age");
            System.out.println(getTableScheme(editor.connection, "people"));
            editor.renameColumn("people", "name", "names");
            System.out.println(getTableScheme(editor.connection, "people"));
        }
    }
}