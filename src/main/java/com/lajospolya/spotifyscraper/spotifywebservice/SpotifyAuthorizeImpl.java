package com.lajospolya.spotifyscraper.spotifywebservice;

import com.google.gson.Gson;
import com.lajospolya.spotifyscraper.spotifyresponse.ApiTokenResponse;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Service
public class SpotifyAuthorizeImpl implements SpotifyAuthorize
{
    private static final URI API_TOKEN_URL = URI.create("https://accounts.spotify.com/api/token");
    private static final String CONTENT_TYPE = "Content-Type";
    private static final String URL_ENCODED_CONTENT_TYPE = "application/x-www-form-urlencoded";
    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String BASIC_AUTHORIZATION = "Basic ";

    @Override
    public ApiTokenResponse authorize(String clientId, String clientSecret) throws IOException, InterruptedException
    {
        HttpClient client = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .followRedirects(HttpClient.Redirect.NORMAL)
                .build();

        String base64EncodedAuthKey = getBase64EncodedAuthorizationKey(clientId, clientSecret);

        String params = "grant_type=client_credentials";
        byte[] paramByte = params.getBytes(StandardCharsets.UTF_8);
        HttpRequest auth = HttpRequest.newBuilder()
                .uri(API_TOKEN_URL)
                .header(CONTENT_TYPE, URL_ENCODED_CONTENT_TYPE)
                .header(AUTHORIZATION_HEADER, BASIC_AUTHORIZATION + base64EncodedAuthKey)
                .POST(HttpRequest.BodyPublishers.ofByteArray(paramByte))
                .build();

        HttpResponse<String> response = client.send(auth, HttpResponse.BodyHandlers.ofString());

        String body = response.body();

        Gson gson = new Gson();
        return gson.fromJson(body, ApiTokenResponse.class);
    }

    private String getBase64EncodedAuthorizationKey(String clientId, String clientSecret)
    {
        byte[] authorizationKey = (clientId + ":" + clientSecret).getBytes(StandardCharsets.UTF_8);
        return Base64.getEncoder().encodeToString(authorizationKey);

    }
}
