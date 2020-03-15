import static java.lang.Math.toRadians;

public class Shoot extends SpaceObject {

    //Constructor Disparo
    public Shoot(double posX, double posY, double velocidad, double angulo, double recorrido, double hitbox, double seed) {
        super(posX, posY, velocidad, angulo, recorrido, hitbox, seed);
    }
}
