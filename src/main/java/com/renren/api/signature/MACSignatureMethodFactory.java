package com.renren.api.signature;

/**
 * 实现了MAC令牌的签名
 * Implements the signatures defined in oauth2 MAC token
 *
 * @author Ryan Heaton
 */
public class MACSignatureMethodFactory{

  private boolean supportHMAC_SHA1 = true;
  private boolean supportHMAC_SHA256 = true;

  /**
   * 根据签名方法名来获得签名方法
   */
  public OAuth2SignatureMethod getSignatureMethod(String methodName, SharedSecret shareSecret) throws UnsupportedSignatureMethodException {
    if (supportHMAC_SHA1 && HMACSHA1SignatureMethod.SIGNATURE_NAME.equalsIgnoreCase(methodName)) {
      return new HMACSHA1SignatureMethod(shareSecret);
    }
    throw new UnsupportedSignatureMethodException("Unsupported signature method: " + methodName);
  }

}
