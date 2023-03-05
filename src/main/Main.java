package main;

import java.util.Map;

public class Main {

    public static void main(String[] args) throws Exception {
        System.out.println(calc("12*10"));
    }

    static String numOrRoman;
    static String sign;
    static String[] array;
    static int Res;
    static String[] romeInit = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};
    static String[] romeTen = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC", "C"};
    static Map romans = Map.of("I", 1, "II", 2, "III", 3, "IV", 4,
            "V", 5, "VI", 6, "VII", 7, "VIII", 8, "IX", 9,
            "X",10);

    static String str = "";

    public static String calc(String input) throws Exception {
        str = input;
        getValue(str);
        if (checkIsNum(str) == "arabic") {
            int res;
            if (getSign(str) == "+") {
                res = Integer.parseInt(getArray()[0]) + Integer.parseInt(getArray()[1]);
                return String.valueOf(res);
            } else if (getSign(str) == "-") {
                res = Integer.parseInt(getArray()[0]) - Integer.parseInt(getArray()[1]);
                return String.valueOf(res);
            } else if (getSign(str) == "*") {
                res = Integer.parseInt(getArray()[0]) * Integer.parseInt(getArray()[1]);
                return String.valueOf(res);
            } else if (getSign(str) == "/" && getArray()[1] != "0") {
                res = Integer.parseInt(getArray()[0]) / Integer.parseInt(getArray()[1]);
                return String.valueOf(res);
            } else if (getSign(str) == "/" && getArray()[1] == "0") {
                throw new Exception("На ноль делить нельзя");
            } else {
                throw new Exception("Вы ввели неверный знак. Используйте: + - * /");
            }
        } else if (checkIsNum(str) == "roman") {
            int num1, num2, res;
            String resTeen, resUnit;
            num1 = romanInNum(getArray()[0]);
            num2 = romanInNum(getArray()[1]);
            if (getSign(str) == "+") {
                res = num1 + num2;
                resUnit = romeInit[res%10];
                resTeen = romeTen[res/10];
                return String.valueOf(resTeen) + String.valueOf(resUnit);
            } else if (getSign(str) == "-") {
                if (num1 - num2 < 1) {
                    throw new Exception("Ошибка ввода. В римской системе нет отрицательных чисел");
                } else {
                    res = num1 - num2;
                    resUnit = romeInit[res%10];
                    resTeen = romeTen[res/10];
                    return resTeen + resUnit;
                }
            } else if (getSign(str) == "*") {
                res = num1 * num2;
                resUnit = romeInit[res%10];
                resTeen = romeTen[res/10];
                return resTeen + resUnit;
            } else if (getSign(str) == "/" && getArray()[1] != "0") {
                res = num1 / num2;
                resUnit = romeInit[res%10];
                resTeen = romeTen[res/10];
                return resTeen + resUnit;
            } else if (getSign(str) == "/" && getArray()[1] == "0") {
                throw new Exception("На ноль делить нельзя");
            } else {
                throw new Exception("Вы ввели неверный знак. Используйте: + - * /");
            }
        } else {
            throw new Exception("Ошибка! Используются одновременно разные системы счисления");
        }
    }

    public static void getValue(String str) throws Exception {//выброс исключений
        if (cheсkUnnessaryNum(str) == "Одна цифра"){
            throw new Exception("Строка не является математической операцией");
        } else if (checkUnnessarySign(str) > 1) {
            throw new Exception("Формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
        } else if (checkIsNum(str) == "arabic") {
            int a = Integer.parseInt(getArray()[0]);
            int b = Integer.parseInt(getArray()[1]);
            if (a < 1 || b < 1 || a > 10 || b > 10) {
                throw new Exception("Выход за пределы. Введите два целых числа в диапазоне от 1 до 10");
            }
        }
    }

    public static String cheсkUnnessaryNum(String str) {
        String[] array = str.split("[+\\-*/]+");
        if (array.length < 2) {
            return "Одна цифра";
        } else if (array.length > 2) {
            return "Больше двух цифр";
        }
        return "true";
    }

    public static int checkUnnessarySign(String str) {
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

    public static String checkIsNum(String str) throws Exception {

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

    public static String[] getArray() {
        String[] array = str.split("[+\\-*/]+");
        return array;
    }

    public static String getSign(String str) {// присваиваем значение sign
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

    public static int romanInNum(String str) {//переводим римские в арабские для счёта
        int num;
        num = (int) romans.get(str);
        return num;
    }
}
