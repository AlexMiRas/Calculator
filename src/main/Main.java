package main;

import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) throws Exception {

        // получаем данные от пользователя
        Scanner user_input = new Scanner(System.in, "Cp1251");
        System.out.println("Введите выражение в одно действие:" + "\n"
                + "одна арабская или римская цифра," + "\n" +
                "знак сложения, вычитания, умножения или деления," + "\n" +
                "вторая цифра должна соответствовать первой. Римская и римская или арабская и арабская" + "\n");
        String str = user_input.next();

        // передаем строку в класс тест

        Test test = new Test(str);

        System.out.println(test.calculate());
    }
}
