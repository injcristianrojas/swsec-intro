package swsec.api.helpers;

import java.nio.charset.StandardCharsets;
import java.security.Key;

import org.jose4j.jws.JsonWebSignature;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.jwt.MalformedClaimException;
import org.jose4j.jwt.consumer.InvalidJwtException;
import org.jose4j.jwt.consumer.JwtConsumer;
import org.jose4j.jwt.consumer.JwtConsumerBuilder;
import org.jose4j.keys.HmacKey;
import org.jose4j.lang.JoseException;

public class TokenSecurity {
	
	public static String generateJwtTokenSHA(String id) throws JoseException {
		JwtClaims claims = new JwtClaims();
		claims.setExpirationTimeMinutesInTheFuture(Constants.EXPIRATION_TIME_IN_MINUTES);
		claims.setIssuer(Constants.TOKEN_ISSUER);
	    claims.setGeneratedJwtId();
	    claims.setIssuedAtToNow();
	    claims.setNotBeforeMinutesInThePast(2);
	    claims.setClaim( "id", id );
	    
	    Key key = new HmacKey(Constants.SECRET.getBytes(StandardCharsets.UTF_8));
	    JsonWebSignature jws = new JsonWebSignature();
	    jws.setPayload(claims.toJson());
	    jws.setAlgorithmHeaderValue(Constants.VERIFICATION_ALGORITHM);
	    jws.setKey(key);
	    jws.setDoKeyValidation(false);
	    
	    return jws.getCompactSerialization();
	}
	
	public static boolean isTokenValid(String jwt) throws InvalidJwtException, MalformedClaimException {
		Key key = new HmacKey(Constants.SECRET.getBytes(StandardCharsets.UTF_8));
		JwtConsumer jwtConsumer = new JwtConsumerBuilder()
		        .setRequireExpirationTime()
		        .setMaxFutureValidityInMinutes(300) 
		        .setAllowedClockSkewInSeconds(30)
		        .setExpectedIssuer(Constants.TOKEN_ISSUER)
		        .setVerificationKey(key)
		        .setRelaxVerificationKeyValidation() // relaxes key length requirement 
		        .build();

		JwtClaims claims = jwtConsumer.processToClaims(jwt.replace(Constants.TOKEN_PREFIX, ""));
		return claims.getIssuer().equals(Constants.TOKEN_ISSUER);
	}
}