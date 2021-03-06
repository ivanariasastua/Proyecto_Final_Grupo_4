/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aeropuerto.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.util.ResourceUtils;
import org.una.aeropuerto.dto.ServiciosGastosDTO;
import org.una.aeropuerto.AeropuertoApplication;
import org.una.aeropuerto.dto.EmpleadosMarcajesDTO;
import org.una.aeropuerto.dto.IncidentesRegistradosDTO;

/**
 *
 * @author Ivan Josué Arias Astua
 */
public class ReportBuilder {

    public static JasperPrint reporteGastos(List<ServiciosGastosDTO> lista, HashMap<String, Object> map) {
        try {
            List<ReporteGastos> datos = new ArrayList<>();
            Float costo = 0F;
            lista.forEach(gasto -> {
                datos.add(new ReporteGastos(gasto));
            });
            costo = datos.stream().map(dato -> Float.valueOf(dato.getCosto())).reduce(costo, (accumulator, _item) -> accumulator + _item);
            map.put("costo", String.valueOf(costo));
            map.put("gastos", String.valueOf(datos.size()));
            File file = ResourceUtils.getFile("classpath:rep_servicios_gastos.jrxml");
            JasperReport report = JasperCompileManager.compileReport(file.getAbsolutePath());
            JasperPrint jprint = JasperFillManager.fillReport(report, map, new JRBeanCollectionDataSource(datos));
            return jprint;
        } catch (JRException ex) {
            System.out.println("Error al cargar el reporte [ " + ex + " ]");
            return null;
        } catch (FileNotFoundException ex) {
            System.out.println("Error al cargar el reporte [ " + ex + " ]");
            return null;
        }
    }

    public static JasperPrint reporteIncidente(List<IncidentesRegistradosDTO> lista,HashMap<String,Object> map) {
        try {
            List<ReporteIncidentes> datos = new ArrayList<>();
            lista.forEach(x -> {
                datos.add(new ReporteIncidentes(x));
            });
            map.put("total", String.valueOf(datos.size()));
            File file = ResourceUtils.getFile("classpath:rep_incidentes.jrxml");
            JasperReport report = JasperCompileManager.compileReport(file.getAbsolutePath());
            JasperPrint jprint = JasperFillManager.fillReport(report, map, new JRBeanCollectionDataSource(datos));
            return jprint;
        } catch (JRException ex) {
            System.out.println("Error al cargar el reporte [ " + ex + " ]");
            return null;
        } catch (FileNotFoundException ex) {
            System.out.println("Error al cargar el reporte [ " + ex + " ]");
            return null;
        }
    }
    
    public static JasperPrint reporteHorasLaboradas(List<ReporteHorarios> lista, String totalHoras, 
                                                    Date fecha1, Date fecha2, String encargado) throws FileNotFoundException{
        try {
            SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
            String f1 = formatoFecha.format(fecha1);
            String f2 = formatoFecha.format(fecha2);
            String fActual = formatoFecha.format(new Date());
            File file = ResourceUtils.getFile("classpath:rep_horas_laboradas.jrxml");
            HashMap<String, Object> map = new HashMap<>();
            map.put("total", totalHoras);
            map.put("fecha1", f1);
            map.put("fecha2", f2);
            map.put("encargado", encargado);
            map.put("fechaCreacion", fActual);
            JasperReport report = JasperCompileManager.compileReport(file.getAbsolutePath());
            JasperPrint jprint = JasperFillManager.fillReport(report, map, new JRBeanCollectionDataSource(lista));
            return jprint;
        } catch (JRException ex) {
            System.out.println("Error al cargar el reporte [ "+ex+" ]");
            return null;
        } catch (FileNotFoundException ex) {
            System.out.println("Error al cargar el reporte [ "+ex+" ]");
            return null;
        }
    }
}
