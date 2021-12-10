package com.digitalinnovationone.heroesapi.controller;

import com.digitalinnovationone.heroesapi.document.Heroes;
import com.digitalinnovationone.heroesapi.repository.HeroesRepository;
import com.digitalinnovationone.heroesapi.service.HeroesService;
import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Publisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

import static com.digitalinnovationone.heroesapi.constans.HeroesConstant.HEROES_ENDPOINT_LOCAL;

@RestController
@Slf4j
public class HeroesController {

    HeroesService heroesService;
    HeroesRepository heroesRepository;

    private static final org.slf4j.Logger log =
            org.slf4j.LoggerFactory.getLogger(HeroesController.class);


    public HeroesController (HeroesService heroesService, HeroesRepository heroesRepository){
        this.heroesService=heroesService;
        this.heroesRepository=heroesRepository;
    }

    @GetMapping(HEROES_ENDPOINT_LOCAL)
    @ResponseStatus(HttpStatus.OK)
    public Flux<Heroes> getAllItems() {
        log.info("requesting the list off all heroes");
        return heroesService.findAll();

    }

    @GetMapping(HEROES_ENDPOINT_LOCAL+"/{id}")
     public Mono <ResponseEntity<Heroes>> FindByIdHero(@PathVariable String id){
        log.info("request the hero with id {}",id);
        return  heroesService.findByIdHero(id)
                .map((item)-> new ResponseEntity<>(item,HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));

    }
    @PostMapping(HEROES_ENDPOINT_LOCAL)
    @ResponseStatus(code = HttpStatus.CREATED)
    public Mono<Heroes> createHero(@RequestBody Heroes heroes) {
        log.info("A new Hero was Created");
        return heroesService.save(heroes);
    }


    @DeleteMapping(HEROES_ENDPOINT_LOCAL + "/{id}")
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    public Mono <HttpStatus> deleteByIdHero(@PathVariable String id){
        heroesService.deleteByIdHero(id);
        log.info("deleting a hero with id {}", id);
        return Mono.just(HttpStatus.NOT_FOUND);
    }

}
