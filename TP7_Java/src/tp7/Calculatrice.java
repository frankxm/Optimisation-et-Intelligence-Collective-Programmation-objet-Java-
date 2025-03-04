package tp7;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
@SuppressWarnings({"serial","rawtypes","unchecked"})
public class Calculatrice  extends JFrame implements ActionListener {
	private JPanel top, bottom;
	private JComboBox box;
	private final String[] legend = { "Addition","Soustraction", "Multiplication", "Division"};
	private JButton ok;
	private JButton clear;
	private JTextField text1;
	private JTextField text2;
	private JTextField result_txt;
	
	public Calculatrice(String arg0) {
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
    	first.add(new JLabel("entrez premier operande ici:"));
    	text1=new JTextField("0");
    	text1.setColumns(20);
    	first.add(text1);
    	JPanel second=new JPanel();
    	second.setBackground(new Color(0xFF9933));
    	second.setLayout(new FlowLayout(FlowLayout.LEADING,40,20));
    	second.add(new JLabel("entrez seconde operande ici:"));
    	text2=new JTextField("0");
    	text2.setColumns(20);
    	second.add(text2);
    	top.add(first);
    	top.add(second);
    }
	public void completeBottom(){
    	bottom.setLayout(new FlowLayout(FlowLayout.LEADING,80,40));
    	box = new JComboBox(legend);
    	bottom.add(box);
    	ok=new JButton("OK");
    	bottom.add(ok);
    	clear = new JButton("CLEAR");
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
		if (event.equals("OK")){
			String input=text1.getText();
			String input2=text2.getText();
			try {			 
				double operand1=Double.parseDouble(input);
				double operand2=Double.parseDouble(input2);
				if (legend[index].equals("Addition")){
					result_txt.setText(String.valueOf(operand1+operand2));					
				}
				if (legend[index].equals("Soustraction")){
					result_txt.setText(String.valueOf(operand1-operand2));
					
				}
				if (legend[index].equals("Multiplication")){
					result_txt.setText(String.valueOf(operand1*operand2));
					
				}
				if (legend[index].equals("Division")){
					result_txt.setText(String.valueOf(operand1/operand2));
					
				}
	 
	        } catch (NumberFormatException e) {
	            System.err.println("impossible de convertir :" + input +" et "+input2+ " au type double");
	        }

			
		}
		if (event.equals("CLEAR")){

			result_txt.setText("");
		}
	}
    private static void createAndShowGUI() {
        //Create and set up the window.
    	Calculatrice frame = new Calculatrice("Calculatrice");
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