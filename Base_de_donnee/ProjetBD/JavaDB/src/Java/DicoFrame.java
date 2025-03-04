package Java;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings({"serial","rawtypes","unchecked"})
public class DicoFrame extends JFrame implements ActionListener {
    private JPanel top, bottom;
    private JComboBox<String> box; // Ajout du type générique pour JComboBox
    private final String[] legend = { "Anglais" };
    private JButton ok;
    private JButton clear;

    private JTextField MotTraduit;
    private JTextField traduction;

    public DicoFrame(String arg0) {
        super(arg0);
        setResizable(false);
        Container pane = getContentPane();

        pane.setLayout(new GridLayout(2, 0)); // créer une grille de 2 lignes égales pour top et btm

        top = new JPanel(); // créer le panneau top
        top.setBackground(new Color(0xFF9933)); // change couleur

        bottom = new JPanel(); // créer le panneau btm
        bottom.setBackground(new Color(0xFFB266)); // change couleur

        pane.add(top); // ajoute réellement
        pane.add(bottom); // ajoute réellement
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(600, 400);
    }

    public void completeTop() {
        top.setBackground(new Color(0xFF9933));
        top.setLayout(new FlowLayout(FlowLayout.LEADING, 80, 20)); // place l'élément

        top.add(new JLabel("Mot à traduire"));

        MotTraduit = new JTextField("Mot à traduire"); // écrit dans l'endroit ou écrire + créer la zone de texte pour
                                                        // écrire
        MotTraduit.setColumns(20);
        top.add(MotTraduit);
    }

    public void completeBottom() {
        bottom.setLayout(new FlowLayout(FlowLayout.LEADING, 80, 40));

        box = new JComboBox<>(legend); // créer une boîte combiné avec type générique
        bottom.add(box);

        ok = new JButton("TRADUIRE"); // créer un bouton ok
        bottom.add(ok);

        clear = new JButton("EFFACER"); // créer un bouton clear
        bottom.add(clear);

        ok.addActionListener(this); // ok contrôlé par this
        clear.addActionListener(this); // clear contrôlé par this

        bottom.setLayout(new FlowLayout(FlowLayout.LEADING, 80, 20)); // place l'élément
        traduction = new JTextField("Traduction"); // écrit dans l'endroit ou écrire + créer la zone de texte pour écrire
        traduction.setColumns(20);
        traduction.setEditable(false);
        bottom.add(traduction);
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        String event = evt.getActionCommand(); // label de l'évènement
        int index = box.getSelectedIndex();
        if (event.equals("TRADUIRE")) { // Modification de "OK" à "TRADUIRE"
            // String input1 = MotTraduit.getText();// récupère choix utilisateur

            if (legend[index].equals("Anglais")) {

            }
        }
    }

    private static void createAndShowGUI() {
        // Create and set up the window.
        DicoFrame frame = new DicoFrame("DicoFrame");// nom fenêtre
        frame.completeTop();
        frame.completeBottom();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Display the window.
        frame.pack();
        frame.setVisible(true);// rendre visible
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
