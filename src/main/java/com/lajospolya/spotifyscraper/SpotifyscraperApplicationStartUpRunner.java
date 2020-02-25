package com.lajospolya.spotifyscraper;

import com.lajospolya.spotifyapiwrapper.client.SpotifyApiClient;
import com.lajospolya.spotifyapiwrapper.enumeration.AlbumType;
import com.lajospolya.spotifyapiwrapper.enumeration.SearchItemType;
import com.lajospolya.spotifyapiwrapper.response.*;
import com.lajospolya.spotifyapiwrapper.spotifyexception.SpotifyResponseException;
import com.lajospolya.spotifyapiwrapper.spotifyrequest.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static com.lajospolya.spotifyapiwrapper.enumeration.TuneableTrackAttributeFactory.*;

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

        SpotifyApiClient client = SpotifyApiClient
                .createClientCredentialsAuthorizedClient(clientAuthorizationProperties.getClientId(), clientAuthorizationProperties.getClientSecret());
        try
        {
            getRecommendations(client);
            getCategorysPlaylists(client);
            getCategory(client);
            getAllCategories(client);
            getSearch(client);
            getAlbumsTracks(client);
            getAlbum(client);
            getAlbums(client);
            getAudioAudioAnalysis(client);
            getSeveralAudioFeatures(client);
            getAudioFeatures(client);
            getTrack(client);
            getTracks(client);
            getArtistsRelatedArtists(client);
            getArtistsTopTracks(client);
            getArtistsAlbums(client);
            getArtists(client);
            getArtist(client);
        }
        catch (SpotifyResponseException e)
        {
            System.out.println("Caught SpotifyException");
        }

        System.out.println("App Started");
    }

    private void getRecommendations(SpotifyApiClient client)
    {
        List<String> artists = new ArrayList<>();
        artists.add("1ctkBmvz80MGyi72Ix055S");
        artists.add("2Z7UcsdweVlRbAk5wH5fsf");
        List<String> tracks = new ArrayList<>();
        tracks.add("6u0x5ad9ewHvs3z6u9Oe3c");
        tracks.add("6ooluO7DiEhI1zmK94nRCM");
        List<String> genres = new ArrayList<>();
        genres.add("sheffield indie");
        GetRecommendations recommendationsRequest = new GetRecommendations.Builder(artists, tracks, genres)
                .limit(100)
                .market("CA")
                .min(acousticness, 0.0)
                .max(acousticness, 1.0)
                .target(acousticness, 0.5)
                //.min(danceability, 0.0)
                //.max(danceability, 1.0)
                //.target(danceability, 0.5)
                //.min(durationMs, 0)
                //.max(durationMs, 300)
                //.target(durationMs, 180)
                //.min(energy, 0.0)
                //.max(energy, 1.0)
                //.target(energy, 0.4)
                //.min(instrumentalness, 0.0)
                //.max(instrumentalness, 1.0)
                //.target(instrumentalness, 0.2)
                //.min(key, 0)
                //.max(key, 15)
                //.target(key, 7)
                //.min(liveness, 0.0)
                //.max(liveness, 1.0)
                //.target(liveness, 0.1)
                //.min(loudness, 0.01)
                //.max(loudness, 1.0)
                //.target(loudness, 0.45)
                //.min(modality, 1)
                //.max(modality, 0)
                //.target(modality, 1)
                //.min(popularity, 20)
                //.max(popularity, 100)
                //.target(popularity, 80)
                //.min(speechiness, 0.0)
                //.max(speechiness, 0.3)
                //.target(speechiness, 1.0)
                //.min(tempo, 0.0)
                //.max(tempo, 1000.0)
                //.target(tempo, 130.0)
                //.min(timeSignature, 2)
                //.max(timeSignature, 8)
                //.target(timeSignature, 4)
                //.min(valence, 0.0)
                //.max(valence, 1.0)
                //.target(valence, 0.66)
                .build();
        Recommendation recommendations = client.sendRequest(recommendationsRequest);
        System.out.println(recommendations);
    }

    private void getCategorysPlaylists(SpotifyApiClient client)
    {
        GetCategorysPlaylists searchRequest = new GetCategorysPlaylists.Builder("hiphop")
                .country("CA").locale("de_DE").build();
        CategorysPlaylists categorysPlaylist = client.sendRequest(searchRequest);
        System.out.println(categorysPlaylist);
    }

    private void getCategory(SpotifyApiClient client)
    {
        GetCategory searchRequest = new GetCategory.Builder("toplists")
                .country("CA").locale("es_MX").build();
        Category category = client.sendRequest(searchRequest);
        System.out.println(category);
    }

    private void getAllCategories(SpotifyApiClient client)
    {
        GetAllCategories searchRequest = new GetAllCategories.Builder()
                .offset(0).limit(50).country("CA").locale("en_CA").build();
        Categories categories = client.sendRequest(searchRequest);
        System.out.println(categories);
    }

    private void getSearch(SpotifyApiClient client)
    {
        String query = "a a";
        List<SearchItemType> searchItemTypes = new ArrayList<>();
        searchItemTypes.add(SearchItemType.Album);
        searchItemTypes.add(SearchItemType.Artist);
        searchItemTypes.add(SearchItemType.Playlist);
        searchItemTypes.add(SearchItemType.Track);
        GetSearch searchRequest = new GetSearch.Builder(query, searchItemTypes)
                .offset(0).limit(50).market("CA").build();
        SearchResults results = client.sendRequest(searchRequest);
        System.out.println(results);
    }

    private void getAlbumsTracks(SpotifyApiClient client)
    {
        String albumId = "1HLd8IsRFf0siJGgVMZ8HZ";
        GetAlbumsTracks getAlbumsTracksRequest = new GetAlbumsTracks.Builder(albumId)
                .offset(0).limit(50).market("CA").build();
        Paging<SimplifiedTrack> tracks = client.sendRequest(getAlbumsTracksRequest);
        System.out.println(tracks);
    }

    private void getAlbum(SpotifyApiClient client)
    {
        String albumId = "5yMCA6HdFAeL1aqUjxO3MO";
        GetAlbum getAlbumRequest = new GetAlbum.Builder(albumId)
                .market("CA").build();
        Album album = client.sendRequest(getAlbumRequest);
        System.out.println(album);
    }

    private void getAlbums(SpotifyApiClient client)
    {
        List<String> albumIds = new ArrayList<>();
        albumIds.add("2tH1S9Q2RUcLrOizMy9I1K");
        albumIds.add("0S0KGZnfBGSIssfF54WSJh");
        albumIds.add("4m2880jivSbbyEGAKfITCa");
        GetAlbums getAlbumsRequest = new GetAlbums.Builder(albumIds)
                .market("CA").build();
        Albums albums = client.sendRequest(getAlbumsRequest);
        System.out.println(albums);
    }

    private void getAudioAudioAnalysis(SpotifyApiClient client)
    {
        GetAudioAnalysis getAudioAnalysisRequest = new GetAudioAnalysis.Builder("74SFqzOS8Z0rbbG2llSVaQ").build();
        String audioFeatures = client.sendRequest(getAudioAnalysisRequest);
        System.out.println(audioFeatures);
    }

    private void getSeveralAudioFeatures(SpotifyApiClient client)
    {
        List<String> trackIds = new ArrayList<>();
        trackIds.add("0mQiDbYxHElUp1eNpLZXaY");
        trackIds.add("4xjjaT7yDpZ6jDRZXBHxM4");
        trackIds.add("1osYkIYegkLLnPutvItBta");
        GetSeveralAudioFeatures getAudioFeaturesRequest = new GetSeveralAudioFeatures.Builder(trackIds).build();
        TracksAudioFeatures audioFeatures = client.sendRequest(getAudioFeaturesRequest);
        System.out.println(audioFeatures);
    }

    private void getAudioFeatures(SpotifyApiClient client)
    {
        GetAudioFeatures getAudioFeaturesRequest = new GetAudioFeatures.Builder("74SFqzOS8Z0rbbG2llSVaQ").build();
        AudioFeatures audioFeatures = client.sendRequest(getAudioFeaturesRequest);
        System.out.println(audioFeatures);
    }

    private void getTrack(SpotifyApiClient client)
    {
        GetTrack getTrackRequest = new GetTrack.Builder("1EaKU4dMbesXXd3BrLCtYG")
                .market("CA").build();
        Track track = client.sendRequest(getTrackRequest);
        System.out.println(track);
    }

    private void getTracks(SpotifyApiClient client)
    {
        List<String> trackIds = new ArrayList<>();
        trackIds.add("3YB9cvd668HXBEq8rbBW8P");
        trackIds.add("44ObvXOOdYZMKWV9pwS3be");
        trackIds.add("3X8yPPFopeDilHeB89R5El");
        GetTracks getTracksRequest = new GetTracks.Builder(trackIds)
                .market("CA").build();
        Tracks tracks = client.sendRequest(getTracksRequest);
        System.out.println(tracks);
    }

    private void getArtistsRelatedArtists(SpotifyApiClient client)
    {
        GetArtistsRelatedArtists getRelatedArtistsRequest = new GetArtistsRelatedArtists.Builder("4V8LLVI7PbaPR0K2TGSxFF").build();
        Artists relatedArtists = client.sendRequest(getRelatedArtistsRequest);
        System.out.println(relatedArtists);
    }

    private void getArtistsTopTracks(SpotifyApiClient client)
    {
        GetArtistsTopTracks topTracksRequest = new GetArtistsTopTracks.Builder("6Q192DXotxtaysaqNPy5yR", "CA").build();
        ArtistsTopTracks topTracks =  client.sendRequest(topTracksRequest);
        System.out.println(topTracks);
    }

    private void getArtistsAlbums(SpotifyApiClient client)
    {
        List<AlbumType> includeGroups = new ArrayList<>();
        includeGroups.add(AlbumType.SINGLE);
        includeGroups.add(AlbumType.ALBUM);
        GetArtistsAlbums artistAlbumsRequest = new GetArtistsAlbums.Builder("5jLbQGcvxehi2Z6qkUP9Rh")
                .limit(50).offset(0).market("CA").albumType(includeGroups).build();
        ArtistsAlbums albums = client.sendRequest(artistAlbumsRequest);
        System.out.println(albums);
    }

    private void getArtists(SpotifyApiClient client)
    {
        List<String> artistIds = new ArrayList<>();
        artistIds.add("4LLpKhyESsyAXpc4laK94U");
        artistIds.add("7Ln80lUS6He07XvHI8qqHH");
        GetArtists getArtists = new GetArtists.Builder(artistIds).build();
        Artists artists = client.sendRequest(getArtists);
        System.out.println(artists);
    }

    private void getArtist(SpotifyApiClient client)
    {
        AbstractSpotifyRequest<Artist> getArtist = new GetArtist.Builder("7Ln80lUS6He07XvHI8qqHH")
                .build();
        Artist newArcticMonkeys = client.sendRequest(getArtist);
        System.out.println(newArcticMonkeys);
    }
}
