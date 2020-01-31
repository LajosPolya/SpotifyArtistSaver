package com.lajospolya.spotifyscraper;

import com.lajospolya.spotifyscraper.spotifyresponse.ApiTokenResponse;
import com.lajospolya.spotifyscraper.spotifywebservice.SpotifyAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class SpotifyscraperApplicationStartUpRunner implements ApplicationRunner
{
    @Autowired
    private ClientAuthorizationProperties clientAuthorizationProperties;

    @Autowired
    private SpotifyAuthorize  spotifyAuthorize;

    @Override
    public void run(ApplicationArguments args) throws Exception
    {
        ApiTokenResponse reponse = spotifyAuthorize.authorize(clientAuthorizationProperties.getClientId(), clientAuthorizationProperties.getClientSecret());
        System.out.println("App Started");
        System.out.println(clientAuthorizationProperties.getClientId());
    }
}
