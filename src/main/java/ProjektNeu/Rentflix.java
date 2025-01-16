package ProjektNeu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Rentflix extends JFrame {

    // Hauptkomponenten des UI
    private JPanel RentflixPanel; // Panel

    // Eingabekomponenten (für die Benutzereingaben)
    private JTextField tf_filmname;    // Filmname Eingabe
    private JTextArea textArea;        //  // TextArea (für die Anzeige gespeicherter Filmen)
    private JComboBox comboBox_genre;  // Combobox zur Auswahl des Genres

    private JTextField tf_auswahlzeitraum; // Auswahlzeitraums zum Ausleihen Eingabe

    private JTextField tf_gesamtpreis;  // Gesampreis Ausgabe

    // Buttons (für verschiedene Aktionen)
    private JButton sucheButton;     // Button zum Suchen
    private JButton speichernButton; // Button zum Speichern des zuvor ausgewählten Films und auf TextArea ausgeben
    private JButton ausgebenButton; // Button zum Ausgeben der ArrayList
    private JButton preisBerechnenButton; // Button zum Ausrechnen des Ausleihpreises und als Ergänzung ausgeben
    private JButton resetButton;     // Button um alles zu resetten
    private JButton ausleihenButton; // Button, um das Ausleihen zu bestätigen

    // Radiobutton (für Bewertungsangabe)
    private JRadioButton radioButton1Stern;  // Radiobutton zur 1Sterne Bewertung
    private JRadioButton radioButton2Sterne; // Radiobutton zur 2Sterne Bewertung
    private JRadioButton radioButton3Sterne; // Radiobutton zur 3Sterne Bewertung

    // Fehlerlabels (für Eingabefehler)
    private JLabel errorLabelFilmname;
    private JLabel errorLabelGenre;
    private JLabel errorLabelBewertung;
    private JLabel errorLabelAusleihzeitraum;

    // ScrollPane
    private JScrollPane scrollpane;

    private JRadioButton radio_abAchtzehn;  // Radiobutton, um auszuwählen, ob Film ab 18

    private final static double preisProTag = 5.0;  // Feste Gebühr pro Tag für Ausleihe

    // ArrayList zum Speichern der Filmauswahl
    private ArrayList<Filme> filmliste = new ArrayList<>();

    // Gruppierung der Radiobuttons, damit nur eine Auswahl möglich ist
    ButtonGroup gruppierung = new ButtonGroup();

    // Konstruktor
    public Rentflix() {

        setTitle("Videothek");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1300, 800);
        scrollpane.setPreferredSize(new Dimension(500, 300));
        setContentPane(RentflixPanel);
        setVisible(true);
        setResizable(false);

        // Gruppierung von Radiobuttons, damit nur eine Auswahl möglich ist
        gruppierung.add(radioButton1Stern);
        gruppierung.add(radioButton2Sterne);
        gruppierung.add(radioButton3Sterne);


        // Fehlerlabels standardmäßig unsichtbar setzen
        errorLabelFilmname.setVisible(false);
        errorLabelGenre.setVisible(false);
        errorLabelBewertung.setVisible(false);
        errorLabelAusleihzeitraum.setVisible(false);


        // Aufruf der Methode initObjekte(), damit beim Programmstart Filme in der Liste vorhanden sind
        initObjekte();

        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                clear();
            }
        });

        sucheButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                suchen();
            }
        });

        preisBerechnenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // Setzen des Gesamtpreises in das Textfeld
                double gesamtpreis = preisBerechnen();
                tf_gesamtpreis.setText(gesamtpreis + " €");

            }
        });

        speichernButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                hinzufuegen();

            }

        });

        ausgebenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                ausgeben();
            }
        });

        ausleihenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                clear();

                // Prüfen, ob ArrayList Filme beinhaltet
                if (!filmliste.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Es befinden sich keine Filme in Ihrer Liste.");
                } else {
                    JOptionPane.showMessageDialog(null, "Die ausgewählten Filme wurden erfolgreich ausgeliehen.");

                }
            }
        });


    }

}