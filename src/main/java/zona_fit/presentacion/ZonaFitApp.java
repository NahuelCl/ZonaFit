package zona_fit.presentacion;

import zona_fit.datos.ClienteDAO;

import java.util.Scanner;

public class ZonaFitApp {
    public static void main(String[] args) {
        ZonaFitApp();
    }

    private static void  ZonaFitApp(){
        var salir = false;
        var consola = new Scanner(System.in);

        // Creamos un objeto de la clase ClienteDAO

        var clienteDao = new ClienteDAO();
        while(!salir){
            try {
                mostrarMenu();
                //salir = ejecutarOpciones(consola, clienteDao);
            } catch (Exception e) {
                System.out.println("Error al ejecutar:" +e.getMessage());
            }
            System.out.println();

        }

    }
    private static void mostrarMenu(){
        System.out.println("""
                *** Zona Fit 
                1. Listar Clientes
                2. Buscar Clientes
                3. Agregar Cliente
                4. Modificar Cliente
                5. Eliminar Cliente
                6. Salir
                Elige una opcion:\s""");
    }
}
