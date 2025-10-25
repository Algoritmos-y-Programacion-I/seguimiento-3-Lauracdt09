package model;

import java.time.LocalDate;

public class SchoolController {
    private String name;
    private int hourSpentSupport;
    private Computer [][] estructuraComputadores;

    public SchoolController(String name) {
        this.name = name;
        this.hourSpentSupport = 0;
        this.estructuraComputadores = new Computer[5][10];
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHourSpentSupport() {
        return hourSpentSupport;
    }

    public void setHourSpentSupport(int hourSpentSupport) {
        this.hourSpentSupport = hourSpentSupport;
    }

    public Computer[][] getEstructuraComputadores() {
        return estructuraComputadores;
    }

    public void setEstructuraComputadores(Computer[][] estructuraComputadores) {
        this.estructuraComputadores = estructuraComputadores;
    }

    /**
     * Descripción:
     * Este método modificador registra un nuevo computador en la matriz de estructura de computadores
     * del colegio, ubicándolo en el piso y la columna especificados.
     * Además, verifica que el número de serie no exista y que ubicación (piso y columna) sea
     * válida y esté disponible antes de registrar el computador.
     * 
     * Precondición:
     * El parámetro piso debe encontrarse dentro de los rangos válidos.
     * El número de serial no debe existir previamente en la matriz de computadores.
     * 
     * Postcondición:
     * Si se cumplen las condiciones anteriores el nuevo computador se agrega correctamente en 
     * la posición deseada de la matriz estructuraComputadores.
     * Si no se cumplen, no se realiza ningún cambio.
     * 
     * @param serialNumber es el número serial del computador, que no debe existir previamente.
     * @param piso es el piso del edificio de computadores que debe estar dentro del rango válido (1-5).
     * @param nextWindow indica si el computador está cerca de una ventana (true = sí, false = no)
     * @return boolean, true si el computador fue agregado exitosamente;
     * false si el número de serie ya existe, la posición es inválida o el espacio ya está ocupado.
     */
    public boolean agregarComputador(String serialNumber, int piso, boolean nextWindow) {
        if (searchSerial(serialNumber) != null){
            System.out.println("⚠︎ No puede agregar un computador, el número serial " + serialNumber + " ya existe.");
            return false;
        }
        if (piso < 1 || piso > 5){
            System.out.println("⚠︎ El piso que desea registrar no existe");
            return false;
        }
        int indicePiso = piso - 1;
        for (int j = 0; j < 10; j++) {
            if (estructuraComputadores[indicePiso][j] == null) {
                Computer newComputer = new Computer(serialNumber, nextWindow);
                estructuraComputadores[indicePiso][j] = newComputer;
                System.out.println("Computador agregado exitosamente en Piso " + piso + ", Columna " + (j + 1));
                return true;
            }
        }
        
        System.out.println(" ⚠︎ Error: No hay espacio disponible en el piso " + piso);
        return false;
    }

    /**
     * Descripción:
     * Este método analizador busca un computador por su número de serie escaneando la matriz de estructura de computadores.
     * 
     * Precondición:
     * no debe ser null el numero serial.
     * 
     * Postcondición:
     * no modifica el estado del sistema. Devuelve la referencia al computador cuyo serial coincide 
     * o null si no se encuentra.
     * 
     * @param serialNumber es el número de serie a buscar.
     * @return el Computer encontrado o null si no existe.
     */

    private Computer searchSerial(String serialNumber) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 10; j++) {
                Computer computer = estructuraComputadores[i][j];
                if (computer != null && computer.getSerialNumber().equals(serialNumber)) {
                    return computer;
                }
            }
        }
        return null;
    }

    /**
     * Este método modificador registra un nuevo incidente para el computador ubicado en la pocisión indicada
     * (piso, columna), validando que la ubicación sea válida, que exista un computador en esa celda de la matriz 
     * y que el serial ingresado corresponda con el del computador almacenado. 
     * Si el incidente se marca resuelto registra las horas de solución y actualiza el acumulado de las mismas.
     * 
     * Precondición:
     * Debe existir un computador en estructura computadores [piso -1][columna-1] y su serial debe coincidir con 
     * serialNumber. 
     * Se asume que description no es nula ni vacía.
     * Si resuelto es true, entonces horas debe ser >= 0 y el total acumulado no puede superar 100.
     * 
     * Postcondición:
     * Se agrega un objeto Incident con fecha actual y la descripción dada a la lista de incidentes del computador. 
     * El tamaño de dicha lista aumenta en 1.
     * Si resuelto es true, el incidente queda marcado como solucionado, se registran las horas en solutionHours
     * y se incrementa hourSpentSupport en horas.
     * Si alguna validación falla, no se realizan cambios
     * 
     * @param serialNumber es el número serial que debe coincidir con el computador en la ubicación indicada.
     * @param piso es el piso del edificio de computadores que debe estar dentro del rango válido (1-5).
     * @param columna es el columna del piso del edificio de computadores que debe estar dentro del rango válido (1-10).
     * @param description es la descripción del incidente reportado.
     * @param resuelto indica si el incidente fue solucionado.
     * @param horas horas de solucion a registrar
     * @return boolean true si el incidente fue agregado; false si la ubicación es inválida, no hay computador registrado
     * o el serial no coincide.
     */

    public boolean agregarIncidenteEnComputador(String serialNumber, int piso, int columna, String description, boolean resuelto, int horas) {
        if (piso < 1 || piso > 5 || columna < 1 || columna > 10) {
            System.out.println("⚠︎ La ubicación es inválida");
            return false;
        }
        Computer computer = estructuraComputadores[piso-1][columna -1];

        if (computer == null){
            System.out.println("⚠︎ No hay un computador registrado en esa ubicación");
            return false;
        }
        if (!computer.getSerialNumber().equals(serialNumber)){
            System.out.println("⚠︎ El numero serial ingresado no corresponde al computador seleccionado");
            return false;
        }
        if (resuelto && horas > 0) {
        if (hourSpentSupport + horas > 100) {
            System.out.println("⚠︎ Error: Se excederia el limite de 100 horas de soporte");
            System.out.println("Horas disponibles: " + (100 - hourSpentSupport));
            return false;
        }
    }
        Incident newIncident = new Incident(LocalDate.now(), description);
        if (resuelto) {
            newIncident.setSolution(true);
            newIncident.setSolutionHours(horas);
            hourSpentSupport += horas;
        }
        computer.addIncident(newIncident);
        
        System.out.println("✔ El incidente fue reportado exitosamente");
        if (resuelto) {
            System.out.println("Horas de soporte gastadas: " + hourSpentSupport + "/100");
        }
        return true;
    }

    /**
     * Descripción: 
     * Este método analizador encuentra el computador con mayor número de incidentes en el sistema y presenta en consola
     * un reporte con sus datos (incluye ubicación y detalle de incidentes).
     * 
     * Precondición: ninguna
     * Postcondición: no modifica el estado del sistema. Imprime en consola el reporte.
     * Si no hay computadores registrados, imprime un mensaje informativo y finaliza.
     * 
     */

    public void getComputerList() {
        Computer computadorMasIncidentes = null;
        int pisoEncontrado = -1;
        int columnaEncontrada = -1;
        int maxIncidents = -1;

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 10; j++) {
                Computer computer = estructuraComputadores[i][j];
                if (computer != null && computer.getIncidentSize() > maxIncidents) {
                    maxIncidents = computer.getIncidentSize();
                    computadorMasIncidentes = computer;
                    pisoEncontrado = i + 1;
                    columnaEncontrada = j + 1;
                }

            }
        }

        if (computadorMasIncidentes == null) {
            System.out.println("⚠︎ No hay computadores registrados en el sistema");
            return;
        }

        System.out.println("\n --- REPORTE: COMPUTADOR CON MÁS INCIDENTES ---");
        System.out.println(computadorMasIncidentes);
        System.out.println("\n - Ubicación: piso " + pisoEncontrado + " en la columna " + columnaEncontrada);
        System.out.println("Detalle de los incidentes: ");
       
        if (computadorMasIncidentes.getIncidentSize() == 0) {
            System.out.println("  (Sin incidentes reportados)");
            return;
        }else{
            for (int i = 0; i < computadorMasIncidentes.getIncidents().size(); i++) {
                System.out.println((i + 1) + ". " + computadorMasIncidentes.getIncidents().get(i));
        }
    }
    }
}
