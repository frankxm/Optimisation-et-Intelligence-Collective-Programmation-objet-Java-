package tp7;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.*;
@SuppressWarnings({"serial","rawtypes","unchecked"})

public class Traduction  extends JFrame implements ActionListener {
	private JPanel top, bottom;
	private JComboBox box;
	private final String[] legend = { "Anglais","Francais"};
	private JButton ok;
	private JButton clear;
	private JTextField text1;
	private JTextField text2;
	private JTextField result_txt;
	private Dico langue;
	private MenuBar bar;
	private Menu fileMenu;
	private MenuItem openItem,saveItem;

	
	public Traduction(String arg0) {
	super(arg0);
    setResizable(false);
    Container pane=getContentPane();
    pane.setLayout(new GridLayout(2,0));
    top=new JPanel();
    top.setBackground(new Color(0xFF9933));
    bottom=new JPanel();
    bottom.setBackground(new Color(0xFFB266));
    pane.add(top);
    pane.add(bottom);
    langue=new Dico("data/dico.txt");
    bar = new MenuBar();
	fileMenu = new Menu("File");
	openItem = new MenuItem("Add");
	saveItem = new MenuItem("Export");
	fileMenu.add(openItem);
	fileMenu.add(saveItem);
	bar.add(fileMenu);
	this.setMenuBar(bar);

	openItem.addActionListener(this);
	saveItem.addActionListener(this);

}
	  @Override
	  public Dimension getPreferredSize() {
	    return new Dimension(600, 400);
	  }
    public void completeTop(){
    	top.setLayout(new GridLayout(0,2));
    	JPanel first=new JPanel();
    	first.setBackground(new Color(0xFF9933));
    	first.setLayout(new FlowLayout(FlowLayout.LEADING,40,20));
    	first.add(new JLabel("entrez premier mot ici:"));
    	text1=new JTextField("");
    	text1.setColumns(20);
    	first.add(text1);
    	JPanel second=new JPanel();
    	second.setBackground(new Color(0xFF9933));
    	second.setLayout(new FlowLayout(FlowLayout.LEADING,40,20));
    	second.add(new JLabel("entrez seconde mot ici:"));
    	text2=new JTextField("");
    	text2.setColumns(20);
    	second.add(text2);
    	top.add(first);
    	top.add(second);
    }
	public void completeBottom(){
    	bottom.setLayout(new FlowLayout(FlowLayout.LEADING,80,40));
    	box = new JComboBox(legend);
    	bottom.add(box);
    	ok=new JButton("Traduire");
    	bottom.add(ok);
    	clear = new JButton("Effacer");
    	bottom.add(clear);
    	result_txt=new JTextField("");
    	result_txt.setColumns(20);
    	bottom.add(result_txt);
    	ok.addActionListener(this);
    	clear.addActionListener(this);
    }
	@Override
	public void actionPerformed(ActionEvent evt) {
		String event=evt.getActionCommand();
		int index=box.getSelectedIndex();
		if (event.equals("Traduire")){
			String input=text1.getText();
			String input2=text2.getText();
			if(input.equals("")||input2.equals("")) {
				result_txt.setText("entrez des deux mots");
			}
			else {
				String mot1="",mot2="";
				if(legend[index].equals("Anglais")) {
					mot1=langue.find(input);
					mot2=langue.find(input2);
				}
				else if(legend[index].equals("Francais")) {
					mot1=langue.find_inverse(input);
					mot2=langue.find_inverse(input2);
				}
				result_txt.setText(mot1+" , "+mot2);

				
			}

			
		}
		else if (event.equals("Effacer")){

			result_txt.setText("");
		}
		
		else if(event.equals("Add")) {
			String input=text1.getText();
			String input2=text2.getText();
			String s="data/dico.txt";
			langue=new Dico(s);
			langue.add(input, input2);
		}
		else if(event.equals("Export")) {
			String input=text1.getText();
			String input2=text2.getText();
			String s="data/dico.txt";
			langue.add(input,input2);
			langue.save(s);
		}
		



	}


    private static void createAndShowGUI() {
        //Create and set up the window.
    	Traduction frame = new Traduction("Traduction de francais");
    	frame.completeTop();
    	frame.completeBottom();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }
    // use a new task for GUI creation
	public static void main(String[] args) {
	       javax.swing.SwingUtilities.invokeLater(new Runnable() {
	            public void run() {
	                createAndShowGUI();
	            }
	        });

	}

}
