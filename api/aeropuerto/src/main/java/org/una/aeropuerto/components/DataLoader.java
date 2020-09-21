
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.una.aeropuerto.dto.EmpleadosDTO;
import org.una.aeropuerto.dto.RolesDTO;
import org.una.aeropuerto.entities.Roles;
import org.una.aeropuerto.services.IEmpleadosService;
import org.una.aeropuerto.services.IRolesService;



@Component
public class DataLoader implements ApplicationRunner {

    @Value("${app.admin-user}")
    private String cedula;

    @Value("${app.password}")
    private String password;

    @Autowired
    private IEmpleadosService empleadosService;

    @Autowired
    private IRolesService rolesService;

    @Override
    public void run(ApplicationArguments args) {

        if (empleadosService.findByCedula(cedula).isEmpty()) {
            
            RolesDTO rol;
            final String nombre = "GESTOR";
            
            Optional<List<RolesDTO>> Rol = rolesService.findByNombre(nombre);

            if (Rol.isEmpty()) { 
                rol = new RolesDTO();
                rol.setNombre(nombre);
                rol = rolesService.create(rol);

            } else {
                rol = Rol.get().get(0);
            }
            
            EmpleadosDTO empleado = new EmpleadosDTO();
            empleado.setNombre("Usuario");
            empleado.setCedula(cedula);
            empleado.setRol(rol);
            empleado.setContrasenaEncriptada(password);
            
            empleadosService.create(empleado);

            System.out.println("Se agrega el usuario inicial");
        } else {
            System.out.println("Se encontro el admin");
        }

    }
}

