import java.util.Scanner;

public class Console {
    public static void main(String[] args) {
        System.out.println("Пожалуйста выберите тип двойного интеграла:\n" +
                "1: II = (x*y)dxdy, где подынтегральная функция f(x,y) =  (x * y)\n" +
                "2: II = ((sin(x)/x)*2*y)dxdy, где продынтегральная функция f(x,y) = ((sin(x)/x)*y^2)\n" +
                "3: II = ((1/x)*2*y)dxdy, где подынтегральная фукнция f(x,y) = ((1/x)*2*y)");
        Scanner scanner = new Scanner(System.in);
        int type = scanner.nextInt();
        Integral integral = new Integral();
        switch (type) {
            case 1:
                /* Тест 1:  x = [1;3] (I = 4) y = [2;5] (I = 10,5) */
                System.out.println("Пожалуйста введите левую и правую границу для x:");
                integral.setLeftBoardX(scanner.nextDouble());
                integral.setRightBoarX(scanner.nextDouble());
                System.out.println("Пожалуйста введите левую и правую границу для y:");
                integral.setLeftBoardY(scanner.nextDouble());
                integral.setRightBoarY(scanner.nextDouble());
                System.out.println("Пожалуйста введите количество изначального разбиения функции по x:");
                integral.setnX(scanner.nextInt());
                System.out.println("Пожалуйста введите количество изначального разбиения функции по y:");
                integral.setnY(scanner.nextInt());
                System.out.println("Пожалуйста введите точность:");
                Integral.eps = scanner.nextDouble();
                Function function1;
                Function function2;
                function1 = (x) -> x;
                function2 = (y) -> y;
                Integral.methodGeneral(integral, function1, function2);
                System.out.println("I = " + integral.getResultX());
                System.out.println("I = " + integral.getResultY());
                System.out.println("I = " + integral.getResultI());

                break;
            case 2:
                /* Тест 1:  x = [2;4] (I = 0,15) y = [1;6] (I = 71,6) */
                /* Тест 2:  x = [-1;1] (I = 1,89) y = [1;6] (I = 71,6)  --- разрыв первого рода*/
                System.out.println("Пожалуйста введите левую и правую границу для x:");
                integral.setLeftBoardX(scanner.nextDouble());
                integral.setRightBoarX(scanner.nextDouble());
                System.out.println("Пожалуйста введите левую и правую границу для y:");
                integral.setLeftBoardY(scanner.nextDouble());
                integral.setRightBoarY(scanner.nextDouble());
                System.out.println("Пожалуйста введите количество изначального разбиения функции по x:");
                integral.setnX(scanner.nextInt());
                System.out.println("Пожалуйста введите количество изначального разбиения функции по y:");
                integral.setnY(scanner.nextInt());
                System.out.println("Пожалуйста введите точность:");
                Integral.eps = scanner.nextDouble();
                Function function3;
                Function function4;
                function3 = (x) -> x != 0 ? (Math.sin(x) / x) : 1;
                function4 = (y) -> Math.pow(y, 2);
                Integral.methodGeneral(integral, function3, function4);
                System.out.println("I = " + integral.getResultX());
                System.out.println("I = " + integral.getResultY());
                System.out.println("I = " + integral.getResultI());
                break;
            case 3:
                /* Тест 1:  x = [2;4] (I = 0,69) y = [1;6] (I = 35) */
                /* Тест 2:  x = [-1;1] (I = 0,15) y = [1;6] (I = 35)  --- разрыв первого рода*/
                System.out.println("Пожалуйста введите левую и правую границу для x:");
                integral.setLeftBoardX(scanner.nextDouble());
                integral.setRightBoarX(scanner.nextDouble());
                System.out.println("Пожалуйста введите левую и правую границу для y:");
                integral.setLeftBoardY(scanner.nextDouble());
                integral.setRightBoarY(scanner.nextDouble());
                System.out.println("Пожалуйста введите количество изначального разбиения функции по x:");
                integral.setnX(scanner.nextInt());
                System.out.println("Пожалуйста введите количество изначального разбиения функции по y:");
                integral.setnY(scanner.nextInt());
                System.out.println("Пожалуйста введите точность:");
                Integral.eps = scanner.nextDouble();
                Function function5;
                Function function6;
                function5 = (x) -> (1 / x);
                function6 = (y) -> 2 * y;
                Integral.checkSecondRazrivAndPromezutki(integral);
                Integral.methodGeneral(integral, function5, function6);
                System.out.println("I = " + integral.getResultX());
                System.out.println("I = " + integral.getResultY());
                System.out.println("I = " + integral.getResultI());
                break;
            default:
                System.out.println("Завершение программы");
                break;
        }
    }
}
