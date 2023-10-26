package dio.santander.controller;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import dio.santander.controller.dto.CardDto;
import dio.santander.service.CardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@CrossOrigin
@RestController
@RequestMapping("/cards")
@Tag(name = "Cards Controller", description = "RESTful API for managing cards.")
public record CardController(CardService cardService) {

  @GetMapping("/{id}")
  @Operation(summary = "Get a card by ID", description = "Retrieve a specific card based on its ID")
  public ResponseEntity<CardDto> findById(@PathVariable Long id) {
    var card = cardService.findById(id);
    return ResponseEntity.ok(new CardDto(card));
  }

  @GetMapping
  @Operation(summary = "Get all cards", description = "Retrieve a list of all registered cards")
  @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Operation successful") })
  public ResponseEntity<List<CardDto>> findAll() {
    var cards = cardService.findAll();
    var cardsDto = cards.stream().map(CardDto::new).collect(Collectors.toList());
    return ResponseEntity.ok(cardsDto);
  }

  @PostMapping
  @Operation(summary = "Create a new card", description = "Create a new card and return the created card's data")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "201", description = "Card created successfully"),
      @ApiResponse(responseCode = "422", description = "Invalid card data provided")
  })
  public ResponseEntity<CardDto> create(@RequestBody CardDto cardDto) {
    var cardCreated = cardService.create(cardDto.toModel());
    URI location = ServletUriComponentsBuilder.fromCurrentRequest()
        .path("/{id}")
        .buildAndExpand(cardCreated.getId())
        .toUri();
    return ResponseEntity.created(location).body(new CardDto(cardCreated));
  }

  @PutMapping("/{id}")
  @Operation(summary = "Update a card", description = "Update the data of an existing card based on its ID")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Card updated successfully"),
      @ApiResponse(responseCode = "404", description = "Card not found"),
      @ApiResponse(responseCode = "422", description = "Invalid card data provided")
  })
  public ResponseEntity<CardDto> update(@PathVariable Long id, @RequestBody CardDto cardDto) {
    var card = cardService.update(id, cardDto.toModel());
    return ResponseEntity.ok(new CardDto(card));
  }

  @DeleteMapping("/{id}")
  @Operation(summary = "Delete a card", description = "Delete an existing card based on its ID")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "204", description = "Card deleted successfully"),
      @ApiResponse(responseCode = "404", description = "Card not found")
  })
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    cardService.delete(id);
    return ResponseEntity.noContent().build();
  }
}
