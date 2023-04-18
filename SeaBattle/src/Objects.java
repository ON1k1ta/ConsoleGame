//класс по работе с объектами
public class Objects {
    private static Object_1[] M_object1 = new Object_1[4];          //создаем список кораблей по одной клетке
    private static Object_2[] M_object2 = new Object_2[3];
    private static Object_3[] M_object3 = new Object_3[2];
    private static Object_4[] M_object4 = new Object_4[1];

    public static int KillCount = M_object1.length+M_object2.length+M_object3.length+M_object4.length;      //переменная общего количества кораблей

    public int getLength(int number) {                              //вывод количества кораблей по количеству клеток
    //принимает целые числа от 0-3
    //return -2 - ошибка, иначе количество
        switch (number) {
            case 0:
                return M_object1.length;
            case 1:
                return M_object2.length;
            case 2:
                return M_object3.length;
            case 3:
                return M_object4.length;
        }
        return -2;

    }


    public static void CreateObject() {                                     //создание объектов на поле

        for(int i=0; i < M_object4.length; i++) {                           //создание кораблей из 4 клеток
            M_object4[i] = new Object_4();
            M_object4[i].CreateObject_4();
        }
        for(int i=0; i < M_object3.length; i++) {
            M_object3[i] = new Object_3();
            M_object3[i].CreateObject_3();
        }
        for(int i=0; i < M_object2.length; i++) {
            M_object2[i] = new Object_2();
            M_object2[i].CreateObject_2();
        }
        for(int i=0; i < M_object1.length; i++) {
            M_object1[i] = new Object_1();
            M_object1[i].CreateObject_1();
        }
    }

    Object get_object(int numb, int count) {                                //вывод ссылки на конкретный корабль
    //return ссылка на корабль
    //принимает номер списка кораблей и конкретный корабль из списка
        Object result = new Object();

        switch (numb) {
            case 0:
                result = M_object1[count];
                break;
            case 1:
                result = M_object2[count];
                break;
            case 2:
                result = M_object3[count];
                break;
            case 3:
                result = M_object4[count];
                break;
        }

        return result;
    }
}
