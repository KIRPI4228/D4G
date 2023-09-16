package ru.KIRPI4228.d4g;

public class D4G {
    public static void main(final String[] args) {
        String chars = "123";
        char[] symbols = new char[chars.length()];
        chars.getChars(0, chars.length(), symbols, 0);

        Generator generator = new Generator(3, symbols);

        generator.generate("D:\\huy228");
    }
}
