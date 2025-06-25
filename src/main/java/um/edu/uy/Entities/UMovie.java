package um.edu.uy.Entities;

import um.edu.uy.Cargas.CargarDatos;
import um.edu.uy.Metodos.*;

import java.util.InputMismatchException;
import java.util.Scanner;

public class UMovie {

    private CargarDatos datos;

    public UMovie() {
        // Constructor vacío
        this.datos = new CargarDatos();
    }

    public void iniciar() {
        boolean activo = true;
        Scanner scanner = new Scanner(System.in);

        while (activo) {
            mostrarMenuPrincipal();
            try {
                int opcion = scanner.nextInt();
                activo = manejarOpcionPrincipal(opcion);
            } catch (InputMismatchException e) {
                mostrarErrorPrincipal();
                scanner.nextLine(); // limpiar entrada
            }
        }
    }

    private void mostrarMenuPrincipal() {
        System.out.println("========== MENÚ PRINCIPAL ==========");
        System.out.println("1. Cargar Datos");
        System.out.println("2. Ejecutar Consultas");
        System.out.println("3. Salir");
        System.out.println("=====================================");
    }

    private void mostrarMenuConsultasTexto() {
        System.out.println("========== MENÚ DE CONSULTAS ==========");
        System.out.println("1. Top 5 películas con más calificaciones por idioma");
        System.out.println("2. Top 10 películas con mejor calificación media");
        System.out.println("3. Top 5 sagas o películas con más ingresos generados");
        System.out.println("4. Top 10 directores mejor calificados");
        System.out.println("5. Actor con más calificaciones recibidas por mes");
        System.out.println("6. Usuarios con más calificaciones por género");
        System.out.println("7. Volver al menú principal");
        System.out.println("==========================================");
    }

    private void mostrarErrorPrincipal() {
        System.out.println("[ERROR] Opción no válida. Ingrese un número entre 1 y 3.");
    }

    private void mostrarErrorConsultas() {
        System.out.println("[ERROR] Opción no válida. Ingrese un número entre 1 y 7.");
    }

    private boolean manejarOpcionPrincipal(int opcion) {
        switch (opcion) {
            case 1:{
                if (!datos.cargado()) {
                    datos.cargarDatos();
                } else {
                    System.out.println("Los datos ya han sido cargados.");
                }
                break;
            }
            case 2: {
                if (datos.cargado()) {
                    mostrarMenuConsultas();
                } else {
                    System.out.println("Primero debe cargar los datos (opción 1).");
                }
                break;
            }
            case 3: {
                System.out.println("¡Gracias por usar UMovie! Saliendo del sistema...");
                return false;
            }
            default:
                mostrarErrorPrincipal();
                break;
        }
        return true;
    }

    private void mostrarMenuConsultas() {
        boolean enConsultas = true;
        Scanner scanner = new Scanner(System.in);

        while (enConsultas) {
            mostrarMenuConsultasTexto();
            try {
                int opcion = scanner.nextInt();
                enConsultas = manejarOpcionConsulta(opcion);
            } catch (InputMismatchException e) {
                mostrarErrorConsultas();
                scanner.nextLine();
            }
        }
    }

    private boolean manejarOpcionConsulta(int opcion) {
        switch (opcion) {
            case 1:
                new Top5PeliculasPorIdioma(datos).realizarConsulta();
                break;
            case 2:
                new Top10PeliculasMejorCalificacionPorUsuarios(datos).realizarConsulta();
                break;
            case 3:
                new Top5SagasMasExitosas(datos).realizarConsulta();
                break;
            case 4:
                new Top10DirectoresMejorCalificacion(datos).realizarConsulta();
                break;
            case 5:
                new ActorConMasCalificacionesEnCadaMesDelAnio(datos).realizarConsulta();
                break;
            case 6:
                new UsuarioConMasCalificacionesPorGenero(datos).realizarConsulta();
                break;
            case 7: {
                System.out.println("Volviendo al menú principal...");
                return false;
            }
            default:
                mostrarErrorConsultas();
                break;
        }
        return true;
    }
}