package project6.Pegs;

import javafx.scene.paint.Color;

public abstract class Peg {
	Color color;
/*	int x;
	int y;
	int index;
	Color color;
	char pegText;
	String pegName;*/

	public boolean equals(Peg p2){ //same color, does this method look at the dynamic type?
		if(this.getPegName().equals(p2.getPegName())){
			return true;
		}
		return false;
	}
	public abstract int hashCode();
	public abstract String getPegName();
	public abstract char getPegText();
	public abstract Peg copy();



}
