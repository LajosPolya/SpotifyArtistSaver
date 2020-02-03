package com.lajospolya.spotifyscraper;

import com.lajospolya.spotifyapiwrapper.authorization.AuthorizationResponse;
import com.lajospolya.spotifyapiwrapper.client.SpotifyApiClient;
import com.lajospolya.spotifyapiwrapper.client.response.*;
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
        authorizationResponse.setAccessToken("BQAOlT7_xd4f3Kwso8VsSPHHxoHJhXy-cOWrAdF5oMMB2Kk199ickasd2ShGeunALL2e0Mmg5XPG-J-dcSw");
        authorizationResponse.setTokenType("Bearer");
        SpotifyApiClient client = new SpotifyApiClient(authorizationResponse);
        //SpotifyApiClient client = SpotifyAuthorizationManager.getAuthorizedApiClient(clientAuthorizationProperties.getClientId(), clientAuthorizationProperties.getClientSecret());

        getArtist(client);
        getArtists(client);
        getArtistsAlbums(client);
        getArtistsTopTracks(client);
        getArtistsRelatedArtists(client);

        getTracks(client);
        getTrack(client);
        getAudioFeatures(client);
        getTracksAudioFeatures(client);
        getAudioAnalysis(client);

        getAlbums(client);
        getAlbum(client);
        getAlbumTracks(client);

        System.out.println("App Started");
    }

    private void getArtist(SpotifyApiClient client)
    {
        Artist arcticMonkeys = client.getArtist("7Ln80lUS6He07XvHI8qqHH");
        System.out.println(arcticMonkeys);
    }

    private void getArtists(SpotifyApiClient client)
    {
        List<String> artistIds = new ArrayList<>();
        artistIds.add("4LLpKhyESsyAXpc4laK94U");
        artistIds.add("7Ln80lUS6He07XvHI8qqHH");
        List<Artist> artists = client.getArtists(artistIds);
        System.out.println(artists);
    }

    private void getArtistsAlbums(SpotifyApiClient client)
    {
        List<SimplifiedAlbum> arcticMonkeysAlbums = client.getArtistsAlbums("7Ln80lUS6He07XvHI8qqHH");
        System.out.println(arcticMonkeysAlbums);
    }

    private void getArtistsTopTracks(SpotifyApiClient client)
    {
        List<Track> arcticMonkeysTopTracks = client.getArtistsTopTracks("7Ln80lUS6He07XvHI8qqHH");
        System.out.println(arcticMonkeysTopTracks);
    }

    private void getArtistsRelatedArtists(SpotifyApiClient client)
    {
        List<Artist> arcticMonkeysRelatedArtists = client.getArtistsRelatedArtists("7Ln80lUS6He07XvHI8qqHH");
        System.out.println(arcticMonkeysRelatedArtists);
    }

    private void getTracks(SpotifyApiClient client)
    {
        List<String> trackIds = new ArrayList<>();
        trackIds.add("1DWZUa5Mzf2BwzpHtgbHPY");
        trackIds.add("23zRkeMfBMweIRFveMygKq");
        List<Track> tracks = client.getTracks(trackIds);
        System.out.println(tracks);
    }

    private void getTrack(SpotifyApiClient client)
    {
        Track track = client.getTrack("1DWZUa5Mzf2BwzpHtgbHPY");
        System.out.println(track);
    }

    private void getAudioFeatures(SpotifyApiClient client)
    {
        AudioFeatures audioFeatures = client.getAudioFeatures("1DWZUa5Mzf2BwzpHtgbHPY");
        System.out.println(audioFeatures);
    }

    private void getTracksAudioFeatures(SpotifyApiClient client)
    {
        List<String> trackIds = new ArrayList<>();
        trackIds.add("1DWZUa5Mzf2BwzpHtgbHPY");
        trackIds.add("23zRkeMfBMweIRFveMygKq");
        List<AudioFeatures> audioFeatures = client.getAudioFeatures(trackIds);
        System.out.println(audioFeatures);
    }

    private void getAudioAnalysis(SpotifyApiClient client)
    {
        String audioAnalysis = client.getAudioAnalysis("1DWZUa5Mzf2BwzpHtgbHPY");
        System.out.println(audioAnalysis);
    }

    private void getAlbums(SpotifyApiClient client)
    {
        List<String> albumIds = new ArrayList<>();
        albumIds.add("50o7kf2wLwVmOTVYJOTplm");
        albumIds.add("2NBVxjZcbH5H1N1Ab2ExDH");
        List<Album> albums = client.getAlbums(albumIds);
        System.out.println(albums);
    }

    private void getAlbum(SpotifyApiClient client)
    {
        Album album = client.getAlbum("50o7kf2wLwVmOTVYJOTplm");
        System.out.println(album);
    }

    private void getAlbumTracks(SpotifyApiClient client)
    {
        Paging<SimplifiedTrack> albumTracks = client.getAlbumTracks("50o7kf2wLwVmOTVYJOTplm");
        System.out.println(albumTracks);
    }
}
