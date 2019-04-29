/**
 * Created by andre on 04/03/2017.
 */
public class Caballeria extends Unit{
    private static int Cost = 5;

    public Caballeria(){
        super(getIdCount(), 'C', "Caballeria",25, 25, 15, 5, 1,3);

    }

    public static int getCost(){
        return Cost;
    }
}
