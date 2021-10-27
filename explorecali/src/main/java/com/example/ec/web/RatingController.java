/*
 Copyright 2021 Google LLC

 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

      https://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
 */

package com.example.ec.web;

import com.example.ec.service.TourRatingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;


/**
 * Created by Mary Ellen Bowman
 */
@RestController
@RequestMapping(path = "/ratings")
public class RatingController {
    private static final Logger LOGGER = LoggerFactory.getLogger(RatingController.class);
    private TourRatingService tourRatingService;


    @Autowired
    public RatingController(TourRatingService tourRatingService) {
        this.tourRatingService = tourRatingService;
    }

    @GetMapping
    public List<RatingDto> getAll() {
        LOGGER.info("GET /ratings");
        return tourRatingService.lookupAll().stream()
                .map(t -> new RatingDto(t.getScore(), t.getComment(), t.getCustomerId()))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public RatingDto getRating(@PathVariable("id") Integer id) {
        LOGGER.info("GET /ratings/{id}", id);
        return tourRatingService.lookupRatingById(id)
                .map(t -> new RatingDto(t.getScore(), t.getComment(), t.getCustomerId()))
                .orElseThrow(() -> new NoSuchElementException("Rating " + id + " not found"));

    }


    /**
     * Exception handler if NoSuchElementException is thrown in this Controller
     *
     * @param ex exception
     * @return Error message String.
     */
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoSuchElementException.class)
    public String return400(NoSuchElementException ex) {
        LOGGER.error("Unable to complete transaction", ex);
        return ex.getMessage();
    }
}