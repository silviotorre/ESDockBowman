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

import com.example.ec.domain.Role;
import com.example.ec.security.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import java.util.Arrays;

import static org.springframework.http.MediaType.APPLICATION_JSON;

/**
 * Helper class for creating HTTP Headers before invoking an API with TestRestClient.
 */
@Component
public class JwtRequestHelper {

    @Autowired
    private JwtProvider jwtProvider;

    /**
     * Generate the appropriate headers for JWT Authentication.
     *
     * @param roleName role identifier
     * @return Http Headers for Content-Type and Authorization
     */
    public  HttpHeaders withRole(String roleName){
        HttpHeaders headers = new HttpHeaders();
        Role r = new Role();
        r.setRoleName(roleName);
        String token =  jwtProvider.createToken("anonymous", Arrays.asList(r));
        headers.setContentType(APPLICATION_JSON);
        headers.add("Authorization", "Bearer " + token);
        return headers;
    }
}
//2021
