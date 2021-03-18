package ru.job4j.io;

import org.junit.Test;


import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ConfigTest {
    @Test
    public void whenPairWithoutComment() {
        String path = "./files/app.properties";
        Config config = new Config(path);
        config.load();
        assertThat(
                config.value("hibernate.connection.driver_class"),
                is("org.postgresql.Driver")
        );
        assertNull(config.value("# PostgreSQL"));
        assertNull(config.value(""));
    }

}