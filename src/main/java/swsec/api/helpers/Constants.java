package swsec.api.helpers;

import org.jose4j.jws.AlgorithmIdentifiers;

public class Constants {
	public static final String SECRET = "abc";
	public static final String TOKEN_ISSUER = "injcristianrojas";
	public static final int EXPIRATION_TIME_IN_MINUTES = 30;
	public static final String VERIFICATION_ALGORITHM = AlgorithmIdentifiers.HMAC_SHA256;
	
	public static final String TOKEN_PREFIX = "Bearer ";
	
	public static final boolean USE_JWT_AUTH = true;
}
