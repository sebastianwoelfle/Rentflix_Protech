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

    public void initObjekte() {

        // 12 Filme initialisieren und der Liste hinzufügen
        Filme f1 = new Filme("Spider-Man", "Action", false, "3-Sterne", 5);
        Filme f2 = new Filme("Interstellar", "Sci-Fi", false, "3-Sterne", 5);
        Filme f3 = new Filme("Smile", "Horror", true, "2-Sterne", 7);
        Filme f4 = new Filme("Gladiator", "Action", false, "3-Sterne", 2);
        Filme f5 = new Filme("Iron Man", "Sci-Fi", false, "2-Sterne", 9);
        Filme f6 = new Filme("Fast and Furious X", "Action", false, "1-Stern", 1);
        Filme f7 = new Filme("Minions", "Kinderfilm", false, "3-Sterne", 14);
        Filme f8 = new Filme("The Lego Movie 2", "Kinderfilm", false, "1-Stern", 4);
        Filme f9 = new Filme("Men in Black", "Sci-Fi", false, "3-Sterne", 3);
        Filme f10 = new Filme("Cast Away", "Abenteuer", false, "2-Sterne", 4);
        Filme f11 = new Filme("Django Unchained", "Western", false, "3-Sterne", 6);
        Filme f12 = new Filme("Conjuring", "Horror", true, "2-Sterne", 10);

        filmliste.add(f1);
        filmliste.add(f2);
        filmliste.add(f3);
        filmliste.add(f4);
        filmliste.add(f5);
        filmliste.add(f6);
        filmliste.add(f7);
        filmliste.add(f8);
        filmliste.add(f9);
        filmliste.add(f10);
        filmliste.add(f11);
        filmliste.add(f12);
    }

    public void hinzufuegen() {

        try {

            String name = tf_filmname.getText();
            // Filmtitel überprüfen
            if (name.isEmpty()) {
                errorLabelFilmname.setVisible(true);
                throw new IllegalArgumentException("Bitte geben Sie einen Filmtitel ein.");
            } else {
                errorLabelFilmname.setVisible(false);
            }

            String genre = comboBox_genre.getSelectedItem().toString();
            // Genre überprüfen
            if (genre == null || genre.equals("-")) {
                errorLabelGenre.setVisible(true); // Fehlerlabel für Genre anzeigen
                throw new IllegalArgumentException("Bitte wählen Sie ein Genre aus.");
            } else {
                //errorLabelGenre.setVisible(false);
            }

            boolean fsk = radio_abAchtzehn.isSelected();

            String bewertung = bewertung();
            // Bewergung überprüfen
            if (bewertung.isEmpty()) {
                errorLabelBewertung.setVisible(true);
                throw new IllegalArgumentException("Bitte wählen Sie eine Bewertungsstufe aus.");
            } else {
                errorLabelBewertung.setVisible(false);
            }

            String ausleihZeitraumStr = tf_auswahlzeitraum.getText();
            // Ausleihzeitraum überprüfen
            if (ausleihZeitraumStr.isEmpty()) {
                errorLabelAusleihzeitraum.setVisible(true);
                throw new IllegalArgumentException("Bitte geben Sie einen Ausleihzeitraum an.");
            } else {
                errorLabelAusleihzeitraum.setVisible(false);
            }

            int ausleihZeitraum = Integer.parseInt(ausleihZeitraumStr);

            Filme f = new Filme(name, genre, fsk, bewertung, ausleihZeitraum);

            filmliste.add(f);

            JOptionPane.showMessageDialog(null, "Film wurde erfolgreich hinzugefügt");

        } catch (NumberFormatException e) {
            errorLabelAusleihzeitraum.setVisible(true); // Setzen des Labels auf true, falls String statt Zahl eingegeben wurde
            JOptionPane.showMessageDialog(null, "Ungültiger Ausleihzeitraum: Der Ausleihzeitraum muss im Zahlenformat angegeben werden");
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    // Methode, um Rückgabewert für jeweilgen Radiobutton (Bewertungsangabe) zu definieren
    public String bewertung() {

        try {
            if (radioButton1Stern.isSelected()) {
                return "1-Stern";
            }
            if (radioButton2Sterne.isSelected()) {
                return "2-Sterne";
            }
            if (radioButton3Sterne.isSelected()) {
                return "3-Sterne";
            }
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null, "Bitte geben Sie eine Bewertung ab");
        }

        return "";

    }

    public void ausgeben() {

        for (Filme film : filmliste) {
            // Ausgabe der Liste, wenn diese Filme beinhaltet, ansonsten Meldefenster anzeigen
            if (!filmliste.isEmpty()) {
                textArea.append(film.toString());
            } else {
                JOptionPane.showMessageDialog(null, "Es befinden sich keine Filme in Ihrer Liste.");
            }
        }
    }

    public void clear() {

        // Textfelder leeren
        tf_filmname.setText("");
        tf_auswahlzeitraum.setText("");
        tf_gesamtpreis.setText("");

        // Combobox zurücksetzen
        comboBox_genre.setSelectedIndex(0);

        // Radiobutton zurücksetzen
        radio_abAchtzehn.setSelected(false);
        gruppierung.clearSelection();

        // Liste leeren
        filmliste.clear(); // Liste leeren

        // TextArea leeren
        textArea.setText("");
    }


    public void suchen() {

        // Eingabe des Benutzers abrufen
        String suchbegriff = tf_filmname.getText().trim();
        if (suchbegriff.isEmpty()) {
            JOptionPane.showMessageDialog(null,
                    "Bitte geben Sie einen Filmtitel ein.",
                    "Eingabefehler",
                    JOptionPane.WARNING_MESSAGE);
        }

        // Nach Übereinstimmungen suchen
        int count = 0;      // Anzahl gefundener Übereinstimmungen
        boolean gefunden = false;
        for (Filme film : filmliste) {
            if (film.getName().toLowerCase().contains(suchbegriff.toLowerCase())) {
                textArea.append(film.toString());
                count++;
                gefunden = true;
            }
        }

        // Prüfen, ob Filme gefunden wurden
        if (gefunden) {

            // Wenn Filme gefunden wurden
            JOptionPane.showMessageDialog(null,
                    count + " Treffer gefunden.",
                    "Suche erfolgreich",
                    JOptionPane.INFORMATION_MESSAGE);
        } else {

            // Wenn keine Filme gefunden wurden
            JOptionPane.showMessageDialog(null,
                    "Kein Film mit dem Namen \"" + suchbegriff + "\" gefunden.",
                    "Keine Treffer",
                    JOptionPane.INFORMATION_MESSAGE);

        }
    }

    public double preisBerechnen() {

        double preisProFilm = 0.0;
        double gesamtPreis = 0.0;

        for (Filme film : filmliste) {
            preisProFilm = film.getAusleihzeitraum() * preisProTag;
            gesamtPreis += preisProFilm;
        }
        return gesamtPreis;


    }
}