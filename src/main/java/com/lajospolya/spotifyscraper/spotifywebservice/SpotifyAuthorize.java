package com.lajospolya.spotifyscraper.spotifywebservice;

import com.lajospolya.spotifyscraper.spotifyresponse.ApiTokenResponse;

import java.io.IOException;

public interface SpotifyAuthorize
{
    ApiTokenResponse authorize(String clientId, String clientSecret) throws IOException, InterruptedException;
}
