
package org.una.aeropuerto.repositories;

import java.util.Date;
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
            "where UPPER(i.categoria.nombre) like CONCAT('%', UPPER(:categoria), '%')")
    public List<IncidentesRegistrados> findByCategoria(@Param("categoria")String categoria);
    
    @Query("Select i from IncidentesRegistrados i "+
            "where UPPER(i.emisor.nombre) like CONCAT('%', UPPER(:emisor), '%')")
    public List<IncidentesRegistrados> findByEmisor(@Param("emisor")String emisor);
    
    @Query("Select i from IncidentesRegistrados i "+
            "where UPPER(i.areaTrabajo.nombre) like CONCAT('%', UPPER(:area), '%')")
    public List<IncidentesRegistrados> findByAreas(@Param("area")String area);

    @Query("Select i from IncidentesRegistrados i "+
            "where UPPER(i.responsable.nombre) like CONCAT('%', UPPER(:responsable), '%')")
    public List<IncidentesRegistrados> findByResponsable(@Param("responsable")String responsable);
    
    @Query("SELECT i FROM IncidentesRegistrados i WHERE  UPPER(i.responsable.nombre) like CONCAT('%', UPPER(:responsable), '%') and i.fechaRegistro < :fechaIni " +
           "and i.estado = :estado and UPPER(i.emisor.nombre) like CONCAT('%', UPPER(:emisor), '%')")
    public List<IncidentesRegistrados> findByFiltro(@Param("fechaIni") Date fechaIni, @Param("estado")boolean estado, 
            @Param("responsable")String reponsable,@Param("emisor")String emisor);
}
