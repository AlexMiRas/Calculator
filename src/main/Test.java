package main;

import java.util.HashMap;
import java.util.Map;

public class Test {
    private String numOrRoman; // цифры или римские или error

    private String length; // значение три или false

    private String str;

    private String sign; // значение +-*/ или ошибка

    private String[] array;

    private int Res;

    private String[] romeInit = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};
    private String[] romeTeen = {"", "XI", "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX"};
    private String[] romeTen = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
    private String[] romeHund = {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};
    private Map romans = Map.of("I", 1, "II", 2, "III", 3, "IV", 4,
            "V", 5, "VI", 6, "VII", 7, "VIII", 8, "IX", 9,
            "X",10);

    public Test(String string) {// передаём значение в str из main
        this.str = string;
    }


    public String getSignValue() {// получаем знак выражения (+-*/ или ошибка)
        getSign();
        return sign;
    }

    // получаем массив из цифр

    public String[] getArray() {
        String[] array = str.split("[+\\-*/]+");
        return array;
    }

    // проверяем цифры или латинские буквы

    public String checkIsNum() throws Exception {

        if (getArray().length > 1) {
            String str1 = String.valueOf(getArray()[0]);
            String str2 = String.valueOf(getArray()[1]);
            if (str1.matches("\\d+") == true && str2.matches("\\d+") == true) {
                numOrRoman = "arabic";
                return "arabic";
            } else if (str1.matches("[ILVXilvx]+") == true && str2.matches("[ILVXilvx]+") == true) {
                numOrRoman = "roman";
                return "roman";
            }
        }
        return null;
    }

    public String getSign() {// присваиваем значение sign
        //sign = String.valueOf(str.matches("[+\\-*/]+"));

        array = str.split("");
        for (int i = 0; i < array.length; i++) {
            if (String.valueOf(array[i]).matches("[+]") == true) {
                sign = "+";
                break;
            } else if (String.valueOf(array[i]).matches("[\\-]") == true) {
                sign = "-";
                break;
            } else if (String.valueOf(array[i]).matches("[*]") == true) {
                sign = "*";
                break;
            } else if (String.valueOf(array[i]).matches("[/]") == true) {
                sign = "/";
                break;
            } else {
                sign = "false";
            }
        }
        return sign;
    }

    // проверка на лишние символы или цифры

    public String cheсkUnnessaryNum() {
        String[] array = str.split("[+\\-*/]+");
        if (array.length < 2) {
            return "Одна цифра";
        } else if (array.length > 2) {
            return "Больше двух цифр";
        }
        return "true";
    }

    public int checkUnnessarySign() {
        int count = 0;
        char element;
        for (int i = 0; i < str.length(); i++) {
            element = str.charAt(i);
            if (element == '+' || element == '-' || element == '*' || element == '/') {
                count++;
            }
        }
        return count;
    }

    public int romanInNum(String str) {//переводим римские в арабские для счёта
        int num;
        num = (int) romans.get(str);
        return num;
    }
    public void getValue() throws Exception {//выброс исключений

        if (cheсkUnnessaryNum() == "Одна цифра"){
            throw new Exception("Строка не является математической операцией");
        } else if (checkUnnessarySign() > 1) {
            throw new Exception("Формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
        } else if (checkIsNum() == "arabic") {
            int a = Integer.parseInt(getArray()[0]);
            int b = Integer.parseInt(getArray()[1]);
            if (a < 1 || b < 1 || a > 10 || b > 10) {
                throw new Exception("Выход за пределы. Введите два целых числа в диапазоне от 1 до 10");
            }
        }
    }

    public String calculate() throws Exception {
        getValue();
        if (checkIsNum() == "arabic") {
            int res;
            if (getSign() == "+") {
                res = Integer.parseInt(getArray()[0]) + Integer.parseInt(getArray()[1]);
                return String.valueOf(res);
            } else if (getSign() == "-") {
                res = Integer.parseInt(getArray()[0]) - Integer.parseInt(getArray()[1]);
                return String.valueOf(res);
            } else if (getSign() == "*") {
                res = Integer.parseInt(getArray()[0]) * Integer.parseInt(getArray()[1]);
                return String.valueOf(res);
            } else if (getSign() == "/" && getArray()[1] != "0") {
                res = Integer.parseInt(getArray()[0]) / Integer.parseInt(getArray()[1]);
                return String.valueOf(res);
            } else if (getSign() == "/" && getArray()[1] == "0") {
                throw new Exception("На ноль делить нельзя");
            } else {
                throw new Exception("Вы ввели неверный знак. Используйте: + - * /");
            }
        } else if (checkIsNum() == "roman") {
            int num1, num2, res;
            String resTeen, resUnit;
            num1 = romanInNum(getArray()[0]);
            num2 = romanInNum(getArray()[1]);
            if (getSign() == "+") {
                res = num1 + num2;
                resUnit = romeInit[res%10];
                resTeen = romeTen[res/10];
                return String.valueOf(resTeen) + String.valueOf(resUnit);
            } else if (getSign() == "-") {
                if (num1 - num2 < 1) {
                    throw new Exception("Ошибка ввода. В римской системе нет отрицательных чисел");
                } else {
                    res = num1 - num2;
                    resUnit = romeInit[res%10];
                    resTeen = romeTen[res/10];
                    return resTeen + resUnit;
                }
            } else if (getSign() == "*") {
                res = num1 * num2;
                resUnit = romeInit[res%10];
                resTeen = romeTen[res/10];
                return resTeen + resUnit;
            } else if (getSign() == "/" && getArray()[1] != "0") {
                res = num1 / num2;
                resUnit = romeInit[res%10];
                resTeen = romeTen[res/10];
                return resTeen + resUnit;
            } else if (getSign() == "/" && getArray()[1] == "0") {
                throw new Exception("На ноль делить нельзя");
            } else {
                throw new Exception("Вы ввели неверный знак. Используйте: + - * /");
            }
        } else {
            throw new Exception("Ошибка! Используются одновременно разные системы счисления");
        }
    }






}
