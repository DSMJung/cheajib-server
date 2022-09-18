package com.cheajib.cheajibserver.global.configuration

import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.context.annotation.Configuration

@ConfigurationPropertiesScan("com.cheajib.cheajibserver.global.security.properties")
@Configuration
class ConfigurationPropertiesScanConfig
