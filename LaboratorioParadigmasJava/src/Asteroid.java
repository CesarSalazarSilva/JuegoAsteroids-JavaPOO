import static java.lang.Math.toRadians;

public class Asteroid extends SpaceObject{
    private double numeroParticiones;

    //Constructor Asteroide.
    public Asteroid(double posX, double posY, double velocidad, double angulo, double hitbox, double seed,double numeroParticiones) {
        super(posX, posY, velocidad, angulo, 0, hitbox, seed);
        this.numeroParticiones = numeroParticiones;
    }

    //Getters and Setters
    public double getNumeroParticiones() {
        return numeroParticiones;
    }

    public void setNumeroParticiones(double numeroParticiones) {
        this.numeroParticiones = numeroParticiones;
    }
}
