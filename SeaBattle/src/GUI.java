//класс интерфейса игры
public class GUI {
//\u2588 - пустое поле \u2591 - мимо \u2580 84 - попал \u256C - убил, везде ставится по два символа

    static String[] M_x = new String[]{" ","A  ", "B  ", "C  ", "D  ", "E  ", "F  ", "G  ", "H  ", "I  ", "J  ", "K  ", "L  ", "M  ", "N  ", "O  ", "P  ", "Q  ", "R  ", "S  ", "T  ", "U  ", "V  ", "W  ", "X  ", "Y  ", "Z  "};
    //массив строчек карты
    static String[] M_y = new String[]{"   ", "1  ", "2  ", "3  ", "4  ", "5  ", "6  ", "7  ", "8  ", "9  ", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26"};
    //массив столбцов карты

    public static void Field() {                                            //метод отображения карты

        Field field = new Field();                                          //создаем экземпляр поля

        System.out.println("\n\n\n\n");                                     //пропускаем пару строк для корректного отображения

        loop:for(int x = 0; x <= field.get_length(0); x++) {          //построчное построение карты

            System.out.println("");                                         //для переноса строки

            for( int i = 0; i<9; i++) {
                System.out.print(" ");                                      //отступ от границы для красоты
            }

            if(x==0) {                                                      //первая строка идет отображение номеров столбцов 1, 2, 3..
                for (int y = 0; y <= field.get_length(0); y++) {
                    System.out.print(M_y[y]);
                }
                continue loop;
            }

            for(int y = 0; y<= field.get_length(0); y++) {              //отображаем остальные строки по столбцам

                if(y==0) {                                                   //вначале отображаем буквенное отображение строк A, B, C...
                    System.out.print(M_x[x]);
                } else {                                                     //берем из массива карты значения и в зависимости от них окрашиваем элементы карты

                    int value = field.get(x-1, y-1);
                    switch(value) {
                        case -1:
                            System.out.print("\u2580\u2584 ");
                            break;
                        case 2:
                            System.out.print("\u256C\u256C ");
                            break;
                        case 3:
                            System.out.print("\u2591\u2591 ");
                            break;
                        case 1:
                            //System.out.print("\u2593\u2593 ");               //помечает корабли на карте
                            //break;
                        case 0:
                            System.out.print("\u2588\u2588 ");
                            break;
                    }
                }
            }

        }
        System.out.println("");                                                //отступ пары строк
        System.out.println("");
    }

}
