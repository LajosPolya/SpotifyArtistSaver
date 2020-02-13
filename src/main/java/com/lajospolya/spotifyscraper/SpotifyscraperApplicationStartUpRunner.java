package com.lajospolya.spotifyscraper;

import com.lajospolya.spotifyapiwrapper.response.AuthorizationResponse;
import com.lajospolya.spotifyapiwrapper.enumeration.AlbumType;
import com.lajospolya.spotifyapiwrapper.client.SpotifyApiClient;
import com.lajospolya.spotifyapiwrapper.response.*;
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

        SpotifyApiClient authorizedManager = SpotifyApiClient.createClientCredentialsAuthorizedClient(clientAuthorizationProperties.getClientId(), clientAuthorizationProperties.getClientSecret());
        try
        {
            getAlbumsTracks(authorizedManager);
            getAlbum(authorizedManager);
            getAlbums(authorizedManager);
            getAudioAudioAnalysis(authorizedManager);
            getSeveralAudioFeatures(authorizedManager);
            getAudioFeatures(authorizedManager);
            getTrack(authorizedManager);
            getTracks(authorizedManager);
            getArtistsRelatedArtists(authorizedManager);
            getArtistsTopTracks(authorizedManager);
            getArtistsAlbums(authorizedManager);
            getArtists(authorizedManager);
            getArtist(authorizedManager);
        }
        catch (SpotifyResponseException e)
        {
            System.out.println("Caught SpotifyException");
        }

        System.out.println("App Started");
    }

    private void getAlbumsTracks(SpotifyApiClient manager)
    {
        String albumId = "1HLd8IsRFf0siJGgVMZ8HZ";
        GetAlbumsTracks getAlbumsTracksRequest = new GetAlbumsTracks.Builder(albumId)
                .offset(0).limit(50).market("CA").build();
        Paging<SimplifiedTrack> tracks = manager.sendRequest(getAlbumsTracksRequest);
        System.out.println(tracks);
    }

    private void getAlbum(SpotifyApiClient manager)
    {
        String albumId = "5yMCA6HdFAeL1aqUjxO3MO";
        GetAlbum getAlbumRequest = new GetAlbum.Builder(albumId)
                .market("CA").build();
        Album album = manager.sendRequest(getAlbumRequest);
        System.out.println(album);
    }

    private void getAlbums(SpotifyApiClient manager)
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

    private void getAudioAudioAnalysis(SpotifyApiClient manager)
    {
        GetAudioAnalysis getAudioAnalysisRequest = new GetAudioAnalysis.Builder("74SFqzOS8Z0rbbG2llSVaQ").build();
        String audioFeatures = manager.sendRequest(getAudioAnalysisRequest);
        System.out.println(audioFeatures);
    }

    private void getSeveralAudioFeatures(SpotifyApiClient manager)
    {
        List<String> trackIds = new ArrayList<>();
        trackIds.add("0mQiDbYxHElUp1eNpLZXaY");
        trackIds.add("4xjjaT7yDpZ6jDRZXBHxM4");
        trackIds.add("1osYkIYegkLLnPutvItBta");
        GetSeveralAudioFeatures getAudioFeaturesRequest = new GetSeveralAudioFeatures.Builder(trackIds).build();
        TracksAudioFeatures audioFeatures = manager.sendRequest(getAudioFeaturesRequest);
        System.out.println(audioFeatures);
    }

    private void getAudioFeatures(SpotifyApiClient manager)
    {
        GetAudioFeatures getAudioFeaturesRequest = new GetAudioFeatures.Builder("74SFqzOS8Z0rbbG2llSVaQ").build();
        AudioFeatures audioFeatures = manager.sendRequest(getAudioFeaturesRequest);
        System.out.println(audioFeatures);
    }

    private void getTrack(SpotifyApiClient manager)
    {
        GetTrack getTrackRequest = new GetTrack.Builder("1EaKU4dMbesXXd3BrLCtYG").build();
        Track track = manager.sendRequest(getTrackRequest);
        System.out.println(track);
    }

    private void getTracks(SpotifyApiClient manager)
    {
        List<String> trackIds = new ArrayList<>();
        trackIds.add("3YB9cvd668HXBEq8rbBW8P");
        trackIds.add("44ObvXOOdYZMKWV9pwS3be");
        trackIds.add("3X8yPPFopeDilHeB89R5El");
        GetTracks getTracksRequest = new GetTracks.Builder(trackIds).build();
        Tracks tracks = manager.sendRequest(getTracksRequest);
        System.out.println(tracks);
    }

    private void getArtistsRelatedArtists(SpotifyApiClient manager)
    {
        GetArtistsRelatedArtists getRelatedArtistsRequest = new GetArtistsRelatedArtists.Builder("4V8LLVI7PbaPR0K2TGSxFF").build();
        Artists relatedArtists = manager.sendRequest(getRelatedArtistsRequest);
        System.out.println(relatedArtists);
    }

    private void getArtistsTopTracks(SpotifyApiClient manager)
    {
        GetArtistsTopTracks topTracksRequest = new GetArtistsTopTracks.Builder("6Q192DXotxtaysaqNPy5yR", "CA").build();
        ArtistsTopTracks topTracks =  manager.sendRequest(topTracksRequest);
        System.out.println(topTracks);
    }

    private void getArtistsAlbums(SpotifyApiClient manager)
    {
        List<AlbumType> includeGroups = new ArrayList<>();
        includeGroups.add(AlbumType.SINGLE);
        includeGroups.add(AlbumType.ALBUM);
        GetArtistsAlbums artistAlbumsRequest = new GetArtistsAlbums.Builder("5jLbQGcvxehi2Z6qkUP9Rh")
                .limit(50).offset(0).market("CA").albumType(includeGroups).build();
        ArtistsAlbums albums = manager.sendRequest(artistAlbumsRequest);
        System.out.println(albums);
    }

    private void getArtists(SpotifyApiClient manager)
    {
        List<String> artistIds = new ArrayList<>();
        artistIds.add("4LLpKhyESsyAXpc4laK94U");
        artistIds.add("7Ln80lUS6He07XvHI8qqHH");
        GetArtists getArtists = new GetArtists.Builder(artistIds).build();
        Artists artists = manager.sendRequest(getArtists);
        System.out.println(artists);
    }

    private void getArtist(SpotifyApiClient manager)
    {
        AbstractSpotifyRequest<Artist> getArtist = new GetArtist.Builder("7Ln80lUS6He07XvHI8qqHH")
                .build();
        Artist newArcticMonkeys = manager.sendRequest(getArtist);
        System.out.println(newArcticMonkeys);
    }
}
