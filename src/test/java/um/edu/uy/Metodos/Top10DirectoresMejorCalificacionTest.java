package um.edu.uy.Metodos;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import um.edu.uy.Cargas.CargarDatos;
import um.edu.uy.Entities.Director;
import um.edu.uy.Entities.Genero;
import um.edu.uy.Entities.Pelicula;
import um.edu.uy.Entities.Rating;
import um.edu.uy.Metodos.Top10DirectoresMejorCalificacion;
import um.edu.uy.TADS.LinkedList.MyLinkedList;
import java.util.Date;

class Top10DirectoresMejorCalificacionTest {

    private Top10DirectoresMejorCalificacion consulta;
    private CargarDatos datos;

    @BeforeEach
    void setUp() {
        datos = new CargarDatos();
        consulta = new Top10DirectoresMejorCalificacion(datos);
    }

    @Test
    void testConstructor() {
        assertNotNull(consulta);
        assertDoesNotThrow(() -> consulta.realizarConsulta());
    }

    @Test
    void testCreacionDirector() {
        // Test para crear un director
        Director director = new Director("Christopher Nolan");

        assertEquals("Christopher Nolan", director.getNombre());
        assertEquals(0, director.getCantidadPeliculas());
        assertEquals(0, director.getCantidadCalificaciones());
        assertEquals(0.0, director.getSumaCalificaciones(), 0.01);
        assertEquals(0.0, director.obtenerPromedio(), 0.01);
        assertNotNull(director.getListaPeliculas());
    }

    @Test
    void testAgregarPeliculaADirector() {
        // Test para agregar película a director
        Director director = new Director("Steven Spielberg");

        MyLinkedList<Genero> generos = new MyLinkedList<>();
        generos.add(new Genero(1, "Aventura"));

        Pelicula pelicula = new Pelicula(1, "Jurassic Park", "en", 1000000000.0, generos);

        // Agregar algunos ratings a la película
        pelicula.agregarRating(new Rating(1, 5.0f, 1, new Date()));
        pelicula.agregarRating(new Rating(1, 4.5f, 2, new Date()));
        pelicula.agregarRating(new Rating(1, 4.8f, 3, new Date()));

        director.agregarPelicula(pelicula);

        assertEquals(1, director.getCantidadPeliculas());
        assertEquals(3, director.getCantidadCalificaciones());

        // Verificar suma de calificaciones: 5.0 + 4.5 + 4.8 = 14.3
        assertEquals(14.3, director.getSumaCalificaciones(), 0.01);

        // Verificar promedio: 14.3 / 3 = 4.766...
        assertEquals(4.766, director.obtenerPromedio(), 0.01);
    }

    @Test
    void testDirectorConMultiplesPeliculas() {
        // Test para director con múltiples películas
        Director director = new Director("Martin Scorsese");

        MyLinkedList<Genero> generos = new MyLinkedList<>();
        generos.add(new Genero(2, "Drama"));

        // Primera película
        Pelicula pelicula1 = new Pelicula(1, "Goodfellas", "en", 0, generos);
        pelicula1.agregarRating(new Rating(1, 5.0f, 1, new Date()));
        pelicula1.agregarRating(new Rating(1, 4.9f, 2, new Date()));

        // Segunda película
        Pelicula pelicula2 = new Pelicula(2, "The Departed", "en", 0, generos);
        pelicula2.agregarRating(new Rating(2, 4.8f, 3, new Date()));
        pelicula2.agregarRating(new Rating(2, 4.7f, 4, new Date()));
        pelicula2.agregarRating(new Rating(2, 4.6f, 5, new Date()));

        director.agregarPelicula(pelicula1);
        director.agregarPelicula(pelicula2);

        assertEquals(2, director.getCantidadPeliculas());
        assertEquals(5, director.getCantidadCalificaciones()); // 2 + 3 ratings

        // Suma: 5.0 + 4.9 + 4.8 + 4.7 + 4.6 = 24.0
        assertEquals(24.0, director.getSumaCalificaciones(), 0.01);

        // Promedio: 24.0 / 5 = 4.8
        assertEquals(4.8, director.obtenerPromedio(), 0.01);
    }

    @Test
    void testDirectorSinPeliculas() {
        // Test para director sin películas
        Director director = new Director("Director Nuevo");

        assertEquals(0, director.getCantidadPeliculas());
        assertEquals(0, director.getCantidadCalificaciones());
        assertEquals(0.0, director.obtenerPromedio(), 0.01);
    }

    @Test
    void testCalculoPromedioCero() {
        // Test para evitar división por cero
        Director director = new Director("Director Sin Ratings");

        // Crear película sin ratings
        MyLinkedList<Genero> generos = new MyLinkedList<>();
        Pelicula pelicula = new Pelicula(1, "Sin Calificaciones", "en", 0, generos);

        director.agregarPelicula(pelicula);

        assertEquals(1, director.getCantidadPeliculas());
        assertEquals(0, director.getCantidadCalificaciones());
        assertEquals(0.0, director.obtenerPromedio(), 0.01); // No debe dividir por cero
    }

    @Test
    void testFiltroMasDe100Calificaciones() {
        // Test para director que cumple el criterio (>= 100 calificaciones)
        Director director = new Director("Director Popular");

        MyLinkedList<Genero> generos = new MyLinkedList<>();
        generos.add(new Genero(1, "Acción"));

        Pelicula pelicula = new Pelicula(1, "Película Popular", "en", 0, generos);

        // Agregar 120 ratings
        for (int i = 0; i < 120; i++) {
            pelicula.agregarRating(new Rating(1, 4.5f, i, new Date()));
        }

        director.agregarPelicula(pelicula);

        assertEquals(1, director.getCantidadPeliculas());
        assertEquals(120, director.getCantidadCalificaciones());
        assertTrue(director.getCantidadCalificaciones() >= 100);
        assertEquals(4.5, director.obtenerPromedio(), 0.01);
    }

    @Test
    void testFiltroMasDeUnaPelicula() {
        // Test para director que cumple el criterio (> 1 película)
        Director director = new Director("Director Productivo");

        MyLinkedList<Genero> generos = new MyLinkedList<>();
        generos.add(new Genero(1, "Comedia"));

        Pelicula pelicula1 = new Pelicula(1, "Película 1", "en", 0, generos);
        Pelicula pelicula2 = new Pelicula(2, "Película 2", "en", 0, generos);
        Pelicula pelicula3 = new Pelicula(3, "Película 3", "en", 0, generos);

        director.agregarPelicula(pelicula1);
        director.agregarPelicula(pelicula2);
        director.agregarPelicula(pelicula3);

        assertEquals(3, director.getCantidadPeliculas());
        assertTrue(director.getCantidadPeliculas() > 1);
    }

    @Test
    void testRealizarConsulta() {
        // Test que no lance excepción y mida tiempo
        assertDoesNotThrow(() -> consulta.realizarConsulta());
    }

    @Test
    void testConsultaConDatosVacios() {
        CargarDatos datosVacios = new CargarDatos();
        Top10DirectoresMejorCalificacion consultaVacia =
                new Top10DirectoresMejorCalificacion(datosVacios);

        // Debería manejar datos vacíos sin lanzar excepción
        assertDoesNotThrow(() -> consultaVacia.realizarConsulta());
    }

    @Test
    void testRecalcularCalificaciones() {
        // Test para el método recalcularCalificaciones
        Director director = new Director("Director Test");

        MyLinkedList<Genero> generos = new MyLinkedList<>();
        Pelicula pelicula = new Pelicula(1, "Test Movie", "en", 0, generos);

        pelicula.agregarRating(new Rating(1, 4.0f, 1, new Date()));
        pelicula.agregarRating(new Rating(1, 5.0f, 2, new Date()));

        director.agregarPelicula(pelicula);

        // Verificar valores iniciales
        assertEquals(2, director.getCantidadCalificaciones());
        assertEquals(9.0, director.getSumaCalificaciones(), 0.01);

        // Recalcular (debería dar los mismos resultados)
        director.recalcularCalificaciones();

        assertEquals(2, director.getCantidadCalificaciones());
        assertEquals(9.0, director.getSumaCalificaciones(), 0.01);
        assertEquals(4.5, director.obtenerPromedio(), 0.01);
    }
}