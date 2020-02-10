package com.lajospolya.spotifyscraper;

import com.lajospolya.spotifyapiwrapper.authorization.AuthorizationResponse;
import com.lajospolya.spotifyapiwrapper.authorization.SpotifyAuthorizationManager;
import com.lajospolya.spotifyapiwrapper.client.AlbumType;
import com.lajospolya.spotifyapiwrapper.client.SpotifyApiClient;
import com.lajospolya.spotifyapiwrapper.client.SpotifyManagingClient;
import com.lajospolya.spotifyapiwrapper.client.response.*;
import com.lajospolya.spotifyapiwrapper.spotifyexception.SpotifyResponseException;
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
        authorizationResponse.setAccessToken("BQAYDuKxmk2l72-xOUhrp8foQiSFpzokeZ2h8onvyCij7b1hpSPQkcX2o7vnOrVqPE5zF91NHwLFA9vUtek");
        authorizationResponse.setTokenType("Bearer");

        SpotifyManagingClient manager = new SpotifyManagingClient(authorizationResponse);
        try
        {
            getAlbumsTracks(manager);
            getAlbum(manager);
            getAlbums(manager);
            getAudioAudioAnalysis(manager);
            getSeveralAudioFeatures(manager);
            getAudioFeatures(manager);
            getTrack(manager);
            getTracks(manager);
            getArtistsRelatedArtists(manager);
            getArtistsTopTracks(manager);
            getArtistsAlbums(manager);
            getArtists(manager);
            getArtist(manager);
        }
        catch (SpotifyResponseException e)
        {
            System.out.println("Caught SpotifyException");
        }

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

    private void getAlbumsTracks(SpotifyManagingClient manager)
    {
        String albumId = "1HLd8IsRFf0siJGgVMZ8HZ";
        GetAlbumsTracks getAlbumsTracksRequest = new GetAlbumsTracks.Builder(albumId)
                .offset(0).limit(50).market("CA").build();
        Paging<SimplifiedTrack> tracks = manager.sendRequest(getAlbumsTracksRequest);
        System.out.println(tracks);
    }

    private void getAlbum(SpotifyManagingClient manager)
    {
        String albumId = "5yMCA6HdFAeL1aqUjxO3MO";
        GetAlbum getAlbumRequest = new GetAlbum.Builder(albumId)
                .market("CA").build();
        Album album = manager.sendRequest(getAlbumRequest);
        System.out.println(album);
    }

    private void getAlbums(SpotifyManagingClient manager)
    {
        List<String> albumIds = new ArrayList<>();
        albumIds.add("2tH1S9Q2RUcLrOizMy9I1K");
        albumIds.add("0S0KGZnfBGSIssfF54WSJh");
        albumIds.add("4m2880jivSbbyEGAKfITCa");
        GetAlbums getAlbumsRequest = new GetAlbums.Builder(albumIds)
                .market("CA").build();
        Albums albums = manager.sendRequest(getAlbumsRequest);
        System.out.println(albums);
    }

    private void getAudioAudioAnalysis(SpotifyManagingClient manager)
    {
        GetAudioAnalysis getAudioAnalysisRequest = new GetAudioAnalysis.Builder("74SFqzOS8Z0rbbG2llSVaQ").build();
        String audioFeatures = manager.sendRequest(getAudioAnalysisRequest);
        System.out.println(audioFeatures);
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
        List<AlbumType> includeGroups = new ArrayList<>();
        includeGroups.add(AlbumType.SINGLE);
        includeGroups.add(AlbumType.ALBUM);
        GetArtistsAlbums artistAlbumsRequest = new GetArtistsAlbums.Builder("5jLbQGcvxehi2Z6qkUP9Rh")
                .limit(50).offset(0).market("CA").albumType(includeGroups).build();
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
