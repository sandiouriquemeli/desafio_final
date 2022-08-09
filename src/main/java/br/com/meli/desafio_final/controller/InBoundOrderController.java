package br.com.meli.desafio_final.controller;

import br.com.meli.desafio_final.dto.InBoundOrderDto;
import br.com.meli.desafio_final.model.entity.InBoundOrder;
import br.com.meli.desafio_final.service.IInBoundOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/fresh-products/inboundorder")
public class InBoundOrderController {


        @Autowired
        private IInBoundOrderService service;

        @PostMapping("/{agentId}")
        public ResponseEntity<List<InBoundOrderDto>> createInBoundOrder(@PathVariable long agentId,@RequestBody InBoundOrder inBoundOrder) {
            return ResponseEntity.ok(service.create(inBoundOrder, agentId));
        }


        @PutMapping("/{agentId}")
        public ResponseEntity<List<InBoundOrderDto>> updateInBoundOrder(@PathVariable long agentId,@RequestBody InBoundOrder inBoundOrder) {
            return ResponseEntity.ok(service.update(inBoundOrder, agentId));
        }

    }



