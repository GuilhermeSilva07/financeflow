package com.grupoestudos.financeflow.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(info = @Info(title = "FinanceFlow API", version = "v1", description = "API para gerenciar transações"))
@Configuration
public class OpenApiConfig {
}
