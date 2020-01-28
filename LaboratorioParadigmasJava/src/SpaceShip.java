import static java.lang.Math.toRadians;

public class SpaceShip extends SpaceObject {
    private double posX;
    private double posY;
    private double velocidad;
    private double angulo;
    private double hitbox;
    private double seed;
    private double vida;

    //Constructor Ship
    public SpaceShip(double posX, double posY, double velocidad, double angulo, double hitbox, double seed, double vida) {
        this.posX = posX;
        this.posY = posY;
        this.velocidad = velocidad;
        this.angulo = angulo;
        this.hitbox = hitbox;
        this.seed = seed;
        this.vida = vida;
    }

    public void moveElement(double angulo, double velocidad) {
        if (velocidad > 0) {
            this.velocidad = this.velocidad + velocidad;
            this.angulo = this.angulo + angulo;
            double angulogradian = toRadians(this.angulo);
            this.posX = (int) Math.round(this.posX + this.velocidad * Math.cos(angulogradian));
            this.posY = (int) Math.round(this.posY + this.velocidad * Math.sin(angulogradian));
        }
        else{
            this.angulo = this.angulo + angulo;
            double angulogradian = toRadians(this.angulo);
            this.posX = (int) Math.round(this.posX + this.velocidad * Math.cos(angulogradian));
            this.posY = (int) Math.round(this.posY + this.velocidad * Math.sin(angulogradian));
        }
    }

    //Getters and Setters
    @Override
    public double getPosX() {
        return posX;
    }

    @Override
    public void setPosX(double posX) {
        this.posX = posX;
    }

    @Override
    public double getPosY() {
        return posY;
    }

    @Override
    public void setPosY(double posY) {
        this.posY = posY;
    }

    @Override
    public double getVelocidad() {
        return velocidad;
    }

    @Override
    public void setVelocidad(double velocidad) {
        this.velocidad = velocidad;
    }

    @Override
    public double getAngulo() {
        return angulo;
    }

    @Override
    public void setAngulo(double angulo) {
        this.angulo = angulo;
    }

    @Override
    public double getHitbox() {
        return hitbox;
    }

    @Override
    public void setHitbox(double hitbox) {
        this.hitbox = hitbox;
    }

    @Override
    public double getSeed() {
        return seed;
    }

    @Override
    public void setSeed(double seed) {
        this.seed = seed;
    }

    @Override
    public double getVida() {
        return vida;
    }

    @Override
    public void setVida(double vida) {
        this.vida = vida;
    }
}
