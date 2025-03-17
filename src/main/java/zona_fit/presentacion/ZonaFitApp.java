package zona_fit.presentacion;

import zona_fit.datos.ClienteDAO;
import zona_fit.datos.IClienteDAO;
import zona_fit.dominio.Cliente;

import java.util.Scanner;

public class ZonaFitApp {
    public static void main(String[] args) {
        ZonaFitApp();
    }

    private static void  ZonaFitApp(){
        var salir = false;
        var consola = new Scanner(System.in);
        IClienteDAO clienteDao = new ClienteDAO();

        while(!salir){
            try {
                var opcion = mostrarMenu(consola);
                salir = ejecutarOpciones(consola,opcion, clienteDao);
            } catch (Exception e) {
                System.out.println("Error al ejecutar:" +e.getMessage());
            }
            System.out.println();

        }

    }
    private static int mostrarMenu(Scanner consola){
        System.out.println("""
                *** Zona Fit 
                1. Listar Clientes
                2. Buscar Clientes
                3. Agregar Cliente
                4. Modificar Cliente
                5. Eliminar Cliente
                6. Salir
                Elige una opcion:\s""");
        return Integer.parseInt(consola.nextLine());
    }

    private static boolean ejecutarOpciones(Scanner consola, int opcion, IClienteDAO clienteDAO){
        var salir = false;
        switch (opcion){
            case 1 -> {// listar cleintes
                System.out.println("--- Listado de cleintes---");
                var clientes = clienteDAO.listarCLiente();
                clientes.forEach(System.out::println);
            }
            case 2 -> { // buscar cleintes
                System.out.println("--- Buscar clientes---");
                var idCliente = Integer.parseInt(consola.nextLine());
                var cliente = new Cliente(idCliente);
                var encontrado = clienteDAO.buscarClienteporId(cliente);
                if(encontrado){
                    System.out.println("Cliente encontrado:" + cliente);
                }else{
                    System.out.println("Cliente no encontrado:");
                }
            }
            case 3 -> {//agregar cliente
                System.out.println("--- Agregar cliente---");
                System.out.println("Nombre: ");
                var nombre = consola.nextLine();
                System.out.println("Apellido: ");
                var apellido = consola.nextLine();
                System.out.println("Memebresia: ");
                var membresia = Integer.parseInt(consola.nextLine());
                //creamos el objeto
                var cliente = new Cliente(nombre,apellido,membresia);
                var agregado = clienteDAO.agregarCliente(cliente);
                if(agregado){
                    System.out.println("Cliente agregado: " + cliente);
                }else{
                    System.out.println("No agregado");
                }
            }

            case 4 -> {
                System.out.println("--- Modificar cliente---");
                System.out.println("Id Cliente: ");
                var idCliente = Integer.parseInt(consola.nextLine());
                System.out.println("Nombre: ");
                var nombre = consola.nextLine();
                System.out.println("Apellido: ");
                var apellido = consola.nextLine();
                System.out.println("Memebresia: ");
                var membresia = Integer.parseInt(consola.nextLine());
                //creamos el objeto
                var cliente = new Cliente(idCliente,nombre,apellido,membresia);
                var modificado = clienteDAO.modificarCliente(cliente);
                if(modificado){
                    System.out.println("Modificado: "+ cliente);
                }else{
                    System.out.println("No modificado");
                }
            }
            case 5 ->{
                System.out.println("---Eliminar cliente---");
                System.out.println("--- Id cliente:---");
                var idCliente = Integer.parseInt(consola.nextLine());
                var cliente = new Cliente(idCliente);
                var eliminado = clienteDAO.eliminarCliente(cliente);
                if(eliminado){
                    System.out.println("Eliminado "+ idCliente);
                }else{
                    System.out.println("No eliminado.");
                }
            }
            case 6 ->{
                System.out.println("Hasta pronto");
                salir = true;
            }
            default ->{
                System.out.println("Opcion no reconocida");
            }
        }
        return salir;
    }
}
