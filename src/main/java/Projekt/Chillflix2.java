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

    private ArrayList<String> filmbib = new ArrayList<String>();

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

        setupDynamicFocusTraversal();


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
    private void save() {
        // Speichern der Daten aus den Eingabefeldern und Comboboxen
        String titel = tf_titel.getText();
        String dauer = tf_dauer.getText();
        String streambar = combo_streambar.getSelectedItem().toString();
        String genre = combo_genre.getSelectedItem().toString();
        String filtern = combo_filmtyp.getSelectedItem().toString();
        String fsk = combo_fsk.getSelectedItem().toString();
        String regisseur = tf_regesseur.getText();

        String bewertung;
        if (rb_1stern.isSelected()) {
            bewertung = "1 Stern";
        } else if (rb_2stern.isSelected()) {
            bewertung = "2 Sterne";
        } else if (rb_3stern.isSelected()) {
            bewertung = "3 Sterne";
        } else {
            bewertung = "Keine Bewertung";
        }

        // Erstellen eines Format-Strings und Hinzufügen zur Liste
        String filmDetails = String.format(
                "Titel: %s, Genre: %s, Dauer: %s, FSK: %s, Regisseur: %s, Streambar: %s, Typ: %s, Bewertung: %s",
                titel, genre, dauer, fsk, regisseur, streambar, filtern, bewertung
        );
        filmbib.add(filmDetails);

        // Benutzerinformation
        JOptionPane.showMessageDialog(this, "Film erfolgreich gespeichert!", "Information", JOptionPane.INFORMATION_MESSAGE);
    }

    private void ausgeben() {
        // TextArea zurücksetzen
        textArea1.setText("");

        // Alle gespeicherten Filme ausgeben
        for (String film : filmbib) {
            textArea1.append(film + "\n");
        }

        if (filmbib.isEmpty()) {
            textArea1.setText("Keine Filme gespeichert.");
        }
    }

    private void setupDynamicFocusTraversal() {
        // Liste aller relevanten Komponenten in der gewünschten Reihenfolge
        java.util.List<JComponent> components = new java.util.ArrayList<>();
        components.add(tf_titel);
        components.add(combo_genre);
        components.add(combo_fsk);
        components.add(tf_dauer);
        components.add(rb_1stern);
        components.add(rb_2stern);
        components.add(rb_3stern);
        components.add(tf_regesseur);
        components.add(combo_streambar);
        components.add(combo_filmtyp);

        // Komponenten einen KeyListener hinzufügen
        for (int i = 0; i < components.size(); i++) {
            JComponent current = components.get(i);
            int nextIndex = (i + 1) % components.size(); // Zyklisch zum ersten zurückkehren
            JComponent next = components.get(nextIndex);

            current.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyPressed(java.awt.event.KeyEvent evt) {
                    if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
                        next.requestFocus();
                    }
                }
            });
        }
    }

    /*public void initObjekte() {
//Eingabefelder definieren

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

     */

    public static void main(String[] args) {
        new Chillflix2();
    }
}
