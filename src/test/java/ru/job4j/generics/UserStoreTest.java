package ru.job4j.generics;


import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class UserStoreTest {
    @Test
    public void whenFindById() {
        UserStore userStore = new UserStore();
        User user = new User("1");
        userStore.add(user);
        assertThat(userStore.findById("1"), is(user));
    }

    @Test
    public void whenReplaceUser(){
        UserStore userStore = new UserStore();
        User user = new User("1");
        userStore.add(user);
        User user2 = new User("2");
        assertTrue(userStore.replace("1", user2));
        assertThat(userStore.findById("2"), is(user2));
    }

    @Test
    public void whenDeleteUser() {
        UserStore userStore = new UserStore();
        User user = new User("1");
        userStore.add(user);
        assertTrue(userStore.delete("1"));
        assertNull(userStore.findById("1"));
    }
}