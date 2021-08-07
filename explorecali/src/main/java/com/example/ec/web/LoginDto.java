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

import javax.validation.constraints.NotNull;

/**
 * Created By Mary Ellen Bowman
 */
public class LoginDto {
    @NotNull
    private String username;

    @NotNull
    private String password;

    private String firstName;

    private String lastName;

    /**
     * Default constructor
     */
    protected LoginDto() {
    }

    /**
     * Partial constructor
     * @param username
     * @param password
     */
    public LoginDto(String username, String password) {
        this.username = username;
        this.password = password;
    }
    /**
     * Full constructor
     * @param username
     * @param password
     */
    public LoginDto(String username, String password, String firstName, String lastName) {
       this(username, password);
       this.firstName = firstName;
       this.lastName = lastName;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}
