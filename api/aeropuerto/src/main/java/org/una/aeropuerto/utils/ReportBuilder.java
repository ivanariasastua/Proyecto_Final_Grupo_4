/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aeropuerto.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import org.springframework.util.ResourceUtils;
import org.una.aeropuerto.dto.ServiciosGastosDTO;
import org.una.aeropuerto.AeropuertoApplication;

/**
 *
 * @author Ivan Josué Arias Astua
 */
public class ReportBuilder {
    
    public static JasperPrint reporteGastos(List<ServiciosGastosDTO> lista){
        try {
            List<ReporteGastos> datos = new ArrayList<>();
            Float costo = 0F;
            lista.forEach(gasto -> {
                datos.add(new ReporteGastos(gasto));
            });
            costo = datos.stream().map(dato -> Float.valueOf(dato.getCosto())).reduce(costo, (accumulator, _item) -> accumulator + _item);
            HashMap<String, Object> map = new HashMap<>();
            map.put("costo", String.valueOf(costo));
            map.put("gastos", String.valueOf(datos.size()));
            File file = ResourceUtils.getFile("classpath:rep_servicios_gastos.jrxml");
            JasperReport report = JasperCompileManager.compileReport(file.getAbsolutePath());
            JasperPrint jprint = JasperFillManager.fillReport(report, map, new JRBeanCollectionDataSource(datos));
            return jprint;
            /*JasperViewer viewer = new JasperViewer(jprint, false);
            viewer.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            return viewer;*/
        } catch (JRException ex) {
            System.out.println("Error al cargar el reporte [ "+ex+" ]");
            return null;
        } catch (FileNotFoundException ex) {
            System.out.println("Error al cargar el reporte [ "+ex+" ]");
            return null;
        }
    }
}
