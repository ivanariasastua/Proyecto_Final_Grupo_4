
package org.una.aeropuerto.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.una.aeropuerto.entities.IncidentesRegistrados;

/**
 *
 * @author cordo
 */
public interface IIncidentesRegistradosRepository extends JpaRepository<IncidentesRegistrados, Long>{
    
    public List<IncidentesRegistrados> findByCategoria(Long id);
    
    public List<IncidentesRegistrados> findByAreaTrabajoId(Long id);
    
    public List<IncidentesRegistrados> findByAreaTrabajo(Long id);
}
