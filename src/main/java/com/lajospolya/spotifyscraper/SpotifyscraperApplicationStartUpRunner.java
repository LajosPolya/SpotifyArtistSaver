package com.lajospolya.spotifyscraper;

import com.lajospolya.spotifyapiwrapper.client.SpotifyApiClient;
import com.lajospolya.spotifyapiwrapper.enumeration.*;
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
        ClientCredentialsFlowResponse clientCredentialsFlowResponse = new ClientCredentialsFlowResponse();
        clientCredentialsFlowResponse.setAccessToken("BQAYDuKxmk2l72-xOUhrp8foQiSFpzokeZ2h8onvyCij7b1hpSPQkcX2o7vnOrVqPE5zF91NHwLFA9vUtek");
        clientCredentialsFlowResponse.setTokenType("Bearer");

        //SpotifyApiClient client = SpotifyApiClient
        //        .createClientCredentialsFlowClient(clientAuthorizationProperties.getClientId(), clientAuthorizationProperties.getClientSecret());

        SpotifyApiClient client = SpotifyApiClient.createAuthorizationFlowClient(clientAuthorizationProperties.getClientId(), clientAuthorizationProperties.getClientSecret(),
                clientAuthorizationProperties.getCode(), clientAuthorizationProperties.getRedirectUrl());


        try
        {
            getMeAlbums(client);
            getUserIfSavedTracks(client);
            getMeTracks(client);
            getUserIfSavedAlbums(client);
            putMeTracks(client);
            deleteMeTracks(client);
            putMeAlbums(client);
            deleteMeAlbums(client);
            getTopArtists(client);
            getTopTracks(client);
            deleteFollowingPlaylist(client);
            deleteMeFollowing(client);
            getMeFollowing(client);
            putPlaylist(client);
            putFollow(client);
            getUserFollowsPlaylist(client);
            getMeFollowingContains(client);
            getMe(client);
            getUser(client);
            getFeaturesPlaylists(client);
            getAllNewReleases(client);
            getRecommendationGenres(client);
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

    private void getMeAlbums(SpotifyApiClient client)
    {
        GetMeAlbums getUsersTracksRequest = new GetMeAlbums.Builder()
                .offset(0).limit(50).market("CA").build();
        Paging<SavedAlbum> tracks = client.sendRequest(getUsersTracksRequest);
        System.out.println(tracks);
    }

    private void getUserIfSavedTracks(SpotifyApiClient client)
    {
        List<String> ids = new ArrayList<>();
        ids.add("07GvNcU1WdyZJq3XxP0kZa");
        ids.add("35rf8iduzQ7vd8hFbDlv0o");
        GetMeTracksContains hasSavedTracksRequest = new GetMeTracksContains.Builder(ids)
                .build();
        List<Boolean> hasSaveToLibrary = client.sendRequest(hasSavedTracksRequest);
        System.out.println(hasSaveToLibrary);
    }

    private void getMeTracks(SpotifyApiClient client)
    {
        GetMeTracks getUsersTracksRequest = new GetMeTracks.Builder()
                .offset(0).limit(50).market("CA").build();
        Paging<SavedTrack> tracks = client.sendRequest(getUsersTracksRequest);
        System.out.println(tracks);
    }

    private void getUserIfSavedAlbums(SpotifyApiClient client)
    {
        List<String> ids = new ArrayList<>();
        ids.add("1eN0asiUp2OoMuRkI61cmm");
        ids.add("5ZnOKznPxZTWuMusR4tmGG");
        GetMeAlbumsContains hasSavedAlbumRequest = new GetMeAlbumsContains.Builder(ids)
                .build();
        List<Boolean> hasSaveToLibrary = client.sendRequest(hasSavedAlbumRequest);
        System.out.println(hasSaveToLibrary);
    }

    private void putMeTracks(SpotifyApiClient client)
    {
        List<String> ids = new ArrayList<>();
        ids.add("7LlkkivFiUXnnT8poNrE7k");
        ids.add("1NlGXcWeUSe1s3IgRKcqmB");
        ids.add("1w327AHTCoChRIkJUprAnV");
        ids.add("59DqOEiZvbyRNHew4U6guS");
        PutMeTracks saveToLibraryRequest = new PutMeTracks.Builder(ids)
                .build();
        Void saveToLibrary = client.sendRequest(saveToLibraryRequest);
        System.out.println(saveToLibrary);
    }

    private void deleteMeTracks(SpotifyApiClient client)
    {
        List<String> ids = new ArrayList<>();
        ids.add("7LlkkivFiUXnnT8poNrE7k");
        ids.add("1NlGXcWeUSe1s3IgRKcqmB");
        ids.add("1w327AHTCoChRIkJUprAnV");
        ids.add("59DqOEiZvbyRNHew4U6guS");
        DeleteMeTracks removeFromLibraryRequest = new DeleteMeTracks.Builder(ids)
                .build();
        Void removeFrom = client.sendRequest(removeFromLibraryRequest);
        System.out.println(removeFrom);
    }

    private void deleteMeAlbums(SpotifyApiClient client)
    {
        List<String> ids = new ArrayList<>();
        ids.add("7wGLeeJt18EBjc181FP2cM");
        ids.add("3TC40H9dIJArFzy0rWnWCg");
        ids.add("16ah4zHJlxx3wjRFg3nkSl");
        DeleteMeAlbums deleteFollowingRequest = new DeleteMeAlbums.Builder(ids)
                .build();
        Void unfollow = client.sendRequest(deleteFollowingRequest);
        System.out.println(unfollow);
    }

    private void putMeAlbums(SpotifyApiClient client)
    {
        List<String> ids = new ArrayList<>();
        ids.add("7wGLeeJt18EBjc181FP2cM");
        ids.add("3TC40H9dIJArFzy0rWnWCg");
        ids.add("16ah4zHJlxx3wjRFg3nkSl");
        PutMeAlbums saveToLibraryRequest = new PutMeAlbums.Builder(ids)
                .build();
        Void saveToLibrary = client.sendRequest(saveToLibraryRequest);
        System.out.println(saveToLibrary);
    }

    private void getTopArtists(SpotifyApiClient client)
    {
        GetUsersTopArtists getUsersTopTracksRequest = new GetUsersTopArtists.Builder()
                .limit(50)
                .offset(0)
                .timeRange(TimeRange.long_term)
                .build();
        Paging<Artist> topTracks = client.sendRequest(getUsersTopTracksRequest);
        System.out.println(topTracks);
    }

    private void getTopTracks(SpotifyApiClient client)
    {
        GetUsersTopTracks getUsersTopTracksRequest = new GetUsersTopTracks.Builder()
                .limit(50)
                .offset(0)
                .timeRange(TimeRange.medium_term)
                .build();
        Paging<Track> topTracks = client.sendRequest(getUsersTopTracksRequest);
        System.out.println(topTracks);
    }

    private void deleteFollowingPlaylist(SpotifyApiClient client)
    {
        DeleteFollowPlaylist deleteFollowingRequest = new DeleteFollowPlaylist.Builder("1khS5Pll0YEduwuZdciEbe")
                .build();
        Void unfollow = client.sendRequest(deleteFollowingRequest);
        System.out.println(unfollow);
    }

    private void deleteMeFollowing(SpotifyApiClient client)
    {
        List<String> ids = new ArrayList<>();
        ids.add("37M5pPGs6V1fchFJSgCguX");
        DeleteFollow deleteFollowingRequest = new DeleteFollow.Builder(FollowType.artist, ids)
                .build();
        Void unfollow = client.sendRequest(deleteFollowingRequest);
        System.out.println(unfollow);
    }

    private void getMeFollowing(SpotifyApiClient client)
    {
        GetMeFollowing getFollowingRequest = new GetMeFollowing.Builder(FollowType.artist)
                .limit(50)
                .after("0rH93aHDYyJfMAcPB9OKus")
                .build();
        Following following = client.sendRequest(getFollowingRequest);
        System.out.println(following);
    }

    private void putPlaylist(SpotifyApiClient client)
    {
        String id = "1khS5Pll0YEduwuZdciEbe";

        PutFollowPlaylist getUserFollowsPlaylistRequest = new PutFollowPlaylist.Builder(id)
                .isPublic(false)
                .build();
        Void follows = client.sendRequest(getUserFollowsPlaylistRequest);
        System.out.println(follows);
    }

    private void putFollow(SpotifyApiClient client)
    {
        List<String> ids = new ArrayList<>();
        ids.add("greentekki");

        PutFollow getUserFollowsPlaylistRequest = new PutFollow.Builder(FollowType.user, ids)
                .build();
        Void follows = client.sendRequest(getUserFollowsPlaylistRequest);
        System.out.println(follows);
    }

    private void getUserFollowsPlaylist(SpotifyApiClient client)
    {
        List<String> ids = new ArrayList<>();
        ids.add("szucs2020");
        ids.add("lajospolya");
        ids.add("matebors21");

        GetUsersFollowsPlaylist getUserFollowsPlaylistRequest = new GetUsersFollowsPlaylist.Builder("3VJcxliXZ3178iws5iimId", ids)
                .build();
        List<Boolean> follows = client.sendRequest(getUserFollowsPlaylistRequest);
        System.out.println(follows);
    }

    private void getMeFollowingContains(SpotifyApiClient client)
    {
        List<String> ids = new ArrayList<>();
        ids.add("1ctkBmvz80MGyi72Ix055S");

        GetMeFollowingContains getMeRequest = new GetMeFollowingContains.Builder(FollowType.artist, ids).build();
        List<Boolean> me = client.sendRequest(getMeRequest);
        System.out.println(me);
    }

    private void getMe(SpotifyApiClient client)
    {
        GetMe getMeRequest = new GetMe.Builder().build();
        UserPublic me = client.sendRequest(getMeRequest);
        System.out.println(me);
    }

    private void getUser(SpotifyApiClient client)
    {
        String userId = "lajospolya";
        GetUser getUserRequest = new GetUser.Builder(userId).build();
        UserPublic user = client.sendRequest(getUserRequest);
        System.out.println(user);
    }

    private void getFeaturesPlaylists(SpotifyApiClient client)
    {
        GetFeaturesPlaylists featuredPlaylistsRequest = new GetFeaturesPlaylists.Builder()
                .country("BR")
                .limit(50)
                .offset(0)
                .timestamp("2014-10-23T09:00:00")
                .build();
        FeaturedPlaylists featuredPlaylists = client.sendRequest(featuredPlaylistsRequest);
        System.out.println(featuredPlaylists);
    }

    private void getAllNewReleases(SpotifyApiClient client)
    {
        GetAllNewReleases recomendationGenresRequest = new GetAllNewReleases.Builder()
                .country("CA")
                .limit(50)
                .offset(0)
                .build();
        NewReleases recommendationGenres = client.sendRequest(recomendationGenresRequest);
        System.out.println(recommendationGenres);
    }

    private void getRecommendationGenres(SpotifyApiClient client)
    {
        GetRecomendationGenres recomendationGenresRequest = new GetRecomendationGenres.Builder()
                .build();
        RecommendationGenres recommendationGenres = client.sendRequest(recomendationGenresRequest);
        System.out.println(recommendationGenres);
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
                .offset(0).limit(50).market("CA").includeExternal(ExternalContent.Audio).build();
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
