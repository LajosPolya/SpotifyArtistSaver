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

    @NotBlank(message = "code cannot be empty")
    private String code;

    @NotBlank(message = "redirectUrl cannot be empty")
    private String redirectUrl;

    @NotBlank(message = "base64Image cannot be empty")
    private String base64Image;

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

    public String getCode()
    {
        return code;
    }

    public void setCode(String code)
    {
        this.code = code;
    }

    public String getRedirectUrl()
    {
        return redirectUrl;
    }

    public void setRedirectUrl(String redirectUrl)
    {
        this.redirectUrl = redirectUrl;
    }

    public String getBase64Image()
    {
        return base64Image;
    }

    public void setBase64Image(String base64Image)
    {
        this.base64Image = base64Image;
    }
}
