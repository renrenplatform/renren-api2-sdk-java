/**
 * @(#)RennClient.java, Jan 21, 2013. 
 * 
 * Copyright 2012 RenRen, Inc. All rights reserved.
 */
package com.renren.api;

import java.net.HttpURLConnection;
import java.util.HashMap;
import java.util.Map;

import com.renren.api.AccessToken.Type;
import com.renren.api.http.HttpRequest;
import com.renren.api.http.HttpRequest.HttpRequestException;
import com.renren.api.json.JSONException;
import com.renren.api.json.JSONObject;
import com.renren.api.mapper.ObjectMapper;
import com.renren.api.service.AppService;
import com.renren.api.service.StatusService;
import com.renren.api.service.LocationService;
import com.renren.api.service.AlbumService;
import com.renren.api.service.BlogService;
import com.renren.api.service.LikeService;
import com.renren.api.service.PhotoService;
import com.renren.api.service.ShareService;
import com.renren.api.service.NotificationService;
import com.renren.api.service.UbbService;
import com.renren.api.service.FeedService;
import com.renren.api.service.PlaceService;
import com.renren.api.service.CommentService;
import com.renren.api.service.UserService;
import com.renren.api.service.FriendService;
import com.renren.api.service.ProfileService;


/**
 * @author Xun Dai <xun.dai@renren-inc.com>
 * 
 */
public class RennClient {

    /**
     * 客户端ID。应用的App ID。
     */
    private String clientId;

    /**
     * 客户端密钥。应用的Secret Key。
     */
    private String clientSecret;

    /**
     * Access Token
     */
    private AccessToken accessToken;

    /**
     * API执行器
     */
    private RennExecutor executor;

    /**
     * JSON到对象映射策略对象
     */
    private ObjectMapper objectMapper;

    /**
     *  app 对应的API服务
     */
    private AppService appService;
    /**
     *  status 对应的API服务
     */
    private StatusService statusService;
    /**
     *  location 对应的API服务
     */
    private LocationService locationService;
    /**
     *  album 对应的API服务
     */
    private AlbumService albumService;
    /**
     *  blog 对应的API服务
     */
    private BlogService blogService;
    /**
     *  like 对应的API服务
     */
    private LikeService likeService;
    /**
     *  photo 对应的API服务
     */
    private PhotoService photoService;
    /**
     *  share 对应的API服务
     */
    private ShareService shareService;
    /**
     *  notification 对应的API服务
     */
    private NotificationService notificationService;
    /**
     *  ubb 对应的API服务
     */
    private UbbService ubbService;
    /**
     *  feed 对应的API服务
     */
    private FeedService feedService;
    /**
     *  place 对应的API服务
     */
    private PlaceService placeService;
    /**
     *  comment 对应的API服务
     */
    private CommentService commentService;
    /**
     *  user 对应的API服务
     */
    private UserService userService;
    /**
     *  friend 对应的API服务
     */
    private FriendService friendService;
    /**
     *  profile 对应的API服务
     */
    private ProfileService profileService;

    /**
     * @param clientId
     * @param clientSecret
     */
    public RennClient(String clientId, String clientSecret) {
        super();
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.executor = new DefaultRennExecutor();
        this.objectMapper = new ObjectMapper();
    }

    public AccessToken authorizeWithAuthorizationCode(String code, String redirectUri)
            throws AuthorizationException {

        Map<String, String> params = new HashMap<String, String>();
        params.put("client_id", this.clientId);
        params.put("client_secret", this.clientSecret);
        params.put("grant_type", "authorization_code");
        params.put("code", code);
        params.put("redirect_uri", redirectUri);
        params.put("token_type", AccessToken.Type.MAC.toString());

        this.accessToken = requestAccessToken(params);
        return this.accessToken;
    }

    /**
     * 
     * @param params
     * @return
     * @throws AuthorizationException
     */
    private AccessToken requestAccessToken(Map<String, String> params)
            throws AuthorizationException {
        try {
            HttpRequest request = HttpRequest.post("https://graph.renren.com/oauth/token");
            request.form(params);
            int statusCode = request.code();
            String body = request.body();
            JSONObject response = new JSONObject(body);

            if (statusCode == HttpURLConnection.HTTP_OK && response.has("access_token")) {
                String accessToken = response.getString("access_token");
                String refreshToken = response.has("refresh_token") ? response
                        .getString("refresh_token") : null;
                String macKey = response.has("mac_key") ? response.getString("mac_key") : null;
                String macAlgorithm = response.has("mac_algorithm") ? response
                        .getString("mac_algorithm") : null;

                AccessToken.Type type = response.has("mac_algorithm")
                        && response.has("mac_algorithm") ? AccessToken.Type.MAC
                        : AccessToken.Type.Bearer;
                
                int expiresIn = response.getInt("expires_in");

                return new AccessToken(type, accessToken, refreshToken, macKey, macAlgorithm, expiresIn);

            } else {
                throw new AuthorizationException("Authorization failed with Authorization Code. "
                        + response.getString("error") + ": "
                        + response.getString("error_description"));
            }

        } catch (HttpRequestException e) {
            throw new AuthorizationException("Authorization failed. Failed to send http request");
        } catch (JSONException e) {
            throw new AuthorizationException("Parse OAuth response failed. " + e.getMessage());
        }
    }

    public void authorizeWithAccessToken(String bearerToken) throws AuthorizationException {
        this.accessToken = new AccessToken(Type.Bearer, bearerToken, null, null, null);
    }

    public void authorizeWithAccessToken(String macToken, String macKey, String macAlgorithm)
            throws AuthorizationException {
        this.accessToken = new AccessToken(Type.MAC, macToken, null, macKey, macAlgorithm);
    }

    public void authorizeWithAccessToken(AccessToken token) throws AuthorizationException {
        this.accessToken = token;
    }

    public void authorizeWithClientCredentials() throws AuthorizationException {
        Map<String, String> params = new HashMap<String, String>();
        params.put("client_id", this.clientId);
        params.put("client_secret", this.clientSecret);
        params.put("grant_type", "client_credentials");
        params.put("token_type", AccessToken.Type.MAC.toString());

        this.accessToken = requestAccessToken(params);
    }

    public void authorizeWithResourceOwnerPassword(String username, String password)
            throws AuthorizationException {
        Map<String, String> params = new HashMap<String, String>();
        params.put("client_id", this.clientId);
        params.put("client_secret", this.clientSecret);
        params.put("grant_type", "password");
        params.put("username", username);
        params.put("password", password);
        params.put("token_type", AccessToken.Type.MAC.toString());

        this.accessToken = requestAccessToken(params);
    }

    /**
     * @return the appService
     */
    public AppService getAppService(){
        if (appService == null) {
            appService = new AppService(executor, accessToken, objectMapper);
        }
        return appService;
    }
    /**
     * @return the statusService
     */
    public StatusService getStatusService(){
        if (statusService == null) {
            statusService = new StatusService(executor, accessToken, objectMapper);
        }
        return statusService;
    }
    /**
     * @return the locationService
     */
    public LocationService getLocationService(){
        if (locationService == null) {
            locationService = new LocationService(executor, accessToken, objectMapper);
        }
        return locationService;
    }
    /**
     * @return the albumService
     */
    public AlbumService getAlbumService(){
        if (albumService == null) {
            albumService = new AlbumService(executor, accessToken, objectMapper);
        }
        return albumService;
    }
    /**
     * @return the blogService
     */
    public BlogService getBlogService(){
        if (blogService == null) {
            blogService = new BlogService(executor, accessToken, objectMapper);
        }
        return blogService;
    }
    /**
     * @return the likeService
     */
    public LikeService getLikeService(){
        if (likeService == null) {
            likeService = new LikeService(executor, accessToken, objectMapper);
        }
        return likeService;
    }
    /**
     * @return the photoService
     */
    public PhotoService getPhotoService(){
        if (photoService == null) {
            photoService = new PhotoService(executor, accessToken, objectMapper);
        }
        return photoService;
    }
    /**
     * @return the shareService
     */
    public ShareService getShareService(){
        if (shareService == null) {
            shareService = new ShareService(executor, accessToken, objectMapper);
        }
        return shareService;
    }
    /**
     * @return the notificationService
     */
    public NotificationService getNotificationService(){
        if (notificationService == null) {
            notificationService = new NotificationService(executor, accessToken, objectMapper);
        }
        return notificationService;
    }
    /**
     * @return the ubbService
     */
    public UbbService getUbbService(){
        if (ubbService == null) {
            ubbService = new UbbService(executor, accessToken, objectMapper);
        }
        return ubbService;
    }
    /**
     * @return the feedService
     */
    public FeedService getFeedService(){
        if (feedService == null) {
            feedService = new FeedService(executor, accessToken, objectMapper);
        }
        return feedService;
    }
    /**
     * @return the placeService
     */
    public PlaceService getPlaceService(){
        if (placeService == null) {
            placeService = new PlaceService(executor, accessToken, objectMapper);
        }
        return placeService;
    }
    /**
     * @return the commentService
     */
    public CommentService getCommentService(){
        if (commentService == null) {
            commentService = new CommentService(executor, accessToken, objectMapper);
        }
        return commentService;
    }
    /**
     * @return the userService
     */
    public UserService getUserService(){
        if (userService == null) {
            userService = new UserService(executor, accessToken, objectMapper);
        }
        return userService;
    }
    /**
     * @return the friendService
     */
    public FriendService getFriendService(){
        if (friendService == null) {
            friendService = new FriendService(executor, accessToken, objectMapper);
        }
        return friendService;
    }
    /**
     * @return the profileService
     */
    public ProfileService getProfileService(){
        if (profileService == null) {
            profileService = new ProfileService(executor, accessToken, objectMapper);
        }
        return profileService;
    }
}

