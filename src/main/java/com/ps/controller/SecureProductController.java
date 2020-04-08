package com.ps.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ps.model.Product;
import com.ps.model.ProductVo;
import com.ps.model.Response;
import com.ps.service.ProductService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/product/secure")
@CrossOrigin
@Api(tags = "3- Secured Endpoints", description = "Secure Product Management")
public class SecureProductController {

	@Autowired
	private ProductService service;

	@PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
	@PostMapping("/add")
	@ApiOperation("Add a Product")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Product added successfully") })
	public ResponseEntity<Product> addProduct(@RequestHeader("Authorization") String authorizationToken,
			@RequestBody ProductVo productData) {
		Product product = new ObjectMapper().convertValue(productData, Product.class);
		Product addProduct = service.addProduct(product);
		return ResponseEntity.ok(addProduct);
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@PutMapping("/update/{productId}")
	@ApiOperation("Update a Product")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Product Updated successfully"),
			@ApiResponse(code = 400, message = "Product Id Not Found") })
	public ResponseEntity<?> updateProduct(@RequestHeader("Authorization") String authorizationToken,
			@PathVariable String productId, @RequestBody ProductVo productData) {

		Product product = new ObjectMapper().convertValue(productData, Product.class);
		product.setProductId(productId);
		try {
			return ResponseEntity.ok(service.updateProduct(product.getProductId(), product));
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@DeleteMapping("/delete/{productId}")
	@ApiOperation("Delete a Product")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Product deleted successfully"),
			@ApiResponse(code = 400, message = "Product Id Not Found") })
	public ResponseEntity<?> deleteProduct(@RequestHeader("Authorization") String authorizationToken,
			@PathVariable String productId) {

		try {
			service.removeProduct(productId);
			Response resp = new Response();
			resp.setStatus("Product deleted successfully");
			return ResponseEntity.ok(resp);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}


}
