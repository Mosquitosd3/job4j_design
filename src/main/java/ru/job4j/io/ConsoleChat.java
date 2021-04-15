package ru.job4j.io;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class ConsoleChat {
    private final String path;
    private final String botAnswers;
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run() {
        List<String> logChat = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            List<String> line = Files.readAllLines(Paths.get(botAnswers));
            String read = reader.readLine();
            while (!read.equals(OUT)) {
                if (read.equals(STOP)) {
                    logChat.add(read);
                    System.out.println(read);
                    while (!read.equals(CONTINUE) && !read.equals(OUT)) {
                        read = reader.readLine();
                        logChat.add(read);
                        System.out.println(read);
                    }
                    if (read.equals(OUT)) {
                        break;
                    }
                    read = reader.readLine();
                }
                String ans = line.get(ThreadLocalRandom.current().nextInt(0, line.size() - 1));
                logChat.add(read);
                System.out.println(read);
                logChat.add(ans);
                System.out.println(ans);
                read = reader.readLine();
            }
            writeChatToFile(logChat);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void writeChatToFile(List<String> logChat) {
        try (BufferedWriter out = new BufferedWriter(new FileWriter(path, Charset.forName("WINDOWS-1251"), true))) {
            logChat.stream().forEach(el -> {
                try {
                    out.write(el);
                    out.newLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        ArgsName arg = ArgsName.of(args);
        ConsoleChat cc = new ConsoleChat(arg.get("path"), arg.get("botAnswers"));
        cc.run();
    }
}
