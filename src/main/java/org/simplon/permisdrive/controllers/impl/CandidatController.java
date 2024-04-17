package org.simplon.permisdrive.controllers.impl;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.util.List;

import org.simplon.permisdrive.controllers.ICandidatController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/candidat")
public class CandidatController implements ICandidatController {

//    @Override
//    @GetMapping
//    public ResponseEntity<List<CandidatDto>> getAllCandidats() {
//        return null;
//    }
//
//    @Override
//    @GetMapping("/{candidatId}")
//    public ResponseEntity<CandidatDto> getCandidatById(
//            @PathVariable(name = "candidatId") Long candidatId) throws ChangeSetPersister.NotFoundException {
//        return null;
//    }
//
//    @Override
//    @PostMapping
//    public ResponseEntity<CandidatDto> createCandidat(
//            @Valid @RequestBody CandidatDto candidatDto) {
//        return null;
//    }
//
//    @Override
//    @PutMapping("/{candidatId}")
//    public ResponseEntity<CandidatDto> updateCandidat(
//            @PathVariable(name = "candidatId") Long candidatId,
//            @Valid @RequestBody CandidatDto candidatDto) throws ChangeSetPersister.NotFoundException {
//        return null;
//    }
//
//    @Override
//    @DeleteMapping("/{candidatId}")
//    public ResponseEntity<String> deleteCandidat(
//            @PathVariable(name = "candidatId") Long candidatId) throws ChangeSetPersister.NotFoundException {
//        return null;
//    }
}


