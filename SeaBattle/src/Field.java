//класс поля игры где распологаются корабли
public class Field {
    private static int[][] M_field = new int[10][10];       //массив с ячейками поля игры 10х10

    public int get(int unit_x, int unit_y) {                //вывод значения ячейки
    //return -2 - ошибка, иначе вывод значения
    //принимает координаты запрашиваемой ячейки
        if(unit_x<0 || unit_y<0) {                          //проверка запрашиваемых значений на положительные числа
            return -2;
        }
        if(M_field.length <= unit_x) {                      //проверка координаты ячейки на пределы поля по Х координате
            return -2;
        }
        if(M_field[unit_x].length <= unit_y) {              //проверка координаты ячейки на пределы поля по У координате
            return -2;
        }

        return M_field[unit_x][unit_y];                     //вывод значения ячейки
    }

    public int set(int unit_x, int unit_y, int value) {     //запись значения в ячейку
    //return -2 - ошибка, 0 - ОК
    //принимает координаты запрашиваемой ячейки и ее новое зачение
    //-2 - ошибка | -1 - подбит | 0 - пусто | 1 - объект находится тут | 2 - потоплен | 3 промах

        if(unit_x<0 || unit_y<0) {                          //проверка значений на положительные числа
            return -2;
        }
        if(M_field.length <= unit_x) {                      //проверка координаты ячейки на пределы поля по Х координате
            return -2;
        }
        if(M_field[unit_x].length <= unit_y) {              //проверка координаты ячейки на пределы поля по У координате
            return -2;
        }

        if(value > -3 && value < 4) {                       //проверка значения на допустимые
            M_field[unit_x][unit_y] = value;
            return 0;
        }
        else {
            return -2;
        }
    }

    public int get_length(int value) {                      //вывод размера поля по Х (0) или У (1)
    //return -2 - ошибка, иначе вывод значения
    //принимает значение 0 и 1
        if(value == 1) {
            return M_field[0].length;
        } else if(value == 0){
            return M_field.length;
        } else return -2;
    }
}
