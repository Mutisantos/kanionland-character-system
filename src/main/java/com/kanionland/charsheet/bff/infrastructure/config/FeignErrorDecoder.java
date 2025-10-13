package com.kanionland.charsheet.bff.infrastructure.config;

import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class FeignErrorDecoder implements ErrorDecoder {

  @Override
  public Exception decode(String methodKey, Response response) {
    switch (response.status()) {
      case 400:
        return new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad Request");
      case 404:
        return new ResponseStatusException(HttpStatus.NOT_FOUND, "Resource not found");
      case 500:
        return new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
            "Internal server error");
      default:
        return new Exception("Error while processing request");
    }
  }
}