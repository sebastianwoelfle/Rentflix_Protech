package ProjektNeu;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
//eine Klasse ist der Bauplan
public class Filme {

    //Attribute für die Filme
    private String name;
    private String genre;
    private boolean fsk;
    private String bewertung;
    private int ausleihzeitraum; // in Tagen

    //Konstrucktor ist eine methode die dabei hilft Objekte aufzurufen und zu initialisieren
    //Konstruktor mit Attributen
    public Filme(String name, String genre, boolean fsk, String bewertung, int ausleihzeitraum) {
        this.name = name;
        this.genre = genre;
        this.fsk = fsk;
        this.bewertung = bewertung;
        this.ausleihzeitraum = ausleihzeitraum;
    }

    //Getter-Methoden
    public String getName() {
        return name;
    }

    public int getAusleihzeitraum() {
        return ausleihzeitraum;
    }

    //Ausgabe in der Text-Area
    public String toString() {
        return "Name: " + name + "\nGenre: " + genre + "\nAb 18: " + (fsk ? "Ja" : "Nein") + "\nBewertung: " + bewertung + "\nAusleihzeitraum: " + ausleihzeitraum
                + "\n-------------------------------------------------------\n";
    }

}
