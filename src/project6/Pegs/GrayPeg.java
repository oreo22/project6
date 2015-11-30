package project6.Pegs;

import javafx.scene.paint.Color;

public class GrayPeg extends Peg{
	public GrayPeg(){
        color= Color.GRAY;
        pegText='G';
        pegName="Gray";
    }


    @Override
    public Peg copy() {
        GrayPeg copy=new GrayPeg();
        return copy;
    }
}
