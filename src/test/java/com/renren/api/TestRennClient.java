package com.renren.api;

import junit.framework.Assert;

import org.junit.Test;

import com.renren.api.service.User;

public class TestRennClient {

    public static final String API_KEY = "fee11992a4ac4caabfca7800d233f814";

    public static final String SECRET_KEY = "a617e78710454b12aab68576382e8e14";

    public static final String BEARER_TOKEN = "127089|2.g6mjfTbhTRenE72hv7ZgTalQuqCv50uX.318782080.1339497972985";

    public static final String REFRESH_TOKEN = "127089|2.g6mjfTbhTRenE72hv7ZgTalQuqCv50uX.318782080.1339497972985";

    public static final AccessToken BEARER_ACCESS_TOKEN = new AccessToken(AccessToken.Type.Bearer,
            BEARER_TOKEN, REFRESH_TOKEN, null, null);

    public static final String MAC_TOKEN = "127089|2.g6mjfTbhTRenE72hv7ZgTalQuqCv50uX.318782080.1339497972985";

    public static final String MAC_KEY = "c57b5a3c6db747b7bb2ca6d877827369";

    public static final String MAC_ALGORITHM = "hmac-sha-1";

    public static final AccessToken MAC_ACCESS_TOKEN = new AccessToken(AccessToken.Type.MAC,
            MAC_TOKEN, null, MAC_KEY, MAC_ALGORITHM);

    @Test
    public void testAuthWithMACTokenObject() {
        RennClient client = new RennClient(API_KEY, SECRET_KEY);
        try {
            client.authorizeWithAccessToken(MAC_ACCESS_TOKEN);
        } catch (AuthorizationException e) {
            Assert.assertTrue(false);
        }
        try {
            User user = client.getUserService().getUser(222209506l);
            Assert.assertEquals(222209506l, user.getId());
        } catch (RennException e) {
            Assert.assertTrue(false);
        }
    }

    @Test
    public void testAuthWithMACToken() {
        RennClient client = new RennClient(API_KEY, SECRET_KEY);
        try {
            client.authorizeWithAccessToken(MAC_TOKEN, MAC_KEY, MAC_ALGORITHM);
        } catch (AuthorizationException e) {
            Assert.assertTrue(false);
        }
        try {
            User user = client.getUserService().getUser(222209506l);
            Assert.assertEquals(222209506l, user.getId());
        } catch (RennException e) {
            Assert.assertTrue(false);
        }
    }

    @Test
    public void testAuthWithAuthorizationCode() {
        RennClient client = new RennClient(API_KEY, SECRET_KEY);
        try {
            client.authorizeWithAuthorizationCode("adummycode", "http://www.renren.com/callback");
        } catch (AuthorizationException e) {
            Assert.assertTrue(true);
        }
    }

    @Test
    public void testAuthWithClientCredentials() {
        RennClient client = new RennClient(API_KEY, SECRET_KEY);
        try {
            client.authorizeWithClientCredentials();
        } catch (AuthorizationException e) {
            Assert.assertTrue(false);
        }
        try {
            User user = client.getUserService().getUser(222209506l);
            Assert.assertEquals(222209506l, user.getId());
        } catch (RennException e) {
            Assert.assertTrue(false);
        }
    }

    @Test
    public void testAuthWithResourceOwnerPassword() {
        RennClient client = new RennClient(API_KEY, SECRET_KEY);
        try {
            client.authorizeWithResourceOwnerPassword("adummymail@renren.com", "adummypassword");
        } catch (AuthorizationException e) {
            Assert.assertTrue(e.getMessage().contains("Invalid username or password."));
        }
    }

}
