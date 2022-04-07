package ru.job4j.io;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ArgsName {
    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        if (!values.containsKey(key)) {
            throw new IllegalArgumentException("The argument is null. Usage java -jar argsName.jar -KEY=VALUE.");
        }
        return values.get(key);
    }

    private void parse(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("The argument is null. Usage java -jar argsName.jar -KEY=VALUE.");
        }
        for (String arg : args) {
            if (!arg.startsWith("-")) {
                throw new IllegalArgumentException("One of the arguments` names does not start with -."
                        + " Usage java -jar argsName.jar -KEY=VALUE.");
            }
            String[] argValues = arg.split("=", 2);
            if (argValues.length != 2 || "".equals(argValues[1])) {
                throw new IllegalArgumentException("One of the arguments is not legal."
                        + " Usage java -jar argsName.jar -KEY=VALUE.");
            }
            StringBuilder sb = new StringBuilder(argValues[0]);
            String name = sb.deleteCharAt(0).toString();
            values.put(name, argValues[1]);
        }
    }

    public static ArgsName of(String[] args) {
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[] {"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));

        ArgsName zip = ArgsName.of(new String[] {"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
    }
}
