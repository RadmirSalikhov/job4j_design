package ru.job4j.map;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class User {
    private final String name;
    private final int children;
    private final Calendar birthday;

    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }

    @Override
    public boolean equals(Object o) {
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, children, birthday);
    }

    public static void main(String[] args) {
        Map<User, Object> map = new HashMap<>();
        Calendar birthday = Calendar.getInstance();
        birthday.set(2015, Calendar.JANUARY, 1, 10, 0, 0);
        birthday.set(Calendar.MILLISECOND, 123);

        User user1 = new User("John", 2, birthday);
        User user2 = new User("John", 2, birthday);

        int hashCode1 = user1.hashCode();
        int hash1 = hashCode1 ^ (hashCode1 >>> 16);
        int bucket1 = hash1 & 15;

        int hashCode2 = user2.hashCode();
        int hash2 = hashCode2 ^ (hashCode2 >>> 16);
        int bucket2 = hash2 & 15;

        map.put(user1, new Object());
        map.put(user2, new Object());

        System.out.printf("user1 - хэшкод: %s, хэш: %s, бакет: %s%n", hashCode1, hash1, bucket1);
        System.out.printf("user2 - хэшкод: %s, хэш: %s, бакет: %s%n", hashCode2, hash2, bucket2);

        System.out.println("Содержимое карты:");
        for (Map.Entry<User, Object> entry : map.entrySet()) {
            System.out.println(entry.getKey() + " => " + entry.getValue());
        }
    }

    @Override
    public String toString() {
        return String.format("User{name='%s', children=%d, birthday=%tc}", name, children, birthday);
    }
}