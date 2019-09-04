Como compilar y ejecutar el programa : 
//Se asume que el usuario tiene previamente instalado Java 8, tanto como su JDK y JRE.

1.- Se accede por consola a la carpeta src del proyecto y se ejecuta javac.exe

//Referente a la ruta de javac.exe de no tener configurada la variable de entorno:
//Usualmente es : (UnidadDisco):\Program Files\Java\jdk1.8.0_211\bin
"Ruta de ...\Javac.exe" Main.java Menu.java GameSpace.java Space.java SpaceObject.java Shoot.java SpaceShip.java Asteroid.java  

2.- En el mismo directorio de ejecuta: 
java Main

El programa está creado para suportar un cantidad de asteroides equivalente al 30% del Area total de juego, El radio inicial de estos es de 2 unidades.

