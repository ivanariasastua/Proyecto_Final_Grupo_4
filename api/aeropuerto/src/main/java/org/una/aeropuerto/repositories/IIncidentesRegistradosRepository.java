
package org.una.aeropuerto.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.una.aeropuerto.entities.IncidentesRegistrados;

/**
 *
 * @author cordo
 */
public interface IIncidentesRegistradosRepository extends JpaRepository<IncidentesRegistrados, Long>{
    
    @Query("Select i from IncidentesRegistrados i "+
            "join i.categoria eat on i.id = eat.id "+
            "where UPPER(eat.nombre) like CONCAT('%', UPPER(:categoria), '%')")
    public List<IncidentesRegistrados> findByCategoria(@Param("categoria")String categoria);
    
    @Query("Select i from IncidentesRegistrados i "+
            "join i.emisor eat on i.id = eat.id "+
            "where UPPER(eat.nombre) like CONCAT('%', UPPER(:emisor), '%')")
    public List<IncidentesRegistrados> findByEmisor(@Param("emisor")String emisor);
    
    @Query("Select i from IncidentesRegistrados i "+
            "join i.areaTrabajo eat on i.id = eat.id "+
            "where UPPER(eat.nombre) like CONCAT('%', UPPER(:area), '%')")
    public List<IncidentesRegistrados> findByAreas(@Param("area")String area);

    @Query("Select i from IncidentesRegistrados i "+
            "join i.responsable eat on i.id = eat.id "+
            "where UPPER(eat.nombre) like CONCAT('%', UPPER(:responsable), '%')")
    public List<IncidentesRegistrados> findByResponsable(@Param("responsable")String responsable);
}
