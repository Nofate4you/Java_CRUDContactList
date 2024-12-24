package Kevin.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.List;

// Static imports for HTTP headers and methods
import static Kevin.backend.constant.Constant.X_REQUESTED_WITH; // Update import to your Constant class
import static org.springframework.http.HttpHeaders.ACCEPT;
import static org.springframework.http.HttpHeaders.ACCESS_CONTROL_ALLOW_CREDENTIALS;
import static org.springframework.http.HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN;
import static org.springframework.http.HttpHeaders.ACCESS_CONTROL_REQUEST_HEADERS;
import static org.springframework.http.HttpHeaders.ACCESS_CONTROL_REQUEST_METHOD;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.http.HttpHeaders.ORIGIN;
import static org.springframework.http.HttpMethod.DELETE;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.OPTIONS;
import static org.springframework.http.HttpMethod.PATCH;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.HttpMethod.PUT;

// Configuration annotation to mark this as a configuration class
@Configuration
public class CorsConfig {

    // Bean annotation to create and register the CORS filter
    @Bean
    public CorsFilter corsFilter() {
        // Create an object to handle CORS configuration
        var urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();

        // Create a new CORS configuration object
        var corsConfiguration = new CorsConfiguration();

        // Enable credentials for cross-origin requests
        corsConfiguration.setAllowCredentials(true);

        // Define allowed origins (the frontend applications allowed to access the backend)
        corsConfiguration.setAllowedOrigins(List.of("http://localhost:3000", "http://localhost:4200"));

        // Define allowed headers in requests
        corsConfiguration.setAllowedHeaders(List.of(
                ORIGIN, ACCESS_CONTROL_ALLOW_ORIGIN, CONTENT_TYPE, ACCEPT, AUTHORIZATION,
                X_REQUESTED_WITH, ACCESS_CONTROL_REQUEST_METHOD, ACCESS_CONTROL_REQUEST_HEADERS, ACCESS_CONTROL_ALLOW_CREDENTIALS
        ));

        // Define which headers will be exposed in responses
        corsConfiguration.setExposedHeaders(List.of(
                ORIGIN, ACCESS_CONTROL_ALLOW_ORIGIN, CONTENT_TYPE, ACCEPT, AUTHORIZATION,
                X_REQUESTED_WITH, ACCESS_CONTROL_REQUEST_METHOD, ACCESS_CONTROL_REQUEST_HEADERS, ACCESS_CONTROL_ALLOW_CREDENTIALS
        ));

        // Define allowed HTTP methods (GET, POST, PUT, etc.)
        corsConfiguration.setAllowedMethods(List.of(
                GET.name(), POST.name(), PUT.name(), PATCH.name(), DELETE.name(), OPTIONS.name()
        ));

        // Register the CORS configuration to apply to all endpoints
        urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);

        // Return a new CorsFilter using the defined configuration
        return new CorsFilter(urlBasedCorsConfigurationSource);
    }
}
