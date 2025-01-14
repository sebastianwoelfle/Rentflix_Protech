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


    // TextArea (für die Anzeige gespeicherter Reisen)
    private JTextField tf_auswahlzeitraum; // Auswahlzeitraums zum Ausleihen Eingabe


    // Buttons (für verschiedene Aktionen)
    private JButton sucheButton;     //Button zum Suchen
    private JButton speichernButton; //Button zum Speichern des zufor ausgewählten Films und auf TextArea ausgeben
    private JButton berechnenButton; //Button zum Ausrechnen des Ausleihpreises und als Ergänzung ausgeben
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


                gruppierung.clearSelection();
                filmliste.clear(); // Liste leeren
                textArea.setText(""); // TextArea leeren


                //Bewertung fehlt noch

                //Könnte oberes mit den comboboxen ersetzen, muss man testen wenn ich die methode aufrufen kann
                if (comboBox_genre.getItemCount() > 0) {
                    comboBox_genre.setSelectedIndex(0);
                }
                if (comboBox_fsk.getItemCount() > 0) {
                    comboBox_fsk.setSelectedIndex(0);
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

                    //wahrscheinlich falsch(unnötig)( eher speichern der auswahlen)
                    //Speichern des Films(muss villeicht in eine eigene Methode)
                    try {
                        // Ausleihzeitraum prüfen
                        String auswahlzeitraumText = tf_auswahlzeitraum.getText();
                        int ausleihzeitraum = Integer.parseInt(auswahlzeitraumText);

                        // Alle benötigten Felder abrufen
                        String genre = (String) comboBox_genre.getSelectedItem();
                        String fsk = (String) comboBox_fsk.getSelectedItem();


                        //Bewertung sammeln
                        ArrayList<String> bewertung = new ArrayList<>();
                        if (radioButton1Stern.isSelected()) bewertung.add("Bewertung: 1 Stern");
                        if (radioButton2Sterne.isSelected()) bewertung.add("Bewertung: 2 Stern");
                        if (radioButton3Sterne.isSelected()) bewertung.add("Bewertung: 3 Stern");

                        // Überprüft ob die Liste der Bewertungen leer ist
                        // Wenn die Liste leer ist, wird "Keine Bewertungen" als Standardwert zugewiesen
                        // Sonst werden die Bewertungen in die Liste hinzugefügt
                        // Überprüfen, ob die Liste der Bewertungen leer ist
                        String bewertungText = bewertung.isEmpty()
                                ? "Keine Bewertung ausgewählt"
                                : String.join(", ", bewertung);

                        // Filminformationen zusammenstellen
                        String Filme = String.format(
                                "Film: %s\n" +
                                        "Filmname: %s\n" +
                                        "Genre: %s\n" +
                                        "FSK: %s\n" +
                                        "Bewertung: %s\n"+
                                        "Ausleihzeitraum: %d Tage\n" +
                                        "Verfügbar: %d\n" +
                                        "Preis pro Tag: %.2f €");// bei berechnen dann Ausleihzeitraum*PreisProTag = Ausleihkosten);


                        // TextArea aktualisieren
                        textArea.setText(""); // TextArea zurücksetzen
                        for (int i = 0; i < filmliste.size(); i++) {
                            textArea.append((i + 1) + ". " + filmliste.get(i) + "\n\n"); // Nummerierung und Leerzeile
                        }

                        // Erfolgsmeldung
                        JOptionPane.showMessageDialog(Rentflix.this, "Film erfolgreich ausgewählt!",
                                "Erfolg", JOptionPane.INFORMATION_MESSAGE);

                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(Rentflix.this, "Ein unerwarteter Fehler ist aufgetreten: " + ex.getMessage(),
                                "Fehler", JOptionPane.ERROR_MESSAGE);

                }}
        });
    }
}
