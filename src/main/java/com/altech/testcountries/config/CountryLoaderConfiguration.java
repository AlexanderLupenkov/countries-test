package com.altech.testcountries.config;

import com.altech.testcountries.loader.CountryLoader;
import com.altech.testcountries.loader.impl.RemoteCountryLoader;
import org.apache.hc.client5.http.classic.HttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.List;


@Configuration
public class CountryLoaderConfiguration {
    @Value("${remote.countries.url}")
    private String remoteUrl;

    @Bean
    public CountryLoader remoteCountryLoader() {
        HttpClient httpClient = HttpClientBuilder.create().build();
        ClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory(httpClient);
        RestTemplate restTemplate = new RestTemplate(requestFactory);
        restTemplate.setMessageConverters(List.of(mappingJackson2HttpMessageConverter()));
        return new RemoteCountryLoader(restTemplate, remoteUrl);
    }

    @Bean
    public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
        final MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setSupportedMediaTypes(List.of(MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON));
        return converter;
    }
}
