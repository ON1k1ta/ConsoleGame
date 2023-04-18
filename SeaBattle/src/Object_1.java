//корабль на 1 клетку
public class Object_1 {
    private int[][] M_cell = new int[1][2];                         //массив расположения объекта на поле содержит координаты объекта
    Field Field1 = new Field();

    public void CreateObject_1() {                                  //создание объекта на поле

        loop:for(int count = 0; count < 50; count++) {              //цикл повторений в случае неудачи создания объекта

            int unit_x = (int) (Math.random() * Field1.get_length(0));  //выбор рандомных координат корабля
            int unit_y = (int) (Math.random() * Field1.get_length(1));

            for (int x = unit_x - 1; x < unit_x + 2; x++) {                  //по правилам морского моя на расстоянии
                for (int y = unit_y - 1; y < unit_y + 2; y++) {              //одной клетки не должно распологаться кораблей
                    if (Field1.get(x, y) == 1) {                             //тут осуществляется проверка есть ли на расстоянии
                        continue loop;                                       //клетки еще корабли, если есть
                    }                                                        //то создаем корабль заново
                }
            }
            Field1.set(unit_x, unit_y, 1);                              //при успешном создании помечаем в массиве что там есть корабль
            M_cell[0][0]=unit_x;
            M_cell[0][1]=unit_y;
            break;

        }


    }

    public int HitCheck(int unit_x, int unit_y) {                       //проверка на попадание и потопление корабля
    //return 0 - промах, 2 - потопил
    //принимает координаты которые ввел пользователь
        if((M_cell[0][0] == unit_x) && (M_cell[0][1] == unit_y)) {      //проверяет совпали ли координаты объекта и пользователя

            for( int ix = M_cell[0][0]-1; ix < M_cell[0][0]+2; ix++) {  //помечаем область вокруг объекта (поле) как "промах"
                for(int iy = M_cell[0][1]-1; iy < M_cell[0][1]+2; iy++) {
                    Field1.set(ix, iy, 3);
                }
            }

            Field1.set(unit_x, unit_y, 2);                        //отмечаем на поле потопленный корабль
            return 2;
        }
        else return 0;
    }


}
