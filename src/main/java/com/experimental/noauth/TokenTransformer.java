package com.experimental.noauth;

import com.github.tomakehurst.wiremock.client.ResponseDefinitionBuilder;
import com.github.tomakehurst.wiremock.common.FileSource;
import com.github.tomakehurst.wiremock.extension.Parameters;
import com.github.tomakehurst.wiremock.extension.ResponseDefinitionTransformer;
import com.github.tomakehurst.wiremock.http.Request;
import com.github.tomakehurst.wiremock.http.ResponseDefinition;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

/**
 * Translates information smuggled into the authorization bearer into token info details.
 *
 * @see "http://wiremock.org/docs/extending-wiremock/"
 */
public class TokenTransformer extends ResponseDefinitionTransformer {

    @Override
    public ResponseDefinition transform(Request request, ResponseDefinition responseDefinition, FileSource files, Parameters parameters) {

        String scopes = "";

        String requestBody = decodedRequestBody(request);
        if (requestBody != null && !requestBody.isEmpty()) {
            scopes = requestBody.split("=")[1].trim();
        }

        //language=JSON
        String responseBody = "{\n" +
                "  \"active\" : true,\n" +
                "  \"client_id\": \"apitest\",\n" +
                "  \"scope\" : [\"" + scopes + "\"]\n" +
                "}";

        return new ResponseDefinitionBuilder()
                .withHeader("Content-Type", "application/json")
                .withStatus(200)
                .withBody(responseBody)
                .build();
    }

    @Override
    public String getName() {
        return "token-transformer";
    }

    private static String decodedRequestBody(Request request) {
        try {
            return URLDecoder.decode(request.getBodyAsString(), StandardCharsets.UTF_8.toString());
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }
}
