package edu.api.reactive.examen.gt.constants;

public class JWTConstants {
	public static final String SALT_KEY = "feed23942715ff7f319bc765078e97ec3929868c";
	public static final int TOKEN_VALIDITY = 86_400 * 1_000;
	public static final String AUTHORITIES_KEY = "auth";
	public static final String BEARER = "Bearer ";
	public static final String ROLE_ADMIN = "ROLE_ADMIN";
	public static final String LOGIN_URL = "/login";
	public static final String[] AUTH_WHITELIST = { "/login", "/users" };
}
