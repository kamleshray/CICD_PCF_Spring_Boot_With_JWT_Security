package com.ps.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ps.model.Product;
import com.ps.service.ProductService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/product")
@CrossOrigin
@Api(tags = "2- Normal Endpoints", description = "Product Management")
public class ProductController {

	@Autowired
	private ProductService service;

	@GetMapping(value = "/getAll", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation("Get All Product List")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Product List retrived Successfully") })
	public ResponseEntity<List<Product>> getAllProduct() {
		return ResponseEntity.ok(service.getAllProduct());
	}

	@GetMapping("/get/{productId}")
	@ApiOperation("get a Product")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "get a Product"),
			@ApiResponse(code = 400, message = "Product Id Not Found") })
	public ResponseEntity<?> getProduct(@PathVariable String productId) {
		try {
			return ResponseEntity.ok(service.getProductBuId(productId));
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

}
