package com.lajospolya.spotifyscraper;

import com.lajospolya.spotifyapiwrapper.authorization.AuthorizationResponse;
import com.lajospolya.spotifyapiwrapper.authorization.SpotifyAuthorizationManager;
import com.lajospolya.spotifyapiwrapper.client.SpotifyApiClient;
import com.lajospolya.spotifyapiwrapper.client.SpotifyManagingClient;
import com.lajospolya.spotifyapiwrapper.client.response.*;
import com.lajospolya.spotifyapiwrapper.spotifyrequest.*;
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
        authorizationResponse.setAccessToken("BQC2PvNW5QNHxRCS0VYmDQN0cgK9kLe5CUBb8dQYYoCmSRAwWXx5aj5NEh8TmJ5KCntBE8UqiwJMptHv7Co");
        authorizationResponse.setTokenType("Bearer");

        SpotifyManagingClient manager = new SpotifyManagingClient(authorizationResponse);
        getSeveralAudioFeatures(manager);
        getAudioFeatures(manager);
        getTrack(manager);
        getTracks(manager);
        getArtistsRelatedArtists(manager);
        getArtistsTopTracks(manager);
        getArtistsAlbums(manager);
        getArtists(manager);
        getArtist(manager);

        //SpotifyApiClient client = new SpotifyApiClient(authorizationResponse);
        SpotifyApiClient client = SpotifyAuthorizationManager.getAuthorizedApiClient(clientAuthorizationProperties.getClientId(), clientAuthorizationProperties.getClientSecret());
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

    private void getSeveralAudioFeatures(SpotifyManagingClient manager)
    {
        List<String> trackIds = new ArrayList<>();
        trackIds.add("0mQiDbYxHElUp1eNpLZXaY");
        trackIds.add("4xjjaT7yDpZ6jDRZXBHxM4");
        trackIds.add("1osYkIYegkLLnPutvItBta");
        GetSeveralAudioFeatures getAudioFeaturesRequest = new GetSeveralAudioFeatures.Builder(trackIds).build();
        TracksAudioFeatures audioFeatures = manager.sendRequest(getAudioFeaturesRequest);
        System.out.println(audioFeatures);
    }

    private void getAudioFeatures(SpotifyManagingClient manager)
    {
        GetAudioFeatures getAudioFeaturesRequest = new GetAudioFeatures.Builder("74SFqzOS8Z0rbbG2llSVaQ").build();
        AudioFeatures audioFeatures = manager.sendRequest(getAudioFeaturesRequest);
        System.out.println(audioFeatures);
    }

    private void getTrack(SpotifyManagingClient manager)
    {
        GetTrack getTrackRequest = new GetTrack.Builder("1EaKU4dMbesXXd3BrLCtYG").build();
        Track track = manager.sendRequest(getTrackRequest);
        System.out.println(track);
    }

    private void getTracks(SpotifyManagingClient manager)
    {
        List<String> trackIds = new ArrayList<>();
        trackIds.add("3YB9cvd668HXBEq8rbBW8P");
        trackIds.add("44ObvXOOdYZMKWV9pwS3be");
        trackIds.add("3X8yPPFopeDilHeB89R5El");
        GetTracks getTracksRequest = new GetTracks.Builder(trackIds).build();
        Tracks tracks = manager.sendRequest(getTracksRequest);
        System.out.println(tracks);
    }

    private void getArtistsRelatedArtists(SpotifyManagingClient manager)
    {
        GetArtistsRelatedArtists getRelatedArtistsRequest = new GetArtistsRelatedArtists.Builder("4V8LLVI7PbaPR0K2TGSxFF").build();
        Artists relatedArtists = manager.sendRequest(getRelatedArtistsRequest);
        System.out.println(relatedArtists);
    }

    private void getArtistsTopTracks(SpotifyManagingClient manager)
    {
        GetArtistsTopTracks topTracksRequest = new GetArtistsTopTracks.Builder("6Q192DXotxtaysaqNPy5yR", "CA").build();
        ArtistsTopTracks topTracks =  manager.sendRequest(topTracksRequest);
        System.out.println(topTracks);
    }

    private void getArtistsAlbums(SpotifyManagingClient manager)
    {
        GetArtistsAlbums artistAlbumsRequest = new GetArtistsAlbums.Builder("5jLbQGcvxehi2Z6qkUP9Rh").build();
        ArtistsAlbums albums = manager.sendRequest(artistAlbumsRequest);
        System.out.println(albums);
    }

    private void getArtists(SpotifyManagingClient manager)
    {
        List<String> artistIds = new ArrayList<>();
        artistIds.add("4LLpKhyESsyAXpc4laK94U");
        artistIds.add("7Ln80lUS6He07XvHI8qqHH");
        GetArtists getArtists = new GetArtists.Builder(artistIds).build();
        Artists artists = manager.sendRequest(getArtists);
        System.out.println(artists);
    }

    private void getArtist(SpotifyManagingClient manager)
    {
        SpotifyRequest<Artist> getArtist = new GetArtist.Builder("7Ln80lUS6He07XvHI8qqHH")
                .build();
        Artist newArcticMonkeys = manager.sendRequest(getArtist);
        System.out.println(newArcticMonkeys);
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
