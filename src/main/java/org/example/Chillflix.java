package org.example;

import javax.swing.*;

public class Chillflix extends JFrame{
    private JPanel Chillflix;
    private JComboBox comboBoxStreambar;
    private JButton speichernButton;
    private JButton ausgebenButton;
    private JTextField tf_titel;
    private JTextField tf_dauer;
    private JComboBox combo_Genre;
    private JTextField textField4;
    private JCheckBox check_Titel;
    private JCheckBox check_Genre;
    private JCheckBox check_Dauer;
    private JCheckBox check_Berwertung;
    private JCheckBox check_Regesseur;
    private JCheckBox check_Streambar;
    private JComboBox combo_Filtern;
    private JButton filternButton;
    private JComboBox combo_FSK;
    private JCheckBox check_FSK;
    private JRadioButton bt_herz;
    private JRadioButton bt_1Stern;
    private JRadioButton bt_2Stern;
    private JRadioButton bt_3Stern;
    private JTextArea textArea1;

    public Chillflix(){

        setTitle("Chillflix");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 650);
        setContentPane(Chillflix);
        setVisible(true);
        setResizable(false);
        ButtonGroup gruppierung = new ButtonGroup();
        gruppierung.add(bt_1Stern);
        gruppierung.add(bt_2Stern);
        gruppierung.add(bt_3Stern);

    }

    public static void main(String[] args) {
        new Chillflix();
    }

}
