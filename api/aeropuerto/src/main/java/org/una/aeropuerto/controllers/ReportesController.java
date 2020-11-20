/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aeropuerto.controllers;

import io.swagger.annotations.Api;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import net.sf.jasperreports.engine.JasperPrint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.una.aeropuerto.dto.EmpleadosMarcajesDTO;
import org.una.aeropuerto.dto.ServiciosGastosDTO;
import org.una.aeropuerto.services.IEmpleadosMarcajesService;
import org.una.aeropuerto.services.IReportesService;
import org.una.aeropuerto.utils.ReportBuilder;
import org.una.aeropuerto.utils.ReporteHorarios;
import org.una.aeropuerto.dto.IncidentesRegistradosDTO;

/**
 *
 * @author Ivan Josué Arias Astua
 */
@RestController
@RequestMapping("/reportes")
@Api(tags = {"Reportes"})
public class ReportesController {

    @Autowired
    private IReportesService service;

    @Autowired
    private IEmpleadosMarcajesService empMarcajeService;

    private final SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    private final SimpleDateFormat format2 = new SimpleDateFormat("dd/MM/yyyy");

    @GetMapping("reporteGastos1/{fecha}/{fecha2}/{empresa}/{servicio}/{estPago}/{estGasto}/{responsable}/{creador}")
    public ResponseEntity<?> reporteGastosConEstados(@PathVariable("fecha") Date fecha, @PathVariable("fecha2") Date fecha2, @PathVariable("empresa") String empresa,
            @PathVariable("servicio") String servicio, @PathVariable("estPago") boolean estPago, @PathVariable("estGasto") boolean estGasto, @PathVariable("responsable") String responsable, @PathVariable("creador") String creador) {
        try {
            Optional<List<ServiciosGastosDTO>> optional = service.serviciosGastos(empresa.equals("null") ? "%" : empresa, fecha, fecha2, servicio.equals("null") ? "%" : servicio, estPago, estGasto, responsable.equals("null") ? "%" : responsable);
            if (optional.isPresent()) {
                List<ServiciosGastosDTO> lista = optional.get();
                if (lista == null || lista.isEmpty()) {
                    return new ResponseEntity<>("La lista está vacía", HttpStatus.NOT_FOUND);
                } else {
                    HashMap<String, Object> parametros = new HashMap<>();
                    parametros.put("fecha_creacion", format.format(new Date()));
                    parametros.put("fecha_inicial", format2.format(fecha));
                    parametros.put("fecha_final", format2.format(fecha2));
                    parametros.put("servicio", servicio.equals("null") ? "Todos" : servicio);
                    parametros.put("empresa", empresa.equals("null") ? "Todas" : empresa);
                    parametros.put("responsable", responsable.equals("null") ? "Todos" : responsable);
                    parametros.put("creador", creador);
                    parametros.put("estado_pago", estPago ? "Pagos" : "No pagos");
                    parametros.put("estado_gasto", estGasto ? "Activos" : "Inactivos");
                    return new ResponseEntity<>(convertirReporte(lista, parametros), HttpStatus.OK);
                }
            } else {
                return new ResponseEntity<>("Lista Vacia", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("reporteGastos2/{fecha}/{fecha2}/{empresa}/{servicio}/{responsable}/{creador}")
    public ResponseEntity<?> reporteGastosSinEstados(@PathVariable("fecha") Date fecha, @PathVariable("fecha2") Date fecha2, @PathVariable("empresa") String empresa,
            @PathVariable("servicio") String servicio, @PathVariable("responsable") String responsable, @PathVariable("creador") String creador) {
        try {
            Optional<List<ServiciosGastosDTO>> optional = service.serviciosGastos(empresa.equals("null") ? "%" : empresa, fecha, fecha2, servicio.equals("null") ? "%" : servicio, responsable.equals("null") ? "%" : responsable);
            if (optional.isPresent()) {
                List<ServiciosGastosDTO> lista = optional.get();
                if (lista == null || lista.isEmpty()) {
                    return new ResponseEntity<>("La lista está vacía", HttpStatus.NOT_FOUND);
                } else {
                    HashMap<String, Object> parametros = new HashMap<>();
                    parametros.put("fecha_creacion", format.format(new Date()));
                    parametros.put("fecha_inicial", format.format(fecha));
                    parametros.put("fecha_final", format.format(fecha2));
                    parametros.put("servicio", servicio.equals("null") ? "Todos" : servicio);
                    parametros.put("empresa", empresa.equals("null") ? "Todas" : empresa);
                    parametros.put("responsable", responsable.equals("null") ? "Todos" : responsable);
                    parametros.put("creador", creador);
                    parametros.put("estado_pago", "Todos");
                    parametros.put("estado_gasto", "Todos");
                    return new ResponseEntity<>(convertirReporte(lista, parametros), HttpStatus.OK);
                }
            } else {
                return new ResponseEntity<>("Lista Vacia", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception ex) {
            System.out.println("reporte: " + ex);
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("reporteGastos3/{fecha}/{fecha2}/{empresa}/{servicio}/{estPago}/{responsable}/{creador}")
    public ResponseEntity<?> reporteGastosConEstados(@PathVariable("fecha") Date fecha, @PathVariable("fecha2") Date fecha2, @PathVariable("empresa") String empresa,
            @PathVariable("servicio") String servicio, @PathVariable("estPago") boolean estPago, @PathVariable("responsable") String responsable, @PathVariable("creador") String creador) {
        try {
            Optional<List<ServiciosGastosDTO>> optional = service.serviciosGastos(empresa.equals("null") ? "%" : empresa, fecha, fecha2, servicio.equals("null") ? "%" : servicio, estPago, responsable.equals("null") ? "%" : responsable);
            if (optional.isPresent()) {
                List<ServiciosGastosDTO> lista = optional.get();
                if (lista == null || lista.isEmpty()) {
                    return new ResponseEntity<>("La lista está vacía", HttpStatus.NOT_FOUND);
                } else {
                    HashMap<String, Object> parametros = new HashMap<>();
                    parametros.put("fecha_creacion", format.format(new Date()));
                    parametros.put("fecha_inicial", format.format(fecha));
                    parametros.put("fecha_final", format.format(fecha2));
                    parametros.put("servicio", servicio.equals("null") ? "Todos" : servicio);
                    parametros.put("empresa", empresa.equals("null") ? "Todas" : empresa);
                    parametros.put("responsable", responsable.equals("null") ? "Todos" : responsable);
                    parametros.put("creador", creador);
                    parametros.put("estado_pago", estPago ? "Pagos" : "No pagos");
                    parametros.put("estado_gasto", "Todos");
                    return new ResponseEntity<>(convertirReporte(lista, parametros), HttpStatus.OK);
                }
            } else {
                return new ResponseEntity<>("Lista Vacia", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("reporteGastos4/{fecha}/{fecha2}/{empresa}/{servicio}/{estGasto}/{responsable}/{creador}")
    public ResponseEntity<?> reporteGastosConEstados(@PathVariable("fecha") Date fecha, @PathVariable("fecha2") Date fecha2, @PathVariable("empresa") String empresa,
            @PathVariable("servicio") String servicio, @PathVariable("responsable") String responsable, @PathVariable("estGasto") boolean estGasto, @PathVariable("creador") String creador) {
        try {
            Optional<List<ServiciosGastosDTO>> optional = service.serviciosGastos(empresa.equals("null") ? "%" : empresa, fecha, fecha2, servicio.equals("null") ? "%" : servicio, responsable.equals("null") ? "%" : responsable, estGasto);
            if (optional.isPresent()) {
                List<ServiciosGastosDTO> lista = optional.get();
                if (lista == null || lista.isEmpty()) {
                    return new ResponseEntity<>("La lista está vacía", HttpStatus.NOT_FOUND);
                } else {
                    HashMap<String, Object> parametros = new HashMap<>();
                    parametros.put("fecha_creacion", format.format(new Date()));
                    parametros.put("fecha_inicial", format.format(fecha));
                    parametros.put("fecha_final", format.format(fecha2));
                    parametros.put("servicio", servicio.equals("null") ? "Todos" : servicio);
                    parametros.put("empresa", empresa.equals("null") ? "Todas" : empresa);
                    parametros.put("responsable", responsable.equals("null") ? "Todos" : responsable);
                    parametros.put("creador", creador);
                    parametros.put("estado_pago", "Todos");
                    parametros.put("estado_gasto", estGasto ? "Activos" : "Inactivos");
                    return new ResponseEntity<>(convertirReporte(lista, parametros), HttpStatus.OK);
                }
            } else {
                return new ResponseEntity<>("Lista Vacia", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("reporteHoras/{cedula}/{fecha1}/{fecha2}/{encargado}")
    public ResponseEntity<?> reporteHorasLaboradas(@PathVariable("cedula") String cedula, @PathVariable("fecha1") Date fecha1,
            @PathVariable("fecha2") Date fecha2, @PathVariable("encargado") String encargado) {
        try {
            Optional<List<EmpleadosMarcajesDTO>> optional = empMarcajeService.findByEmpleadoCedulaAndFechas(cedula.equals("null") ? "%" : '%' + cedula + '%', fecha1, fecha2);
            if (optional.isPresent()) {
                List<EmpleadosMarcajesDTO> lista = optional.get();
                if (lista == null || lista.isEmpty()) {
                    return new ResponseEntity<>("La lista está vacía", HttpStatus.NOT_FOUND);
                } else {
                    return new ResponseEntity<>(convertirReporteHorasLaboradas(lista,encargado,fecha1,fecha2), HttpStatus.OK);
                }
            } else {
                return new ResponseEntity<>("Lista Vacia", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private String convertirReporte(List<ServiciosGastosDTO> lista, HashMap<String, Object> parametros) {
        try {
            JasperPrint jprint = ReportBuilder.reporteGastos(lista, parametros);
            ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
            ObjectOutputStream bytes = new ObjectOutputStream(byteArray);
            bytes.writeObject(jprint);
            bytes.flush();
            return Base64.getEncoder().encodeToString(byteArray.toByteArray());
        } catch (IOException ex) {
            System.out.println("Error generando reporte: [" + ex + "]");
        }
        return "";
    }

    private String convertirReporteHorasLaboradas(List<EmpleadosMarcajesDTO> lista ,String encargado, Date fecha1, Date fecha2) {
        ObjectOutputStream bytes = null; 
        try {
            List<ReporteHorarios> reportes = ReporteHorarios.unirMarcajesSegunHorario(lista);
            String info = ReporteHorarios.sumarHorasLaboradasEmpleados(reportes);
            JasperPrint jprint = ReportBuilder.reporteHorasLaboradas(reportes, info, fecha1, fecha2, encargado);
            ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
            bytes = new ObjectOutputStream(byteArray);
            bytes.writeObject(jprint);
            bytes.flush();
            return Base64.getEncoder().encodeToString(byteArray.toByteArray());
        } catch (IOException ex) {
            System.out.println("Error generando reporte: [" + ex + "]");
        } finally {
            try {
                bytes.close();
            } catch (IOException ex) {
            }
        }
        return "";
    }

    @GetMapping("reporteIncidente2/{fechaIni}/{fechaFin}/{responsable}/{emisor}/{creador}")
    public ResponseEntity<?> reporteIncidentes2(@PathVariable("fechaIni") Date fechaIni, @PathVariable("fechaFin") Date fechaFin, @PathVariable("responsable") String responsable, @PathVariable("emisor") String emisor, @PathVariable("creador") String creador) {
        Optional<List<IncidentesRegistradosDTO>> optional = service.incidentesRegistradosReportes(fechaIni, fechaFin, responsable.equals("null") ? "%" : responsable, emisor.equals("null") ? "%" : emisor);
        HashMap<String, Object> map = new HashMap<>();
        map.put("fecha_creacion", format.format(new Date()));
        map.put("fecha_inicial", format.format(fechaIni));
        map.put("fecha_final", format.format(fechaFin));
        map.put("responsable", responsable.equals("null") ? "Todos" : responsable);
        map.put("emisor", emisor.equals("null") ? "Todos" : emisor);
        map.put("estado", "Todos");
        map.put("creador", creador);
        return crearReporteIncident(optional, map);
    }

    @GetMapping("reporteIncidente/{fechaIni}/{fechaFin}/{estado}/{responsable}/{emisor}/{creador}")
    public ResponseEntity<?> reporteIncidentes(@PathVariable("fechaIni") Date fechaIni, @PathVariable("fechaFin") Date fechaFin, @PathVariable("estado") boolean estado, @PathVariable("responsable") String responsable, @PathVariable("emisor") String emisor, @PathVariable("creador") String creador) {
        Optional<List<IncidentesRegistradosDTO>> optional = service.incidentesRegistradosReportes(fechaIni, fechaFin, estado, responsable.equals("null") ? "%" : responsable, emisor.equals("null") ? "%" : emisor);
        HashMap<String, Object> map = new HashMap<>();
        map.put("fecha_creacion", format.format(new Date()));
        map.put("fecha_inicial", format.format(fechaIni));
        map.put("fecha_final", format.format(fechaFin));
        map.put("responsable", responsable.equals("null") ? "Todos" : responsable);
        map.put("emisor", emisor.equals("null") ? "Todos" : emisor);
        map.put("estado", estado ? "Activo":"Inactivo");
        map.put("creador", creador);
        return crearReporteIncident(optional, map);
    }

    public ResponseEntity crearReporteIncident(Optional<List<IncidentesRegistradosDTO>> optional, HashMap<String, Object> map) {
        try {
            if (optional.isPresent()) {
                List<IncidentesRegistradosDTO> lista = optional.get();
                if (lista == null || lista.isEmpty()) {
                    return new ResponseEntity<>("La lista está vacía", HttpStatus.NOT_FOUND);
                } else {
                    JasperPrint jprint = ReportBuilder.reporteIncidente(lista, map);
                    ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
                    ObjectOutputStream bytes = new ObjectOutputStream(byteArray);
                    bytes.writeObject(jprint);
                    bytes.flush();
                    String temp = Base64.getEncoder().encodeToString(byteArray.toByteArray());
                    return new ResponseEntity<>(temp, HttpStatus.OK);
                }
            } else {
                return new ResponseEntity<>("Lista Vacia", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
