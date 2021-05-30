import org.knowm.xchart.QuickChart;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;

public class Integral {
    private double leftBoardX; // нижняя граница предела X
    private double rightBoarX; // верхняя граница предела X
    private double leftBoardY; // нижняя граница предела Y
    private double rightBoarY; // верхняя граница предела Y
    private int nX; // пользователь вводит число отрезков начального разбиеничя X
    private int nY; // пользователь вводит число отрезков начального разбиения Y
    public static double eps; // точность
    private double resultX; // значение интеграла по X
    private double resultY; // значение интеграла по Y
    private double resultI;

//    public static void draw(Integral integral, Function function1, Function function2) {
//        double[] xDatsObl = new double[5];
//        double[] yDatsObl = new double[5];
//        xDatsObl[0] = integral.leftBoardX;
//        yDatsObl[0] = integral.leftBoardY;
//        xDatsObl[1] = integral.rightBoarX;
//        yDatsObl[1] = integral.leftBoardY;
//        xDatsObl[2] = integral.rightBoarX;
//        yDatsObl[2] = integral.rightBoarY;
//        xDatsObl[3] = integral.leftBoardX;
//        yDatsObl[3] = integral.rightBoarX;
//        xDatsObl[4] = integral.leftBoardX;
//        yDatsObl[4] = integral.leftBoardY;
//
//        double[] xDatsOsiX = new double[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
//        double[] yDatsOsiX = new double[]{-7, -6, -5, -4, -3, -2, -1, 0, 1, 2, 3, 4, 5, 6, 7};
//        double[] xDatsOsiY = new double[]{-7, -6, -5, -4, -3, -2, -1, 0, 1, 2, 3, 4, 5, 6, 7};
//        double[] yDatsOsiY = new double[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
//        XYChart chart = QuickChart.getChart("Sample Chart", "X", "Y", "y(x)", xDatsObl, yDatsObl);
//        chart.addSeries("X", xDatsOsiX, yDatsOsiX);
//        chart.addSeries("Y", xDatsOsiY, yDatsOsiY);
//
//        double sum = (integral.rightBoarX - integral.leftBoardX) / 0.1 ;
//        double[] xdataFunction1 = new double[(int) sum + 1];
//        double[] ydataFunction1 = new double[(int) sum + 1];
//        double xxx = integral.leftBoardX;
//        for (int i = 0; i < sum + 1; i++) {
//            xdataFunction1[i] = xxx;
//            ydataFunction1[i] = function1.func(xxx);
//            xxx += 0.1;
//        }
//        chart.addSeries("LOL", xdataFunction1, ydataFunction1);
//        double[] xdataFunction2 = new double[(int) sum + 1];
//        double[] ydataFunction2 = new double[(int) sum + 1];
//        xxx = integral.leftBoardX;
//        for (int i = 0; i < sum + 1; i++) {
//            xdataFunction2[i] = xxx;
//            ydataFunction2[i] = function2.func(xxx);
//            xxx += 0.1;
//        }
//        chart.addSeries("KEK", xdataFunction2, ydataFunction2);
//
//        new SwingWrapper(chart).displayChart();
//
//
//    }






//    public static boolean razryv2rodaBad = false;
    public static boolean zeroFlag = false; // флаг для рассмотрения случая a == -b

    public double getResultI() {
        return resultI;
    }

    public void setResultI(double resultI) {
        this.resultI = resultI;
    }

    public static void methodGeneral(Integral integral, Function function1, Function function2) {
        methodSimpsonFirstFunction(integral, function1);
        methodSimpsonSecondFunction(integral, function2);
        integral.resultI = integral.resultX * integral.resultY;
    }


    public static void methodSimpsonFirstFunction(Integral integral, Function function) {

            double h = (integral.rightBoarX - integral.leftBoardX) / integral.nX;
            double sumX_2 = 0d;
            double sumX_4 = 0d;
            double x;
            for (int i = 1; i < integral.nX; i += 2) {
                x = integral.leftBoardX + i * h;
                sumX_4 = sumX_4 + function.func(x);
            }

            for (int i = 2; i < integral.nX - 1; i += 2) {
                x = integral.leftBoardX + i * h;
                sumX_2 = sumX_2 + function.func(x);
            }

            integral.resultX = (sumX_2 * 2 + sumX_4 * 4 + function.func(integral.leftBoardX) + function.func(integral.rightBoarX)) * (h / 3);
            if (zeroFlag) {
                integral.resultX -= integral.resultX;
            }
    }

    public static void methodSimpsonSecondFunction(Integral integral, Function function) {
        double h = (integral.rightBoarY - integral.leftBoardY) / integral.nY;
        double sumX_2 = 0d;
        double sumX_4 = 0d;
        double x;
        for (int i = 1; i < integral.nY; i += 2) {
            x = integral.leftBoardY + i * h;
            sumX_4 = sumX_4 + function.func(x);
        }

        for (int i = 2; i < integral.nY - 1; i += 2) {
            x = integral.leftBoardY + i * h;
            sumX_2 = sumX_2 + function.func(x);
        }

        integral.resultY = (sumX_2 * 2 + sumX_4 * 4 + function.func(integral.leftBoardY) + function.func(integral.rightBoarY)) * (h / 3);

    }

    public static void checkSecondRazrivAndPromezutki(Integral integral) {
        if ((integral.leftBoardX > 0 && integral.rightBoarX < 0 || integral.leftBoardX < 0 && integral.rightBoarX > 0) && Math.abs(integral.leftBoardX) != Math.abs(integral.rightBoarX)) {
            System.out.println("Так вы ввели некорректные промежутки (a != -b), Вы наткнулись на неустранимый разрыв второго рода\n" +
                    "Если хотите все сделать по кайфу, пожалуйста введите такие промежутки, что бы a == -b ");
            System.exit(0);
        }
        if ((integral.leftBoardX > 0 && integral.rightBoarX < 0 || integral.leftBoardX < 0 && integral.rightBoarX > 0) && Math.abs(integral.leftBoardX) == Math.abs(integral.rightBoarX)) {
            System.out.println("Вы наткнулись на разрыв второго рода :) ");
            integral.leftBoardX = 0 + 0.0001;
            zeroFlag = true;
        }else if (integral.leftBoardX == 0 && integral.rightBoarX > 0) {
            integral.leftBoardX = 0 + 0.0001;
            System.out.println("ssssssss");
        }else if (integral.rightBoarX == 0 && integral.leftBoardX < 0) {
            integral.rightBoarX = 0 - 0.0001;
        }
    }

//    /* i1 текущее - i0 - предыдущее */
//    public static double ryhgeMethod(double i1, double i0) {
//        return Math.abs(i1 - i0) / 15;
//    }



    public double getResultX() {
        return resultX;
    }

    public void setResultX(double resultX) {
        this.resultX = resultX;
    }

    public double getResultY() {
        return resultY;
    }

    public void setResultY(double resultY) {
        this.resultY = resultY;
    }

    public Integral(double leftBoard, double rightBoard, int n) {
        this.leftBoardX = leftBoard;
        this.rightBoarX = rightBoard;
        this.nX = n;
    }



    public double getEps() {
        return eps;
    }

    public void setEps(double eps) {
        this.eps = eps;
    }

    public double getLeftBoardX() {
        return leftBoardX;
    }

    public void setLeftBoardX(double leftBoardX) {
        this.leftBoardX = leftBoardX;
    }

    public double getRightBoarX() {
        return rightBoarX;
    }

    public void setRightBoarX(double rightBoarX) {
        this.rightBoarX = rightBoarX;
    }

    public double getLeftBoardY() {
        return leftBoardY;
    }

    public void setLeftBoardY(double leftBoardY) {
        this.leftBoardY = leftBoardY;
    }

    public double getRightBoarY() {
        return rightBoarY;
    }

    public void setRightBoarY(double rightBoarY) {
        this.rightBoarY = rightBoarY;
    }

    public int getnX() {
        return nX;
    }

    public void setnX(int nX) {
        this.nX = nX;
    }

    public int getnY() {
        return nY;
    }

    public void setnY(int nY) {
        this.nY = nY;
    }

    public Integral(double leftBoardX, double rightBoarX, double leftBoardY, double rightBoarY, int nX, int nY) {
        this.leftBoardX = leftBoardX;
        this.rightBoarX = rightBoarX;
        this.leftBoardY = leftBoardY;
        this.rightBoarY = rightBoarY;
        this.nX = nX;
        this.nY = nY;
    }

    public Integral() {}



}
