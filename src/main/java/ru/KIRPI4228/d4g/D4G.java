package ru.KIRPI4228.d4g;

import ru.KIRPI4228.d4g.console.Console;
import ru.KIRPI4228.d4g.console.ConsoleColor;

import java.io.IOException;

public class D4G {
    public static void main(final String[] args) {
        Console.WriteLine(ConsoleColor.BLUE + "Welcome to " + ConsoleColor.PURPLE + "D4G" + ConsoleColor.BLUE + " brute force generator");
        Console.WriteLine(ConsoleColor.BLUE + "\n\nAuthor - " + ConsoleColor.PURPLE + "KIRPI4228");

        Console.WriteLine("\n\n");

        int length = Integer.parseInt(Console.WriteInput("Enter password length: "));
        String symbolsText = Console.WriteInput("Enter password symbols: ");


        char[] symbols = new char[symbolsText.length()];

        symbolsText.getChars(0, symbols.length, symbols, 0);

        Generator generator = new Generator(length - 1, symbols);

        Console.WriteLine(generator.getInfo());

        if (!getYesOrNo("Do want to continue")) {
            return;
        }

        String path = Console.WriteInput("Enter password file path: ");

        generator.generate(path);
    }

    private static boolean getYesOrNo(String text) {
        char generate = Console.WriteInput(text + " (y / n): ").toCharArray()[0];

        if (generate == 'n') {
            return false;
        } else if (generate != 'y') {
            Console.WriteError("Incorrect answer");
            Console.WriteLine("Please write 'y' or 'n'");
            return getYesOrNo(text);
        }

        return true;
    }
}
