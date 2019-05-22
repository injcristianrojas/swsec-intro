package swsec.api.helpers;

import java.io.UnsupportedEncodingException;
import java.security.Key;

import org.jose4j.jws.AlgorithmIdentifiers;
import org.jose4j.jws.JsonWebSignature;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.jwt.consumer.InvalidJwtException;
import org.jose4j.jwt.consumer.JwtConsumer;
import org.jose4j.jwt.consumer.JwtConsumerBuilder;
import org.jose4j.keys.HmacKey;
import org.jose4j.lang.JoseException;

public class TokenSecurity {

	private static String SECRET = "abc";
	private static String TOKEN_ISSUER = "injcristianrojas";
	private static int EXPIRATION_TIME_IN_MINUTES = 30;
	public static String TOKEN_PREFIX = "Bearer ";
	
	public static String generateJwtTokenSHA(String id) throws JoseException, UnsupportedEncodingException {
		JwtClaims claims = new JwtClaims();
		claims.setExpirationTimeMinutesInTheFuture(EXPIRATION_TIME_IN_MINUTES);
		claims.setIssuer(TOKEN_ISSUER);
	    claims.setGeneratedJwtId();
	    claims.setIssuedAtToNow();
	    claims.setNotBeforeMinutesInThePast(2);
	    claims.setClaim( "id", id );
	    
	    Key key = new HmacKey(SECRET.getBytes("UTF-8"));
	    JsonWebSignature jws = new JsonWebSignature();
	    jws.setPayload(claims.toJson());
	    jws.setAlgorithmHeaderValue(AlgorithmIdentifiers.HMAC_SHA256);
	    jws.setKey(key);
	    jws.setDoKeyValidation(false);
	    
	    return jws.getCompactSerialization();
	}
	
	public static String validateJwtTokenSHA(String jwt) throws InvalidJwtException, UnsupportedEncodingException {
		Key key = new HmacKey(SECRET.getBytes("UTF-8"));
		JwtConsumer jwtConsumer = new JwtConsumerBuilder()
		        .setRequireExpirationTime()
		        .setMaxFutureValidityInMinutes(300) 
		        .setAllowedClockSkewInSeconds(30)
		        .setExpectedIssuer(TOKEN_ISSUER)
		        .setVerificationKey(key)
		        .setRelaxVerificationKeyValidation() // relaxes key length requirement 
		        .build();

		JwtClaims processedClaims = jwtConsumer.processToClaims(jwt.replace(TOKEN_PREFIX, ""));
		return processedClaims.getClaimsMap().get("id").toString();
	}
}