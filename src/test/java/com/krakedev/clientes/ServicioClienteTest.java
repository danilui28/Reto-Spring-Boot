package com.krakedev.clientes;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.krakedev.clientes.entidades.Cliente;
import com.krakedev.clientes.servicios.ServicioCliente;

public class ServicioClienteTest {


    @Test
    public void crearClienteNuevoTest() {


        ServicioCliente servicio = new ServicioCliente();

        Cliente cliente = new Cliente("1723456789", "Luis", "Mendoza","luismendoza1008@gmail.com");

        Cliente resultado = servicio.crear(cliente);

        assertEquals(cliente, resultado);

        assertEquals(1, servicio.listar().size());
    }

    @Test
    public void crearClienteDuplicadoTest() {

        ServicioCliente servicio = new ServicioCliente();

        Cliente cliente1 = new Cliente("1723456789", "Luis", "Mendoza","luismendoza1008@gmail.com");

        Cliente cliente2 = new Cliente("1723456789", "Andres", "Castillo", "andrescastillo2020@gmail.com");

        servicio.crear(cliente1);

        Cliente resultado = servicio.crear(cliente2);

        assertNull(resultado);

        assertEquals(1, servicio.listar().size());
    }

    @Test
    public void buscarPorCedulaExistenteTest() {


        ServicioCliente servicio = new ServicioCliente();

        Cliente cliente = new Cliente("0912345678", "Carlos", "Ramirez", "carlos28ramirez@gmail.com");

        servicio.crear(cliente);

        Cliente resultado = servicio.buscarPorCedula("0912345678");

        assertEquals(cliente, resultado);

        assertEquals("Carlos", resultado.getNombre());
    }

    @Test
    public void buscarPorCedulaInexistenteTest() {


        ServicioCliente servicio = new ServicioCliente();

        Cliente cliente = new Cliente("0912345678", "Carlos", "Ramirez", "carlos28ramirez@gmail.com");

        servicio.crear(cliente);

        Cliente resultado = servicio.buscarPorCedula(
                "1109876543");

        assertNull(resultado);
    }


    @Test
    public void listarClientesTest() {

        ServicioCliente servicio = new ServicioCliente();

        Cliente cliente1 = new Cliente("1102345678", "Sofia", "Vargas", "sofiUwUvargas@gmail.com");

        Cliente cliente2 = new Cliente("0709876543", "Daniel", "Torres", "danirep2017torres@gmail.com");

        servicio.crear(cliente1);

        servicio.crear(cliente2);

        List<Cliente> resultado = servicio.listar();

        assertEquals(2, resultado.size());

        assertTrue(resultado.contains(cliente1));

        assertTrue(resultado.contains(cliente2));
    }

    @Test
    public void listarClientesVacioTest() {


        ServicioCliente servicio = new ServicioCliente();

        List<Cliente> resultado = servicio.listar();

        assertTrue(resultado.isEmpty());

        assertEquals(0, resultado.size());
    }


    @Test
    public void actualizarClienteExistenteTest() {


        ServicioCliente servicio = new ServicioCliente();

        Cliente cliente = new Cliente("0601234567", "Miguel", "Herrera", "miguelilloherro20@gmail.com");

        servicio.crear(cliente);

        Cliente clienteActualizado = new Cliente("0601234567", "Fernando", "Salazar", "salazarfernan10@gmail.com");

        Cliente resultado = servicio.actualizar(
                "0601234567", clienteActualizado);

        assertEquals("Fernando", resultado.getNombre());

        assertEquals("Salazar", resultado.getApellido());

        assertEquals("0601234567", resultado.getCedula());
    }

    @Test
    public void actualizarClienteInexistenteTest() {


        ServicioCliente servicio = new ServicioCliente();

        Cliente clienteActualizado = new Cliente("1508765432", "Gabriela", "Navarro", "lagabi20nav@gmail.com");

        Cliente resultado = servicio.actualizar(
                "1508765432", clienteActualizado);

        assertNull(resultado);

        assertEquals(0, servicio.listar().size());
    }

    @Test
    public void actualizarClienteConservarCedulaTest() {


        ServicioCliente servicio = new ServicioCliente();

        Cliente cliente = new Cliente("0807654321", "Roberto", "Molina", "molineroRoberto2022@gmail.com");

        servicio.crear(cliente);

        Cliente clienteActualizado = new Cliente("0101234567", "Diego", "Paredes", "paredeselDiego10@gmail.com");

        Cliente resultado = servicio.actualizar(
                "0807654321", clienteActualizado);

        assertEquals("0807654321", resultado.getCedula());

        assertEquals("Diego", resultado.getNombre());

        assertEquals("Paredes", resultado.getApellido());
    }


    @Test
    public void eliminarClienteExistenteTest() {


        ServicioCliente servicio = new ServicioCliente();

        Cliente cliente = new Cliente("0503456789", "Patricia", "Cedeño", "patriciaCede20033@gmail.com");

        servicio.crear(cliente);

        boolean resultado = servicio.eliminar(
                "0503456789");

        assertTrue(resultado);

        assertEquals(0, servicio.listar().size());

        assertNull(servicio.buscarPorCedula("0503456789"));
    }

    @Test
    public void eliminarClienteInexistenteTest() {


        ServicioCliente servicio = new ServicioCliente();

        Cliente cliente = new Cliente("1204567890", "Andrea", "Flores", "andreitaleflor@gmail.com");

        servicio.crear(cliente);

        boolean resultado = servicio.eliminar(
                "1809876543");

        assertFalse(resultado);

        assertEquals(1, servicio.listar().size());
    }

    @Test
    public void eliminarClienteConservarOtrosClientesTest() {

        ServicioCliente servicio = new ServicioCliente();

        Cliente cliente1 = new Cliente("1305678901", "Jorge", "Acosta", "jorgeacostadecosta20212@gmail.com");

        Cliente cliente2 = new Cliente("0406789012", "Valeria", "Morales", "valm.morales@gmail.com");

        servicio.crear(cliente1);

        servicio.crear(cliente2);

        boolean resultado = servicio.eliminar(
                "1305678901");

        assertTrue(resultado);

        assertEquals(1, servicio.listar().size());

        assertNull(servicio.buscarPorCedula("1305678901"));

        assertEquals(cliente2,
                servicio.buscarPorCedula("0406789012"));
    }

}
