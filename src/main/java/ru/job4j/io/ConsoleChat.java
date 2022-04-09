package ru.job4j.io;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class ConsoleChat {
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private final String path;
    private final String botAnswers;

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run() {
        Scanner input = new Scanner(System.in);
        List<String> log = new ArrayList<>();
        List<String> botPhrases = readPhrases();
        String userPhrase;
        String botAnswer;
        boolean chat = true;
        boolean isStopped = false;
        int size = botPhrases.size();
        System.out.println("Начните общение.");
        while (chat) {
            System.out.print("Пользователь: ");
            userPhrase = input.nextLine();
            log.add("Пользователь: " + userPhrase);
            if (OUT.equalsIgnoreCase(userPhrase)) {
                System.out.println("Чат завершён.");
                log.add("Чат завершён.");
                chat = false;
            } else if (STOP.equalsIgnoreCase(userPhrase)) {
                isStopped = true;
            } else if (CONTINUE.equalsIgnoreCase(userPhrase)) {
                botAnswer = "Бот: " + botPhrases.get(new Random().nextInt(size));
                System.out.println(botAnswer);
                log.add(botAnswer);
                isStopped = false;
            } else if (!isStopped) {
                botAnswer = "Бот: " + botPhrases.get(new Random().nextInt(size));
                System.out.println(botAnswer);
                log.add(botAnswer);
            }
        }
        saveLog(log);
    }

    private List<String> readPhrases() {
        List<String> phrases = new ArrayList<>();
        try (BufferedReader botReader = new BufferedReader(new FileReader(botAnswers, StandardCharsets.UTF_8))) {
            botReader.lines().forEach(phrases::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return phrases;
    }

    private void saveLog(List<String> log) {
        try (PrintWriter logger = new PrintWriter(new FileWriter(path, StandardCharsets.UTF_8, true))) {
            log.forEach(logger::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat("./logger.txt", "./text.txt");
        cc.run();
    }
}
