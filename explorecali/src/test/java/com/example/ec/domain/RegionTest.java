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

import static org.hamcrest.core.Is.is;
import static org.hamcrest.MatcherAssert.assertThat;
/**
 * Created by Mary Ellen Bowman.
 */
public class RegionTest {
    @Test
    public void findByLabel() throws Exception {
        assertThat(Region.Central_Coast, is(Region.findByLabel("Central Coast")));
        assertThat(Region.Northern_California, is(Region.findByLabel("Northern California")));
        assertThat(Region.Southern_California, is(Region.findByLabel("Southern California")));
        assertThat(Region.Varies, is(Region.findByLabel("Varies")));
    }

    @Test
    public void getLabel() throws Exception {
        assertThat(Region.Central_Coast.getLabel(), is("Central Coast"));
        assertThat(Region.Northern_California.getLabel(), is("Northern California"));
        assertThat(Region.Southern_California.getLabel(), is("Southern California"));
        assertThat(Region.Varies.getLabel(), is("Varies"));
    }

}