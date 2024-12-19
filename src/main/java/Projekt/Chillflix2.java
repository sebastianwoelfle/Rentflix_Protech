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
    private void initFilmbib() {
        filmbib.add("Titel: Inception, Genre: Sci-Fi, Dauer: 148, FSK: 12, Regisseur: Christopher Nolan, Streambar: Netflix, Typ: Spielfilm, Bewertung: 3 Sterne");
        filmbib.add("Titel: Avatar, Genre: Sci-Fi, Dauer: 162, FSK: 12, Regisseur: James Cameron, Streambar: Netflix, Typ: Spielfilm, Bewertung: 2 Sterne");
        filmbib.add("Titel: Titanic, Genre: Drama, Dauer: 195, FSK: 12, Regisseur: James Cameron, Streambar: Netflix, Typ: Spielfilm, Bewertung: 3 Sterne");
        // Weitere Filme hier hinzufügen
    }

    private void filtern() {
        String titel = tf_titel.getText().toLowerCase();
        String genre = combo_genre.getSelectedItem() != null ? combo_genre.getSelectedItem().toString().toLowerCase() : "";
        String fsk = combo_fsk.getSelectedItem() != null ? combo_fsk.getSelectedItem().toString() : "";
        String dauer = tf_dauer.getText();
        String regisseur = tf_regesseur.getText().toLowerCase();
        String streambar = combo_streambar.getSelectedItem() != null ? combo_streambar.getSelectedItem().toString().toLowerCase() : "";
        String typ = combo_filmtyp.getSelectedItem() != null ? combo_filmtyp.getSelectedItem().toString().toLowerCase() : "";

        String bewertung = "";
        if (rb_1stern.isSelected()) {
            bewertung = "1 Stern";
        } else if (rb_2stern.isSelected()) {
            bewertung = "2 Sterne";
        } else if (rb_3stern.isSelected()) {
            bewertung = "3 Sterne";
        }

        // Suche in der Filmbibliothek
        textArea1.setText("");
        for (String film : filmbib) {
            if ((titel.isEmpty() || film.toLowerCase().contains("titel: " + titel)) &&
                    (genre.isEmpty() || film.toLowerCase().contains("genre: " + genre)) &&
                    (fsk.isEmpty() || film.toLowerCase().contains("fsk: " + fsk)) &&
                    (dauer.isEmpty() || film.toLowerCase().contains("dauer: " + dauer)) &&
                    (regisseur.isEmpty() || film.toLowerCase().contains("regisseur: " + regisseur)) &&
                    (streambar.isEmpty() || film.toLowerCase().contains("streambar: " + streambar)) &&
                    (typ.isEmpty() || film.toLowerCase().contains("typ: " + typ)) &&
                    (bewertung.isEmpty() || film.toLowerCase().contains("bewertung: " + bewertung.toLowerCase()))) {

                textArea1.append(film + "\n");
            }
        }

        if (textArea1.getText().isEmpty()) {
            textArea1.setText("Keine passenden Filme gefunden.");
        }
    }

    private void ausgeben() {
        // TextArea zeigt bereits gefilterte Ergebnisse, keine weitere Aktion nötig
        if (textArea1.getText().isEmpty()) {
            textArea1.setText("Keine Filme zum Ausgeben.");
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
