FROM rodolpheche/wiremock:2.26.1

ADD ./build/libs /var/wiremock/extensions

CMD java $JAVA_OPTS -cp /var/wiremock/lib/*:/var/wiremock/extensions/* \
    com.github.tomakehurst.wiremock.standalone.WireMockServerRunner \
    --extensions com.experimental.noauth.TokenTransformer
