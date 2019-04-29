/**
 * Created by andre on 04/03/2017.
 */
public class Lancero extends Unit {
    private static int Cost = 3;

    public Lancero (){
        super(getIdCount(), 'L', "Lancero", 20, 20, 10, 10, 1, 1);
    }

    public static int getCost(){
        return Cost;
    }
}
