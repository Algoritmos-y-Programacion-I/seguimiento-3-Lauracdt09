package ui;

import java.util.Scanner;
import model.SchoolController;

public class SchoolApp {
    private SchoolController controller;
    private Scanner input;

    public static void main(String[] args) {
        SchoolApp ui = new SchoolApp();
        ui.menu();

    }

    // Constructor
    public SchoolApp() {
        input = new Scanner(System.in);
        controller = new SchoolController("Computaricemos");
    }

    public void menu() {

        System.out.println("\n Bienvenido a Computaricemos");

        int option = 0;
        do {
            System.out.println("\nMenu Principal");
            System.out.println("--------------------------------------------------------");
            System.out.println("Digite alguna de las siguientes opciones");
            System.out.println("1) Registrar computador");
            System.out.println("2) Registrar incidente en computador");
            System.out.println("3) Consultar el computador con más incidentes");
            System.out.println("0) Salir del sistema");
            System.out.print("Opción: ");
            option = input.nextInt();
            input.nextLine();

            switch (option) {
                case 1:
                    registrarComputador();
                    break;
                case 2:
                    registrarIncidenteEnComputador();
                    break;
                case 3:
                    consultarComputadorConMasIncidentes();
                    break;
                case 0:
                    System.out.println("\nGracias por usar nuestros servicios. Adios!");
                    break;
                default:
                    System.out.println("\nOpción inválida. Intente nuevamente.");
                    break;
            }

        } while (option != 0);
        input.close();

    }

    public void registrarComputador() {
        System.out.println("\n--- REGISTRAR COMPUTADOR ---");
        System.out.println("Ingrese el número serial: ");
        String serialNumber = input.nextLine();

        System.out.println("Ingrese el piso (1-5): ");
        int piso = input.nextInt();
        input.nextLine();

        System.out.println("¿Está cerca de una ventana (true/false)?");
        boolean nextWindow = input.nextBoolean();
        input.nextLine();

        controller.agregarComputador(serialNumber, piso, nextWindow);
    }

    public void registrarIncidenteEnComputador() {
        System.out.println("\n --- REGISTRAR INCIDENTE EN COMPUTADOR ---");
        System.out.print("Ingrese el número serial: ");
        String serialNumber = input.nextLine();

        System.out.println("Ingrese el piso (1-5): ");
        int piso = input.nextInt();
        input.nextLine();

        System.out.println("Ingrese la columna (1-10): ");
        int columna = input.nextInt();
        input.nextLine();

        System.out.println("Ingrese la descripción del incidente: ");
        String description = input.nextLine();

        System.out.print("El incidente ya esta resuelto (true/false): ");
        boolean resuelto = input.nextBoolean();
        input.nextLine();

        int horas = 0;
        if (resuelto) {
            System.out.print("Cuantas horas tomó resolver el incidente: ");
            horas = input.nextInt();
            input.nextLine();
        }
        controller.agregarIncidenteEnComputador(serialNumber, piso, columna, description, resuelto, horas);       
    }

    public void consultarComputadorConMasIncidentes() {
        controller.getComputerList();
    }

}
