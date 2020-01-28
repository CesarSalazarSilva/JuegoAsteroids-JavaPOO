import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {
    public static void menu(){
        Scanner sn = new Scanner(System.in);
        boolean salir = false;
        //Guardaremos la opcion del usuario
        int opcion;

        while (!salir) {
            System.out.println(" ______  _                             _     _         \n" +
                    "(____  \\(_)                           (_)   | |        \n" +
                    " ____)  )_  ____ ____ _   _ ____ ____  _  _ | | ___    \n" +
                    "|  __  (| |/ _  )  _ \\ | | / _  )  _ \\| |/ || |/ _ \\   \n" +
                    "| |__)  ) ( (/ /| | | \\ V ( (/ /| | | | ( (_| | |_| |  \n" +
                    "|______/|_|\\____)_| |_|\\_/ \\____)_| |_|_|\\____|\\___/   \n" +
                    "                                                       \n" +
                    "                                                       \n" +
                    "                                                       \n" +
                    "              ____                                     \n" +
                    "             / _  |                                    \n" +
                    "            ( ( | |                                    \n" +
                    "             \\_||_|                                    \n" +
                    "                                                       \n" +
                    "                                  _     _              \n" +
                    "   /\\        _                   (_)   | |             \n" +
                    "  /  \\   ___| |_  ____  ____ ___  _  _ | | ___         \n" +
                    " / /\\ \\ /___)  _)/ _  )/ ___) _ \\| |/ || |/___)        \n" +
                    "| |__| |___ | |_( (/ /| |  | |_| | ( (_| |___ |        \n" +
                    "|______(___/ \\___)____)_|   \\___/|_|\\____(___/         \n" +
                    "                                               ");
            System.out.println("    - Menu Principal -");
            System.out.println("1. Opcion 1 : Create Space");
            System.out.println("2. Opcion 2 : GameLoop ");
            System.out.println("3. Salir");

            try {

                System.out.println("Escribe una de las opciones");
                opcion = sn.nextInt();

                switch (opcion) {
                    case 1:
                        System.out.println("Create Space");
                        boolean salirloop = false;
                        System.out.println("Bienvenido a Create Space, crearemos un espacio de juego");
                        int N;
                        int M;
                        int NumAst;
                        int NumPart;
                        int fireRange;
                        int seed;
                        do {
                            Scanner reader = new Scanner(System.in);
                            N = -1;
                            System.out.println("Por favor ingrese el largo deseado del tablero \nValor de N: ");
                            do {
                                try {
                                    N = reader.nextInt();
                                } catch (InputMismatchException e) {
                                    System.out.println("Por favor ingrese un numero entero, no otro tipo de dato\nValor de N: ");
                                    reader.next();
                                }
                            } while (N < 0);

                            M = -1;
                            System.out.println("Por favor ingrese el ancho deseado del tablero \nValor de M: ");
                            do {
                                try {
                                    M = reader.nextInt();
                                } catch (InputMismatchException e) {
                                    System.out.println("Por favor ingrese un numero entero, no otro tipo de dato\nValor de M: ");
                                    reader.next();
                                }
                            } while (M < 0);

                            NumAst = -1;
                            System.out.println("Por favor ingrese el numero de Asteroides deseados  \nNumero de Asteroides: ");
                            do {
                                try {
                                    NumAst = reader.nextInt();
                                } catch (InputMismatchException e) {
                                    System.out.println("Por favor ingrese un numero entero, no otro tipo de dato\nNumero de Asteroides: ");
                                    reader.next();
                                }
                            } while (NumAst < 0);

                            NumPart = -1;
                            System.out.println("Por favor ingrese la canridad maxima de particiones deseadas para un Asteroide, (Seleccione una cantidad de 1 a 7) \nValor de Particiones: ");
                            do {
                                try {
                                    NumPart = reader.nextInt();
                                } catch (InputMismatchException e) {
                                    System.out.println("Por favor ingrese un numero entero, no otro tipo de dato\nValor de Numero de particiones: ");
                                    reader.next();
                                }
                            } while (NumPart < 0 || NumPart >7);

                            fireRange = -1;
                            System.out.println("Por favor ingrese el Rango de Disparo deseado \nValor de Rango de Disparo:");
                            do {
                                try {
                                    fireRange = reader.nextInt();
                                } catch (InputMismatchException e) {
                                    System.out.println("Por favor ingrese un numero entero, no otro tipo de dato\nValor del Rango del Disparo: ");
                                    reader.next();
                                }
                            } while (fireRange < 0);

                            seed = -1;
                            System.out.println("Por favor ingrese la Seed \nValor de Seed:");
                            do {
                                try {
                                    seed = reader.nextInt();
                                } catch (InputMismatchException e) {
                                    System.out.println("Por favor ingrese un numero entero, no otro tipo de dato\nValor de Seed:");
                                    reader.next();
                                }
                            } while (seed < 0);

                            if(N*M*0.3 < NumAst*Math.pow(GameSpace.RadioMaximoAsteroide,2)*GameSpace.PI){
                                System.out.println("La cantidad de Asteroides solicitada no permite crear una partida jugable.");
                            }

                        }while(N*M*0.3 < NumAst*Math.pow(GameSpace.RadioMaximoAsteroide,2)*GameSpace.PI);

                        // Se crea un Space y luego espacio valido con createSpace.
                        Space espacio = new Space(N,M,NumAst,NumPart,fireRange,seed);
                        espacio.createSpace(N,M,NumAst,NumPart,fireRange,seed);
                        //Verificar si crea un Space correctamente.
                        String stringSpace = espacio.textyfy();
                        espacio.printify(stringSpace);

                        break;

                    //GAMELOOP
                    case 2:
                        GameSpace.gameLoop();
                        break;
                    case 3:
                        salir = true;
                        break;

                    default:
                        System.out.println("Ingresar una opcion valida");
                }
            } catch (InputMismatchException e) {
                System.out.println("Debes insertar un número");
                sn.next();
            }
        }
    }
}
