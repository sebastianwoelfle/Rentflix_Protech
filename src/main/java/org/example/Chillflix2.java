package org.example;

import javax.swing.*;

public class Chillflix2 extends JFrame{
    private JPanel Chillflix;
    private JTextField tf_titel;
    private JComboBox combo_genre;
    private JComboBox comboBox1;
    private JCheckBox check_titel;
    private JCheckBox check_genre;
    private JCheckBox check_fsk;
    private JTextField tf_dauer;
    private JCheckBox check_dauer;
    private JRadioButton rb_1stern;
    private JRadioButton rb_2stern;
    private JRadioButton rb_3stern;
    private JCheckBox check_bewertug;
    private JTextField tf_regesseur;
    private JCheckBox check_regeseur;
    private JComboBox combo_streambar;
    private JCheckBox check_streamabr;
    private JComboBox comboBox2;
    private JCheckBox check_filtern;
    private JButton b_herz;
    private JButton b_speichern;
    private JButton b_ausgeben;
    private JTextArea textArea1;

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
    }

    public static void main(String[] args) {
        new Chillflix2();
    }
}
