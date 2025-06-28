package um.edu.uy.Metodos;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import um.edu.uy.Cargas.CargarDatos;
import um.edu.uy.Entities.Genero;
import um.edu.uy.Entities.Pelicula;
import um.edu.uy.Entities.Rating;
import um.edu.uy.Entities.Usuario;
import um.edu.uy.Metodos.UsuarioConMasCalificacionesPorGenero;
import um.edu.uy.TADS.LinkedList.MyLinkedList;
import java.util.Date;

class UsuarioConMasCalificacionesPorGeneroTest {

    private UsuarioConMasCalificacionesPorGenero consulta;
    private CargarDatos datos;

    @BeforeEach
    void setUp() {
        datos = new CargarDatos();
        consulta = new UsuarioConMasCalificacionesPorGenero(datos);
    }

    @Test
    void testConstructor() {
        assertNotNull(consulta);
        assertEquals(datos, consulta.datos);
    }

    @Test
    void testCreacionUsuarioConRatings() {
        // Test para crear usuario y validar ratings
        Usuario usuario = new Usuario(1);
        assertEquals(1, usuario.getIdUsuario());
        assertEquals(0, usuario.cantidadRatings());

        // Agregar ratings
        Rating rating1 = new Rating(1, 4.5f, 1, new Date());
        Rating rating2 = new Rating(2, 3.5f, 1, new Date());

        usuario.agregarRating(rating1);
        usuario.agregarRating(rating2);

        assertEquals(2, usuario.cantidadRatings());
    }

    @Test
    void testCreacionPeliculaConGeneros() {
        // Test para crear película con géneros
        MyLinkedList<Genero> generos = new MyLinkedList<>();
        Genero accion = new Genero(1, "Acción");
        Genero drama = new Genero(2, "Drama");

        generos.add(accion);
        generos.add(drama);

        Pelicula pelicula = new Pelicula(1, "Test Movie", "en", 1000000.0, generos);

        assertEquals(2, pelicula.getGeneros().size());
        assertEquals("Acción", pelicula.getGeneros().get(0).getNombre());
        assertEquals("Drama", pelicula.getGeneros().get(1).getNombre());
    }

    @Test
    void testGeneroConPeliculas() {
        // Test para validar que un género puede tener películas
        Genero genero = new Genero(1, "Comedia");

        MyLinkedList<Genero> generosLista = new MyLinkedList<>();
        generosLista.add(genero);

        Pelicula pelicula1 = new Pelicula(1, "Comedia 1", "en", 0, generosLista);
        Pelicula pelicula2 = new Pelicula(2, "Comedia 2", "es", 0, generosLista);

        genero.agregarPelicula(pelicula1);
        genero.agregarPelicula(pelicula2);

        assertEquals(2, genero.getListaPeliculas().size());
    }

    @Test
    void testPeliculaConRatingsDeMultiplesUsuarios() {
        // Test para validar múltiples usuarios calificando una película
        MyLinkedList<Genero> generos = new MyLinkedList<>();
        generos.add(new Genero(1, "Terror"));

        Pelicula pelicula = new Pelicula(1, "Scary Movie", "en", 0, generos);

        // Diferentes usuarios califican la misma película
        pelicula.agregarRating(new Rating(1, 4.0f, 101, new Date()));
        pelicula.agregarRating(new Rating(1, 3.5f, 102, new Date()));
        pelicula.agregarRating(new Rating(1, 5.0f, 103, new Date()));
        pelicula.agregarRating(new Rating(1, 2.0f, 104, new Date()));

        assertEquals(4, pelicula.getRatings().size());

        // Verificar que los IDs de usuario son diferentes
        assertEquals(101, pelicula.getRatings().get(0).getIdUsuario());
        assertEquals(102, pelicula.getRatings().get(1).getIdUsuario());
        assertEquals(103, pelicula.getRatings().get(2).getIdUsuario());
        assertEquals(104, pelicula.getRatings().get(3).getIdUsuario());
    }

    @Test
    void testRealizarConsulta() {
        // Test que no lance excepción y mida tiempo
        assertDoesNotThrow(() -> consulta.realizarConsulta());
    }

    @Test
    void testConsultaConDatosVacios() {
        CargarDatos datosVacios = new CargarDatos();
        UsuarioConMasCalificacionesPorGenero consultaVacia =
                new UsuarioConMasCalificacionesPorGenero(datosVacios);

        // Debería manejar datos vacíos sin lanzar excepción
        assertDoesNotThrow(() -> consultaVacia.realizarConsulta());
    }

    @Test
    void testPeliculaSinGeneros() {
        // Test para película sin géneros
        MyLinkedList<Genero> generosVacios = new MyLinkedList<>();
        Pelicula pelicula = new Pelicula(1, "Sin Género", "en", 0, generosVacios);

        assertEquals(0, pelicula.getGeneros().size());
        assertTrue(pelicula.getGeneros().size() == 0);
    }

    @Test
    void testPeliculaSinRatings() {
        // Test para película sin ratings
        MyLinkedList<Genero> generos = new MyLinkedList<>();
        generos.add(new Genero(1, "Documental"));

        Pelicula pelicula = new Pelicula(1, "Sin Calificaciones", "en", 0, generos);

        assertEquals(0, pelicula.getRatings().size());
        assertTrue(pelicula.getRatings().size() == 0);
    }

    @Test
    void testValidacionEntidadGenero() {
        // Test para validar la correcta creación de Género
        Genero genero = new Genero(28, "Acción");

        assertEquals(28, genero.getId());
        assertEquals("Acción", genero.getNombre());
        assertNotNull(genero.getListaPeliculas());
        assertEquals(0, genero.getListaPeliculas().size());
    }

    @Test
    void testUsuarioCalificandoMultiplesPeliculas() {
        // Test para usuario que califica múltiples películas del mismo género
        Usuario usuario = new Usuario(500);

        // Crear películas del mismo género
        MyLinkedList<Genero> generos = new MyLinkedList<>();
        generos.add(new Genero(1, "Ciencia Ficción"));

        Pelicula pelicula1 = new Pelicula(1, "Sci-Fi 1", "en", 0, generos);
        Pelicula pelicula2 = new Pelicula(2, "Sci-Fi 2", "en", 0, generos);
        Pelicula pelicula3 = new Pelicula(3, "Sci-Fi 3", "en", 0, generos);

        // Usuario califica las 3 películas
        Rating rating1 = new Rating(1, 4.0f, 500, new Date());
        Rating rating2 = new Rating(2, 3.5f, 500, new Date());
        Rating rating3 = new Rating(3, 5.0f, 500, new Date());

        usuario.agregarRating(rating1);
        usuario.agregarRating(rating2);
        usuario.agregarRating(rating3);

        pelicula1.agregarRating(rating1);
        pelicula2.agregarRating(rating2);
        pelicula3.agregarRating(rating3);

        assertEquals(3, usuario.cantidadRatings());
        assertEquals(1, pelicula1.getRatings().size());
        assertEquals(1, pelicula2.getRatings().size());
        assertEquals(1, pelicula3.getRatings().size());
    }
}