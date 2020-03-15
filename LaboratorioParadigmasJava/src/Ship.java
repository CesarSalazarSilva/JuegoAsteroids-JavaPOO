import static java.lang.Math.toRadians;

public class Ship extends SpaceObject {
    private double vida;

    //Constructor Ship
    public Ship(double posX, double posY, double velocidad, double angulo, double hitbox, double seed, double vida) {
        super(posX, posY, velocidad, angulo, recorrido, hitbox, seed);
        this.vida = vida;
    }

    public double getVida() {
        return vida;
    }

    public void setVida(double vida) {
        this.vida = vida;
    }
}
