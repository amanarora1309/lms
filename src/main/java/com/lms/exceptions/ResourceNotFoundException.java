package com.lms.exceptions;

public class ResourceNotFoundException extends RuntimeException{
    private String resourceName;
    private String feildName;
    private Long feildValue;

    public ResourceNotFoundException(String resourceName, String feildName, Long feildValue) {
        super(String.format("%s not found with %s: %d0", resourceName, feildName, feildValue));
        this.resourceName = resourceName;
        this.feildName = feildName;
        this.feildValue = feildValue;
    }
}
