import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Graphics;

public class Panneau extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4417658616519959720L;
	
	private int posX = -50;
	private int posY = -50;
	private int drawSize = 50;
	private int number = 0;
	
	private String form = "ROND";

	private boolean reduce = false;
	private boolean morph = false;
	
	private Color backgroundColor = Color.WHITE;
	private Color foregroundColor = Color.RED;
	
	public void paintComponent(Graphics g) {
		//System.out.println(this.getWidth() + " " + this.getHeight());
		g.setColor(this.backgroundColor);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		g.setColor(this.foregroundColor);
		if(this.morph)
			drawMorph(g);
		else
			draw(g);
	 }
	 
	private void draw(Graphics g) {
		switch(this.form){
			case "ROND":
				g.fillOval(posX, posY, 50, 50);
				break;
			case "CARRE":
				g.fillRect(posX, posY, 50, 50);				
				break;			
			case "TRIANGLE":
				int[] xPoints = {posX+50/2, posX+50, posX};
				int[] yPoints = {posY+50, posY, posY};
				g.fillPolygon(xPoints, yPoints, 3);						
				break;	
			default:					
				int[] xEtoile = {posX+50/2, posX+50, posX, posX+50, posX};
				int[] yEtoile = {posY, posY+50, posY+50/2, posY+50/2, posY+50};
				g.drawPolygon(xEtoile, yEtoile, 5);
		}
	}
	
	private void drawMorph(Graphics g) {
		this.number++;
		
		if(this.drawSize >= 50)
			reduce = true;
		if(this.drawSize <= 10)
			reduce = false;
			
		if(reduce)
			this.drawSize -= this.getUsedSize();
		else
			this.drawSize += this.getUsedSize();
			
		switch(this.form){
			case "ROND":
				g.fillOval(posX, posY, this.drawSize, this.drawSize);
				break;
			case "CARRE":
				g.fillRect(posX, posY, this.drawSize, this.drawSize);				
				break;			
			case "TRIANGLE":
				int[] xPoints = {posX + this.drawSize/2, posX + this.drawSize, posX};
				int[] yPoints = {posY + this.drawSize, posY, posY};
				g.fillPolygon(xPoints, yPoints, 3);						
				break;	
			default:					
				int[] xEtoile = {posX + this.drawSize/2, posX + this.drawSize, posX, posX + this.drawSize, posX};
				int[] yEtoile = {posY, posY + this.drawSize, posY + this.drawSize/2, posY + this.drawSize/2, posY + this.drawSize};
				g.drawPolygon(xEtoile, yEtoile, 5);
		}			
	}
	
	private int getUsedSize()  {
		int resultat = 0;
		if(this.number == 10){
			number = 0;
			resultat = 1;
		}
		return resultat;
	}
	
	public void setPosX(int posX) {
	 	this.posX = posX;
	}
	
	public void setPosY(int posY) {
		this.posY = posY;
	}
	
	public void setDrawSize(int drawSize) {
		this.drawSize = drawSize;
	}
	
	public void setNumber(int number) {
		this.number = number;
	}
	
	public void  setForm(String form) {
		this.form = form;
	}
	
	public void setReduce(boolean reduce) {
		this.reduce = reduce;
	}
	
	public void setMorph(boolean morph) {
		this.morph = morph;
		this.drawSize = 50;
	}
	
	public void setBackgroundColor(Color color){
		this.backgroundColor = color;
	}

	public void setForegroundColor(Color color){
		this.foregroundColor = color;
	}
	
	public int getPosX() {
		return this.posX;
	} 
	
	public int getPosY() {
		return this.posY;
	}
	
	public int getDrawSize() {
		return this.drawSize;
	}
	
	public int getNumber() {
		return this.number;
	}
	
	public String getForm() {
		return this.form;
	}
	
	public boolean getReduce() {
		return this.reduce;
	}
	
	public boolean getMorph() {
		return this.morph;
	}
	
	public Color getBackgroundColor(){
		return this.backgroundColor;
	}
	
	public Color getForegroundColor(){
		return this.foregroundColor;
	}
}
