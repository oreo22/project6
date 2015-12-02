package project6.Pegs;

import javafx.scene.paint.Color;

public abstract class Peg {
	int x;
	int y;
	int index;
	Color color;
	char pegText;
	public String pegName;

	public boolean equals(Peg p2){ //same color, does this method look at the dynamic type?
		if(this.pegName.equals(p2.pegName)){
			return true;
		}
		return false;
	}
	public int hashCode(){
		return this.pegName.hashCode();
	}
	public abstract Peg copy();

}
