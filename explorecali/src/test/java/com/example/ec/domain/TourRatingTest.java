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
package com.example.ec.domain;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
/**
 * Created by Mary Ellen Bowman
 */
public class TourRatingTest {

    private Tour tour = new Tour("title","description","blurb", 50, "1 day", "bullet",
            "keywords",new TourPackage("CC","name"), Difficulty.Difficult, Region.Central_Coast);

    @Test
    public void testConstructor1() throws Exception {
        TourRating rating = new TourRating(tour, 1, 1, "comment");
        testIt(rating);
        assertThat(rating.getComment(), is("comment"));
    }
    @Test
    public void testConstructor2() throws Exception {
        TourRating rating = new TourRating(tour, 1, 1);
        testIt(rating);
        assertThat(rating.getComment(), is("Terrible"));
    }

    private void testIt(TourRating rating){
        assertThat(rating.getId(), is(nullValue()));
        assertThat(rating.getTour(), is(tour));
        assertThat(rating.getScore(), is(1));
        assertThat(rating.getCustomerId(), is(1));
    }

    @Test
    public void equalsHashcodeVerify() {
        TourRating rating1 = new TourRating(tour, 1, 1, "comment");
        TourRating rating2 = new TourRating(tour, 1, 1, "comment");

        assertEquals(rating1,rating2);
        assertEquals(rating1.hashCode(), rating2.hashCode());
    }
}