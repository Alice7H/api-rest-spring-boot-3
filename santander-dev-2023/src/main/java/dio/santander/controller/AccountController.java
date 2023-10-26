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

import dio.santander.controller.dto.AccountDto;
import dio.santander.service.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@CrossOrigin
@RestController
@RequestMapping("/accounts")
@Tag(name = "Account Controller", description = "RESTful API for managingaccount.")
public record AccountController(AccountService accountService) {

  @GetMapping("/{id}")
  @Operation(summary = "Get a account by ID", description = "Retrieve aspecific account based on its ID")
  public ResponseEntity<AccountDto> findById(@PathVariable Long id) {
    var account = accountService.findById(id);
    return ResponseEntity.ok(new AccountDto(account));
  }

  @GetMapping
  @Operation(summary = "Get all accounts", description = "Retrieve a list ofall registered accounts")

  @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Operation successful") })
  public ResponseEntity<List<AccountDto>> findAll() {
    var accounts = accountService.findAll();
    var accountsDto = accounts.stream().map(AccountDto::new).collect(Collectors.toList());
    return ResponseEntity.ok(accountsDto);
  }

  @PostMapping
  @Operation(summary = "Create a new account", description = "Create a newaccount and return the created account'sdata")
  @ApiResponses(value = { @ApiResponse(responseCode = "201", description = "Account createdsuccessfully"),
      @ApiResponse(responseCode = "422", description = "Invalid account dataprovided")
  })

  public ResponseEntity<AccountDto> create(@RequestBody AccountDto accountDto) {
    var accountCreated = accountService.create(accountDto.toModel());
    URI location = ServletUriComponentsBuilder.fromCurrentRequest()
        .path("/{id}")
        .buildAndExpand(accountCreated.getId())
        .toUri();
    return ResponseEntity.created(location).body(new AccountDto(accountCreated));
  }

  @PutMapping("/{id}")
  @Operation(summary = "Update a account", description = "Update the data of anexisting account based on its ID")
  @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Account updatedsuccessfully"),
      @ApiResponse(responseCode = "404", description = "Account not found"),
      @ApiResponse(responseCode = "422", description = "Invalid account dataprovided")
  })

  public ResponseEntity<AccountDto> update(@PathVariable Long id, @RequestBody AccountDto accountDto) {
    var acount = accountService.update(id, accountDto.toModel());
    return ResponseEntity.ok(new AccountDto(acount));
  }

  @DeleteMapping("/{id}")
  @Operation(summary = "Delete a account", description = "Delete an existingaccount based on its ID")
  @ApiResponses(value = { @ApiResponse(responseCode = "204", description = "Account deletedsuccessfully"),
      @ApiResponse(responseCode = "404", description = "Account not found")
  })

  public ResponseEntity<Void> delete(@PathVariable Long id) {
    accountService.delete(id);
    return ResponseEntity.noContent().build();
  }
}
