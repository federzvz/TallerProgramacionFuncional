import java.util.Scanner;
import java.util.function.IntBinaryOperator;
import java.util.stream.IntStream;

public class Main {

    static IntBinaryOperator sum = (a, b)->a+b;

    static IntBinaryOperator rest = (a,b)->a-b;

    static IntBinaryOperator multiply = (a, b)->{
        if(a<0&&b<0){
            a=-a;
            b=-b;
        }
        if(b<0 && a>0){
            int aux=b;
            b=a;
            a=aux;
        }
        int finalA = a;
        return IntStream.range(0,b+1)
                .reduce((acumulated,number)-> sum.applyAsInt(acumulated,finalA))
                .getAsInt();
    };

    static IntBinaryOperator divide = (a,b) ->{
        if(b==0){
            throw new RuntimeException("No se puede dividir entre cero.");
        }
        if(a==0){
            return 0;
        }
        return IntStream.range(0,Math.abs(a)).reduce((acumalate, number)-> multiply
                        .applyAsInt(number, b) <= a ? sum
                        .applyAsInt(acumalate, 1): acumalate)
                .getAsInt();
    };

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String opc="-1";

        while (!opc.equalsIgnoreCase("0")) {
            System.out.println(menuContent());
            opc = scanner.nextLine();
            switch (opc) {
                case "1":
                    System.out.println(sum.applyAsInt(10,-5)); //=5
                    System.out.println(sum.applyAsInt(-10,5)); //=-5
                    break;
                case "2":
                    System.out.println(rest.applyAsInt(10,5)); //=5
                    break;
                case "3":
                    System.out.println(multiply.applyAsInt(-10,-5)); //=50
                    System.out.println(multiply.applyAsInt(10,-5)); //=-50
                    System.out.println(multiply.applyAsInt(-10,5)); //=-50
                    System.out.println(multiply.applyAsInt(10,5)); //=50
                    break;
                case "4":
                    try{
                        System.out.println(divide.applyAsInt(10,2)); //=5
                        System.out.println(divide.applyAsInt(10,2)); //=0
                        System.out.println(divide.applyAsInt(10,0)); //ERROR
                        break;
                    }catch (Exception e){
                        System.out.println(e.toString());
                        break;
                    }
                case "0":
                    break;
                default:
                    break;
            }
        }
    }

    public static String menuContent(){
        return "1- Sum \n2- Rest\n3- Multiply\n4- Divide\n0- Exit\n\nOption: ";
    }
}