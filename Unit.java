/**
 * Created by andre on 03/21/2017.
 */
public abstract class Unit {

    private int id;
    private char type;
    private String name;
    private int maxHitPoints;
    private int hitPoints;
    private int attack;
    private int defense;
    private int range;
    private int level;
    private int experience;
    private int movement;
    private int posX;
    private int posY;
    private static int idCount = 0;

    /**
     * Creador de una unidad; sea cual esta sea
     * @param id
     * @param type
     * @param name
     * @param maxHitPoints
     * @param hitPoints
     * @param attack
     * @param defense
     * @param range
     * @param movement
     */
    public Unit(int id, char type, String name, int maxHitPoints, int hitPoints, int attack, int defense, int range, int movement) {
        this.setId(id);
        this.setType(type);
        this.setName(name);
        this.setMaxHitPoints(maxHitPoints);
        this.setHitPoints(hitPoints);
        this.setAttack(attack);
        this.setDefense(defense);
        this.setRange(range);
        this.setMovement(movement);
        this.setIdCount(this.getIdCount() + 1);
        this.setLevel(1);
        this.setExperience(0);
    }

    /**
     * Recibe un contrincante y returna un valor dependiendo de si esta en rango o no
     * @param Contrincante
     * @return
     */
    protected boolean inRange(Unit Contrincante){
        int distx = this.posX - Contrincante.getPosX();
        int disty = this.posY - Contrincante.getPosY();
        if ((-this.range < distx && distx < this.range) || (-this.range < disty && disty < this.range)  ){
            return true;
        }
        return false;
    }

    public static int getIdCount() {
        return idCount;
    }

    private static void setIdCount(int idCount) {
        Unit.idCount = idCount;
    }

    /**
     * Funcion que recibe una pieza y la ataca
     * @param defensor
     */
    public void attacking(Unit defensor){
        if (this.inRange(defensor)){
            if(defensor.getDefense() <= this.getAttack()) {
                defensor.setHitPoints(this.getAttack() - defensor.getDefense());
                if (defensor.isDead()) {
                    this.levelUp();
                }

            } else {
                System.out.println("Ataque no efectivo");
            }
            System.out.println(defensor.getHitPoints());
            return;
        }
        System.out.println("No estaba en rango, ataque perdido");
    }

    //incompleto
    /**
     * Funcion que recibe dos puntos y mueve la pieza; si se puede mover a la ubicacion indicada
     * @param posX
     * @param posY
     */
    public boolean setLocation(int posX, int posY,Field playfield){
        if (posX == 1024 || posY == 1024){
            return false;
        } if(playfield.getCell(posX, posY).isPassable()) {
            playfield.getCell(this.posX, this.posY).setArmy(null);
            playfield.getCell(posX,posY).setArmy(this);

            return true;
        }
        return false;

    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public char getType() {
        return type;
    }

    public void setType(char type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }

    public int getMaxHitPoints() {
        return maxHitPoints;
    }

    private void setMaxHitPoints(int maxHitPoints) {
        this.maxHitPoints = maxHitPoints;
    }

    public int getHitPoints() {
        return hitPoints;
    }

    public void setHitPoints(int hitPoints) {
        this.hitPoints = hitPoints;
    }

    public int getAttack() {
        return attack;
    }

    private void setAttack(int attack) {
        this.attack = attack;
    }

    public int getDefense() {
        return defense;
    }

    private void setDefense(int defense) {
        this.defense = defense;
    }

    public int getRange() {
        return range;
    }

    private void setRange(int range) {
        this.range = range;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public int getMovement() {
        return movement;
    }

    private void setMovement(int movement) {
        this.movement = movement;
    }

    public int getPosX() {
        return posX;
    }

    protected void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    protected void setPosY(int posY)
    {
        this.posY = posY;
    }

    /**
     * Funcion que retorna true si la pieza esta muerta
     * @return
     */
    public boolean isDead(){
        if(this.getHitPoints() ==0){
            return true;
        }
        return false;
    }

    /**
     * Funcion que sube de nivel una unidad
     * Se da cuando la experiencia es mayor a su nivel por 2
     * Esta aumenta las habilidades de la unidad por 25%
     */
    protected void levelUp(){
        this.experience ++;
        if(this.experience >= this.level * 2) {
            this.experience = 0;
            this.level++;
            this.attack *= 1.25;
            this.defense *= 1.25;
            this.range *= 1.25;
            this.hitPoints *= 1.25;
            this.maxHitPoints *= 1.25;
            this.movement *= 1.25;
        }
    }
}
