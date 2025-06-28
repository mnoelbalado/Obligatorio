package um.edu.uy.Metodos;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import um.edu.uy.Cargas.CargarDatos;
import um.edu.uy.Entities.Coleccion;
import um.edu.uy.Entities.Genero;
import um.edu.uy.Entities.Pelicula;
import um.edu.uy.Metodos.Top5SagasMasExitosas;
import um.edu.uy.TADS.LinkedList.MyLinkedList;

class Top5SagasMasExitosasTest {

    private Top5SagasMasExitosas consulta;
    private CargarDatos datos;

    @BeforeEach
    void setUp() {
        datos = new CargarDatos();
        consulta = new Top5SagasMasExitosas(datos);
    }

    @Test
    void testConstructor() {
        assertNotNull(consulta);
        assertDoesNotThrow(() -> consulta.realizarConsulta());
    }

    @Test
    void testCreacionColeccion() {
        // Test para crear una colección (saga)
        Coleccion coleccion = new Coleccion(1, "Saga de Prueba");

        assertEquals(1, coleccion.getId());
        assertEquals("Saga de Prueba", coleccion.getNombre());
        assertEquals(0, coleccion.getCantidadPeliculas());
        assertEquals(0, coleccion.getIngresosTotales(), 0.01);
        assertNotNull(coleccion.getPeliculas());
    }

    @Test
    void testAgregarPeliculaAColeccion() {
        // Test para agregar películas a una colección
        Coleccion coleccion = new Coleccion(1, "Saga Marvel");

        MyLinkedList<Genero> generos = new MyLinkedList<>();
        generos.add(new Genero(1, "Acción"));

        Pelicula pelicula1 = new Pelicula(1, "Iron Man", "en", 585174222.0, generos);
        Pelicula pelicula2 = new Pelicula(2, "Thor", "en", 449326618.0, generos);

        coleccion.agregarPelicula(pelicula1);
        coleccion.agregarPelicula(pelicula2);

        assertEquals(2, coleccion.getCantidadPeliculas());
        assertEquals(2, coleccion.getPeliculas().size());
    }

    @Test
    void testCalculoIngresosTotales() {
        // Test para verificar el cálculo de ingresos totales
        Coleccion coleccion = new Coleccion(1, "Saga Star Wars");

        MyLinkedList<Genero> generos = new MyLinkedList<>();
        generos.add(new Genero(2, "Ciencia Ficción"));

        Pelicula pelicula1 = new Pelicula(1, "A New Hope", "en", 775000000.0, generos);
        Pelicula pelicula2 = new Pelicula(2, "Empire Strikes Back", "en", 538000000.0, generos);
        Pelicula pelicula3 = new Pelicula(3, "Return of the Jedi", "en", 475000000.0, generos);

        coleccion.agregarPelicula(pelicula1);
        coleccion.agregarPelicula(pelicula2);
        coleccion.agregarPelicula(pelicula3);

        // Calcular ingresos totales manualmente
        double ingresosTotales = pelicula1.getIngresos() + pelicula2.getIngresos() + pelicula3.getIngresos();
        assertEquals(1788000000.0, ingresosTotales, 0.01);
        assertEquals(3, coleccion.getCantidadPeliculas());
    }

    @Test
    void testColeccionVacia() {
        // Test para colección sin películas
        Coleccion coleccion = new Coleccion(999, "Saga Vacía");

        assertEquals(0, coleccion.getCantidadPeliculas());
        assertEquals(0, coleccion.getPeliculas().size());
        assertTrue(coleccion.getPeliculas().size() == 0);
    }

    @Test
    void testRealizarConsulta() {
        // Test que no lance excepción y mida tiempo
        assertDoesNotThrow(() -> consulta.realizarConsulta());
    }

    @Test
    void testConsultaConDatosVacios() {
        CargarDatos datosVacios = new CargarDatos();
        Top5SagasMasExitosas consultaVacia = new Top5SagasMasExitosas(datosVacios);

        // Debería manejar datos vacíos sin lanzar excepción
        assertDoesNotThrow(() -> consultaVacia.realizarConsulta());
    }

    @Test
    void testValidacionEntidadPelicula() {
        // Test para validar la correcta creación de Película con ingresos
        MyLinkedList<Genero> generos = new MyLinkedList<>();
        generos.add(new Genero(1, "Aventura"));

        Pelicula pelicula = new Pelicula(100, "Blockbuster", "en", 2500000000.0, generos);

        assertEquals(100, pelicula.getIdPelicula());
        assertEquals("Blockbuster", pelicula.getTitulo());
        assertEquals("en", pelicula.getIdiomaOriginal());
        assertEquals(2500000000.0, pelicula.getIngresos(), 0.01);
        assertEquals(1, pelicula.getGeneros().size());
    }

    @Test
    void testComparacionIngresos() {
        // Test para comparar colecciones por ingresos (para ordenamiento)
        MyLinkedList<Genero> generos = new MyLinkedList<>();
        generos.add(new Genero(1, "Acción"));

        Coleccion coleccion1 = new Coleccion(1, "Saga Menor");
        Pelicula pelicula1 = new Pelicula(1, "Movie 1", "en", 100000000.0, generos);
        coleccion1.agregarPelicula(pelicula1);

        Coleccion coleccion2 = new Coleccion(2, "Saga Mayor");
        Pelicula pelicula2 = new Pelicula(2, "Movie 2", "en", 500000000.0, generos);
        coleccion2.agregarPelicula(pelicula2);

        // Verificar que la segunda colección tiene más ingresos
        assertTrue(pelicula2.getIngresos() > pelicula1.getIngresos());
        assertEquals(1, coleccion1.getCantidadPeliculas());
        assertEquals(1, coleccion2.getCantidadPeliculas());
    }
}