/**
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech) (4.3.1).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */
package de.senacor.innolab.graalvm.customer.controller.openapiMock.api;

import de.senacor.innolab.graalvm.customer.controller.openapiMock.model.CustomerDto;
import io.swagger.annotations.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.NativeWebRequest;

import java.util.List;
import java.util.Optional;

@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2020-11-20T10:07:32.582612+01:00[Europe/Berlin]")

@Validated
@Api(value = "customer", description = "the customer API")
public interface CustomerApi {

    default Optional<NativeWebRequest> getRequest() {
        return Optional.empty();
    }

    /**
     * POST /customer : Creates a new customer
     *
     * @param customer  (required)
     * @return Created (status code 201)
     */
    @ApiOperation(value = "Creates a new customer", nickname = "createCustomer", notes = "", response = CustomerDto.class, tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 201, message = "Created", response = CustomerDto.class) })
    @RequestMapping(value = "/customer",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.POST)
    default ResponseEntity<CustomerDto> createCustomer(@ApiParam(value = "", required = true) @RequestBody CustomerDto customer) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"firstName\" : \"firstName\", \"lastName\" : \"lastName\", \"birthdate\" : \"2000-01-23T04:56:07.000+00:00\", \"id\" : 0 }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * DELETE /customer/{customerId} : Delete a customer
     *
     * @param customerId  (required)
     * @return OK (status code 204)
     */
    @ApiOperation(value = "Delete a customer", nickname = "deleteCustomer", notes = "", tags={  })
    @ApiResponses(value = {
        @ApiResponse(code = 204, message = "OK") })
    @RequestMapping(value = "/customer/{customerId}",
        method = RequestMethod.DELETE)
    default ResponseEntity<Void> deleteCustomer(@ApiParam(value = "", required = true) @PathVariable("customerId") Long customerId) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * GET /customer : Returns a list of customer.
     *
     * @return OK (status code 200)
     */
    @ApiOperation(value = "Returns a list of customer.", nickname = "getAllCustomer", notes = "", response = CustomerDto.class, responseContainer = "List", tags={  })
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK", response = CustomerDto.class, responseContainer = "List") })
    @RequestMapping(value = "/customer",
        produces = { "application/json" },
        method = RequestMethod.GET)
    default ResponseEntity<List<CustomerDto>> getAllCustomer() {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"firstName\" : \"firstName\", \"lastName\" : \"lastName\", \"birthdate\" : \"2000-01-23T04:56:07.000+00:00\", \"id\" : 0 }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * GET /customer/{customerId} : Returns a customer
     *
     * @param customerId  (required)
     * @return OK (status code 200)
     */
    @ApiOperation(value = "Returns a customer", nickname = "getCustomer", notes = "", response = CustomerDto.class, tags={  })
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK", response = CustomerDto.class) })
    @RequestMapping(value = "/customer/{customerId}",
        produces = { "application/json" },
        method = RequestMethod.GET)
    default ResponseEntity<CustomerDto> getCustomer(@ApiParam(value = "", required = true) @PathVariable("customerId") Long customerId) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"firstName\" : \"firstName\", \"lastName\" : \"lastName\", \"birthdate\" : \"2000-01-23T04:56:07.000+00:00\", \"id\" : 0 }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * PUT /customer/{customerId} : Updates a customer
     *
     * @param customerId  (required)
     * @param customer  (required)
     * @return OK (status code 200)
     */
    @ApiOperation(value = "Updates a customer", nickname = "updateCustomer", notes = "", response = CustomerDto.class, tags={  })
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK", response = CustomerDto.class) })
    @RequestMapping(value = "/customer/{customerId}",
        produces = { "application/json" },
        consumes = { "application/json" },
        method = RequestMethod.PUT)
    default ResponseEntity<CustomerDto> updateCustomer(@ApiParam(value = "", required = true) @PathVariable("customerId") Long customerId, @ApiParam(value = "", required = true) @RequestBody CustomerDto customer) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"firstName\" : \"firstName\", \"lastName\" : \"lastName\", \"birthdate\" : \"2000-01-23T04:56:07.000+00:00\", \"id\" : 0 }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

}