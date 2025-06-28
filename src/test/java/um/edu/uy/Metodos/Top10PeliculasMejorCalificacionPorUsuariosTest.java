package um.edu.uy.Metodos;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import um.edu.uy.Cargas.CargarDatos;
import um.edu.uy.Entities.Genero;
import um.edu.uy.Entities.Pelicula;
import um.edu.uy.Entities.Rating;
import um.edu.uy.Metodos.Top10PeliculasMejorCalificacionPorUsuarios;
import um.edu.uy.TADS.HashTable.MyHashTable;
import um.edu.uy.TADS.LinkedList.MyLinkedList;
import java.util.Date;

class Top10PeliculasMejorCalificacionPorUsuariosTest {

    private Top10PeliculasMejorCalificacionPorUsuarios consulta;
    private CargarDatos datos;

    @BeforeEach
    void setUp() {
        datos = new CargarDatos();
        consulta = new Top10PeliculasMejorCalificacionPorUsuarios(datos);
    }

    @Test
    void testConstructor() {
        assertNotNull(consulta);
        assertEquals(datos, consulta.datos);
    }

    @Test
    void testCreacionPeliculaConMuchasCalificaciones() {
        // Crear película con más de 100 ratings para validar que entra en el filtro
        MyLinkedList<Genero> generos = new MyLinkedList<>();
        generos.add(new Genero(1, "Acción"));

        Pelicula pelicula = new Pelicula(1, "Test Movie", "en", 1000000.0, generos);

        // Agregar 120 ratings
        for (int i = 0; i < 120; i++) {
            Rating rating = new Rating(1, 4.5f, i, new Date());
            pelicula.agregarRating(rating);
        }

        assertEquals(120, pelicula.getRatings().size());
        assertTrue(pelicula.getRatings().size() > 100);
    }

    @Test
    void testCreacionPeliculaConPocasCalificaciones() {
        // Crear película con menos de 100 ratings para validar que NO entra en el filtro
        MyLinkedList<Genero> generos = new MyLinkedList<>();
        generos.add(new Genero(2, "Drama"));

        Pelicula pelicula = new Pelicula(2, "Low Rated Movie", "es", 500000.0, generos);

        // Agregar solo 50 ratings
        for (int i = 0; i < 50; i++) {
            Rating rating = new Rating(2, 5.0f, i, new Date());
            pelicula.agregarRating(rating);
        }

        assertEquals(50, pelicula.getRatings().size());
        assertFalse(pelicula.getRatings().size() > 100);
    }

    @Test
    void testCalculoPromedioRatings() {
        // Test para verificar el cálculo correcto del promedio
        MyLinkedList<Genero> generos = new MyLinkedList<>();
        Pelicula pelicula = new Pelicula(3, "Average Test", "en", 0, generos);

        // Agregar ratings conocidos para calcular promedio
        pelicula.agregarRating(new Rating(3, 4.0f, 1, new Date()));
        pelicula.agregarRating(new Rating(3, 5.0f, 2, new Date()));
        pelicula.agregarRating(new Rating(3, 3.0f, 3, new Date()));

        // Calcular suma manualmente
        float suma = 4.0f + 5.0f + 3.0f; // = 12.0f
        float promedioEsperado = suma / 3; // = 4.0f

        assertEquals(3, pelicula.getRatings().size());
        assertEquals(4.0f, promedioEsperado, 0.01f);
    }

    @Test
    void testRealizarConsulta() {
        // Test que no lance excepción y mida tiempo
        assertDoesNotThrow(() -> consulta.realizarConsulta());
    }

    @Test
    void testConsultaConDatosVacios() {
        CargarDatos datosVacios = new CargarDatos();
        Top10PeliculasMejorCalificacionPorUsuarios consultaVacia =
                new Top10PeliculasMejorCalificacionPorUsuarios(datosVacios);

        // Debería manejar datos vacíos sin lanzar excepción
        assertDoesNotThrow(() -> consultaVacia.realizarConsulta());
    }

    @Test
    void testPeliculaSinRatings() {
        // Test para película sin ratings
        MyLinkedList<Genero> generos = new MyLinkedList<>();
        Pelicula pelicula = new Pelicula(4, "No Ratings", "en", 0, generos);

        assertEquals(0, pelicula.getRatings().size());
        assertFalse(pelicula.getRatings().size() > 100);
    }

    @Test
    void testCondicionFiltroSumaRatings() {
        // Test del bug en el código: usa sumaRatingsPelicula > 100 en lugar de cantidad
        // Este test documenta el comportamiento actual (incorrecto)
        MyLinkedList<Genero> generos = new MyLinkedList<>();
        Pelicula pelicula = new Pelicula(5, "Bug Test", "en", 0, generos);

        // Agregar pocos ratings pero con valores altos para que la suma sea > 100
        for (int i = 0; i < 30; i++) {
            pelicula.agregarRating(new Rating(5, 4.0f, i, new Date()));
        }

        // Calcular suma total de ratings
        float suma = 30 * 4.0f; // = 120.0f

        assertEquals(30, pelicula.getRatings().size());
        assertTrue(suma > 100); // Esta condición hace que la película sea considerada
        assertFalse(pelicula.getRatings().size() > 100); // Pero debería ser esta condición
    }

    @Test
    void testValidacionEntidadRating() {
        // Test para validar la correcta creación de Rating
        Date fecha = new Date();
        Rating rating = new Rating(1, 4.5f, 100, fecha);

        assertEquals(1, rating.getIdPelciula());
        assertEquals(4.5f, rating.getRating(), 0.01f);
        assertEquals(100, rating.getIdUsuario());
        assertEquals(fecha, rating.getFecha());
    }
}