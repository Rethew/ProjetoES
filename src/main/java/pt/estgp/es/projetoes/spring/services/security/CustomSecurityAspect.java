package pt.estgp.es.projetoes.spring.services.security;

import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component
public class CustomSecurityAspect {
    @Pointcut("@annotation(pt.estgp.es.projetoes.spring.services.security.CustomSecurityAnnotation)")
    private void customSecurityAnnotation() {
    }

    @Around("pt.estgp.es.projetoes.spring.services.security.CustomSecurityAspect.customSecurityAnnotation()")
    public Object doSomething(ProceedingJoinPoint pjp) throws Throwable {
        HttpServletRequest req = getRequest();
        System.out.println("AQUI na zona de controlo");

        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println(userDetails.getUsername());
        // Check header values
        // Throw Spring's AccessDeniedException if needed
        return pjp.proceed();
    }

    private HttpServletRequest getRequest() {
        ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return sra.getRequest();
    }
}