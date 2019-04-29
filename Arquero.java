/**
 * Created by andre on 04/03/2017.
 */
public class Arquero extends Unit {
    private static int Cost = 4;


    public Arquero (){
        super(getIdCount(), 'A', "Arquero", 15, 15, 10, 5, 3, 1);
    }

    public static int getCost(){
        return Cost;
    }

}
