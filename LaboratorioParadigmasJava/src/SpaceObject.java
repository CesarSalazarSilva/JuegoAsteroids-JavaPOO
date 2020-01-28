import static java.lang.Math.toRadians;

public class SpaceObject {
    /* Atributos */
    private double posX;
    private double posY;
    private double velocidad;
    private double angulo;
    private double recorrido;
    private double hitbox;
    private double seed;
    private double vida;


    /* Metodo que mueve un elemento espacial a un t+1. */
    public void moveElement(double angulo, double velocidad) {
        if (velocidad > 0) {
            this.velocidad = this.velocidad + velocidad;
            this.angulo = this.angulo + angulo;
            double angulogradian = toRadians(this.angulo);
            this.posX = (int) Math.round(this.posX + this.velocidad * Math.cos(angulogradian));
            this.posY = (int) Math.round(this.posY + this.velocidad * Math.sin(angulogradian));
            this.recorrido = (int) (this.recorrido - this.velocidad);
        }
        else{
            this.angulo = this.angulo + angulo;
            double angulogradian = toRadians(this.angulo);
            this.posX = (int) Math.round(this.posX + this.velocidad * Math.cos(angulogradian));
            this.posY = (int) Math.round(this.posY + this.velocidad * Math.sin(angulogradian));
            this.recorrido = (int) (this.recorrido - this.velocidad);
        }
    }



    //Getters and Setters.
    public double getPosX() {
        return posX;
    }

    public void setPosX(double posX) {
        this.posX = posX;
    }

    public double getPosY() {
        return posY;
    }

    public void setPosY(double posY) {
        this.posY = posY;
    }

    public double getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(double velocidad) {
        this.velocidad = velocidad;
    }

    public double getAngulo() {
        return angulo;
    }

    public void setAngulo(double angulo) {
        this.angulo = angulo;
    }

    public double getRecorrido() {
        return recorrido;
    }

    public void setRecorrido(double recorrido) {
        this.recorrido = recorrido;
    }

    public double getHitbox() {
        return hitbox;
    }

    public void setHitbox(double hitbox) {
        this.hitbox = hitbox;
    }

    public double getSeed() {
        return seed;
    }

    public void setSeed(double seed) {
        this.seed = seed;
    }

    public double getVida() {
        return vida;
    }

    public void setVida(double vida) {
        this.vida = vida;
    }
}
