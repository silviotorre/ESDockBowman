/**
 * Copyright 2021 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.ec.web;

import com.example.ec.domain.Tour;
import com.example.ec.domain.TourRating;
import com.example.ec.service.TourRatingService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.StringContains.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

/**
 * Invoke the Controller methods via HTTP.
 * Do not invoke the tourRatingService methods, use Mock instead
 * Created by Mary Ellen Bowman.
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class RatingControllerTest {
    private static final String RATINGS_URL = "/ratings";

    //These Tour and rating id's do not already exist in the db
    private static final int TOUR_ID = 999;
    private static final int RATING_ID = 555;
    private static final int CUSTOMER_ID = 1000;
    private static final int SCORE = 3;
    private static final String COMMENT = "comment";

    @MockBean
    private TourRatingService tourRatingServiceMock;

    @Mock
    private TourRating tourRatingMock;

    @Mock
    private Tour tourMock;

    @Autowired
    private TestRestTemplate restTemplate;


    @Before
    public void setupReturnValuesOfMockMethods() {
        when(tourRatingMock.getTour()).thenReturn(tourMock);
        when(tourMock.getId()).thenReturn(TOUR_ID);
        when(tourRatingMock.getComment()).thenReturn(COMMENT);
        when(tourRatingMock.getScore()).thenReturn(SCORE);
        when(tourRatingMock.getCustomerId()).thenReturn(CUSTOMER_ID);
    }


    /**
     *  HTTP GET /ratings
     */
    @Test
    public void getRatings() {
        when(tourRatingServiceMock.lookupAll()).thenReturn(Arrays.asList(tourRatingMock, tourRatingMock, tourRatingMock));

        ResponseEntity<List<RatingDto>> response = restTemplate.exchange(RATINGS_URL, HttpMethod.GET,null,
                                                    new ParameterizedTypeReference<List<RatingDto>>() {});

        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        assertThat(response.getBody().size(), is(3));
    }

    /**
     *  HTTP GET /ratings/{id}
     */
    @Test
    public void getOne()  {

        when(tourRatingServiceMock.lookupRatingById(RATING_ID)).thenReturn(Optional.of(tourRatingMock));

        ResponseEntity<RatingDto> response =
                restTemplate.getForEntity(RATINGS_URL + "/" + RATING_ID, RatingDto.class);

        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        assertThat(response.getBody().getCustomerId(), is(CUSTOMER_ID));
        assertThat(response.getBody().getComment(), is(COMMENT));
        assertThat(response.getBody().getScore(), is(SCORE));
    }
    @Test
    public void getOne_notFound() {
        when(tourRatingServiceMock.lookupRatingById(RATING_ID))
                .thenReturn(Optional.empty());

        ResponseEntity<String> response =
                restTemplate.getForEntity(RATINGS_URL + "/" + RATING_ID, String.class);
        assertThat(response.getStatusCode(), is(HttpStatus.NOT_FOUND));
        assertThat(response.getBody(),
                containsString("Rating " + RATING_ID + " not found"));
    }
}