import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.AbstractAction;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;

public class Fenetre extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private Panneau pan = new Panneau();
	
	private JPanel jp = new JPanel();
	
	private ButtonGroup group = new ButtonGroup();
	
	private JLabel jl = new JLabel();	
	
	private JMenuBar menuBar = new JMenuBar();
	
	private JMenu animer = new JMenu("Animation");
	private JMenu forme = new JMenu("Forme");	
	private JMenu aPropos = new JMenu("À propos");	
	private JMenu typeDeForme = new JMenu("Type de forme");
	
	private JMenuItem lancer = new JMenuItem("Lancer");
	private JMenuItem interrompre = new JMenuItem("Interrompre");
	private JMenuItem quitter = new JMenuItem("Quitter");
	private JMenuItem question = new JMenuItem("?");

	private JCheckBox morphing = new JCheckBox("Morphing");
	
	private JRadioButtonMenuItem rond = new JRadioButtonMenuItem("Rond");
	private JRadioButtonMenuItem carre = new JRadioButtonMenuItem("Carre");
	private JRadioButtonMenuItem triangle = new JRadioButtonMenuItem("Triangle");
	private JRadioButtonMenuItem etoile = new JRadioButtonMenuItem("Etoile");
	
	private boolean anim = true;
	private boolean retourX, retourY;
	
	private int numClick = 0;
	private int x, y;
	
	private Thread t;
	
	
	private JPopupMenu menuPopup = new JPopupMenu("Menu Contextuel"); 
	
	private JMenu backgroundPopup = new JMenu("Background Color");
	private JMenu foregroundPopup = new JMenu("Foreground Color");
	
	private JMenuItem lancerPopup = new JMenuItem("Lancer");
	private JMenuItem interromprePopup = new JMenuItem("Interrompre");

	private JRadioButtonMenuItem redBack = new JRadioButtonMenuItem("RED");
	private JRadioButtonMenuItem blueBack = new JRadioButtonMenuItem("BLUE");
	private JRadioButtonMenuItem yellowBack = new JRadioButtonMenuItem("YELLOW");
	private JRadioButtonMenuItem greenBack = new JRadioButtonMenuItem("GREEN");
	private JRadioButtonMenuItem blackBack = new JRadioButtonMenuItem("BLACK");
	private JRadioButtonMenuItem whiteBack = new JRadioButtonMenuItem("WHITE");
	
	private ButtonGroup groupBack = new ButtonGroup();
	
	private JRadioButtonMenuItem redFore = new JRadioButtonMenuItem("RED");
	private JRadioButtonMenuItem blueFore = new JRadioButtonMenuItem("BLUE");
	private JRadioButtonMenuItem yellowFore = new JRadioButtonMenuItem("YELLOW");
	private JRadioButtonMenuItem greenFore = new JRadioButtonMenuItem("GREEN");
	private JRadioButtonMenuItem blackFore = new JRadioButtonMenuItem("BLACK");
	private JRadioButtonMenuItem whiteFore = new JRadioButtonMenuItem("WHITE");
	
	private ButtonGroup groupFore = new ButtonGroup();
	
	private JToolBar toolBar = new JToolBar();
		
	private JButton lancerTool = new JButton(new ImageIcon("src/399319.jpg"));
	private JButton interrompreTool = new JButton(new ImageIcon("src/399321.jpg"));	
	
	/*
	 * private JButton rondTool = new JButton(new RondAction("ROND", new ImageIcon("src/399320.jpg")));
	 * private JButton carreTool = new JButton(new CarreAction("CARRE", new ImageIcon("src/399317.jpg")));
	 * private JButton triangleTool = new JButton(new TriangleAction("TRIANGLE", new ImageIcon("src/399322.jpg")))
	 * private JButton etoileTool = new JButton(new EtoileAction("ETOILE", new ImageIcon("src/399318.jpg")));
	 */
		
	private RondAction rondAction =  new RondAction("ROND", new ImageIcon("src/399320.jpg"));
	private CarreAction carreAction =  new CarreAction("CARRE", new ImageIcon("src/399317.jpg"));
	private TriangleAction triangleAction =  new TriangleAction("CARRE", new ImageIcon("src/399322.jpg"));
	private EtoileAction etoileAction =  new EtoileAction("CARRE", new ImageIcon("src/399318.jpg"));
	
	public Fenetre() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(400, 400);
		this.setLocationRelativeTo(null);
		this.setTitle("Animation");
		
		// action toolbar
		
		this.lancerTool.addActionListener(new ActionLancer());
		this.interrompreTool.addActionListener(new ActionInterrompre());
		
		// enabled toolbar
		
		this.lancerTool.setEnabled(false);
		this.interrompreTool.setEnabled(true);
			
		// button toolbar
		
		this.toolBar.add(this.lancerTool);
		this.toolBar.add(this.interrompreTool);

		this.toolBar.add(this.rondAction);
		this.toolBar.add(this.carreAction);
		this.toolBar.add(this.triangleAction);
		this.toolBar.add(this.etoileAction);
		
		// enabled contextuel
		
		this.lancerPopup.setEnabled(false);
		this.interromprePopup.setEnabled(true);
		
		// action contextuel
	
		this.jp.addMouseListener(new ActionMouse());
		
		this.interromprePopup.addActionListener(new ActionInterrompre());
		this.lancerPopup.addActionListener(new ActionLancer());
		
		this.redBack.addActionListener(new ActionBackground());
		this.blueBack.addActionListener(new ActionBackground());
		this.yellowBack.addActionListener(new ActionBackground());
		this.greenBack.addActionListener(new ActionBackground());
		this.blackBack.addActionListener(new ActionBackground());
		this.whiteBack.addActionListener(new ActionBackground());	
		
		this.redFore.addActionListener(new ActionForeground());
		this.blueFore.addActionListener(new ActionForeground());
		this.yellowFore.addActionListener(new ActionForeground());
		this.greenFore.addActionListener(new ActionForeground());
		this.blackFore.addActionListener(new ActionForeground());
		this.whiteFore.addActionListener(new ActionForeground());
		
		// menu contextuel
		
		this.backgroundPopup.add(this.redBack);		
		this.backgroundPopup.add(this.blueBack);				
		this.backgroundPopup.add(this.yellowBack);		
		this.backgroundPopup.add(this.greenBack);				
		this.backgroundPopup.add(this.blackBack);
		this.backgroundPopup.add(this.whiteBack);
		
		this.foregroundPopup.add(this.redFore);		
		this.foregroundPopup.add(this.blueFore);				
		this.foregroundPopup.add(this.yellowFore);		
		this.foregroundPopup.add(this.greenFore);				
		this.foregroundPopup.add(this.blackFore);
		this.foregroundPopup.add(this.whiteFore);
		
		this.groupBack.add(this.redBack);		
		this.groupBack.add(this.blueBack);				
		this.groupBack.add(this.yellowBack);		
		this.groupBack.add(this.greenBack);				
		this.groupBack.add(this.blackBack);	
		this.groupBack.add(this.whiteBack);
		
		this.groupFore.add(this.redFore);		
		this.groupFore.add(this.blueFore);				
		this.groupFore.add(this.yellowFore);		
		this.groupFore.add(this.greenFore);				
		this.groupFore.add(this.blackFore);	
		this.groupFore.add(this.whiteFore);
		
		this.menuPopup.add(this.lancerPopup);
		this.menuPopup.add(this.interromprePopup);		
		this.menuPopup.add(this.backgroundPopup);
		this.menuPopup.add(this.foregroundPopup);	
		
		this.jp.add(this.menuPopup);
		
		// raccourcis
		
		this.animer.setMnemonic('a');
		this.forme.setMnemonic('f');
		this.aPropos.setMnemonic('p');
		this.lancer.setAccelerator(KeyStroke.getKeyStroke('l'));
		this.interrompre.setAccelerator(KeyStroke.getKeyStroke('i'));
		this.rond.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, KeyEvent.CTRL_MASK));		
		this.carre.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, KeyEvent.CTRL_DOWN_MASK + KeyEvent.SHIFT_DOWN_MASK));		

		// action
		
		this.lancer.addActionListener(new ActionLancer());
		 	
		this.interrompre.addActionListener(new ActionInterrompre());
		this.interrompre.addActionListener(new ActionCompter());
		
		this.quitter.addActionListener(new ActionQuitter());
		
		this.rond.addActionListener(new ActionForm());			
		this.carre.addActionListener(new ActionForm());		
		this.triangle.addActionListener(new ActionForm());		
		this.etoile.addActionListener(new ActionForm());		
		
		this.morphing.addActionListener(new ActionMorphing());
		
		// enabled
		
		this.lancer.setEnabled(false);
		this.interrompre.setEnabled(true);		
		
		// label
		
		Font f = new Font("Arial", Font.BOLD, 12);
		
		this.jl.setFont(f);
		this.jl.setForeground(Color.BLUE);
		this.jl.setText("Compteur");
		this.jl.setHorizontalAlignment(JLabel.CENTER);
		
		// menus
		
		this.menuBar.add(this.animer);
		this.menuBar.add(this.forme);
		this.menuBar.add(this.aPropos);
		this.menuBar.add(this.jl);
		
		this.animer.add(this.lancer);	
		this.animer.add(this.interrompre);
		this.animer.add(this.quitter);

		this.typeDeForme.add(this.rond);
		this.typeDeForme.add(this.carre);		
		this.typeDeForme.add(this.triangle);		
		this.typeDeForme.add(this.etoile);
		
		this.rond.setSelected(true);
		
		this.group.add(this.rond);
		this.group.add(this.carre);		
		this.group.add(this.triangle);		
		this.group.add(this.etoile);
		
		this.forme.add(this.typeDeForme);
		this.forme.add(this.morphing);
		
		this.aPropos.add(this.question);
		
		this.setJMenuBar(this.menuBar);
		
		// pan
		
		this.jp.setLayout(new BorderLayout());
		this.jp.add(this.pan, BorderLayout.CENTER);
		
		// jp toolbar
		
		this.jp.add(this.toolBar, BorderLayout.NORTH);
		
		this.setContentPane(this.jp);
		
		
		// animer
		
		animation();		
	}

	private void animation(){
		retourX = false;
		retourY = false;
		x = pan.getPosX();
		y = pan.getPosY();
		while(this.anim) {
			if(this.pan.getMorph()) {
				if(x > pan.getWidth() - pan.getDrawSize()) 
					retourX = true;			
				if(y > pan.getHeight() - pan.getDrawSize())
					retourY = true;			
				if(x == 0)
					retourX = false;
				if(y == 0)
					retourY = false;
				if(retourX)
					x--;
				if(retourY)
					y--;
				if(!retourX)
					x++;			
				if(!retourY)
					y++;		
			}	
					
			else {		
				if(x > pan.getWidth()-50) 
					retourX = true;
				if(y > pan.getHeight() - 50)
					retourY = true;			
				if(x == 0)
					retourX = false;			
				if(y == 0)
					retourY = false;		
				if(retourX)
					x--;		
				if(retourY)
					y--;		
				if(!retourX)
					x++;			
				if(!retourY)
					y++;			
			}
			
			pan.setPosX(x);
			pan.setPosY(y);
			pan.repaint();  
			
			try {
				Thread.sleep(5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
    }  
	
	// class Run
	
	class AnimationRunnable implements Runnable {
		public void run() {
			animation();		
		}	
	}
	
	// class action
	
	class ActionLancer implements ActionListener {
	   	public void actionPerformed(ActionEvent event) {
			numClick++;
			jl.setText(numClick + " clics !");
			
			int answer = JOptionPane.showConfirmDialog(null, 
					"Do you want to go ?", 
					"Information", 
					JOptionPane.YES_NO_OPTION,
					JOptionPane.QUESTION_MESSAGE);
			
			if(answer == 0){
				lancer.setEnabled(false);
				interrompre.setEnabled(true);
				
				lancerPopup.setEnabled(false);
				interromprePopup.setEnabled(true);
				
				lancerTool.setEnabled(false);
				interrompreTool.setEnabled(true);
				
				anim = true;			   	
			   	t = new Thread(new AnimationRunnable());
		   		t.start();				
			}
	   	}
	}

	class ActionInterrompre implements ActionListener {
	   	public void actionPerformed(ActionEvent event) {

			int answer = JOptionPane.showConfirmDialog(null, 
					"Do you want to stop ?", 
					"Information", 
					JOptionPane.YES_NO_OPTION,
					JOptionPane.QUESTION_MESSAGE);
			
			if(answer == 0){
			   	lancer.setEnabled(true);
			   	interrompre.setEnabled(false);
			   	
			   	lancerPopup.setEnabled(true);
			   	interromprePopup.setEnabled(false);
			   	
			   	lancerTool.setEnabled(true);
			   	interrompreTool.setEnabled(false);
			   	
			   	anim = false;			
			}
	   	}		
	}
	
	class ActionCompter implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			numClick++;
			jl.setText(numClick + "clics !");		
		}	
	}

	class ActionForm implements ActionListener {
		public void actionPerformed(ActionEvent e){
			// pan.setForm(getForm());
			pan.setForm(((JRadioButtonMenuItem)e.getSource()).getText().toUpperCase());
		}
	}
	
	class RondAction extends AbstractAction {		

		private static final long serialVersionUID = -726083620671691308L;

		public RondAction(String name) {
			super(name);			
		}
		
		public RondAction(String name, ImageIcon img){
			super(name, img);
		}
			
		@Override
		public void actionPerformed(ActionEvent e) {
			pan.setForm("ROND");
		}	
	}
	
	class CarreAction extends  AbstractAction {

		private static final long serialVersionUID = -2625100422802581843L;
		
		public CarreAction(String name) {
			super(name);			
		}
		
		public CarreAction(String name, ImageIcon img){
			super(name, img);
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			pan.setForm("CARRE");	
		}	
	}
	
	class TriangleAction extends  AbstractAction {

		private static final long serialVersionUID = 8255188307278422423L;
		
		public TriangleAction(String name) {
			super(name);			
		}
		
		public TriangleAction(String name, ImageIcon img){
			super(name, img);
			
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			pan.setForm("TRIANGLE");
		}
	}
	
	class EtoileAction extends  AbstractAction {

		private static final long serialVersionUID = 516640873423097188L;
		public EtoileAction(String name) {
			super(name);			
		}
		
		public EtoileAction(String name, ImageIcon img){
			super(name, img);
			
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			pan.setForm("ETOILE");
		}	
	}
	
	class ActionMorphing implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if(((JCheckBox) e.getSource()).isSelected())
				pan.setMorph(true);
			else
				pan.setMorph(false);		
		}	
	}
	
	class ActionQuitter implements ActionListener {
		public void actionPerformed (ActionEvent e){
			System.exit(0);			
		}
	}

	class ActionBackground implements ActionListener {
		public void actionPerformed(ActionEvent e){
			pan.setBackgroundColor(getBackgroundItem());		
		}		
	}
	
	class ActionForeground implements ActionListener {
		public void actionPerformed(ActionEvent e){
			pan.setForegroundColor(getForegroundItem());
		}		
	}
	
	class ActionMouse implements MouseListener{
		@Override
		public void mousePressed(MouseEvent e) {
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			if(!menuPopup.isVisible() && e.getButton() == MouseEvent.BUTTON3){
				menuPopup.show(jp, e.getX(), e.getY());
				menuPopup.setVisible(true);
			} else
				menuPopup.setVisible(false);
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			if(!menuPopup.isVisible() && e.getButton() == MouseEvent.BUTTON3){
				menuPopup.show(jp, e.getX(), e.getY());
				menuPopup.setVisible(true);
			} else
				menuPopup.setVisible(false);			
		}
	}
	
	// getters	
	
	public String getForm(){
		return 	(this.rond.isSelected()) ? "ROND" :
				(this.carre.isSelected()) ? "CARRE" :
				(this.triangle.isSelected()) ? "TRIANGLE" :
				"ETOILE";
	}
	
	public Color getBackgroundItem(){
		return 	(this.redBack.isSelected()) ? Color.RED :
				(this.blueBack.isSelected()) ? Color.BLUE :
				(this.yellowBack.isSelected()) ? Color.YELLOW :
				(this.greenBack.isSelected()) ? Color.GREEN :
				(this.blackBack.isSelected()) ? Color.BLACK :
				Color.WHITE;
	}
	
	public Color getForegroundItem(){
		return 	(this.whiteFore.isSelected()) ? Color.WHITE :
				(this.blueFore.isSelected()) ? Color.BLUE :
				(this.yellowFore.isSelected()) ? Color.YELLOW :
				(this.greenFore.isSelected()) ? Color.GREEN :
				(this.blackFore.isSelected()) ? Color.BLACK :
				Color.RED;
	}

	
}

