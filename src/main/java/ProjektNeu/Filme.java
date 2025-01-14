package ProjektNeu;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Filme extends Component {

    //Attribute für die filme
    public String Name;
    public String Genre;
    public int FSK;
    public String Bewertung;
    public double Ausleihzeitraum;// in Tagen
    public double PreisProTag;
    public boolean Verfuegbar;

    //Konstruktor
    public Filme(String name, String genre, int FSK, String bewertung, double ausleihzeitraum, double preisProTag, boolean verfuegbar) {
        Name = name;
        Genre = genre;
        this.FSK = FSK;
        Bewertung = bewertung;
        Ausleihzeitraum = ausleihzeitraum;
        PreisProTag = preisProTag;
        Verfuegbar = verfuegbar;
    }


    private void initObjekte(){

        this.Name = "X-Men";
        this.Genre = "Action";
        this.FSK = 16;
        this.Bewertung = "3 Sterne";
        this.Ausleihzeitraum = 7;
        this.PreisProTag = 2.50;
        this.Verfuegbar = true;
        
    }

    public static ArrayList<Filme> getfilms() {
        ArrayList<Filme> filme = new ArrayList<>();
        //Filme in die Liste Hinzufügen
        filme.add(new Filme("Spider-Man", "Action", 12, "3-Sterne", 5, 2.50, true));
        filme.add(new Filme("Interstellar", "Sci-Fi",12,"3-Sterne",5,4.50, true));
        filme.add(new Filme("Smile","Horror",18,"2-Sterne",7,2.00,true));
        filme.add(new Filme("Gladiator","Action",16,"3-Sterne",2,5.00,true));
        filme.add(new Filme("Iron Man","Sci-Fi",12,"2-Sterne",9,1.50,true));
        filme.add(new Filme("Fast and Furious X","Action",12,"1-Stern",1,0.50,true));
        filme.add(new Filme("Minions","Kinderfilm",0,"3-Sterne",14,1.00,true));
        filme.add(new Filme("The Lego Movie 2","Kinderfilm",6,"1-Stern",4,5.20,true));
        filme.add(new Filme("Men in Black","Sci-Fi",12,"3-Sterne", 3,3.20,true));
        filme.add(new Filme("Cast Away","Abenteuer",12,"2-Sterne",4,2.80,true));
        filme.add(new Filme("Django Unchained","Western",16,"3-Sterne",6,7.80,true));
        filme.add(new Filme("Conjuring","Horror",18,"2-Sterne",10,9.90,true));


        return filme;


    }
}
