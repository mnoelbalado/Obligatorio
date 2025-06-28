package um.edu.uy.Metodos;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import um.edu.uy.Cargas.CargarDatos;
import um.edu.uy.Entities.Genero;
import um.edu.uy.Entities.Idioma;
import um.edu.uy.Entities.Pelicula;
import um.edu.uy.Entities.Rating;
import um.edu.uy.Metodos.Top5PeliculasPorIdioma;
import um.edu.uy.TADS.LinkedList.MyLinkedList;
import java.util.Date;

class Top5PeliculasPorIdiomaTest {

    private Top5PeliculasPorIdioma consulta;
    private CargarDatos datos;

    @BeforeEach
    void setUp() {
        datos = new CargarDatos();
        consulta = new Top5PeliculasPorIdioma(datos);
    }

    @Test
    void testConstructor() {
        assertNotNull(consulta);
        assertEquals(datos, consulta.datos);
    }

    @Test
    void testCreacionPeliculaConIdioma() {
        // Test para crear película con idioma específico
        MyLinkedList<Genero> generos = new MyLinkedList<>();
        generos.add(new Genero(1, "Acción"));

        Pelicula peliculaIngles = new Pelicula(1, "English Movie", "en", 1000000.0, generos);
        Pelicula peliculaEspanol = new Pelicula(2, "Película Española", "es", 500000.0, generos);
        Pelicula peliculaFrances = new Pelicula(3, "Film Français", "fr", 750000.0, generos);

        assertEquals("en", peliculaIngles.getIdiomaOriginal());
        assertEquals("es", peliculaEspanol.getIdiomaOriginal());
        assertEquals("fr", peliculaFrances.getIdiomaOriginal());
    }

    @Test
    void testCreacionEntidadIdioma() {
        // Test para validar la entidad Idioma
        Idioma idioma = new Idioma("en");

        assertEquals("en", idioma.getAcronimo());
        assertNotNull(idioma.getListaPeliculas());
        assertEquals(0, idioma.getListaPeliculas().size());

        // Agregar películas al idioma
        MyLinkedList<Genero> generos = new MyLinkedList<>();
        Pelicula pelicula1 = new Pelicula(1, "Movie 1", "en", 0, generos);
        Pelicula pelicula2 = new Pelicula(2, "Movie 2", "en", 0, generos);

        idioma.agregarPelicula(pelicula1);
        idioma.agregarPelicula(pelicula2);

        assertEquals(2, idioma.getListaPeliculas().size());
    }

    @Test
    void testPeliculaConMuchosRatings() {
        // Test para película con muchos ratings (debería aparecer primero)
        MyLinkedList<Genero> generos = new MyLinkedList<>();
        Pelicula pelicula = new Pelicula(1, "Popular Movie", "en", 0, generos);

        // Agregar 150 ratings
        for (int i = 0; i < 150; i++) {
            Rating rating = new Rating(1, 4.0f, i, new Date());
            pelicula.agregarRating(rating);
        }

        assertEquals(150, pelicula.getRatings().size());
        assertFalse(pelicula.getRatings().isEmpty());
    }

    @Test
    void testPeliculaConPocosRatings() {
        // Test para película con pocos ratings
        MyLinkedList<Genero> generos = new MyLinkedList<>();
        Pelicula pelicula = new Pelicula(2, "Unpopular Movie", "es", 0, generos);

        // Agregar solo 5 ratings
        for (int i = 0; i < 5; i++) {
            Rating rating = new Rating(2, 3.0f, i, new Date());
            pelicula.agregarRating(rating);
        }

        assertEquals(5, pelicula.getRatings().size());
        assertFalse(pelicula.getRatings().isEmpty());
    }

    @Test
    void testPeliculaSinIdioma() {
        // Test para película sin idioma (null)
        MyLinkedList<Genero> generos = new MyLinkedList<>();
        Pelicula pelicula = new Pelicula(3, "No Language", null, 0, generos);

        assertNull(pelicula.getIdiomaOriginal());
    }

    @Test
    void testPeliculaIdiomaVacio() {
        // Test para película con idioma vacío
        MyLinkedList<Genero> generos = new MyLinkedList<>();
        Pelicula pelicula = new Pelicula(4, "Empty Language", "", 0, generos);

        assertEquals("", pelicula.getIdiomaOriginal());
        assertTrue(pelicula.getIdiomaOriginal().trim().isEmpty());
    }

    @Test
    void testPeliculaSinRatings() {
        // Test para película sin ratings
        MyLinkedList<Genero> generos = new MyLinkedList<>();
        Pelicula pelicula = new Pelicula(5, "No Ratings", "ja", 0, generos);

        assertEquals(0, pelicula.getRatings().size());
        assertTrue(pelicula.getRatings().isEmpty());
    }

    @Test
    void testComparacionCantidadRatings() {
        // Test para comparar películas por cantidad de ratings
        MyLinkedList<Genero> generos = new MyLinkedList<>();

        Pelicula pelicula1 = new Pelicula(1, "Movie High", "en", 0, generos);
        Pelicula pelicula2 = new Pelicula(2, "Movie Low", "en", 0, generos);

        // Película 1: 100 ratings
        for (int i = 0; i < 100; i++) {
            pelicula1.agregarRating(new Rating(1, 4.0f, i, new Date()));
        }

        // Película 2: 50 ratings
        for (int i = 0; i < 50; i++) {
            pelicula2.agregarRating(new Rating(2, 4.5f, i, new Date()));
        }

        assertTrue(pelicula1.getRatings().size() > pelicula2.getRatings().size());
        assertEquals(100, pelicula1.getRatings().size());
        assertEquals(50, pelicula2.getRatings().size());
        }}