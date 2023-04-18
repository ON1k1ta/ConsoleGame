//морской бой v.1                                           ONA
public class Main {

    public static void main(String[] args) {

        boolean game = true;                                //переменная выхода из игры
        int[] units;                                        //переменная ввода координат пользователя

        Objects.CreateObject();                             //создание объектов

        while(game) {                                       //цикл игры

            GUI.Field();                                    //отрисовка карты на экране

            units = Engine.Input();                         //ввод данных, и получение координат
            if (units[0] == -2) {                           //проверка ввода на ошибку
                System.out.println("Ошибка ввода данных");
            }

            switch (Engine.KillCheck(units[0], units[1])) { //проверка на конец игры ошибку и попадания
                case (-2):
                    System.out.println("Ошибка KillCheck");
                    game = false;
                    break;
                case 2:
                    game = false;
                    GUI.Field();
                    break;
            }
        }


    }
}