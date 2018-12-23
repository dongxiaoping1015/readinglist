package com.dong.readinglist;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.fail;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ReadinglistApplication.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class SimpleWebTest {

    @Test(expected = HttpClientErrorException.class)
    public void pageNotFound() {
       try {
           RestTemplate rest = new RestTemplate();
           rest.getForObject("http://localhost:8081/bogusPage", String.class); // 发起GET请求
           fail("Should result in HTTP 404");
       } catch (HttpClientErrorException e) {
           assertEquals(HttpStatus.NOT_FOUND, e.getStatusCode());
           throw e;
       }
    }
}