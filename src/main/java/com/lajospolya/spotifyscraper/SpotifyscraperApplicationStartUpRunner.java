package com.lajospolya.spotifyscraper;

import com.lajospolya.spotifyapiwrapper.authorization.AuthorizationResponse;
import com.lajospolya.spotifyapiwrapper.client.SpotifyApiClient;
import com.lajospolya.spotifyapiwrapper.client.response.Artist;
import com.lajospolya.spotifyapiwrapper.client.response.SimplifiedAlbum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SpotifyscraperApplicationStartUpRunner implements ApplicationRunner
{
    @Autowired
    private ClientAuthorizationProperties clientAuthorizationProperties;

    @Override
    public void run(ApplicationArguments args)
    {
        AuthorizationResponse authorizationResponse = new AuthorizationResponse();
        authorizationResponse.setAccessToken("BQBLRW_lvFMqpOZGXVVpeeOAtbqsWwKFBW-9EhfeShbAEbbyCeUZSnrFZw70qHuZxuNQ7aXsn4zmN1f5Puk");
        authorizationResponse.setTokenType("Bearer");
        SpotifyApiClient client = new SpotifyApiClient(authorizationResponse);
        //SpotifyApiClient client = SpotifyAuthorizationManager.getAuthorizedApiClient(clientAuthorizationProperties.getClientId(), clientAuthorizationProperties.getClientSecret());
        Artist arcticMonkeys = client.getArtist("7Ln80lUS6He07XvHI8qqHH");

        List<String> artistIds = new ArrayList<>();
        artistIds.add("4LLpKhyESsyAXpc4laK94U");
        artistIds.add("7Ln80lUS6He07XvHI8qqHH");

        List<Artist> artists = client.getArtists(artistIds);

        List<SimplifiedAlbum> arcticMonkeysAlbums = client.getArtistsAlbums("7Ln80lUS6He07XvHI8qqHH");

        System.out.println("App Started");
        System.out.println(clientAuthorizationProperties.getClientId());
    }
}
