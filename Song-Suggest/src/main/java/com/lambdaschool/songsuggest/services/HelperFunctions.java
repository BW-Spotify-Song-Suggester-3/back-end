package com.lambdaschool.songsuggest.services;

import com.lambdaschool.songsuggest.models.ValidationError;

import java.util.List;


public interface HelperFunctions {

    List<ValidationError> getConstraintViolation(Throwable cause);

}
