package com.macm.cpdash.controllers;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.macm.cpdash.domain.dto.Sheet;
import com.macm.cpdash.domain.entities.SheetEntity;
import com.macm.cpdash.domain.entities.UserEntity;
import com.macm.cpdash.repositories.SheetRepository;
import com.macm.cpdash.repositories.UserRepository;

@RestController
@RequestMapping("/api/sheets")
public class SheetsController {

    @Autowired
    private SheetRepository sheetRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping(path = "/user/{userId}")
    public ResponseEntity<List<Sheet>> getAllByUser(@PathParam(value = "userId") Long userId) {
        UserEntity user = userRepository.getOne(userId);
        return new ResponseEntity<>(user.getSheets().stream().map(Sheet::new).collect(Collectors.toList()),
                HttpStatus.ACCEPTED);
    }

    @GetMapping(path = "/{sheetId}")
    public ResponseEntity<Sheet> getById(@PathParam(value = "sheetId") Long sheetId) {
        return new ResponseEntity<>(new Sheet(sheetRepository.findOne(sheetId)), HttpStatus.ACCEPTED);
    }

    @PostMapping(path = "/{userId}")
    public ResponseEntity<Sheet> create(@RequestBody Sheet sheet, @PathParam(value = "userId") Long userId) {
        SheetEntity sheetEntity = new SheetEntity();
        sheetEntity.setOwner(userRepository.getOne(userId));
        sheetEntity.setName(sheet.getName());
        sheetEntity.setDescription(sheet.getDescription());
        sheetEntity.setSheetHash(UUID.randomUUID().toString().substring(0, 10));
        sheetEntity = sheetRepository.save(sheetEntity);
        return new ResponseEntity<>(new Sheet(sheetEntity), HttpStatus.ACCEPTED);
    }
}
