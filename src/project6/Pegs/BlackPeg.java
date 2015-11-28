package project6.Pegs;

import javafx.scene.paint.Color;
public class BlackPeg extends Peg{
    public BlackPeg(){
        color= Color.BLACK;
        pegName="Black";
    }
    @Override
    public Peg copy() {
        return null;
    }
}
