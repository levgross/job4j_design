package ru.job4j.generics;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class RoleStoreTest {

    @Test
    public void whenAddAndFindThenRoleNameIsTeacher() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "teacher"));
        Role result = store.findById("1");
        assertThat(result.getRoleName(), is("teacher"));
    }

    @Test
    public void whenAddAndFindThenRoleIsNull() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "teacher"));
        Role result = store.findById("10");
        assertNull(result);
    }

    @Test
    public void whenAddDuplicateAndFindRoleNameIsTeacher() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "teacher"));
        store.add(new Role("1", "pupil"));
        Role result = store.findById("1");
        assertThat(result.getRoleName(), is("teacher"));
    }

    @Test
    public void whenReplaceThenRoleNameIsPupil() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "teacher"));
        store.replace("1", new Role("1", "pupil"));
        Role result = store.findById("1");
        assertThat(result.getRoleName(), is("pupil"));
    }

    @Test
    public void whenNoReplaceRoleThenNoChangeRoleName() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "teacher"));
        store.replace("10", new Role("10", "pupil"));
        Role result = store.findById("1");
        assertThat(result.getRoleName(), is("teacher"));
    }

    @Test
    public void whenDeleteRoleThenRoleIsNull() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "teacher"));
        store.delete("1");
        Role result = store.findById("1");
        assertNull(result);
    }

    @Test
    public void whenNoDeleteROleThenRoleNameIsTeacher() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "teacher"));
        store.delete("10");
        Role result = store.findById("1");
        assertThat(result.getRoleName(), is("teacher"));
    }
}