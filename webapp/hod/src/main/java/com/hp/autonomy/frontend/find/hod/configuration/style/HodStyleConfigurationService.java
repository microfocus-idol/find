/*
 * Copyright 2017 Hewlett Packard Enterprise Development Company, L.P.
 * Licensed under the MIT License (the "License"); you may not use this file except in compliance with the License.
 */

package com.hp.autonomy.frontend.find.hod.configuration.style;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.hp.autonomy.frontend.configuration.ConfigException;
import com.hp.autonomy.frontend.configuration.ConfigService;
import com.hp.autonomy.frontend.find.core.configuration.style.StyleConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.atomic.AtomicReference;

@Service
@Slf4j
public class HodStyleConfigurationService implements ConfigService<StyleConfiguration> {
    private AtomicReference<StyleConfiguration> config = new AtomicReference<>();

    @PostConstruct
    public void init() throws Exception {
        final ObjectMapper mapper = (new Jackson2ObjectMapperBuilder())
            .featuresToEnable(SerializationFeature.INDENT_OUTPUT)
            .createXmlMapper(false)
            .build();

        try(final InputStream resource = getClass().getClassLoader().getResourceAsStream("defaultStyleConfigFile.json")) {
            config.set(mapper.readValue(resource, StyleConfiguration.class));
        } catch(final IOException e) {
            log.error("Failed to read defaultStyleConfigFile.json, falling back to empty config", e);
            throw new ConfigException("Failed to read defaultStyleConfigFile.json", e);
        }
    }

    @Override
    public StyleConfiguration getConfig() {
        return config.get();
    }
}
