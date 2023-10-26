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

import dio.santander.controller.dto.FeatureDto;
import dio.santander.service.FeatureService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@CrossOrigin
@RestController
@RequestMapping("/features")
@Tag(name = "Features Controller", description = "RESTful API for managing features.")
public record FeatureController(FeatureService featureService) {

  @GetMapping("/{id}")
  @Operation(summary = "Get a feature by ID", description = "Retrieve a specific feature based on its ID")
  public ResponseEntity<FeatureDto> findById(@PathVariable Long id) {
    var feature = featureService.findById(id);
    return ResponseEntity.ok(new FeatureDto(feature));
  }

  @GetMapping
  @Operation(summary = "Get all features", description = "Retrieve a list of all registered features")
  @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Operation successful") })
  public ResponseEntity<List<FeatureDto>> findAll() {
    var features = featureService.findAll();
    var featuresDto = features.stream().map(FeatureDto::new).collect(Collectors.toList());
    return ResponseEntity.ok(featuresDto);
  }

  @PostMapping
  @Operation(summary = "Create a new feature", description = "Create a new feature and return the created feature's data")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "201", description = "Feature created successfully"),
      @ApiResponse(responseCode = "422", description = "Invalid feature data provided")
  })
  public ResponseEntity<FeatureDto> create(@RequestBody FeatureDto featureDto) {
    var featureCreated = featureService.create(featureDto.toModel());
    URI location = ServletUriComponentsBuilder.fromCurrentRequest()
        .path("/{id}")
        .buildAndExpand(featureCreated.getId())
        .toUri();
    return ResponseEntity.created(location).body(new FeatureDto(featureCreated));
  }

  @PutMapping("/{id}")
  @Operation(summary = "Update a feature", description = "Update the data of an existing feature based on its ID")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Feature updated successfully"),
      @ApiResponse(responseCode = "404", description = "Feature not found"),
      @ApiResponse(responseCode = "422", description = "Invalid feature data provided")
  })
  public ResponseEntity<FeatureDto> update(@PathVariable Long id, @RequestBody FeatureDto featureDto) {
    var feature = featureService.update(id, featureDto.toModel());
    return ResponseEntity.ok(new FeatureDto(feature));
  }

  @DeleteMapping("/{id}")
  @Operation(summary = "Delete a feature", description = "Delete an existing feature based on its ID")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "204", description = "Feature deleted successfully"),
      @ApiResponse(responseCode = "404", description = "Feature not found")
  })
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    featureService.delete(id);
    return ResponseEntity.noContent().build();
  }
}
