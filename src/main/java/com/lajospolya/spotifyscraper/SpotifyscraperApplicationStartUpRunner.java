package com.lajospolya.spotifyscraper;

import com.lajospolya.spotifyapiwrapper.authorization.SpotifyAuthorizationManager;
import com.lajospolya.spotifyapiwrapper.client.SpotifyApiClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class SpotifyscraperApplicationStartUpRunner implements ApplicationRunner
{
    @Autowired
    private ClientAuthorizationProperties clientAuthorizationProperties;

    @Override
    public void run(ApplicationArguments args)
    {

        SpotifyApiClient client = SpotifyAuthorizationManager.getAuthorizedApiClient(clientAuthorizationProperties.getClientId(), clientAuthorizationProperties.getClientSecret());
        client.getArtist();

        System.out.println("App Started");
        System.out.println(clientAuthorizationProperties.getClientId());
    }
}
