package project6.Pegs;
import javafx.scene.paint.Color;

public class RedPeg extends Peg {
   public RedPeg(){
        color= Color.RED;
        pegText='R';
        pegName="Red";
    }
    public Peg copy() {
        RedPeg copy=new RedPeg();
        return copy;
    }
}
