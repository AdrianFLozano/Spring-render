
package central.vehiculos.repositorios;


        import central.vehiculos.Entidad.Usuario;
        import org.springframework.data.jpa.repository.JpaRepository;
        import central.vehiculos.Entidad.Usuario;


public interface  UsuarioRepositorio extends JpaRepository<Usuario, Long> {

}
