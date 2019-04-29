/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Axel FJ
 */
public class Field {
    private int columns;
    private int rows;// lo quitamos ya que nos parecía innecesario si la matriz es cuadrada en todos los casos.
    private final Cell[][] playfield;
    
    public Field(int columns)
    {
        if (columns < 5)
        {
            System.out.println("We don't recommend matrix under 5x5");
        }
        this.columns = columns;
        this.rows = columns;
        this.playfield = new Cell[rows][columns];
        for (int i = 0; i < rows; i++)
        {
            for (int j = 0; j < columns; j++)
            {
                playfield[i][j] = new Cell();
            }
        }
    }
    
    @Override
    public String toString()
    {
        String result = new String(); //crea un string result
        for (int i = 0; i  < rows; i++)
            {
                result += "\n";
                for (int j = 0; j < columns; j++)
                {
                    if (playfield[i][j].isPassable() == true)
                    {
                        result+= " - ";
                    }
                    else
                    {
                        if (playfield[i][j].getArmy().getName() == "Arquero")
                        {
                            result+=" A ";
                        }
                        if (playfield[i][j].getArmy().getName() == "Caballeria")
                        {
                            result+=" C ";
                        }
                        if (playfield[i][j].getArmy().getName() == "Lancero")
                        {
                            result+= " L ";
                        }
                    }

                }
            }
        return result;
    }

    public int getRows()
    {
        return this.rows;
    }

    public void setRows(int rows)
    {
        this.rows = rows;
    }

    public void setColums(int columns)
    {
        this.columns = columns;
    }

    public int getColums()
    {
        return this.columns;
    }

    public Cell[][] getPlayfield()
    {
        return this.playfield;
    }


    public Cell getCell(int i, int j){
        try {
            return this.playfield[i][j];
        } catch (Exception e){
            System.out.println(e);
            return null;
        }
    }
}
