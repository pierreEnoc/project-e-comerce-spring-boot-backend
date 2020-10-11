package com.pierredev.projectjavaspringboot.resource;

import com.pierredev.projectjavaspringboot.dto.ProductDTO;
import com.pierredev.projectjavaspringboot.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/products")
public class ProductResource {
    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<Page<ProductDTO>> findAll(
          @RequestParam(value = "page",defaultValue ="0") Integer page,
          @RequestParam(value = "linesPerPage", defaultValue = "12") Integer linesPerPage,
          @RequestParam(value = "orderBy", defaultValue = "product") String oderBy,
          @RequestParam(value = "direction", defaultValue = "ASC") String direction
    ){
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), oderBy);
        Page<ProductDTO> list = productService.findAllPaged(pageRequest);
        return  ResponseEntity.ok().body(list);
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<ProductDTO> findbyId(@PathVariable Long id) {
        ProductDTO dto = productService.findbyId(id);
        return ResponseEntity.ok().body(dto);
    }
}
