package central.vehiculos.servicios;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import central.vehiculos.Entidad.Usuario;
import central.vehiculos.Entidad.Vehiculo;
import central.vehiculos.repositorios.UsuarioRepositorio;
import central.vehiculos.repositorios.VehiculoRepositorio;

import java.awt.*;
import java.util.List;
import java.util.Optional;

@Service
@WebService(serviceName = "usuarios")
public class SOAPImplementacion {

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Autowired
    private VehiculoRepositorio vehiculoRepositorio;



    @WebMethod(operationName = "añadir")
    private void addUsuario(@WebParam Usuario usuario) {
        usuarioRepositorio.save(usuario);
    }

    @WebMethod(operationName = "obtener")
    private List<Usuario> getUsuarios() {
        return usuarioRepositorio.findAll();
    }

    @WebMethod(operationName = "obtenerVehiculos")
    public List<Vehiculo> consultarVehiculos() {
        return vehiculoRepositorio.findAll();
    }

    @WebMethod(operationName = "obtenerVehiculo")
    public Vehiculo obtenerVehiculo(@WebParam(name = "placaVehiculo") String placa){
        Vehiculo vehiculo = vehiculoRepositorio.findById(placa).orElse(null);
        return vehiculo;
    }

    @WebMethod(operationName = "consultaValorSeguro2")
    private Long consultaValorSeguro2(@WebParam(name = "placaVehiculo") String placa) {
        Optional<Vehiculo> vehiculoOptional = vehiculoRepositorio.findById(placa);
        if (vehiculoOptional.isPresent()) {
            Vehiculo vehiculo = vehiculoOptional.get();
            return vehiculo.consultarSeguro();
        } else {
            // Manejo de vehículo no encontrado
            return null;
        }
    }

    @WebMethod(operationName = "crearVehiculo")
    public boolean crearVehiculo(@WebParam(name = "placaVehiculo") String placa,
                                 @WebParam(name = "modeloVehiculo") int modelo, @WebParam(name = "precioVehiculo") long precio,
                                 @WebParam(name = "Color") String Color) {
        try {

            Optional<Vehiculo> existingVehiculo = vehiculoRepositorio.findById(placa);
            if (existingVehiculo.isPresent()) {
                // El vehículo ya existe
                return false;
            } else {
                // Crear un nuevo
                Vehiculo vehiculo = new Vehiculo(placa, modelo, precio, Color);
                vehiculoRepositorio.save(vehiculo);
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @WebMethod(operationName = "consultarSOAT")
    public Long consultarSOAT(@WebParam(name = "placaVehiculo") String placa) {
        Vehiculo vehiculo = vehiculoRepositorio.findById(placa).orElse(null);
        if (vehiculo == null) {
            return null; // Puedes devolver null o algún valor de error si lo prefieres
        }

        int modelo = vehiculo.getModelo();
        int antiguedad = 2023 - modelo;
        Long valorSOAT;

        if (antiguedad < 5) {
            valorSOAT = 100000L;
        } else {
            valorSOAT = 150000L;
        }
        return valorSOAT;
    }

        }


