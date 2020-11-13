package org.una.aeropuerto;

import static io.swagger.annotations.ApiKeyAuthDefinition.ApiKeyLocation.HEADER;
import java.util.Collections;
import static java.util.Collections.singletonList;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 *
 * @author Yohell Buzo
 */

@Configuration
@EnableSwagger2

public class SwaggerConfiguration {

@Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .securitySchemes(singletonList(new ApiKey("JWT", AUTHORIZATION, HEADER.name())))
                .securityContexts(singletonList(
                        SecurityContext.builder()
                                .securityReferences(
                                        singletonList(SecurityReference.builder()
                                                .reference("JWT")
                                                .scopes(new AuthorizationScope[0])
                                                .build()
                                        )
                                )
                                .build())
                )
                .select()
                .apis(
                        RequestHandlerSelectors
                                .basePackage("org.una.aeropuerto.controllers"))
                .paths(PathSelectors.regex("/.*"))
                .build()
                .apiInfo(apiInfo())
                .tags(new Tag("Seguridad", "Metodos de Seguridad"),
                        new Tag("Transacciones", "Entidad de Transacciones"),
                        new Tag("Servicios_Precios", "Entidad de Precios de Servicios"),
                        new Tag("Gastos_Mantenimientos", "Entidad de Gastos en Servicios"),
                        new Tag("Servicios", "Entidad de Servicios"),
                        new Tag("Roles", "Entidad de Roles"),
                        new Tag("Parametros_Sistema", "Entidad de los Parametros del Sistema"),
                        new Tag("Incidentes_Registrados_Estados", "Entidad de los estados de los incidentes registrados"),
                        new Tag("Incidentes_Registrados", "Entidad de los incidentes Registrados"),
                        new Tag("Incidentes_Categorias", "Entidad de lascategorias de los incidentes"),
                        new Tag("Empleados", "Entidad de Empleados"),
                        new Tag("Empleados_Marcajes", "Entidad de los marcajes de los empleados"),
                        new Tag("Empleados_Horarios", "Entidad de los horarios de los empleados"),
                        new Tag("Empleados_Areas_Trabajos", "Entidad de la areas de trabajo de los empleados"),
                        new Tag("Areas_Trabajos", "Entidad de Areas de Trabajo"),
                        new Tag("Password", "Maneja las transacciones para el cambio de contraseña via correo"),
                        new Tag("Servicios_Gastos", "Entidad de gastos en servicios"),
                        new Tag("Reportes", "Maneja las transacciones para generar los reportes")
                );

    }


    private ApiInfo apiInfo() {
        return new ApiInfo(
                "Aeropuerto",
                "Rest API sobre el Aeropuerto.",
                "Versión:2.1.0",
                "https://google.com",
                new Contact("UNA Sede Región Brunca", "https://srb.una.ac.cr/index.php/es/", "decanatosrb@una.cr") {},
                "Apache-2.0", "http://www.apache.org/licenses/LICENSE-2.0", Collections.emptyList());
    }

}
