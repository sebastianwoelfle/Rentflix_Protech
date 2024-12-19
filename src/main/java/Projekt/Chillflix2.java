package Projekt;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Chillflix2 extends JFrame{
    private JPanel Chillflix;
    private JTextField tf_titel;
    private JComboBox combo_genre;
    private JComboBox combo_fsk;
    private JTextField tf_dauer;
    private JRadioButton rb_1stern;
    private JRadioButton rb_2stern;
    private JRadioButton rb_3stern;
    private JTextField tf_regesseur;
    private JComboBox combo_streambar;
    private JComboBox combo_filmtyp;
    private JButton b_herz;
    private JButton b_filtern;
    private JButton b_ausgeben;
    private JTextArea textArea1;

    private ArrayList<Film> filmbib = new ArrayList<>();

    public Chillflix2(){

        setTitle("Chillflix");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 800);
        setContentPane(Chillflix);
        setVisible(true);
        setResizable(false);
        ButtonGroup gruppierung = new ButtonGroup();
        gruppierung.add(rb_1stern);
        gruppierung.add(rb_2stern);
        gruppierung.add(rb_3stern);


        b_filtern.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //save();
            }
        });
        b_ausgeben.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //ausgeben();
            }
        });
    }
    public void initObjekte() {

        String titel = tf_titel.getText();
        String dauer = tf_dauer.getText();
        String streambar = combo_streambar.getSelectedItem().toString();
        String genre = combo_genre.getSelectedItem().toString();
        String filtern = combo_filmtyp.getSelectedItem().toString();
        String fsk = combo_fsk.getSelectedItem().toString();
        String regisseur = tf_regesseur.getText();

        boolean bewertung1;
        if(rb_1stern.isSelected()) {
            bewertung1 = true;
        } else {
            bewertung1 = false;
        }
        boolean berwertung2;
        if (rb_2stern.isSelected()){
            berwertung2 = true;
        }else{
            berwertung2 = false;
        }
        boolean bewertung3;
        if (rb_3stern.isSelected()){
            bewertung3 = true;
        }else{
            bewertung3 = false;
        }

    }

    public static void main(String[] args) {
        new Chillflix2();
    }
}
