import java.util.ArrayList;

public class Space {
    //Atributos de un Space
    private int n;
    private int m;
    private int numAsteroides;
    private int numPart;
    private int firerange;
    private int seed;

    private ArrayList<Asteroid> asteroidList;
    private ArrayList<Shoot> shootList;
    private Ship nave;

    //Constructor de un Space
    public Space(int n, int m, int numAsteroides, int numPart, int firerange, int seed) {
        this.n = n;
        this.m = m;
        this.numAsteroides = numAsteroides;
        this.numPart = numPart;
        this.firerange = firerange;
        this.seed = seed;

        this.asteroidList = new ArrayList<Asteroid>();
        this.shootList = new ArrayList<Shoot>();
        this.nave = new Ship(0,0,0,0,0,0,0);
    }

    public void createSpace(int n, int m, int numAsteroides, int numPart, int firerange, int seed){
        this.n = n;
        this.m = m;
        this.numAsteroides = numAsteroides;
        this.numPart = numPart;
        this.firerange = firerange;
        this.seed = seed;

        double posX = m/2;
        double posY = n/2;

        this.nave = new Ship(posX,posY,GameSpace.velocidadInicialNave,0,GameSpace.RadioNave,seed,2);

        int i = 0;
        int valorrandom = 14;
        while( i < numAsteroides || valorrandom > 10000){
            int valorposyN = GameSpace.randomEsp(valorrandom, 8,1,n);
            int valorposxM = GameSpace.randomEsp(valorrandom, 3,3,m);

            //De chocar el asteroide con la nave no se asigna el asteroide.
            if (chocan(valorposxM, valorposyN, GameSpace.RadioMaximoAsteroide, Math.round(n / 2), Math.round(m / 2), GameSpace.RadioNave)){
                valorrandom = valorrandom + 1;// Asignamos el asteroide con la posicion valida
            }else{
                Asteroid asteroide = new Asteroid(valorposxM,valorposyN,1,GameSpace.random(valorrandom),GameSpace.RadioMaximoAsteroide,seed,numPart);
                this.asteroidList.add(asteroide);
                valorrandom = valorrandom + 1;
                i = i +1;
            }
        }

        if (valorrandom > 9900){
            System.out.println("No se ha podido crear un espacio valido con lo valores dados, favor usar otros parametros, debe crear una nueva partida (Seleccione 3 , luego 2).");
        }


    }

    //Movimiento de todos los objetos a un t+1
    public void movimientoSimpleAll(){
        this.nave.moveElement(0,0);
        this.asteroidList.stream().forEach(elem->{elem.moveElement(0,0);});
        this.shootList.stream().forEach(elem->{elem.moveElement(0,0);});
        numAsteroides = asteroidList.size();
    }

    //Agrega un disparo
    public void shootElement(){
        double posX = this.nave.getPosX();
        double posY = this.nave.getPosY();
        double firerange = this.firerange;
        double seed = this.seed;
        Shoot disparo = new Shoot(posX,posY,this.nave.getVelocidad()+1,this.nave.getAngulo(),firerange,GameSpace.hitboxDisparo,this.seed);
        this.shootList.add(disparo);
    }

    //Verifica si un elemento choca con otro.
    public boolean chocan(double Ob1Px, double Ob1Py, double Ob1Rad, double Ob2Px, double Ob2Py, double Ob2Rad){
        double suma1 = Ob1Px - Ob2Px;
        double suma2 = Ob1Py - Ob2Py;
        double pot1 = Math.pow(suma1,2);
        double pot2 = Math.pow(suma2,2);
        double res = pot1 + pot2;
        double resultado = Math.sqrt(res);
        //Logica de colision
        if(resultado < (Ob1Rad+Ob2Rad)){
            return true;
        }else{
            return false;
        }
    }

    //Textyfy
    public String textyfy(){
        //Creamos una matriz que contendra nuestro tablero y se rellena de ceros
        int matriz[][] = new int[this.m][this.n];
        for(int i=0; i<this.m; i++){
            for(int j=0; j<this.n; j++){
                matriz[i][j]=0;
            }
        }
        //Introducimos la nave
        matriz[((int)(this.nave.getPosX()))%m][((int)(this.nave.getPosY()))%n] = 5;

        //Introducimos los Asteroides
        for (int i = 0; i<this.getAsteroidList().size();i++){
            matriz[((int)(this.getAsteroidList().get(i).getPosX()))%m][((int)(this.getAsteroidList().get(i).getPosY()))%n] = (int)(this.getAsteroidList().get(i).getHitbox());
        }

        //Introducimos los Disparos
        for (int i = 0; i<this.getShootList().size();i++){
            matriz[((int)(this.getShootList().get(i).getPosX()))%m][((int)(this.getShootList().get(i).getPosY()))%m] = 3;
        }
        String spaceToString= "";
        for(int i=0; i<this.m; i++){
            for(int j=0; j<this.n; j++){
                spaceToString = spaceToString + " " + matriz[i][j] + " ";
            }
            spaceToString = spaceToString + "\n";
        }
        return spaceToString;
    }

    public void printify(String spaceToString){
        System.out.println("Simbologia Asteroids: (5) Ship ; (3) Shoot ; (Otro) Asteroide de radio indicado ;(0) Espacio Vacio");
        System.out.println("");
        System.out.println(spaceToString);
    }

    //Colision entre Nave y Asteroides
    public void colNavAsts(){
        for (int i = 0; i< this.getAsteroidList().size(); i++){
            if (chocan(this.nave.getPosX(),this.nave.getPosY(),GameSpace.RadioNave,this.getAsteroidList().get(i).getPosX(),this.getAsteroidList().get(i).getPosY(),this.getAsteroidList().get(i).getHitbox())){
                this.nave.setVida(0);
            }
        }
    }

    //Colision entre Disparos y Asteroides
    public void colDispAst(){
        //Nos preparamos a separ los disparos que chocan de los que no
        ArrayList<Shoot> dispchocan = new ArrayList<Shoot>();
        ArrayList<Shoot> dispNochocan = new ArrayList<Shoot>();
        //Separamos
        for (int i = 0; i< this.shootList.size();i++){
            for(int j= 0; j<this.asteroidList.size();j++) {
                if(this.shootList.get(i).getRecorrido()>0) {
                    if (chocan(this.shootList.get(i).getPosX(), this.shootList.get(i).getPosY(), this.shootList.get(i).getHitbox(), this.getAsteroidList().get(j).getPosX(), this.getAsteroidList().get(j).getPosY(), this.getAsteroidList().get(j).getHitbox())) {
                        dispchocan.add(this.shootList.get(i));
                    } else {
                        dispNochocan.add(this.shootList.get(i));
                    }
                }
            }
        }
        //Separamos los asteroides que chocan de los que no
        ArrayList<Asteroid> astchocan = new ArrayList<Asteroid>();
        ArrayList<Asteroid> astNochocan = new ArrayList<Asteroid>();
        for (int i = 0; i< this.asteroidList.size();i++){
            for(int j= 0; j<this.shootList.size();j++) {

                if (chocan(this.asteroidList.get(i).getPosX(), this.asteroidList.get(i).getPosY(), this.asteroidList.get(i).getHitbox(), this.shootList.get(j).getPosX(), this.shootList.get(j).getPosY(), this.shootList.get(j).getHitbox())) {
                   if((this.asteroidList.get(i).getHitbox())/2>0.8) {
                       astchocan.add(this.asteroidList.get(i));
                   }
                }else {
                    astNochocan.add(this.asteroidList.get(i));
                }
            }
        }

        //Ya que se tienen separados guardamos solo los no chocados y los que han sido chocados pasaran por fisica de Asteroides.
        for(int i= 0; i<astchocan.size();i++){
            ArrayList<Asteroid> asteroidesnuevos = fisicadeAsteroides(astchocan.get(i),GameSpace.randomEsp(seed, 8,1,numPart));
            astNochocan.addAll(asteroidesnuevos);
        }

        this.asteroidList = astNochocan;
        this.shootList = dispNochocan;

    }

    public ArrayList<Asteroid> fisicadeAsteroides(Asteroid asteroide, int seed){
        if (seed == 1){
            ArrayList<Asteroid> nuevaLista= new ArrayList<Asteroid>();

            double Newpx1 = (asteroide.getPosX())%this.m;
            double Newpy1 = (asteroide.getPosX())%this.n;
            double NewAngulo1 = asteroide.getAngulo();
            nuevaLista.add(new Asteroid(Newpx1,Newpy1,1,NewAngulo1,(asteroide.getHitbox())/2,this.seed,this.numPart));
            return nuevaLista;
        }
        if (seed == 2){
            ArrayList<Asteroid> nuevaLista= new ArrayList<Asteroid>();
            double Newpx1 = (asteroide.getPosX())%this.m;
            double Newpx2 = (asteroide.getPosX() + 2*asteroide.getHitbox())%this.m;

            double Newpy1 = (asteroide.getPosX())%this.n;
            double Newpy2 = (asteroide.getPosX() + 1.5*asteroide.getHitbox())%this.n;

            double NewAngulo1 = asteroide.getAngulo();
            double NewAngulo2 = asteroide.getAngulo() + 45;

            nuevaLista.add(new Asteroid(Newpx1,Newpy1,1,NewAngulo1,(asteroide.getHitbox())/2,this.seed,this.numPart));
            nuevaLista.add(new Asteroid(Newpx2,Newpy2,1,NewAngulo2,(asteroide.getHitbox())/2,this.seed,this.numPart));
            return nuevaLista;

        }
        if (seed == 3){
            ArrayList<Asteroid> nuevaLista= new ArrayList<Asteroid>();
            double Newpx1 = (asteroide.getPosX())%this.m;
            double Newpx2 = (asteroide.getPosX() + 2*asteroide.getHitbox())%this.m;
            double Newpx3 = (asteroide.getPosX() - 2*asteroide.getHitbox())%this.m;

            double Newpy1 = (asteroide.getPosX())%this.n;
            double Newpy2 = (asteroide.getPosX() + 1.5*asteroide.getHitbox())%this.n;
            double Newpy3 = (asteroide.getPosX() - 1.5*asteroide.getHitbox())%this.n;

            double NewAngulo1 = asteroide.getAngulo();
            double NewAngulo2 = asteroide.getAngulo() + 45;
            double NewAngulo3 = asteroide.getAngulo() + 135;

            nuevaLista.add(new Asteroid(Newpx1,Newpy1,1,NewAngulo1,(asteroide.getHitbox())/2,this.seed,this.numPart));
            nuevaLista.add(new Asteroid(Newpx2,Newpy2,1,NewAngulo2,(asteroide.getHitbox())/2,this.seed,this.numPart));
            nuevaLista.add(new Asteroid(Newpx3,Newpy3,1,NewAngulo3,(asteroide.getHitbox())/2,this.seed,this.numPart));
            return nuevaLista;

        }
        if (seed == 4){
            ArrayList<Asteroid> nuevaLista= new ArrayList<Asteroid>();
            double Newpx1 = (asteroide.getPosX())%this.m;
            double Newpx2 = (asteroide.getPosX() + 2*asteroide.getHitbox())%this.m;
            double Newpx3 = (asteroide.getPosX() - 2*asteroide.getHitbox())%this.m;
            double Newpx4 = (asteroide.getPosX() + 2*asteroide.getHitbox())%this.m;

            double Newpy1 = (asteroide.getPosX())%this.n;
            double Newpy2 = (asteroide.getPosX() + 1.5*asteroide.getHitbox())%this.n;
            double Newpy3 = (asteroide.getPosX() - 1.5*asteroide.getHitbox())%this.n;
            double Newpy4 = (asteroide.getPosX() + 1.5*asteroide.getHitbox())%this.n;


            double NewAngulo1 = asteroide.getAngulo();
            double NewAngulo2 = asteroide.getAngulo() + 45;
            double NewAngulo3 = asteroide.getAngulo() + 135;
            double NewAngulo4 = asteroide.getAngulo() + 315;

            nuevaLista.add(new Asteroid(Newpx1,Newpy1,1,NewAngulo1,(asteroide.getHitbox())/2,this.seed,this.numPart));
            nuevaLista.add(new Asteroid(Newpx2,Newpy2,1,NewAngulo2,(asteroide.getHitbox())/2,this.seed,this.numPart));
            nuevaLista.add(new Asteroid(Newpx3,Newpy3,1,NewAngulo3,(asteroide.getHitbox())/2,this.seed,this.numPart));
            nuevaLista.add(new Asteroid(Newpx4,Newpy4,1,NewAngulo4,(asteroide.getHitbox())/2,this.seed,this.numPart));
            return nuevaLista;

        }
        if (seed == 5){
            ArrayList<Asteroid> nuevaLista= new ArrayList<Asteroid>();
            double Newpx1 = (asteroide.getPosX())%this.m;
            double Newpx2 = (asteroide.getPosX() + 2*asteroide.getHitbox())%this.m;
            double Newpx3 = (asteroide.getPosX() - 2*asteroide.getHitbox())%this.m;
            double Newpx4 = (asteroide.getPosX() + 2*asteroide.getHitbox())%this.m;
            double Newpx5 = (asteroide.getPosX() - 2*asteroide.getHitbox())%this.m;

            double Newpy1 = (asteroide.getPosX())%this.n;
            double Newpy2 = (asteroide.getPosX() + 1.5*asteroide.getHitbox())%this.n;
            double Newpy3 = (asteroide.getPosX() - 1.5*asteroide.getHitbox())%this.n;
            double Newpy4 = (asteroide.getPosX() + 1.5*asteroide.getHitbox())%this.n;
            double Newpy5 = (asteroide.getPosX() - 1.5*asteroide.getHitbox())%this.n;

            double NewAngulo1 = asteroide.getAngulo();
            double NewAngulo2 = asteroide.getAngulo() + 45;
            double NewAngulo3 = asteroide.getAngulo() + 135;
            double NewAngulo4 = asteroide.getAngulo() + 315;
            double NewAngulo5 = asteroide.getAngulo() + 225;

            nuevaLista.add(new Asteroid(Newpx1,Newpy1,1,NewAngulo1,(asteroide.getHitbox())/2,this.seed,this.numPart));
            nuevaLista.add(new Asteroid(Newpx2,Newpy2,1,NewAngulo2,(asteroide.getHitbox())/2,this.seed,this.numPart));
            nuevaLista.add(new Asteroid(Newpx3,Newpy3,1,NewAngulo3,(asteroide.getHitbox())/2,this.seed,this.numPart));
            nuevaLista.add(new Asteroid(Newpx4,Newpy4,1,NewAngulo4,(asteroide.getHitbox())/2,this.seed,this.numPart));
            nuevaLista.add(new Asteroid(Newpx5,Newpy5,1,NewAngulo5,(asteroide.getHitbox())/2,this.seed,this.numPart));
            return nuevaLista;

        }
        if (seed == 6){
            ArrayList<Asteroid> nuevaLista= new ArrayList<Asteroid>();
            double Newpx1 = (asteroide.getPosX())%this.m;
            double Newpx2 = (asteroide.getPosX() + 2*asteroide.getHitbox())%this.m;
            double Newpx3 = (asteroide.getPosX() - 2*asteroide.getHitbox())%this.m;
            double Newpx4 = (asteroide.getPosX() + 2*asteroide.getHitbox())%this.m;
            double Newpx5 = (asteroide.getPosX() - 2*asteroide.getHitbox())%this.m;
            double Newpx6 = (asteroide.getPosX())%this.m;

            double Newpy1 = (asteroide.getPosX())%this.n;
            double Newpy2 = (asteroide.getPosX() + 1.5*asteroide.getHitbox())%this.n;
            double Newpy3 = (asteroide.getPosX() - 1.5*asteroide.getHitbox())%this.n;
            double Newpy4 = (asteroide.getPosX() + 1.5*asteroide.getHitbox())%this.n;
            double Newpy5 = (asteroide.getPosX() - 1.5*asteroide.getHitbox())%this.n;
            double Newpy6 = (asteroide.getPosX() + 2.5*asteroide.getHitbox())%this.n;

            double NewAngulo1 = asteroide.getAngulo();
            double NewAngulo2 = asteroide.getAngulo() + 45;
            double NewAngulo3 = asteroide.getAngulo() + 135;
            double NewAngulo4 = asteroide.getAngulo() + 315;
            double NewAngulo5 = asteroide.getAngulo() + 225;
            double NewAngulo6 = asteroide.getAngulo() + 90;

            nuevaLista.add(new Asteroid(Newpx1,Newpy1,1,NewAngulo1,(asteroide.getHitbox())/2,this.seed,this.numPart));
            nuevaLista.add(new Asteroid(Newpx2,Newpy2,1,NewAngulo2,(asteroide.getHitbox())/2,this.seed,this.numPart));
            nuevaLista.add(new Asteroid(Newpx3,Newpy3,1,NewAngulo3,(asteroide.getHitbox())/2,this.seed,this.numPart));
            nuevaLista.add(new Asteroid(Newpx4,Newpy4,1,NewAngulo4,(asteroide.getHitbox())/2,this.seed,this.numPart));
            nuevaLista.add(new Asteroid(Newpx5,Newpy5,1,NewAngulo5,(asteroide.getHitbox())/2,this.seed,this.numPart));
            nuevaLista.add(new Asteroid(Newpx6,Newpy6,1,NewAngulo6,(asteroide.getHitbox())/2,this.seed,this.numPart));
            return nuevaLista;
        }

        if (seed == 7){
            ArrayList<Asteroid> nuevaLista= new ArrayList<Asteroid>();
            double Newpx1 = (asteroide.getPosX())%this.m;
            double Newpx2 = (asteroide.getPosX() + 2*asteroide.getHitbox())%this.m;
            double Newpx3 = (asteroide.getPosX() - 2*asteroide.getHitbox())%this.m;
            double Newpx4 = (asteroide.getPosX() + 2*asteroide.getHitbox())%this.m;
            double Newpx5 = (asteroide.getPosX() - 2*asteroide.getHitbox())%this.m;
            double Newpx6 = (asteroide.getPosX())%this.m;
            double Newpx7 = (asteroide.getPosX())%this.m;

            double Newpy1 = (asteroide.getPosX())%this.n;
            double Newpy2 = (asteroide.getPosX() + 1.5*asteroide.getHitbox())%this.n;
            double Newpy3 = (asteroide.getPosX() - 1.5*asteroide.getHitbox())%this.n;
            double Newpy4 = (asteroide.getPosX() + 1.5*asteroide.getHitbox())%this.n;
            double Newpy5 = (asteroide.getPosX() - 1.5*asteroide.getHitbox())%this.n;
            double Newpy6 = (asteroide.getPosX() + 2.5*asteroide.getHitbox())%this.n;
            double Newpy7 = (asteroide.getPosX() - 2.5*asteroide.getHitbox())%this.n;

            double NewAngulo1 = asteroide.getAngulo();
            double NewAngulo2 = asteroide.getAngulo() + 45;
            double NewAngulo3 = asteroide.getAngulo() + 135;
            double NewAngulo4 = asteroide.getAngulo() + 315;
            double NewAngulo5 = asteroide.getAngulo() + 225;
            double NewAngulo6 = asteroide.getAngulo() + 90;
            double NewAngulo7 = asteroide.getAngulo() + 270;

            nuevaLista.add(new Asteroid(Newpx1,Newpy1,1,NewAngulo1,(asteroide.getHitbox())/2,this.seed,this.numPart));
            nuevaLista.add(new Asteroid(Newpx2,Newpy2,1,NewAngulo2,(asteroide.getHitbox())/2,this.seed,this.numPart));
            nuevaLista.add(new Asteroid(Newpx3,Newpy3,1,NewAngulo3,(asteroide.getHitbox())/2,this.seed,this.numPart));
            nuevaLista.add(new Asteroid(Newpx4,Newpy4,1,NewAngulo4,(asteroide.getHitbox())/2,this.seed,this.numPart));
            nuevaLista.add(new Asteroid(Newpx5,Newpy5,1,NewAngulo5,(asteroide.getHitbox())/2,this.seed,this.numPart));
            nuevaLista.add(new Asteroid(Newpx6,Newpy6,1,NewAngulo6,(asteroide.getHitbox())/2,this.seed,this.numPart));
            nuevaLista.add(new Asteroid(Newpx7,Newpy7,1,NewAngulo7,(asteroide.getHitbox())/2,this.seed,this.numPart));
            return nuevaLista;
        }else{
            ArrayList<Asteroid> nuevaLista= new ArrayList<Asteroid>();
            double Newpx1 = (asteroide.getPosX())%this.m;
            double Newpy1 = (asteroide.getPosX())%this.n;
            double NewAngulo1 = asteroide.getAngulo();
            nuevaLista.add(new Asteroid(Newpx1,Newpy1,1,NewAngulo1,(asteroide.getHitbox())/2,this.seed,this.numPart));
            return nuevaLista;
        }


    }

    //Getters and Setters
    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    public int getM() {
        return m;
    }

    public void setM(int m) {
        this.m = m;
    }

    public int getNumAsteroides() {
        return numAsteroides;
    }

    public void setNumAsteroides(int numAsteroides) {
        this.numAsteroides = numAsteroides;
    }

    public int getNumPart() {
        return numPart;
    }

    public void setNumPart(int numPart) {
        this.numPart = numPart;
    }

    public int getFirerange() {
        return firerange;
    }

    public void setFirerange(int firerange) {
        this.firerange = firerange;
    }

    public int getSeed() {
        return seed;
    }

    public void setSeed(int seed) {
        this.seed = seed;
    }

    public ArrayList<Asteroid> getAsteroidList() {
        return asteroidList;
    }

    public void setAsteroidList(ArrayList<Asteroid> asteroidList) {
        this.asteroidList = asteroidList;
    }

    public ArrayList<Shoot> getShootList() {
        return shootList;
    }

    public void setShootList(ArrayList<Shoot> shootList) {
        this.shootList = shootList;
    }

    public Ship getNave() {
        return nave;
    }

    public void setNave(Ship nave) {
        this.nave = nave;
    }
}
