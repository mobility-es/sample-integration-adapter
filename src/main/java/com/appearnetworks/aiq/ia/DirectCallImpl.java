package com.appearnetworks.aiq.ia;

import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

import static com.appearnetworks.aiq.integrationframework.impl.ProtocolConstants.X_AIQ_DEVICE_ID;
import static com.appearnetworks.aiq.integrationframework.impl.ProtocolConstants.X_AIQ_USER_ID;

/**
 * This class contains the implementation of a methods that are to be called using the Direct Call feature.
 *
 * Controller annotation is required to declare a class as a spring Controller.
 *
 * RequestMapping annotation declares the base endpoint URL for direct calls.
 * This means that requests that have the url pattern as specified by the
 * value attribute will be handled by this controller.
 *
 * @link http://docs.spring.io/spring/docs/4.0.4.RELEASE/spring-framework-reference/html/mvc.html#mvc-controller
 */
@Controller
@RequestMapping(value = "/aiq/integration/direct")
public class DirectCallImpl {
    private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(IntegrationAdapterImpl.class);

    @RequestMapping(value = "/test", method = RequestMethod.GET, produces = MediaType.TEXT_PLAIN_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> test(@RequestHeader(X_AIQ_USER_ID) String userId,
                                       @RequestHeader(X_AIQ_DEVICE_ID) String deviceId) {

        LOG.info("Test direct call from user " + userId + " on device " + deviceId);

        return new ResponseEntity<>("Direct Call Response", HttpStatus.OK);
    }
}
