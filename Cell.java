/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Axel FJ
 */
public class Cell {
    private boolean passable;
    private Unit army;


    public Cell()
    {
        this.army = null;
        this.passable = true;
    }
    
    public void cambioEstado(Unit soldado)
    {
        this.passable = !passable;
        this.army = soldado;
    }
    
    /**
     * Metodo que imprime la info de la celda (si es franqueable o no, e imprime que unidad tiene)
     * @return 
     */
    @Override
    public String toString(){
        String resultado = new String();
        resultado+= "Es franqueable: " + passable + "\n";
        resultado+= "Unidad en la celda: " +  army.getName() + "\n";
        return resultado;
    }

    public boolean isPassable() {
        return passable;
    }

    public void setPassable(boolean passable) {
        this.passable = passable;
    }

    public Unit getArmy() {
        return army;
    }

    public void setArmy(Unit army) {
        this.cambioEstado(army);
    }
}
