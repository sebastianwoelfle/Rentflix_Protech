package Projekt;

// Film-Klasse zur Repräsentation von Filmen
public class Film {
    private String titel;
    private String genre;
    private int dauer;

    public Film(String titel, String genre, int dauer) {
        this.titel = titel;
        this.genre = genre;
        this.dauer = dauer;
    }

    @Override
    public String toString() {
        return "Titel: " + titel + ", Genre: " + genre + ", Dauer: " + dauer + " Minuten";
    }
}
