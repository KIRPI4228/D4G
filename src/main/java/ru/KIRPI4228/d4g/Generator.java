package ru.KIRPI4228.d4g;

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
    private static final String SAVE_PATH = "passwords\\";
    private static final String FILE_EXTENSION = ".pass";

    private int length;
    private char[] symbols;

    public Generator(int length, char[] symbols) {
        this.length = length;
        this.symbols = symbols;
    }

    public void generate(String fileName) {
        File file = new File(/*SAVE_PATH +*/ fileName + FILE_EXTENSION);
        if (file.exists()) {
            // TODO: file has already been created

            return;
        }

        try {
            file.createNewFile();
        } catch (IOException e) {
            System.err.println(e.getMessage());
            return;
        }

        FileOutputStream stream;

        try {
            stream = new FileOutputStream(file);
        } catch (FileNotFoundException e) {
            System.out.println("Could not find file - " + file.getName());
            System.err.println(e.getMessage());
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

        loop.run(new ArrayList<>());
    }
}
