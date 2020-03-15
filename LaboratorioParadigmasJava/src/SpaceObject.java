import static java.lang.Math.toRadians;

public class SpaceObject {
    //Atributos de Movimiento.
    protected double posX;
    protected double posY;
    protected double velocidad;
    protected double angulo;
    protected double recorrido;
    //Atributos Caracteristicos.
    protected double hitbox;
    //Atributos Generales.
    protected double seed;

    public void SpaceObject(double posX, double posY, double velocidad, double angulo, double recorrido, double hitbox, double seed){
        this.posX = posX;
        this.posY = posY;
        this.velocidad = velocidad;
        this.angulo = angulo;
        this.recorrido = recorrido;
        this.hitbox = hitbox;
        this.seed = seed;
    }

    //Metodo que mueve un elemento espacial a un t+1. (m refiere a x, n refiere a y)
    public void moveElement(double angulo, double velocidad, double m, double n ) {
        if (velocidad > 0){
            this.velocidad += velocidad;
            this.angulo += angulo;
            double angulogradian = ToRadians(this.angulo);
            this.posX = (this.posX + this.velocidad * Math.Cos(angulogradian)) % m;
            this.posY = (this.posY + this.velocidad * Math.Sin(angulogradian)) % n;
            this.recorrido -= this.velocidad*0.01;
            if (this.posX <0){
                this.posX = m;
            }
            if(this.posY < 0){
               this.posY = m; 
            }
        }else{
            this.angulo += angulo;
            double angulogradian = ToRadians(this.angulo);            
            this.posX = (this.posX + this.velocidad * Math.Cos(angulogradian)) % m;
            this.posY = (this.posY + this.velocidad * Math.Sin(angulogradian)) % n;
            this.recorrido -= this.velocidad*0.01;
            if (this.posX < 0){
                this.posX = m;
            }
            if (this.posY < 0){
                this.posY = m;   
            }
        }
    }

    private double ToRadians(double angulo)
    {
        return (Math.PI / 180) * angulo;
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
