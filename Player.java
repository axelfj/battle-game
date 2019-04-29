import java.util.Scanner;
/**
 * Created by andre on 03/21/2017.
 */
public class Player {
    private final int maxCost;
    private Unit[] army;
    private final String name;
    private int score;
    private int pieces;
    private Field playfield;
    private int numeroJugador;
    private static int num = 0;
    private int alive;
    /**
     * Constructor del jugador
     * Por default se le asigna un score de 0
     * @param name
     */
    public Player(String name, Field playfield){
        this(name, 0, playfield);
    }

    /**
     * Constructor sobreescrito con la finalidad de poder agregarle el valor score deseado y no el default
     * @param name
     * @param score
     */

    public Player(String name, int score, Field playfield){
         this.name = name;
         this.maxCost = playfield.getColums() * 3; // numero de monedas que tiene para comprar unidades
         this.score=  score;
         this.pieces = 0;
         this.army = new Unit[playfield.getColums()];
         this.setPlayfield(playfield);
         this.numeroJugador = num;
         num++;
    }


    public int getmaxCost() {
        return this.maxCost;
    }

    public Unit[] getArmy() {
        return army;
    }

    /**
     * Funcion que recibe tres enteros y crea un ejercito  con Arqueros, Caballerias y Lanceros.
     * @param Arqueros
     * @param Caballerias
     * @param Lanceros
     */
    public boolean createArmy(int Arqueros, int Caballerias, int Lanceros){
        int totalCost = Arquero.getCost()*Arqueros + Caballeria.getCost() * Caballerias + Lancero.getCost() * Lanceros;
        if (Arqueros < 0 || Caballerias < 0 || Lanceros < 0){
            return false;
        }
        if (totalCost <= this.getMaxCost()){
            this.setAlive(Arqueros + Caballerias + Lanceros);
            for(int i = 0; i < Arqueros; i++){

                this.army[pieces] = new Arquero();
                this.pieces++;

            }
            for(int j = 0;  j < Lanceros; j++){
                this.army[pieces] = new Lancero();
                this.pieces++;
            }
            for (int k = 0; k < Caballerias; k++){
                this.army[pieces] = new Caballeria();
                this.pieces++;
            }
            this.colocar();
            return true;
        }
        return false;
    }

    /**
     * Funcion que coloca cada pieza en el tablero
     * Coloca las piezas de forma que el espacio en tre ellas sea el mismo
     */
    private void colocar(){
        int corrimiento = this.playfield.getColums()/ this.pieces;
        int j = 0;
        Unit u;
        for (int i = 0; i < this.alive; i++)
        {
            int posX = this.numeroJugador * this.getPlayfield().getColums();
            if(this.numeroJugador >0){
                posX--;
            }
            Cell celula = this.playfield.getCell(posX, i*corrimiento);
            u = this.army[i];
            celula.setArmy(u);
            u.setPosX(posX);
            u.setPosY(i*corrimiento);
        }
    }

    public boolean attack(int i, int j,Unit atacante){
        Cell casilla = this.getPlayfield().getCell(i,j);
        if(!casilla.isPassable()){
            Unit defensor = casilla.getArmy();
            atacante.attacking(defensor);
            System.out.println("hello");
            return true;
        }
        return false;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getMaxCost() {
        return maxCost;
    }

    public Field getPlayfield() {
        return playfield;
    }

    public void setPlayfield(Field playfield) {
        this.playfield = playfield;
    }

    private void verificarMuertos(){
        int x;
        int y;
        for(int i = 0; i < alive; i++) {
            if (this.getArmy()[i].isDead()) {
                x = this.getArmy()[i].getPosX();
                y = this.getArmy()[i].getPosY();
                System.out.println(x + " "+ y);
                this.getPlayfield().getCell(x, y).cambioEstado(null);
                this.army[i] = this.army[i + 1];
                alive--;
            }
        }
    }
    public boolean lost(){
        if (this.alive == 0 ){
            return true;
        }
        return false;
    }

    /**
     * Funcion que contiene la logica del juego
     * Recorre cada unidad del jugador y pregunta si la quiere mover o atacar
     */

    public void play(){
        this.verificarMuertos();
        System.out.println("-----------------------" + '\n' + this.getName() + '\n');
        Unit unidad;

        int x;
        int y;

        int wantToAttack;
        int wantToMove;
        System.out.println(this.playfield.toString());

        for (int i = 0; i < alive; i++)
        {
            unidad = this.getArmy()[i];
            System.out.println(unidad.getName() + " (" + unidad.getId() + "):" );

            Scanner reader = new Scanner(System.in);
            System.out.print("Move? (1 = y; 0 = n)");
            wantToMove = reader.nextInt();


            if (wantToMove == 1){
                x = 1024;
                y = 1024;
                while (!unidad.setLocation(x, y, this.playfield)) {
                    reader = new Scanner(System.in);
                    System.out.print("X: ");
                    x = reader.nextInt();

                    reader = new Scanner(System.in);
                    System.out.print("Y: ");
                    y = reader.nextInt();
                }
            }


            reader = new Scanner(System.in);
            System.out.print("Attack? (1  = y; 0 = n)");
            wantToAttack = reader.nextInt();

            if(wantToAttack == 1){
                reader = new Scanner(System.in);
                System.out.print("X: ");
                x = reader.nextInt();

                reader = new Scanner(System.in);
                System.out.print("Y: ");
                y = reader.nextInt();

               this.attack(x, y, unidad);
            }

        }

    }

    public int getAlive() {
        return alive;
    }

    public void setAlive(int alive) {
        this.alive = alive;
    }

    @Override
    public String toString() {
        return "Player{" + "maxCost=" + maxCost + ", army=" + army + ", name=" + name + ", score=" + score + ", pieces=" + pieces + ", playfield=" + playfield + ", numeroJugador=" + numeroJugador + ", alive=" + alive + '}';
    }
    
}
