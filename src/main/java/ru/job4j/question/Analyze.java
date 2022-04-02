package ru.job4j.question;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Analyze {

    public static Info diff(Set<User> previous, Set<User> current) {
        int countAdded = 0;
        int countChanged = 0;
        Iterator<User> it = current.iterator();
        Map<Integer, String> map = previous.stream()
                .collect(Collectors.toMap(User :: getId, User :: getName));
        while (it.hasNext()) {
            User curr = it.next();
            if (!map.containsKey(curr.getId())) {
                countAdded++;
            } else {
                if (!curr.getName().equals(map.get(curr.getId()))) {
                    countChanged++;
                }
            }
        }
        int countDeleted = previous.size() - current.size() + countAdded;
        return new Info(countAdded, countChanged, countDeleted);
    }
}
