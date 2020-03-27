package com.lajospolya.spotifyscraper;

import com.lajospolya.spotifyapiwrapper.client.SpotifyApiClient;
import com.lajospolya.spotifyapiwrapper.enumeration.*;
import com.lajospolya.spotifyapiwrapper.response.*;
import com.lajospolya.spotifyapiwrapper.spotifyexception.SpotifyResponseException;
import com.lajospolya.spotifyapiwrapper.request.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

import static com.lajospolya.spotifyapiwrapper.enumeration.TuneableTrackAttributeFactory.acousticness;

@Component
public class SpotifyscraperApplicationStartUpRunner implements ApplicationRunner
{
    @Autowired
    private ClientAuthorizationProperties clientAuthorizationProperties;

    private static String snapshotId = null;
    private static List<String> deviceIds = null;

    @Override
    public void run(ApplicationArguments args)
    {
        AuthorizingToken authorizingToken = new AuthorizingToken();
        authorizingToken.setAccessToken("BQAYDuKxmk2l72-xOUhrp8foQiSFpzokeZ2h8onvyCij7b1hpSPQkcX2o7vnOrVqPE5zF91NHwLFA9vUtek");
        authorizingToken.setTokenType("Bearer");

        //SpotifyApiClient client = SpotifyApiClient
        //        .createClientCredentialsFlowClient(clientAuthorizationProperties.getClientId(), clientAuthorizationProperties.getClientSecret());

        SpotifyApiClient client = SpotifyApiClient.createAuthorizationFlowClient(clientAuthorizationProperties.getClientId(), clientAuthorizationProperties.getClientSecret(),
                clientAuthorizationProperties.getCode(), clientAuthorizationProperties.getRedirectUrl());

        try
        {
            client.reauthorizeAsync().get();
            getPlaylistTracksToFetchEtag(client);
            getPlaylistToFetchEtag(client);
            getShowsEpisodes(client);
            getShows(client);
            getShow(client);
            getEpisodes(client);
            getEpisode(client);
            getMeDevices(client);
            postMePlayerNext(client);
            putMePlayer(client);
            getMePlayer(client);
            putMePlayerPause(client);
            putMePlayerPlay(client);
            putMePlayerShuffle(client);
            putMePlayerSeek(client);
            putMePlayerVolume(client);
            putMePlayerRepeat(client);
            getMePlayerHistory(client);
            postMePlayerPrevious(client);
            getMePlayerCurrentlyPlaying(client);
            postMePlayerQueue(client);
            deletePlaylistsTracks(client);
            postPlaylists(client);
            getPlaylist(client);
            getPlaylistsImages(client);
            getPlaylistsTracks(client);
            postPlaylistsAdd(client);
            putPlaylistsReorder(client);
            putPlaylistsImages(client);
            putPlaylists(client);
            getUsersPlaylists(client);
            putPlaylistTracks(client);
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
        catch (SpotifyResponseException | ExecutionException | InterruptedException e)
        {
            System.out.println("Caught SpotifyException");
        }

        System.out.println("App Started");
    }

    private void getPlaylistTracksToFetchEtag(SpotifyApiClient client) throws ExecutionException, InterruptedException
    {
        GetPlaylistsTracks getPlaylistRequest = new GetPlaylistsTracks.Builder("3X3gtW72Wwh6v1RR27ZgDe")
                .build();
        Paging<PlaylistTrack> playlistTracks = client.sendRequest(getPlaylistRequest);

        GetPlaylistsTracks getPlaylistCachedRequest = new GetPlaylistsTracks.Builder("3X3gtW72Wwh6v1RR27ZgDe")
                .etag(playlistTracks.getEtag()).build();
        Paging<PlaylistTrack> cachedPlaylist = client.sendRequest(getPlaylistCachedRequest);
        System.out.println(cachedPlaylist);

        addTrackToPlaylist(client);

        Paging<PlaylistTrack> newPlaylistTracksAsync = client.sendRequestAsync(getPlaylistCachedRequest).get();
        System.out.println(newPlaylistTracksAsync);
    }

    private void getPlaylistToFetchEtag(SpotifyApiClient client) throws ExecutionException, InterruptedException
    {
        GetPlaylist getPlaylistRequest = new GetPlaylist.Builder("3X3gtW72Wwh6v1RR27ZgDe")
                .build();
        Playlist playlist = client.sendRequest(getPlaylistRequest);

        GetPlaylist getPlaylistCachedRequest = new GetPlaylist.Builder("3X3gtW72Wwh6v1RR27ZgDe")
                .etag(playlist.getEtag()).build();
        Playlist cachedPlaylist = client.sendRequest(getPlaylistCachedRequest);
        System.out.println(cachedPlaylist);

        addTrackToPlaylist(client);

        Playlist newPlaylistAsync = client.sendRequestAsync(getPlaylistCachedRequest).get();
        System.out.println(newPlaylistAsync);
    }

    private void getShowsEpisodes(SpotifyApiClient client) throws ExecutionException, InterruptedException
    {
        GetShowsEpisodes getShowsRequest = new GetShowsEpisodes.Builder("4xdoysfv0ztl97lrj8Sg4W")
                .market("CA").limit(50).offset(3).build();
        Paging<SimplifiedEpisode> episodes = client.sendRequestAsync(getShowsRequest).get();

        System.out.println(episodes);
    }

    private void getShows(SpotifyApiClient client) throws ExecutionException, InterruptedException
    {
        List<String> showIds = new ArrayList<>();
        showIds.add("7gozmLqbcbr6PScMjc0Zl4");
        showIds.add("2DM3fsZmo4qYRC3VDXfsLi");
        GetShows getShowsRequest = new GetShows.Builder(showIds)
                .market("CA").build();
        Shows shows = client.sendRequestAsync(getShowsRequest).get();

        System.out.println(shows);
    }

    private void getShow(SpotifyApiClient client) throws ExecutionException, InterruptedException
    {
        GetShow getShowRequest = new GetShow.Builder("7gozmLqbcbr6PScMjc0Zl4")
                .market("CA").build();
        Show show = client.sendRequestAsync(getShowRequest).get();

        System.out.println(show);
    }

    private void getEpisodes(SpotifyApiClient client) throws ExecutionException, InterruptedException
    {
        List<String> episodeIds = new ArrayList<>();
        episodeIds.add("61onbjGFDC8VkO2Ld7eCYm");
        episodeIds.add("0CaOGo6xSN51B2aLAQa1kU");
        GetEpisodes getEpisodesRequest = new GetEpisodes.Builder(episodeIds)
                .market("CA").build();
        Episodes episodes = client.sendRequestAsync(getEpisodesRequest).get();

        System.out.println(episodes);
    }

    private void getEpisode(SpotifyApiClient client) throws ExecutionException, InterruptedException
    {
        GetEpisode getEpisodeRequest = new GetEpisode.Builder("61onbjGFDC8VkO2Ld7eCYm")
                .market("CA").build();
        Episode episode = client.sendRequestAsync(getEpisodeRequest).get();

        System.out.println(episode);
    }

    private void getMeDevices(SpotifyApiClient client)
    {
        GetMePlayerDevices getMePlayerDevicesRequest = new GetMePlayerDevices.Builder()
                .build();
        Devices devices = client.sendRequest(getMePlayerDevicesRequest);

        deviceIds = devices.getDevices().stream().map(Device::getId).collect(Collectors.toList());
        System.out.println(devices.getDevices().get(0));
    }

    private void postMePlayerNext(SpotifyApiClient client)
    {
        PostMePlayerNext postMeNextRequest = new PostMePlayerNext.Builder()
                .deviceId(deviceIds.get(0)).build();
        //Void none = client.sendRequest(postMeNextRequest);
        //System.out.println(none);
    }

    private void putMePlayer(SpotifyApiClient client)
    {
        PutMePlayer putMePlayerRequest = new PutMePlayer.Builder(deviceIds)
                .play(true).build();
        //Void none = client.sendRequest(putMePlayerRequest);
        //System.out.println(none);
    }

    private void getMePlayer(SpotifyApiClient client)
    {
        GetMePlayer putMePlayerPauseRequest = new GetMePlayer.Builder("CA")
                .build();
        CurrentlyPlayingContext player = client.sendRequest(putMePlayerPauseRequest);
        System.out.println(player);
    }

    private void putMePlayerPause(SpotifyApiClient client)
    {
        PutMePlayerPause putMePlayerPauseRequest = new PutMePlayerPause.Builder()
                .deviceId(deviceIds.get(0)).build();
        //Void none = client.sendRequest(putMePlayerPauseRequest);
        //System.out.println(none);
    }

    private void putMePlayerPlay(SpotifyApiClient client)
    {
        List<String> uris = new ArrayList<>();
        uris.add("spotify:album:5lOFvOWAdy9G6p44noRILU");
        PutMePlayerPlay putMePlayerPlayRequest = new PutMePlayerPlay.Builder()
                .contextUri("spotify:album:5lOFvOWAdy9G6p44noRILU").offset(2).deviceId(deviceIds.get(0))
                .positionMs(130000)
                //.uris(uris)
                .build();
        //Void none = client.sendRequest(putMePlayerPlayRequest);
        //System.out.println(none);
    }

    private void putMePlayerShuffle(SpotifyApiClient client)
    {
        PutMePlayerShuffle putMePlayerShuffleRequest = new PutMePlayerShuffle.Builder(true)
                .deviceId(deviceIds.get(0)).build();
        //Void none = client.sendRequest(putMePlayerShuffleRequest);
        //System.out.println(none);
    }

    private void putMePlayerSeek(SpotifyApiClient client)
    {
        PutMePlayerSeek putMePlayerSeekRequest = new PutMePlayerSeek.Builder(120000)
                .deviceId(deviceIds.get(0)).build();
        //Void none = client.sendRequest(putMePlayerSeekRequest);
        //System.out.println(none);
    }

    private void putMePlayerVolume(SpotifyApiClient client)
    {
        // Can't on iPhone
        PutMePlayerVolume putMePlayerVolumeRequest = new PutMePlayerVolume.Builder(5)
                .deviceId(deviceIds.get(0)).build();
        //Void none = client.sendRequest(putMePlayerVolumeRequest);
        //System.out.println(none);
    }

    private void putMePlayerRepeat(SpotifyApiClient client)
    {
        PutMePlayerRepeat putMePlayerRepeatRequest = new PutMePlayerRepeat.Builder(RepeatState.OFF)
                .deviceId(deviceIds.get(0)).build();
        //Void none = client.sendRequest(putMePlayerRepeatRequest);
        //System.out.println(none);
    }

    private void getMePlayerHistory(SpotifyApiClient client)
    {
        GetMePlayerRecentlyPlayed getMePlayerHistoryRequest = new GetMePlayerRecentlyPlayed.Builder()
                .limit(50).before(new Date().getTime()).build();
        PagingCursor<PlayHistory> history = client.sendRequest(getMePlayerHistoryRequest);
        System.out.println(history);
    }

    private void postMePlayerPrevious(SpotifyApiClient client)
    {
        PostMePlayerPrevious getMePlayerPreviousRequest = new PostMePlayerPrevious.Builder()
               .deviceId(deviceIds.get(0)).build();
        //Void none = client.sendRequest(getMePlayerPreviousRequest);
        //System.out.println(none);
    }

    private void getMePlayerCurrentlyPlaying(SpotifyApiClient client)
    {
        GetMePlayerCurrentlyPlaying getMePlayerCurrentlyPlayingRequest = new GetMePlayerCurrentlyPlaying.Builder("CA")
                .build();
        CurrentlyPlaying track = client.sendRequest(getMePlayerCurrentlyPlayingRequest);
        System.out.println(track);
    }

    private void postMePlayerQueue(SpotifyApiClient client)
    {
        PostMePlayerQueue postMePlayerQueueRequest = new PostMePlayerQueue.Builder("spotify:track:61mWefnWQOLf90gepjOCb3")
                .deviceId(deviceIds.get(0)).build();
        //Void none = client.sendRequest(postMePlayerQueueRequest);
        //System.out.println(none);
    }

    private void deletePlaylistsTracks(SpotifyApiClient client)
    {
        List<String> ids = new ArrayList<>();
        ids.add("spotify:track:63xdwScd1Ai1GigAwQxE8y");
        DeletePlaylistsTracks removeFromLibraryRequest = new DeletePlaylistsTracks.Builder("3X3gtW72Wwh6v1RR27ZgDe", ids)
                .build();
        PlaylistSnapshot snapshot = client.sendRequest(removeFromLibraryRequest);
        snapshotId = snapshot.getSnapshot_id();
        System.out.println(snapshotId);
    }

    private void postPlaylists(SpotifyApiClient client)
    {
        PostUsersPlaylists createPlaylistRequest = new PostUsersPlaylists.Builder("lajospolya", "Created By API")
                .collaborative(false).isPublic(true)
                .description("Created a playlist with new description").build();
        Playlist details = client.sendRequest(createPlaylistRequest);
        System.out.println(details);
    }

    private void getPlaylist(SpotifyApiClient client)
    {
        GetPlaylist getPlaylistRequest = new GetPlaylist.Builder("3X3gtW72Wwh6v1RR27ZgDe")
                .build();
        Playlist playlist = client.sendRequest(getPlaylistRequest);
        System.out.println(playlist);
    }

    private void getPlaylistsImages(SpotifyApiClient client)
    {
        GetPlaylistsImages getPlaylistImageRequest = new GetPlaylistsImages.Builder("3X3gtW72Wwh6v1RR27ZgDe")
                .build();
        List<Image> images = client.sendRequest(getPlaylistImageRequest);
        System.out.println(images);
    }

    private void getPlaylistsTracks(SpotifyApiClient client)
    {
        GetPlaylistsTracks playlistRequest = new GetPlaylistsTracks.Builder("3X3gtW72Wwh6v1RR27ZgDe")
                .offset(0).limit(100).market("CA").build();
        Paging<PlaylistTrack> playlistTracks = client.sendRequest(playlistRequest);
        System.out.println(playlistTracks);
    }

    private void postPlaylistsAdd(SpotifyApiClient client)
    {
        addTrackToPlaylist(client);
    }

    private void putPlaylistsReorder(SpotifyApiClient client)
    {
        PutPlaylistsTracksReorder playlistRequest = new PutPlaylistsTracksReorder.Builder("3X3gtW72Wwh6v1RR27ZgDe", 0, 3)
                .rangeLength(2).snapshotId(snapshotId).build();
        PlaylistSnapshot reorder = client.sendRequest(playlistRequest);
        snapshotId = reorder.getSnapshot_id();
        System.out.println(snapshotId);
    }

    private void putPlaylistsImages(SpotifyApiClient client)
    {
        PutPlaylistsImages changePlaylistImageRequest = new PutPlaylistsImages.Builder("3X3gtW72Wwh6v1RR27ZgDe", clientAuthorizationProperties.getBase64Image())
                .build();
        //Void image = client.sendRequest(changePlaylistImageRequest);
        //System.out.println(image);
    }

    private void putPlaylists(SpotifyApiClient client)
    {
        PutPlaylists changePlaylistDetailsRequest = new PutPlaylists.Builder("3X3gtW72Wwh6v1RR27ZgDe")
                .name("API Names Dev Playlist")
                .collaborative(false).isPublic(true)
                .description("This is the new description created by an PUT playlists").build();
        //Void details = client.sendRequest(changePlaylistDetailsRequest);
        //System.out.println(details);
    }

    private void getUsersPlaylists(SpotifyApiClient client)
    {
        GetUsersPlaylists getUsersTracksRequest = new GetUsersPlaylists.Builder("lajospolya")
                .offset(0).limit(50).build();
        Paging<SimplifiedPlaylist> playlists = client.sendRequest(getUsersTracksRequest);
        System.out.println(playlists);
    }

    private void putPlaylistTracks(SpotifyApiClient client)
    {
        List<String> ids = new ArrayList<>();
        ids.add("spotify:track:44ObvXOOdYZMKWV9pwS3be");
        ids.add("spotify:track:5fpZBXuorlUCC9SP6LVuw3");
        ids.add("spotify:track:1wYZZtamWTQAoj8B812uKQ");
        ids.add("spotify:track:7djV61C5MXUcorsFktQJsL");
        ids.add("spotify:track:4IO92oN7CBOKruobrRca0I");
        PutPlaylistsTracks hasSavedTracksRequest = new PutPlaylistsTracks.Builder("3X3gtW72Wwh6v1RR27ZgDe", ids)
                .build();
        //Void saved = client.sendRequest(hasSavedTracksRequest);
        //System.out.println(saved);
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
        //Void saveToLibrary = client.sendRequest(saveToLibraryRequest);
        //System.out.println(saveToLibrary);
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
        //Void removeFrom = client.sendRequest(removeFromLibraryRequest);
        //System.out.println(removeFrom);
    }

    private void putMeAlbums(SpotifyApiClient client)
    {
        List<String> ids = new ArrayList<>();
        ids.add("7wGLeeJt18EBjc181FP2cM");
        ids.add("3TC40H9dIJArFzy0rWnWCg");
        ids.add("16ah4zHJlxx3wjRFg3nkSl");
        PutMeAlbums saveToLibraryRequest = new PutMeAlbums.Builder(ids).build();
        //Void saveToLibrary = client.sendRequest(saveToLibraryRequest);
        //System.out.println(saveToLibrary);
    }

    private void deleteMeAlbums(SpotifyApiClient client)
    {
        List<String> ids = new ArrayList<>();
        ids.add("7wGLeeJt18EBjc181FP2cM");
        ids.add("3TC40H9dIJArFzy0rWnWCg");
        ids.add("16ah4zHJlxx3wjRFg3nkSl");
        DeleteMeAlbums deleteFollowingRequest = new DeleteMeAlbums.Builder(ids).build();
        //Void unfollow = client.sendRequest(deleteFollowingRequest);
        //System.out.println(unfollow);
    }

    private void getTopArtists(SpotifyApiClient client)
    {
        GetUsersTopArtists getUsersTopArtistsRequest = new GetUsersTopArtists.Builder()
                .limit(50).offset(0).timeRange(TimeRange.long_term).build();
        Paging<Artist> topArtists = client.sendRequest(getUsersTopArtistsRequest);
        System.out.println(topArtists);
    }

    private void getTopTracks(SpotifyApiClient client)
    {
        GetUsersTopTracks getUsersTopTracksRequest = new GetUsersTopTracks.Builder()
                .limit(50).offset(0).timeRange(TimeRange.medium_term).build();
        Paging<Track> topTracks = client.sendRequest(getUsersTopTracksRequest);
        System.out.println(topTracks);
    }

    private void deleteFollowingPlaylist(SpotifyApiClient client)
    {
        DeleteFollowPlaylist deleteFollowingRequest = new DeleteFollowPlaylist.Builder("1khS5Pll0YEduwuZdciEbe")
                .build();
        //Void unfollow = client.sendRequest(deleteFollowingRequest);
        //System.out.println(unfollow);
    }

    private void deleteMeFollowing(SpotifyApiClient client)
    {
        List<String> ids = new ArrayList<>();
        ids.add("37M5pPGs6V1fchFJSgCguX");
        DeleteFollow deleteFollowingRequest = new DeleteFollow.Builder(FollowType.artist, ids)
                .build();
        //Void unfollow = client.sendRequest(deleteFollowingRequest);
        //System.out.println(unfollow);
    }

    private void getMeFollowing(SpotifyApiClient client)
    {
        GetMeFollowing getFollowingRequest = new GetMeFollowing.Builder(FollowType.artist)
                .limit(50).after("0rH93aHDYyJfMAcPB9OKus").build();
        Following following = client.sendRequest(getFollowingRequest);
        System.out.println(following);
    }

    private void putPlaylist(SpotifyApiClient client)
    {
        String id = "1khS5Pll0YEduwuZdciEbe";

        PutFollowPlaylist getUserFollowsPlaylistRequest = new PutFollowPlaylist.Builder(id)
                .isPublic(false).build();
        //Void follows = client.sendRequest(getUserFollowsPlaylistRequest);
        //System.out.println(follows);
    }

    private void putFollow(SpotifyApiClient client)
    {
        List<String> ids = new ArrayList<>();
        ids.add("greentekki");

        PutFollow getUserFollowsPlaylistRequest = new PutFollow.Builder(FollowType.user, ids)
                .build();
        //Void follows = client.sendRequest(getUserFollowsPlaylistRequest);
        //System.out.println(follows);
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
        GetFeaturedPlaylists featuredPlaylistsRequest = new GetFeaturedPlaylists.Builder()
                .country("BR").limit(50).offset(0).timestamp("2014-10-23T09:00:00").build();
        FeaturedPlaylists featuredPlaylists = client.sendRequest(featuredPlaylistsRequest);
        System.out.println(featuredPlaylists);
    }

    private void getAllNewReleases(SpotifyApiClient client)
    {
        GetAllNewReleases recommendationGenresRequest = new GetAllNewReleases.Builder()
                .country("CA").limit(50).offset(0).build();
        NewReleases recommendationGenres = client.sendRequest(recommendationGenresRequest);
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
                .limit(100).market("CA")
                .min(acousticness, 0.0).max(acousticness, 1.0).target(acousticness, 0.5)
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

    private void getAudioAudioAnalysis(SpotifyApiClient client) throws ExecutionException, InterruptedException
    {
        GetAudioAnalysis getAudioAnalysisRequest = new GetAudioAnalysis.Builder("74SFqzOS8Z0rbbG2llSVaQ").build();
        AudioAnalysis audioAnalysis = client.sendRequestAsync(getAudioAnalysisRequest)
                .get();
        System.out.println(audioAnalysis);
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
        AbstractSpotifyRequest<Artist> getArtist = new GetArtist.Builder("7Ln80lUS6He07XvHI8qqHH").build();
        Artist newArcticMonkeys = client.sendRequest(getArtist);
        System.out.println(newArcticMonkeys);
    }

    private void addTrackToPlaylist(SpotifyApiClient client)
    {
        List<String> trackIds = new ArrayList<>();
        trackIds.add("spotify:track:63xdwScd1Ai1GigAwQxE8y");
        trackIds.add("spotify:track:5uIRujGRZv5t4fGKkUTv4n");

        PostPlaylistsTracksAdd playlistRequest = new PostPlaylistsTracksAdd.Builder("3X3gtW72Wwh6v1RR27ZgDe")
                .position(0).uris(trackIds).build();
        PlaylistSnapshot snapshot = client.sendRequest(playlistRequest);
        snapshotId = snapshot.getSnapshot_id();
        System.out.println(snapshotId);
    }
}
