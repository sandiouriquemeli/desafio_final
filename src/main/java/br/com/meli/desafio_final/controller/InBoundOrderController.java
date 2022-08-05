package br.com.meli.desafio_final.controller;

import br.com.meli.desafio_final.model.entity.InBoundOrder;
import br.com.meli.desafio_final.service.IInBoundOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/fresh-products/inboundorder/")
public class InBoundOrderController {


        @Autowired
        private IInBoundOrderService service;
        @GetMapping
        public ResponseEntity<List<InBoundOrder>>getAll() {
            return ResponseEntity.ok(service.getAll());
        }

       // @PostMapping
        //public ResponseEntity<Author> novoAuthor(@RequestBody Author author) {
        //    return ResponseEntity.ok(service.save(author));
       // }

    }

