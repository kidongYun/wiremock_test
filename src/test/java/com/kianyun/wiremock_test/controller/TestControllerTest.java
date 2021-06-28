package com.kianyun.wiremock_test.controller;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.Result;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.containing;
import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;
import static com.github.tomakehurst.wiremock.client.WireMock.matching;
import static com.github.tomakehurst.wiremock.client.WireMock.ok;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.postRequestedFor;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo;
import static com.github.tomakehurst.wiremock.client.WireMock.verify;
import static junit.framework.TestCase.assertTrue;

@RunWith(SpringRunner.class)
public class TestControllerTest {
    @Rule
    public WireMockRule wireMockRule = new WireMockRule(8089);

    @Before
    public void setupStub() {
        stubFor(post(urlEqualTo("/hello")) // url로 요청이 오면
                .willReturn(aResponse() // 응답으로
                        .withStatus(200) // 200을 전송하고
                        .withHeader("Content-Type", "application/json") // JSON 타입의
                        .withBody("{....json데이터...}"))); // 데이터를 전송

    }

    @Test
    public void run() {

    }

    @Test
    public void exampleTest() {
        stubFor(post("/my/resource")
                .withHeader("Content-Type", containing("xml"))
                .willReturn(ok()
                        .withHeader("Content-Type", "text/xml")
                        .withBody("<response>SUCCESS</response>")));

//        Result result = myHttpServiceCallingObject.doSomething();
//        assertTrue(result.wasSuccessful());

        verify(postRequestedFor(urlPathEqualTo("/my/resource"))
                .withRequestBody(matching(".*message-1234.*"))
                .withHeader("Content-Type", equalTo("text/xml")));
    }
}