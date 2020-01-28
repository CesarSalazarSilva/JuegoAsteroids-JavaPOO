import java.util.InputMismatchException;
import java.util.Scanner;

public class GameSpace {
    public static double PI = 3.14159265358979323846;
    public static int RadioMaximoAsteroide = 2;
    public static int velocidadInicialNave = 1;
    public static int hitboxDisparo = 1;
    public static int RadioNave = 1;
    public static int aumento = 4;
    public static int modulo = 9;
    public static int constante = 1;

    public static void gameLoop(){

        //Menu GameLoop
        Scanner sn = new Scanner(System.in);
        boolean salirloop = false;
        System.out.println("Bienvenido a Asteroids, empezaremos creando un nuevo espacio de juego");
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
            System.out.println("Por favor ingrese la canridad maxima de particiones deseadas para un Asteroide, (Valor debe comprender entre 0 y 7 ) \nValor de Particiones: ");
            do {
                try {
                    NumPart = reader.nextInt();
                } catch (InputMismatchException e) {
                    System.out.println("Por favor ingrese un numero entero, no otro tipo de dato\nValor de Numero de particiones: ");
                    reader.next();
                }
            } while (NumPart < 0 || NumPart > 7);

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
                System.out.println("La cantidad de Asteroides solicitada no permite crear una partida jugable, la cantidad de Asteroides supera el 1/3 del tablero.");
            }

        }while(N*M*0.3 < NumAst*Math.pow(GameSpace.RadioMaximoAsteroide,2)*GameSpace.PI);

        // Se crea un Space y luego espacio valido con createSpace.
        Space espacio = new Space(N,M,NumAst,NumPart,fireRange,seed);
        espacio.createSpace(N,M,NumAst,NumPart,fireRange,seed);

        //Verificar si crea un Space correctamente.
        //System.out.println("NAVE: px");
        //System.out.println("Px " +espacio.getNave().getPosX()+"Py "+espacio.getNave().getPosY());
        //for(int m=0; m < espacio.getAsteroidList().size(); m++){
        //    System.out.println("AstPx " +espacio.getAsteroidList().get(m).getPosX()+"AstPy "+espacio.getAsteroidList().get(m).getPosY());
        //}

        int opcionloop;
        while (!salirloop) {
            System.out.println("- Menu Interaccion -");

            System.out.println("1. Opcion 1 : Mover Nave");
            System.out.println("2. Opcion 2 : Disparar");
            System.out.println("3. Salir");
            try {
                System.out.println("Selecciona una opcion");
                opcionloop = sn.nextInt();

                switch (opcionloop) {
                    case 1:
                        System.out.println("Por favor ingrese el angulo de alteracion para la nave \nValor de Angulo:");
                        double angulo = -1;
                        Scanner reader = new Scanner(System.in);
                        do {
                            try {
                                angulo = reader.nextDouble();
                            } catch (InputMismatchException e) {
                                System.out.println("Por favor ingrese un angulo (numero), no otro tipo de dato\nValor de Angulo: ");
                                reader.next();
                            }
                        } while (angulo < 0);

                        System.out.println("Por favor ingrese la Velocidad a anadir a la nave \nValor de Velocidad: ");
                        double velocidad = -1;
                        do {
                            try {
                                velocidad = reader.nextDouble();
                            } catch (InputMismatchException e) {
                                System.out.println("Por favor ingrese Velocidad (numero) no otro tipo de dato\nValor de Velocidad: ");
                                reader.next();
                            }
                        } while (velocidad < 0);

                        SpaceShip naveModificar = espacio.getNave();
                        naveModificar.moveElement(angulo,velocidad);
                        espacio.setNave( naveModificar );
                        //Mostrar px y py

                        espacio.movimientoSimpleAll();
                        //Se verifica la colision entre Disparos y Asteroides
                        espacio.colDispAst();
                        //Se verifica la colision entre Nave y asteroides Finales
                        espacio.colNavAsts();

                        String string = espacio.textyfy();
                        espacio.printify(string);

                        if(espacio.getAsteroidList().size() == 0){
                            System.out.println("Ganaste!!!!!!");
                        }
                        if(espacio.getNave().getVida() == 0){
                            System.out.println("Perdiste!!!!!");
                        }

                        break;

                    case 2:
                        espacio.shootElement();
                        espacio.movimientoSimpleAll();

                        String stringShoot = espacio.textyfy();
                        espacio.printify(stringShoot);
                        if(espacio.getAsteroidList().size() == 0){
                            System.out.println("Ganaste!!!!!!");
                        }
                        if(espacio.getNave().getVida() == 0){
                            System.out.println("Perdiste!!!!!");
                        }

                        break;
                    case 3:
                        salirloop = true;
                        break;

                    default:
                        System.out.println("Solo números entre 1 y 3");
                }
            } catch (InputMismatchException e) {
                System.out.println("Debes insertar un número");
                sn.next();
            }
        }

    }



    public static int random(int numero){
        return (((numero* aumento) + constante )% modulo);
    }


    public static int randomEsp(int numero,int aumento,int constante,int modulo){
        return (((numero* aumento) + constante )% modulo);
    }





}
