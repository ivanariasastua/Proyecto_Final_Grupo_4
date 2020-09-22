
package org.una.aeropuerto.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.una.aeropuerto.entities.IncidentesEstados;
import org.una.aeropuerto.entities.IncidentesRegistrados;

/**
 *
 * @author cordo
 */
public interface IIncidentesRegistradosRepository extends JpaRepository<IncidentesRegistrados, Long>{
    
    public List<IncidentesRegistrados> findByCategoria(Long id);
    
    public List<IncidentesRegistrados> findByAreaTrabajoId(Long id);
    
    public List<IncidentesRegistrados> findByAreaTrabajo(Long id);
    
    @Query("Select ir from IncidentesRegistrados ir " +
           "join ir.emisor emp on ir.emisor = emp.id or ir.responsable = emp.id " +
           "join ir.categoria cat on ir.categoria = cat.id " +
           "join ir.areaTrabajo at on ir.areaTrabajo = at.id " +
           "where UPPER(emp.nombre) like :nomEmisor and UPPER(emp.cedula) like :cedEmisor and " +
           "UPPER(emp.nombre) like :nomResponsable and UPPER(emp.cedula) like :cedResponsable and " +
           "UPPER(cat.nombre) like :nomCategoria and UPPER(at.nombre) like :nomArea")
    public List<IncidentesEstados> filtro(String nomEmisor, String cedEmisor, String nomResponsable, String cedResponsable, String nomCategoria, String nomArea);

    @Query("update IncidentesRegistrados em set em.estado = 0 where em.id = id")
    public void Inactivar(Long id);
}
