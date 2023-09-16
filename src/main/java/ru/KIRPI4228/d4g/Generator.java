package ru.KIRPI4228.d4g;

import ru.KIRPI4228.d4g.console.Console;
import ru.KIRPI4228.d4g.console.ConsoleColor;
import ru.KIRPI4228.d4g.loops.Loop;
import ru.KIRPI4228.d4g.loops.LoopRunnable;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class Generator {
    private int length;
    private char[] symbols;

    public Generator(int length, char[] symbols) {
        this.length = length;
        this.symbols = symbols;
    }

    public String getInfo() {
        String info = ConsoleColor.BLUE + "chars = " + ConsoleColor.GREEN + "'" + ConsoleColor.YELLOW + new String(symbols) + ConsoleColor.GREEN + "'" + "\n"
                + ConsoleColor.BLUE + "passwords = " + ConsoleColor.YELLOW +  (int)(Math.pow(symbols.length, length)) + "\n"
                + ConsoleColor.BLUE + "size = " + ConsoleColor.YELLOW +  (Math.pow(symbols.length, length) * length / 1024) + "kb";

        return info;
    }

    public void generate(String path) {
        File file = new File(path);
        if (file.exists()) {
            Console.WriteError("\nThis file has already been created");
            return;
        }

        try {
            file.createNewFile();
        } catch (IOException e) {
            Console.WriteError(e.getMessage());
            return;
        }

        FileOutputStream stream;

        try {
            stream = new FileOutputStream(file);
        } catch (FileNotFoundException e) {
            Console.WriteError("\nCould not find file - " + file.getName());
            Console.WriteError(e.getMessage());
            return;
        }

        LoopRunnable runnable = (List<Integer> indexes) -> {
            String line = "";

            for (int index : indexes) {
                line += symbols[index];
            }


            try {
                // Write password line
                stream.write(line.getBytes(StandardCharsets.UTF_8));
                stream.flush();

                // Write new line
                stream.write("\n".getBytes());
                stream.flush();
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        };

        Loop loop = new Loop(0, symbols.length, length, runnable);

        Console.WriteLine("\nStart generation");

        loop.run(new ArrayList<>());
        Console.WriteOk("\nPassword file has been generated successfully ( path = " + path + " )");
    }
}
