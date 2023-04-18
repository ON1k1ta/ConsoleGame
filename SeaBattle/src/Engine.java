//класс основных функций
import java.util.Scanner;


public class Engine {


    public static int[] Input() {                                          //ввод с клавиатуры
    //return массив -2, -2 - ошибка, иначе координаты

        String CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";                       //переменные проверки буквенных значений поля
        String chars = "abcdefghijklmnopqrstuvwxyz";
        int[] out = {-2, -2};                                              //переменная вывода ошибки

        Scanner scan = new Scanner(System.in);                             //инициализация ввода

        while(true) {                                                      //ввод работает пока не будет правильно

            System.out.println("Введите номер ячейки (буква-цифра): ");
            String input = scan.nextLine();                                //ввод

            if (input.length() > 4 || input.length() < 3) {                 //проверка на количество символов
                System.out.println("Некоректно введены данные (не используйте пробел или иные символы)");
                continue;
            }
            if (!input.contains("-")) {                                     //проверка на присутствие разделителя
                System.out.println("Некоректно введены данные (Отсутствует разделитель  -  )");
                continue;
            }
            if (input.indexOf("-") != 1) {                                  //находится ли разделитель посередине
                System.out.println("Некоректно введены данные (разделитель - используется неверно )");
                continue;
            }
            String[] M_input = input.split("-");                       //разделяем введенную строку на необработанные координаты
            if (M_input.length != 2) {                                       //дополнительная проверка разделителя
                System.out.println("Некоректно введены данные (разделитель - используется  неверно )");
                continue;
            }

            int unit_x = CHARS.indexOf(M_input[0]);                          //находим координату X путем поиска в алфавите этой буквы
            if (unit_x == -1) {
                unit_x = chars.indexOf(M_input[0]);                          //ищем и большую и маленькую
                if (unit_x == -1) {                                          //проверка на правильность ввода, ввели ли букву
                    System.out.println("Некоректно введены данные (Вводите 1 символ латиницей)");
                    continue;
                }
            }

            Field field = new Field();
            if (field.get_length(0) < unit_x + 1) {                     //проверяем не вышли ли за пределы карты
                System.out.println("Некоректно введены данные (вводите букву в пределах поля)");
                continue;
            }

            int unit_y = 0;
            try {                                                             //проверка на ошибку перевода типов
                unit_y = Integer.parseInt(M_input[1]);                        //переводим строку с цифрой в тип int
            } catch (
                    NumberFormatException nfe) {                              //срабатывает в случае если в строке не число
                System.out.println("Некоректно введены данные (после разделителя вводите числа)");
                continue;
            }
            if (field.get_length(1) < unit_y || unit_y <= 0) {           //проверяем не вышли ли за пределы поля
                System.out.println("Некоректно введены данные (число больше поля)");
                continue;
            }
            --unit_y;                                                          //корректируем координату Y
            out[0] = unit_x;
            out[1] = unit_y;
            return out;
        }
    }


    public static int KillCheck(int unit_x, int unit_y) {                   //проверка что находится по координатом, что ввел пользователь
    //return -2 - ошибка, 0 - мимо, 1 - мимо, 2 - подбил
        Field field = new Field();


        int value = field.get(unit_x, unit_y);                              //получаем значение с карты

        switch (value) {                                                    //определяем что делать при каждом значении
            case -2:
                System.out.println("Ой, что то пошло не так, попробуй раз");
                return -2;
            case -1:
                System.out.println("Ты уже подбил эту часть корабля, попробуй снова");
                return 0;
            case 0:
                System.out.println("Мимо");
                field.set(unit_x, unit_y, 3);
                return 0;
            case 2:
                System.out.println("Он уже потоплен, добивать некого!");
                return 0;
            case 1:
                break;
            case 3:
                System.out.println("Мажешь, опять попал по месту ГДЕ НИЧЕГО НЕТ!!!");
                return 0;

        }

        Objects OBJ = new Objects();                                        //создаем переменную управления объектами

        for(int numb_obj = 0; numb_obj < 4; numb_obj++) {                   //проверяем все 4 типа объектов

            loop:for (int count_obj = 0; count_obj < OBJ.getLength(numb_obj); count_obj++) {    //проверяем все объекты из списка типов объектов

                Object obj = OBJ.get_object(numb_obj, count_obj);                               //создаем переменную общего типа и присваевам ей ссылку на текущий объект


                int result_hitCheck = 0;                                                        //результат проверки объектов

                if (numb_obj == 0) {                                                            //типизация общего объекта к частным
                    obj = (Object_1) obj;
                    result_hitCheck = ((Object_1) obj).HitCheck(unit_x, unit_y);
                } else if (numb_obj == 1) {
                    obj = (Object_2) obj;
                    result_hitCheck = ((Object_2) obj).HitCheck(unit_x, unit_y);
                } else if (numb_obj == 2) {
                    obj = (Object_3) obj;
                    result_hitCheck = ((Object_3) obj).HitCheck(unit_x, unit_y);
                } else if (numb_obj == 3) {
                    obj = (Object_4) obj;
                    result_hitCheck = ((Object_4) obj).HitCheck(unit_x, unit_y);
                }

                switch (result_hitCheck) {                                                      //вывод в зависимости от результата
                    case 0:
                        continue loop;
                    case 1:
                        System.out.println("Ранил!");
                        return 1;
                    case 2:
                        System.out.println("ПОТОПИЛ!");
                        if (--OBJ.KillCount == 0) {
                            System.out.println("Все корабли потоплены, ПОБЕДА!!!");
                            return 2;
                        }
                        return 1;

                }

            }

        }
        System.out.println("Что то пошло не так");                                               //если код доходит до конца, значит не синхронизированы поля карты и объектов
        return -2;

    }

}
