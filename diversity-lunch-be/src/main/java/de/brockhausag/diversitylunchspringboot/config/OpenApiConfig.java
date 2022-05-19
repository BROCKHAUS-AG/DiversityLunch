package de.brockhausag.diversitylunchspringboot.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    public static final String BEARER = "Bearer";

    @Bean
    public OpenAPI customOpenApi() {

        return new OpenAPI().components(new Components().addSecuritySchemes(BEARER, securityScheme())).info(new Info().title("Diversity-Lunch-API").description("""
                Dieser Service bietet Endpunkte für die Diverity-Lunch App.
                                
                In dieser geht es um Matching zweier Personen
                mit unterschiedlichen Interessen und Merkmalen.
                                
                Ziel der App ist es, die Mitarbeiterinnen und Mitarbeiter der BROCKHAUS AG zu einem
                gemeinsamen Mittagessen zusammenzubringen.\s
                                
                Der Service kann nur mit einem von Microsoft ausgestellten Bearer Token verwendet werden.
                Den Token kann aus den Dev-Tools im Frontend ausgelesen werden.\s
                """)).addSecurityItem(new SecurityRequirement().addList(BEARER));
    }

    private SecurityScheme securityScheme() {
        return new SecurityScheme()
                .in(SecurityScheme.In.HEADER)
                .type(SecurityScheme.Type.APIKEY)
                .name("Authorization")
                .description("""
                        Füge hier deinen Bearer Token ein.
                        Du kannst ihn aus einer Anfrage der App ziehen.
                        """)
                .bearerFormat(BEARER);
    }

}
