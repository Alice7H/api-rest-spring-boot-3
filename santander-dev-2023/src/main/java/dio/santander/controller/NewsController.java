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

import dio.santander.controller.dto.NewsDto;
import dio.santander.service.NewsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@CrossOrigin
@RestController
@RequestMapping("/news")
@Tag(name = "News Controller", description = "RESTful API for managing news.")
public record NewsController(NewsService newsService) {

  @GetMapping("/{id}")
  @Operation(summary = "Get a news by ID", description = "Retrieve a specific news based on its ID")
  public ResponseEntity<NewsDto> findById(@PathVariable Long id) {
    var news = newsService.findById(id);
    return ResponseEntity.ok(new NewsDto(news));
  }

  @GetMapping
  @Operation(summary = "Get all news", description = "Retrieve a list of all registered news")
  @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Operation successful") })
  public ResponseEntity<List<NewsDto>> findAll() {
    var news = newsService.findAll();
    var newsDto = news.stream().map(NewsDto::new).collect(Collectors.toList());
    return ResponseEntity.ok(newsDto);
  }

  @PostMapping
  @Operation(summary = "Create a new news", description = "Create a new news and return the created news' data")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "201", description = "News created successfully"),
      @ApiResponse(responseCode = "422", description = "Invalid news data provided")
  })
  public ResponseEntity<NewsDto> create(@RequestBody NewsDto newsDto) {
    var newsCreated = newsService.create(newsDto.toModel());
    URI location = ServletUriComponentsBuilder.fromCurrentRequest()
        .path("/{id}")
        .buildAndExpand(newsCreated.getId())
        .toUri();
    return ResponseEntity.created(location).body(new NewsDto(newsCreated));
  }

  @PutMapping("/{id}")
  @Operation(summary = "Update a news", description = "Update the data of an existing news based on its ID")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "News updated successfully"),
      @ApiResponse(responseCode = "404", description = "News not found"),
      @ApiResponse(responseCode = "422", description = "Invalid news data provided")
  })
  public ResponseEntity<NewsDto> update(@PathVariable Long id, @RequestBody NewsDto newsDto) {
    var news = newsService.update(id, newsDto.toModel());
    return ResponseEntity.ok(new NewsDto(news));
  }

  @DeleteMapping("/{id}")
  @Operation(summary = "Delete a news", description = "Delete an existing news based on its ID")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "204", description = "News deleted successfully"),
      @ApiResponse(responseCode = "404", description = "News not found")
  })
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    newsService.delete(id);
    return ResponseEntity.noContent().build();
  }
}
