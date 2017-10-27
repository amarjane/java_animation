import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;


public class Bouton extends JButton implements MouseListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6516084140439472953L;
	private String name;
	private Image img;
	
	public Bouton(String str) {
		super(str);
		this.name = str;
		this.addMouseListener(this);
		try {
			img = ImageIO.read(new File("src/396528.png"));	
		} catch (IOException e) {
			e.printStackTrace();	
		}
		this.setPreferredSize(new Dimension(150, 120));
	}
	
	public void paintComponent(Graphics g) {		
		/*
		Graphics2D g2d = (Graphics2D)g;
		GradientPaint gp = new GradientPaint(0, 0, Color.BLUE, 0, 20, Color.CYAN, true);
		g2d.setPaint(gp);
		g2d.fillRect(0, 0, this.getWidth(), this.getHeight());
		g2d.setColor(Color.WHITE);
		g2d.drawString(this.name, this.getWidth()/2 - (this.getWidth()/ 2 /4), (this.getHeight() / 2) + 5);
		*/
				
		g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
		g.drawString(this.name, this.getWidth()/2 - (this.getWidth()/ 2 /4), (this.getHeight() / 2) + 5);		
	}
	
	public void mouseClicked(MouseEvent event) { }
	
	public void mouseEntered(MouseEvent event) { 
		try {
			img = ImageIO.read(new File("src/105976.png"));	
		} catch (IOException e) {
			e.printStackTrace();	
		}
	}
	
	
	public void mouseExited(MouseEvent event) { 
		if(event.getX() > 0 && event.getX() < this.getWidth() && event.getY() > 0 && event.getY() > this.getHeight()) {
			try {
				img = ImageIO.read(new File("src/105974.png"));	
			} catch (IOException e) {
				e.printStackTrace();	
			}
		} else {
			try {
				img = ImageIO.read(new File("src/396528.png"));	
			} catch (IOException e) {
				e.printStackTrace();	
			}		
		}
	}
	
	public void mousePressed(MouseEvent event) {
		try {
			img = ImageIO.read(new File("src/105975.png"));	
		} catch (IOException e) {
			e.printStackTrace();	
		}
	}
	
	public void mouseReleased(MouseEvent event) { 
		try {
			img = ImageIO.read(new File("src/105976.png"));	
		} catch (IOException e) {
			e.printStackTrace();	
		}
	}
	
}

