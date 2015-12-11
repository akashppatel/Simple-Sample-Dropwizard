package org.akashpatel.dropwizard.jwt.auth.example;

import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.joda.time.DateTime;

import com.github.toastshaman.dropwizard.auth.jwt.hmac.HmacSHA512Signer;
import com.github.toastshaman.dropwizard.auth.jwt.model.JsonWebToken;
import com.github.toastshaman.dropwizard.auth.jwt.model.JsonWebTokenClaim;
import com.github.toastshaman.dropwizard.auth.jwt.model.JsonWebTokenHeader;
import io.dropwizard.auth.Auth;
import java.security.Principal;

import static java.util.Collections.singletonMap;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/jwt")
@Produces(APPLICATION_JSON)
public class SecuredResource {

    private final byte[] tokenSecret;

    public SecuredResource(byte[] tokenSecret) {
        this.tokenSecret = tokenSecret;
    }

    @GET
    @Path("/generate-expired-token")
    public Map<String, String> generateExpiredToken() {
        final HmacSHA512Signer signer = new HmacSHA512Signer(tokenSecret);
        final JsonWebToken token = JsonWebToken.builder()
            .header(JsonWebTokenHeader.HS512())
            .claim(JsonWebTokenClaim.builder()
                .subject("good-guy")
                .issuedAt(new DateTime().plusHours(1))
                .build())
            .build();
        final String signedToken = signer.sign(token);
        return singletonMap("token", signedToken);
    }

    @GET
    @Path("/generate-valid-token")
    public Map<String, String> generateValidToken() {
        final HmacSHA512Signer signer = new HmacSHA512Signer(tokenSecret);
        final JsonWebToken token = JsonWebToken.builder()
            .header(JsonWebTokenHeader.HS512())
            .claim(JsonWebTokenClaim.builder()
                .subject("good-guy")
                .issuedAt(DateTime.now())
                .build())
            .build();
        final String signedToken = signer.sign(token);
        return singletonMap("token", signedToken);
    }

    @GET
    @Path("/check-token")
    public Map<String, String> get(@Auth Principal user) {
        return singletonMap("username", user.getName());
    }
}