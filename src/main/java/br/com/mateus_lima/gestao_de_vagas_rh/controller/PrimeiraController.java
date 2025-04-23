package br.com.mateus_lima.gestao_de_vagas_rh.controller;

import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController //utilizamos a annotation @RestController para indicar que a classe é um Controller e que estamos construindo uma API REST.
@RequestMapping("/primeiraController") //utilizamos a annotation @RequestMapping para definir o recurso da rota
public class PrimeiraController {

    @GetMapping("/primeiroMetodo/{id}")
    public String primeiroMetodo(@PathVariable String id){
        return "O paramentro é " + id;
    }

    @GetMapping("/metodoComQueryParams")

    public  String metodoComQuaryParamas(@RequestParam String id){
        return "O paramétro com metodoComQueryParams é " + id;
    }
    @GetMapping("/metodoComQueryParams2")
    public  String metodoComQuaryParamas2(@RequestParam Map<String,String> allParams){
        return "O paramétro com metodoComQueryParams 2 é " + allParams.entrySet();
    }

    @PostMapping("metodoComBodyParams")
    public String metodoComBodyParams(@RequestBody Usuario usuario){
        return  "metodoComBodyParams " + usuario.username();
    }

    record Usuario(String username){}
}
