package com.promineotech.jeep.controller;

import com.promineotech.jeep.entity.Order;
import com.promineotech.jeep.entity.OrderRequest;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Validated
@RequestMapping("/orders")
@OpenAPIDefinition(info = @Info(title = "Jeep Order Service"), servers = {
        @Server(url = "http://localhost:8080", description = "Local server.")
})
public interface JeepOrderController {

    // @formatter:off
    @Operation(
          summary = "Create an order for a Jeep",
          description = "Returns the created Jeep",
          responses = {
                  @ApiResponse(
                          responseCode = "200",
                          description = "The created Jeep is returned",
                          content = @Content(
                                  mediaType="application/json",
                                  schema = @Schema(implementation = Order.class))),
                  @ApiResponse(
                          responseCode = "400",
                          description = "The request parameters are invalid",
                          content = @Content(
                                  mediaType="application/json")),
                  @ApiResponse(
                          responseCode = "404",
                          description = "A Jeep component was found with the input criteria",
                          content = @Content(
                                  mediaType="application/json")),
                  @ApiResponse(
                          responseCode = "500",
                          description = "An unplanned error occurred",
                          content = @Content(
                                  mediaType="application/json"))
          },
          parameters = {
                  @Parameter(
                          name = "orderRequest",
                          required = true,
                          description = "The order as JSON")
          }
    )
    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    Order createOrder(@Valid @RequestBody OrderRequest orderRequest);
    // @formatter:on

    // @formatter:off
    @Operation(
            summary = "Get all orders for a Jeep",
            description = "Returns the Jeep orders",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Return all Jeep orders",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Order.class))),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Error.",
                            content = @Content(mediaType = "application/json")),
                    @ApiResponse(
                            responseCode = "404",
                            description = "No orders found",
                            content = @Content(mediaType = "application/json")),
                    @ApiResponse(
                            responseCode = "500",
                            description = "An unplanned error occurred.",
                            content = @Content(mediaType = "application/json"))
            }
    )
    @GetMapping("/all")
    @ResponseStatus(code = HttpStatus.OK)
    List<Order> fetchAllOrders();
    // @formatter:on

}