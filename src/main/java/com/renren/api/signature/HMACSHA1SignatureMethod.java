/*
 * Copyright 2008 Web Cohesion
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.renren.api.signature;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import com.renren.api.codec.Base64;

/**
 * HMAC-SHA1 signature method.
 * @author Ryan Heaton
 */
public class HMACSHA1SignatureMethod implements OAuth2SignatureMethod {

    /**
     * The name of this HMAC-SHA-1 signature method ("HMAC-SHA-1").
     */
    public static final String SIGNATURE_NAME = "hmac-sha-1";

    /**
     * The MAC name (for interfacing with javax.crypto.*). "HmacSHA1".
     */
    public static final String MAC_NAME = "HmacSHA1";

    private final SecretKey key;

    /**
     * Construct a HMAC-SHA1 signature method with the given HMAC-SHA1 key.
     * 
     * @param key
     *            The key.
     */
    public HMACSHA1SignatureMethod(SharedSecret signatureSecret) {
        // 获得签名的密钥
        String consumerSecret = signatureSecret.getConsumerSecret();
        if (consumerSecret == null) {
            consumerSecret = "";
        }

        byte[] keyBytes;
        try {
            keyBytes = new String(URLEncoder.encode(consumerSecret, "UTF-8")
                    .getBytes(), "US-ASCII").getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e.getMessage());
        }

        // 返回签名方法
        SecretKeySpec spec = new SecretKeySpec(keyBytes,
                HMACSHA1SignatureMethod.MAC_NAME);
        this.key = spec;

    }

    /**
     * The name of this HMAC-SHA1 signature method ("HMAC-SHA1").
     * 
     * @return The name of this HMAC-SHA1 signature method.
     */
    public String getName() {
        return SIGNATURE_NAME;
    }

    /**
     * Sign the signature base string. The signature is the digest octet string,
     * first base64-encoded per RFC2045, section 6.8, then URL-encoded per OAuth
     * Parameter Encoding.
     * 
     * @param signatureBaseString
     *            The signature base string.
     * @return The signature.
     */
    public String sign(String signatureBaseString) {
        try {
            Mac mac = Mac.getInstance(MAC_NAME);
            mac.init(key);
            byte[] text = signatureBaseString.getBytes("UTF-8");
            byte[] signatureBytes = mac.doFinal(text);
            signatureBytes = Base64.encodeBase64(signatureBytes);
            String signature = new String(signatureBytes, "UTF-8");
            return signature;
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException(e);
        } catch (InvalidKeyException e) {
            throw new IllegalStateException(e);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Verify the signature of the given signature base string. The signature is
     * verified by generating a new request signature octet string, and
     * comparing it to the signature provided by the Consumer, first URL-decoded
     * per Parameter Encoding, then base64-decoded per RFC2045 section 6.8. The
     * signature is generated using the request parameters as provided by the
     * Consumer, and the Consumer Secret and Token Secret as stored by the
     * Service Provider.
     * 
     * @param signatureBaseString
     *            The signature base string.
     * @param signature
     *            The signature.
     * @throws InvalidSignatureException
     *             If the signature is invalid for the specified base string.
     */
    public void verify(String signatureBaseString, String signature)
            throws InvalidSignatureException {
        try {
            byte[] signatureBytes = Base64.decodeBase64(signature
                    .getBytes("UTF-8"));

            Mac mac = Mac.getInstance(MAC_NAME);
            mac.init(key);
            byte[] text = signatureBaseString.getBytes("UTF-8");
            byte[] calculatedBytes = mac.doFinal(text);
            if (!safeArrayEquals(calculatedBytes, signatureBytes)) {
                throw new InvalidSignatureException(
                        "Invalid signature for signature method " + getName());
            }
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException(e);
        } catch (InvalidKeyException e) {
            throw new IllegalStateException(e);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    boolean safeArrayEquals(byte[] a1, byte[] a2) {
        if (a1 == null || a2 == null) {
            return (a1 == a2);
        }

        if (a1.length != a2.length) {
            return false;
        }

        byte result = 0;
        for (int i = 0; i < a1.length; i++) {
            result |= a1[i] ^ a2[i];
        }

        return (result == 0);
    }

    /**
     * The secret key.
     * 
     * @return The secret key.
     */
    public SecretKey getSecretKey() {
        return key;
    }
}
