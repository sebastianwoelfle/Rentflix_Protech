package ProjektNeu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Rentflix extends JFrame {


    // Hauptkomponenten des UI
    private JPanel RentflixPanel; //Panel


    // Eingabekomponenten (für die Benutzereingaben)
    private JTextField tf_filmname;    //Filmname Eingabe
    private JTextArea textArea;        //Große Area auf der der Text ausgegeben wird
    private JComboBox comboBox_genre;  //Combobox zur Auswahl des Genres
    private JComboBox comboBox_fsk;    //Combobox zur Auswahl der Altersbegrenzung


    // TextArea (für die Anzeige gespeicherter Filmen)
    private JTextField tf_auswahlzeitraum; // Auswahlzeitraums zum Ausleihen Eingabe


    // Buttons (für verschiedene Aktionen)
    private JButton sucheButton;     //Button zum Suchen
    private JButton speichernButton; //Button zum Speichern des zuvor ausgewählten Films und auf TextArea ausgeben
    private JButton preisBerechnenButton; //Button zum Ausrechnen des Ausleihpreises und als Ergänzung ausgeben
    private JButton resetButton;     //Button um alles zu resetten

    // Radiobutton (für Bewertungsangabe)
    private JRadioButton radioButton1Stern;  //Radiobutton zur 1Sterne Bewertung
    private JRadioButton radioButton2Sterne; //Radiobutton zur 2Sterne Bewertung
    private JRadioButton radioButton3Sterne; //Radiobutton zur 3Sterne Bewertung

    // Fehlerlabels (für Eingabefehler)
    private JLabel errorLabelFilmname;
    private JLabel errorLabelGenre;
    private JLabel errorLabelFSK;
    private JLabel errorLabelBewertung;
    private JLabel errorLabelAusleihzeitraum;
    private JScrollPane scrollpane;


    // ArrayList zum Speichern der Filmauswahl
    private ArrayList<String> filmliste = new ArrayList<>();


    public Rentflix(){


        setTitle("Videothek");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 800);
        scrollpane.setPreferredSize(new Dimension(500,300));
        setContentPane(RentflixPanel);
        setVisible(true);
        setResizable(false);
        ButtonGroup gruppierung = new ButtonGroup();
        gruppierung.add(radioButton1Stern);
        gruppierung.add(radioButton2Sterne);
        gruppierung.add(radioButton3Sterne);



        // Fehlerlabels standardmäßig unsichtbar setzen
        errorLabelFilmname.setVisible(false);
        errorLabelGenre.setVisible(false);
        errorLabelFSK.setVisible(false);
        errorLabelBewertung.setVisible(false);
        errorLabelAusleihzeitraum.setVisible(false);

        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tf_filmname.setText(""); //Textfelder leeren
                tf_auswahlzeitraum.setText(""); //Textfelder leeren

                //combobox leeren weiß aber nicht ob (setToolText) das richtige ist!!!
                comboBox_fsk.setToolTipText("-");
                comboBox_genre.setToolTipText("-");


                gruppierung.clearSelection(); //Bewertungsauswahl leeren
                filmliste.clear(); // Liste leeren
                textArea.setText(""); // TextArea leeren
                // Gespeicherte Comboboxen zurücksetzen
                if (comboBox_genre.getItemCount() > 0) {
                    comboBox_genre.setSelectedIndex(0);
                }
                if (comboBox_fsk.getItemCount() > 0) {
                    comboBox_fsk.setSelectedIndex(0);
                }


            }
        });

        sucheButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Eingabe des Benutzers abrufen
                String suchbegriff = tf_filmname.getText().trim();
                if (suchbegriff.isEmpty()) {
                    JOptionPane.showMessageDialog(Rentflix.this,
                            "Bitte geben Sie einen Filmtitel ein.",
                            "Eingabefehler",
                            JOptionPane.WARNING_MESSAGE);
                    return;
                }

                // Datenbank abrufen
                ArrayList<Filme> datenbank = Filme.getfilms();
                ArrayList<Filme> treffer = new ArrayList<>();

                // Nach Übereinstimmungen suchen
                for (Filme film : datenbank) {
                    if (film.Name.toLowerCase().contains(suchbegriff.toLowerCase())) {
                        treffer.add(film);
                    }
                }

                // Treffer anzeigen oder Fehlermeldung ausgeben
                if (treffer.isEmpty()) {
                    JOptionPane.showMessageDialog(Rentflix.this,
                            "Kein Film mit dem Namen \"" + suchbegriff + "\" gefunden.",
                            "Keine Treffer",
                            JOptionPane.INFORMATION_MESSAGE);
                } else {
                    textArea.setText(""); // TextArea zurücksetzen
                    for (Filme film : treffer) {
                        String filmDetails = String.format(
                                "Name: %s\nGenre: %s\nFSK: %d\nBewertung: %s\nAusleihzeitraum: %.0f Tage\nPreis/Tag: %.2f €\nVerfügbar: %s\n\n",
                                film.Name, film.Genre, film.FSK, film.Bewertung, film.Ausleihzeitraum, film.PreisProTag,
                                film.Verfuegbar ? "Ja" : "Nein");
                        textArea.append(filmDetails);
                    }
                    JOptionPane.showMessageDialog(Rentflix.this,
                            treffer.size() + " Treffer gefunden.",
                            "Suche erfolgreich",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        preisBerechnenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Eingabe des Benutzers abrufen
                String suchbegriff = tf_filmname.getText().trim();
                String auswahlzeitraumText = tf_auswahlzeitraum.getText().trim();

                // Eingaben validieren
                if (suchbegriff.isEmpty()) {
                    JOptionPane.showMessageDialog(Rentflix.this,
                            "Bitte geben Sie einen Filmtitel ein.",
                            "Eingabefehler",
                            JOptionPane.WARNING_MESSAGE);
                    return;
                }

                if (!auswahlzeitraumText.matches("\\d+")) {
                    JOptionPane.showMessageDialog(Rentflix.this,
                            "Bitte geben Sie einen gültigen Ausleihzeitraum (nur ganze Zahlen) ein.",
                            "Ungültige Eingabe",
                            JOptionPane.WARNING_MESSAGE);
                    return;
                }

                int ausleihzeitraum = Integer.parseInt(auswahlzeitraumText);

                // Datenbank abrufen und nach Filmtitel suchen
                ArrayList<Filme> datenbank = Filme.getfilms();
                ArrayList<Filme> treffer = new ArrayList<>();

                for (Filme film : datenbank) {
                    if (film.Name.toLowerCase().contains(suchbegriff.toLowerCase())) {
                        treffer.add(film);
                    }
                }

                // Treffer verarbeiten oder Fehlermeldung ausgeben
                if (treffer.isEmpty()) {
                    JOptionPane.showMessageDialog(Rentflix.this,
                            "Kein Film mit dem Namen \"" + suchbegriff + "\" gefunden.",
                            "Keine Treffer",
                            JOptionPane.INFORMATION_MESSAGE);
                } else {
                    textArea.setText(""); // TextArea zurücksetzen
                    for (Filme film : treffer) {
                        // Gesamtpreis berechnen
                        double gesamtpreis = ausleihzeitraum * film.PreisProTag;

                        // Film-Details mit Preis in der TextArea anzeigen
                        String filmDetails = String.format(
                                "Name: %s\nGenre: %s\nFSK: %d\nBewertung: %s\nAusleihzeitraum: %d Tage\nPreis pro Tag: %.2f €\nGesamtpreis: %.2f €\nVerfügbar: %s\n\n",
                                film.Name, film.Genre, film.FSK, film.Bewertung, ausleihzeitraum, film.PreisProTag, gesamtpreis,
                                film.Verfuegbar ? "Ja" : "Nein");
                        textArea.append(filmDetails);
                    }
                    JOptionPane.showMessageDialog(Rentflix.this,
                            treffer.size() + " Treffer verarbeitet. Preis wurde berechnet.",
                            "Preisberechnung erfolgreich",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });




        speichernButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SpeichernUndAusgeben();
            }
                // Methode zum Speichern und Annzeigen/Ausgeben
                public void SpeichernUndAusgeben(){
                    // Variablen zur Fehlererfassung
                    boolean fehlerInGenre = false;
                    boolean fehlerInFSK = false;
                    boolean fehlerInBewertung = false;
                    boolean fehlerInAusleihzeitraum = false;



                    // Ausleihzeitraum überprüfen
                    String Ausleihzeitraum = tf_auswahlzeitraum.getText();
                    if (!Ausleihzeitraum.matches("\\d+")) { // Nur ganze Zahlen erlaubt
                        fehlerInAusleihzeitraum = true;
                        //errorlabel machen
                        errorLabelAusleihzeitraum.setVisible(true); // Fehlerlabel für Ausleihzeitraum anzeigen
                    } else {
                        errorLabelAusleihzeitraum.setVisible(false); // Fehlerlabel bei korrekter Eingabe unsichtbar machen
                    }

                    // Genre überprüfen
                    String Genre = (String) comboBox_genre.getSelectedItem();
                    if (Genre == null || Genre.equals("-")) {
                        fehlerInGenre = true;
                        errorLabelGenre.setVisible(true); // Fehlerlabel für Genre anzeigen
                    } else {
                        errorLabelGenre.setVisible(false);
                    }

                    // FSK(Altersbeschränkung) überprüfen
                    String FSK = (String) comboBox_fsk.getSelectedItem();
                    if (FSK == null || FSK.equals("-")) {
                        fehlerInFSK = true;
                        errorLabelFSK.setVisible(true); // Fehlerlabel für FSK anzeigen
                    } else {
                        errorLabelFSK.setVisible(false);
                    }
// bewertungen zu den fehlern hinzufügen
                   /* if (bewertung.isEmpty()) {
                        errorLabelBewertung.setVisible(true);
                        fehler = true;

                    } else {
                        errorLabelBewertung.setVisible(false);
                    }

                    */



                    // Wenn Fehler aufgetreten sind, entsprechende Nachricht anzeigen
                    if (fehlerInGenre || fehlerInFSK || fehlerInBewertung || fehlerInAusleihzeitraum) {
                        StringBuilder fehlermeldung = new StringBuilder("Bitte beheben Sie die folgenden Fehler:\n");
                        if (fehlerInGenre) fehlermeldung.append("- Bitte wählen Sie eine Genre aus.\n");
                        if (fehlerInFSK) fehlermeldung.append("- Bitte wählen Sie eine Altersfreigabe(FSK) aus.\n");
                        if (fehlerInBewertung) fehlermeldung.append("- Bitte wählen Sie eine Bewertungsstufe aus.\n");// noch einen fehler für bewertung machen
                        if (fehlerInAusleihzeitraum) fehlermeldung.append("-Ungültiger Ausleihzeitraum (nur ganze Zahlen erlaubt).\n");

                        //Rentflix.this anstatt nur "this" villeicht falsch ("null" auch möglich)
                        JOptionPane.showMessageDialog(Rentflix.this, fehlermeldung.toString(), "Ungültige Eingaben", JOptionPane.WARNING_MESSAGE);
                        return; // Methode verlassen
                    }

                    // Eingaben des Benutzers abrufen
                    String genre = (String) comboBox_genre.getSelectedItem();
                    String fskText = (String) comboBox_fsk.getSelectedItem();
                    String bewertung = "";
                    if (radioButton1Stern.isSelected()) bewertung = "1-Stern";
                    if (radioButton2Sterne.isSelected()) bewertung = "2-Sterne";
                    if (radioButton3Sterne.isSelected()) bewertung = "3-Sterne";
                    String ausleihzeitraumText = tf_auswahlzeitraum.getText();
/*
                    // Validierung der Eingaben
                    boolean fehler = false;

                    if (genre == null || genre.equals("-")) {
                        errorLabelGenre.setVisible(true);
                        fehler = true;
                    } else {
                        errorLabelGenre.setVisible(false);
                    }

                    if (fskText == null || fskText.equals("-")) {
                        errorLabelFSK.setVisible(true);
                        fehler = true;
                    } else {
                        errorLabelFSK.setVisible(false);
                    }

                    if (bewertung.isEmpty()) {
                        errorLabelBewertung.setVisible(true);
                        fehler = true;
                    } else {
                        errorLabelBewertung.setVisible(false);
                    }

                    if (!ausleihzeitraumText.matches("\\d+")) {
                        errorLabelAusleihzeitraum.setVisible(true);
                        fehler = true;
                    } else {
                        errorLabelAusleihzeitraum.setVisible(false);
                    }



                    if (fehler) {
                        JOptionPane.showMessageDialog(Rentflix.this, "Bitte beheben Sie die markierten Fehler.", "Eingabefehler", JOptionPane.WARNING_MESSAGE);
                        return;
                    }

 */

                    // Werte umwandeln
                    int fsk = Integer.parseInt(fskText);
                    int ausleihzeitraum = Integer.parseInt(ausleihzeitraumText);

                    // Filme aus der Datenbank abrufen
                    ArrayList<Filme> datenbank = Filme.getfilms();
                    ArrayList<Filme> gefundeneFilme = new ArrayList<>();

                    // Kriterienprüfung
                    for (Filme film : datenbank) {
                        if (film.Genre.equalsIgnoreCase(genre) &&
                                film.FSK <= fsk &&
                                film.Bewertung.equalsIgnoreCase(bewertung) && //IgnoreCase = ignoriert Groß-und Kleinschreibung
                                film.Ausleihzeitraum >= ausleihzeitraum) {
                            gefundeneFilme.add(film);
                        }
                    }

                    // Ergebnisse anzeigen
                    textArea.setText("");
                    if (gefundeneFilme.isEmpty()) {
                        textArea.setText("Kein Film erfüllt die Kriterien.");
                    } else {
                        for (Filme film : gefundeneFilme) {
                            String filmDetails = String.format(
                                    "Name: %s\nGenre: %s\nFSK: %d\nBewertung: %s\nAusleihzeitraum: %.0f Tage\nPreis/Tag: %.2f €\nVerfügbar: %s\n\n",
                                    film.Name, film.Genre, film.FSK, film.Bewertung, film.Ausleihzeitraum, film.PreisProTag,
                                    film.Verfuegbar ? "Ja" : "Nein"
                            );
                            textArea.append(filmDetails);
                        }
                    }
                }
        });
                }

    }
