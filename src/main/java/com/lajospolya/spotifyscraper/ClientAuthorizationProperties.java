package com.lajospolya.spotifyscraper;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;

@Configuration
@ConfigurationProperties(prefix = "client-authorization")
@Validated
public class ClientAuthorizationProperties
{
    @NotBlank(message = "clientId cannot be empty")
    private String clientId;

    @NotBlank(message = "clientSecret cannot be empty")
    private String clientSecret;

    public String getClientId()
    {
        return clientId;
    }

    public void setClientId(String clientId)
    {
        this.clientId = clientId;
    }

    public String getClientSecret()
    {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret)
    {
        this.clientSecret = clientSecret;
    }
}
