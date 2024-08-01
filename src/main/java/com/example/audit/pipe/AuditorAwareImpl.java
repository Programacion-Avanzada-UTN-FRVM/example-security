package com.example.audit.pipe;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import java.util.Optional;

@Configuration
public class AuditorAwareImpl implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        /**
         * Prestar atencion a la configuracion.
         *
         * Este metodo "getCurrentAuditor" se ejecuta solo por JPA cuando una entidad
         * marcada con auditoria se crea/actualiza.
         * 
         * Lo que queremos lograr aqui es que automaticamente se fije en la persona que
         * hizo la peticion en el controlador (API) para que automaticamente JPA lo
         * rellene en la columna que tiene el decorador @CreatedBy y @LastModifiedBy
         *
         * Para lograr esto, hacemos codigo muy repetitivo que obtiene esa informacion
         * desde el contexto de seguridad de la aplicacion.
         *
         * Para mas informacion, revisar las clases de configuracion de Spring Security.
         */

        // Obtener instancia de autenticacion, si es que existe.
        Optional<Authentication> authentication = Optional
                .ofNullable(SecurityContextHolder.getContext().getAuthentication());

        // Si no existe, retornamos un usuario desconocido.
        if (authentication.isEmpty())
            return Optional.of("desconocido");

        // Si existe, buscamos el nombre de usuario provisto y lo retornamos.
        return Optional.of(authentication.get().getName());
    }
}