package com.mvassoler.runner.runner.prova;

import com.mvassoler.runner.runner.prova.Prova;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

import static com.mvassoler.runner.runner.core.grant.RolesGrant.CREATE_PROVA;
import static com.mvassoler.runner.runner.core.grant.RolesGrant.DELETE_PROVA;
import static com.mvassoler.runner.runner.core.grant.RolesGrant.SUPER_USER;
import static com.mvassoler.runner.runner.core.grant.RolesGrant.UPDATE_PROVA;

@RestController
@RequestMapping("/prova")
@Tag(name = "Provas")
public class ProvaResource {

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("(" + CREATE_PROVA + ") or (" + SUPER_USER + ")")
    @Operation(
            summary = "Create street race test", description = "Enter payload data correctly to create a new record",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Create record success")
            }
    )
    public ResponseEntity<String> insertProva(@RequestBody Prova prova) {
        prova.setId(UUID.randomUUID());
        return ResponseEntity.ok("Prova de ID " + prova.getId() + " criada");
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("(" + UPDATE_PROVA + ") or (" + SUPER_USER + ")")
    @Operation(
            summary = "Update street race test", description = "Enter payload data correctly to update a record",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Update record success")
            }
    )
    public ResponseEntity<String> updateProva(@PathVariable UUID id, @RequestBody Prova prova) {
        prova.setId(id);
        return ResponseEntity.ok("Prova de ID " + prova.getId() + " atualizada");
    }


    @DeleteMapping(value = "/{id}")
    @PreAuthorize("(" + DELETE_PROVA + ") or (" + SUPER_USER + ")")
    @Operation(
            summary = "Delete street race test", description = "Enter a valid identifier to delete the record",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Delete record success")
            }
    )
    public ResponseEntity<String> deleteProva(@PathVariable UUID id) {
        return ResponseEntity.ok("Prova de ID " + id + " excluída");
    }

}
