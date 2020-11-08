package ru.akalavan.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;

public class SecurityBeanConfig {

    @Bean
    public DefaultMethodSecurityExpressionHandler methodSecurityExpressionHandler(
            @Qualifier("defaultPermissionEvaluator") PermissionEvaluator evaluator) {
        DefaultMethodSecurityExpressionHandler methodSecurityExpressionHandler = new DefaultMethodSecurityExpressionHandler();
        methodSecurityExpressionHandler.setPermissionEvaluator(evaluator);
        return methodSecurityExpressionHandler;
    }

    @Bean
    public DefaultWebSecurityExpressionHandler webSecurityExpressionHandler(
            @Qualifier("defaultPermissionEvaluator") PermissionEvaluator evaluator) {
        DefaultWebSecurityExpressionHandler webSecurityExpressionHandler = new DefaultWebSecurityExpressionHandler();
        webSecurityExpressionHandler.setPermissionEvaluator(evaluator);
        return webSecurityExpressionHandler;
    }
}
