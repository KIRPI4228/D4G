package ru.KIRPI4228.d4g.console;

import java.util.Scanner;

public final class Console {
    public static void Write(String text) {
        System.out.print(text + ConsoleColor.RESET);
    }

    public static void WriteLine(String text) {
        Write("\n" + text);
    }

    public static void WriteError(Exception e) {
        WriteError(e.getMessage());
    }

    public static void WriteError(String text) {
        WriteLine(ConsoleColor.RED + text);
    }

    public static void WriteOk(String text) {
        WriteLine(ConsoleColor.GREEN + text);
    }

    public static String WriteInput(String text) {
        WriteLine(ConsoleColor.CYAN + text);

        return new Scanner(System.in).nextLine();
    }
}
