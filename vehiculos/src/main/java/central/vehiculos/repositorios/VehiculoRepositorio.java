package central.vehiculos.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import central.vehiculos.Entidad.Vehiculo;

public interface VehiculoRepositorio extends JpaRepository<Vehiculo, String> {

}