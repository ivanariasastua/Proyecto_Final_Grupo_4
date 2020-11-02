/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aeropuerto.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import org.una.aeropuerto.dto.ServiciosGastosDTO;
import org.una.aeropuerto.AeropuertoApplication;

/**
 *
 * @author Ivan Josué Arias Astua
 */
public class ReportBuilder {
    
    public static JasperViewer reporteGastos(List<ServiciosGastosDTO> lista){
        try {
            List<ReporteGastos> datos = new ArrayList<>();
            Float costo = 0F;
            lista.forEach(gasto -> {
                datos.add(new ReporteGastos(gasto));
            });
            costo = datos.stream().map(dato -> Float.valueOf(dato.getCosto())).reduce(costo, (accumulator, _item) -> accumulator + _item);
            HashMap<String, Object> map = new HashMap<>();
            map.put("costo", String.valueOf(costo));
            map.put("gastos", datos.size());
            JasperReport report = (JasperReport) JRLoader.loadObject(AeropuertoApplication.class.getResource("resources/rep_servicios_gastos.jrxml"));
            JasperPrint jprint = JasperFillManager.fillReport(report, map, new JRBeanCollectionDataSource(datos));
            JasperViewer viewer = new JasperViewer(jprint, false);
            viewer.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            return viewer;
        } catch (JRException ex) {
            System.out.println("Error al cargar el reporte [ "+ex+" ]");
            return null;
        }
    }
}
