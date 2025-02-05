package zona_fit.datos;

import zona_fit.conexion.Conexion;
import zona_fit.dominio.Cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO implements IClienteDAO{

    @Override
    public List<Cliente> listarCLiente() {
        List<Cliente> clientes = new ArrayList<>();
        PreparedStatement ps;
        ResultSet rs;
        Connection con = Conexion.getConexion();
        var sql = "SELECT * FROM cliente ORDER BY id";
        try{
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                var cliente = new Cliente();
                cliente.setId(rs.getInt("id"));
                cliente.setNombre(rs.getString("nombre"));
                cliente.setApellido(rs.getString("apellido"));
                cliente.setMembresia(rs.getInt("membresia"));
                clientes.add(cliente);
            }
        }catch (Exception e){
            System.out.println("Error al listar" + e.getMessage());
        }
        finally {
            try{
                con.close();
            }catch (Exception e){
                System.out.println("Error al cerrar" + e.getMessage());
            }
        }
        return clientes;
    }

    @Override
    public boolean buscarClienteporId(Cliente cliente) {
        PreparedStatement ps;
        ResultSet rs;
        var con = Conexion.getConexion();
        var sql = "SELECT * FROM cliente WHERE id = ?";
        try{
            ps = con.prepareStatement(sql);
            ps.setInt(1, cliente.getId());
            rs = ps.executeQuery();
            if (rs.next()){
                cliente.setNombre(rs.getString("nombre"));
                cliente.setApellido(rs.getString("apellido"));
                cliente.setMembresia(rs.getInt("membresia"));
                return true;
            }
        }catch(Exception e){
            System.out.println("Error al recuperar cliente por id" + e.getMessage());
        }
        finally{
            try{
                con.close();
            }catch(Exception e){
                System.out.println("Error al cerrar"+e.getMessage());
            }
        }
        return false;
    }

    @Override
    public boolean agregarCliente(Cliente cliente) {
        return false;
    }

    @Override
    public boolean modificarCliente(Cliente cliente) {
        return false;
    }

    @Override
    public boolean eliminarCliente(Cliente cliente) {
        return false;
    }

    public static void main(String[] args) {
        IClienteDAO clienteDao = new ClienteDAO();
        // Listar cliente
        /*System.out.println("Listar CLientes");


        var clientes = clienteDao.listarCLiente();
        clientes.forEach(System.out::println);*/

        var cliente1 = new Cliente(1);
        System.out.println("Cliente antes de la busqueda"+ cliente1);
        var encontrado = clienteDao.buscarClienteporId(cliente1);
        if(encontrado){
            System.out.println("Cliente encontrado"+ cliente1);
        }
        else{
            System.out.println("No se encontro registro");
        }
    }
}
