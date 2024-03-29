package ru.job4j.jdbc.spamer;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ImportBD {
    private Properties cfg;
    private String bump;

    public ImportBD(Properties cfg, String bump) {
        this.cfg = cfg;
        this.bump = bump;
    }

    public List<User> load() throws IOException {
        List<User> users = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(bump))) {
            reader.lines().map(el -> el.split(";")).filter(line -> line.length == 2).
                    forEach(line -> users.add(new User(line[0], line[1])));
        }
        return users;
    }

    public void save(List<User> users) throws ClassNotFoundException, SQLException {
        Class.forName(cfg.getProperty("driver"));
        try (Connection connection = DriverManager.getConnection(
                cfg.getProperty("url"),
                cfg.getProperty("login"),
                cfg.getProperty("password")
        )) {
            for (User user : users) {
                try (PreparedStatement statement = connection.prepareStatement("insert into users(name, email) values (?, ?)")) {
                    statement.setString(1, user.name);
                    statement.setString(2, user.email);
                    statement.execute();
                }
            }
        }
    }

    private static class User {
        String name;
        String email;

        public User(String name, String email) {
            this.name = name;
            this.email = email;
        }
    }

    public static <FileInputStream> void main(String[] args) throws Exception {
        ClassLoader loader = ImportBD.class.getClassLoader();
        Properties cfg = new Properties();
        String dump = "./files/dump.txt";
        try (InputStream io = loader.getResourceAsStream("app.properties")) {
            cfg.load(io);
        }
        ImportBD bd = new ImportBD(cfg, dump);
        bd.save(bd.load());
    }
}
