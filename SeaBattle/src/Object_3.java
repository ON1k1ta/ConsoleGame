//корабль на 3 клетки
public class Object_3 {
    private int[][] M_cell = new int[3][2];                             //массив расположения объекта на поле содержит координаты объекта
    Field field = new Field();
    private int hit_counter = 0;                                        //создаем колличество попаданий по объекту

    public void CreateObject_3() {                                      //создание объекта на поле

        int[][] Lp = new int[][]{{-1, -1}, {-1, -1}, {-3, -1}, {-1, -3}};   //массив содержащий крайние координаты поля в которое будет помещен объект
        int[][] Rp = new int[][]{{3, 1}, {1, 3}, {1, 1}, {1, 1}};
        int Lpx, Lpy, Rpx, Rpy;                                             //переменые крайних точек этого поля

        loop:for(int count = 0; count < 50; count++) {                      //цикл повторений в случае неудачи создания объекта

            int unit_x = (int) (Math.random() * field.get_length(0));  //выбор рандомных координат первой клетки корабля
            int unit_y = (int) (Math.random() * field.get_length(1));

            int random_place = (int) (Math.random() * 2 );                  //случайный выбор положений корабля (вертикально или горизонтально)

            rotation:for(int j = random_place; j < 4; j++) {                //поворот корабля на 90 градусов относительно 1 клетки, если при первом построении в поле был другой объект
                Lpx = Lp[j][0];                                             //задаем новые координы поля где будет создан объект
                Lpy = Lp[j][1];
                Rpx = Rp[j][0];
                Rpy = Rp[j][1];

                for(int ix = unit_x+Lpx; ix <= unit_x+Rpx; ix++) {          //проверка поля по Х координате
                    for(int iy = unit_y+Lpy; iy <= unit_y+Rpy;iy++) {       //проверка поля в У координате

                        one:switch (field.get(ix, iy)) {                    //получаем значение координат на основном поле игры
                            case -2:
                                switch (j) {                                //смотрим в какую сторону повернут объект в данный момент
                                    case 0:
                                        if((ix == unit_x+2) && (iy == unit_y)) break;       //проверяем за пределы поля вышла последняя клетка объекта, если да
                                        break one;                                          //поворачиваем объект
                                    case 1:
                                        if((ix == unit_x) && (iy == unit_y+2)) break;
                                        break one;
                                    case 2:
                                        if((ix == unit_x-2) && (iy == unit_y)) break;
                                        break one;
                                    case 3:
                                        if((ix == unit_x) && (iy == unit_y-2)) break;
                                        break one;
                                }

                                continue rotation;
                            case 1:
                                continue rotation;                                           //если в пределах поля находится другой объект, то поворачиваем корабль
                            case 0:
                                break;
                        }
                    }
                }

                int x1=0, y1=0;                                                              //переменные указывают в какую сторону повернут объект
                switch (j) {
                    case 0:
                        x1 = 1;
                        y1 = 0;
                        break;
                    case 1:
                        x1 = 0;
                        y1 = 1;
                        break;
                    case 2:
                        x1 = -1;
                        y1 = 0;
                        break;
                    case 3:
                        x1 = 0;
                        y1 = -1;
                        break;
                }

                for(int i = 0; i<3; i++) {                                                //отмечаем координаты в массиве объекта и в поле
                    field.set(unit_x+(x1*i), unit_y+(y1*i), 1);
                    M_cell[i][0] = unit_x+(x1*i);
                    M_cell[i][1] = unit_y+(y1*i);
                }

                break loop;
            }
        }



    }

    public int HitCheck(int unit_x, int unit_y) {                           //проверка на попадание и потопление корабля
    //return 0 - промах, 1 - попал, 2 - потопил
    //принимает координаты которые ввел пользователь
        for(int i = 0; i<3; i++) {                                          //проверка на попадание происходит по одной клетке за раз

            if((M_cell[i][0] == unit_x) && (M_cell[i][1] == unit_y)) {      //проверяет совпали ли координаты объекта и пользователя

                if(++hit_counter == 3) {                                    //проверяем на потопление корабля

                    for(int j = 0; j < 3; j++) {                            //помечаем область вокруг объекта (поле) как "промах"
                        for(int ix = M_cell[j][0]-1; ix < M_cell[j][0]+2; ix++) {
                            for(int iy = M_cell[j][1]-1; iy < M_cell[j][1]+2; iy++) {
                                field.set(ix, iy, 3);
                            }
                        }
                    }

                    for(int i1 = 0; i1<3; i1++) {                           //помечаем на карте корабль как потопленный
                        field.set(M_cell[i1][0], M_cell[i1][1], 2);
                    }
                    return 2;
                }
                field.set(unit_x, unit_y, -1);                         //если не потоплен то почемаем как "подбит"
                return 1;

            }
        }
        return 0;
    }
}
