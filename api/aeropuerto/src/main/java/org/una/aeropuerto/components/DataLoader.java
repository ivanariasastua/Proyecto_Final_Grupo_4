package org.una.aeropuerto.components;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.una.aeropuerto.dto.EmpleadosDTO;
import org.una.aeropuerto.dto.RolesDTO;
import org.una.aeropuerto.loaders.Roles;
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
            
            crearRoles();
            
            RolesDTO rol;
            final String nombre = "GESTOR";
            
            Optional<List<RolesDTO>> Rol = rolesService.findByNombre(nombre);
            rol = Rol.get().get(0);
            
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
    
    private void crearRoles(){
        for(Roles rol : Roles.values()){
            RolesDTO rolDto = new RolesDTO();
            rolDto.setNombre(rol.getNombre());
            if(rolesService.findByNombre(rol.getNombre()).get().isEmpty()){
                rolesService.create(rolDto);
                System.out.println("Rol creado");
            }else{
                System.out.println("Rol encontrado");
            }
        }
    }
}

