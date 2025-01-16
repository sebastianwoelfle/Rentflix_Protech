package ProjektNeu;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class FilmeTest {

    @Test
    void testFilmeConstructor() {
        Filme film = new Filme("Inception", "Sci-Fi", false, "3-Sterne", 7);
        assertNotNull(film);
    }

    @Test
    void testGetName() {
        Filme film = new Filme("Iron Man", "Sci-Fi", false, "2-Sterne", 9);
        assertEquals("Iron Man", film.getName());
    }

    @Test
    void testGetAusleihzeitraum() {
        Filme film = new Filme("Avatar", "Sci-Fi", false, "3-Sterne", 10);
        assertEquals(10, film.getAusleihzeitraum());
    }

    @Test
    void testToString() {
        Filme film = new Filme("Gladiator", "Action", false, "3-Sterne", 3);
        String result = film.toString();
        assertTrue(result.contains("Name: Gladiator"));
        assertTrue(result.contains("Genre: Action"));
        assertTrue(result.contains("Ab 18: Nein"));
        assertTrue(result.contains("Bewertung: 3-Sterne"));
        assertTrue(result.contains("Ausleihzeitraum: 3"));
    }
}