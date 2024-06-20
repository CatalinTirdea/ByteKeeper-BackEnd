package com.bytekeeper.backend.controller;

import com.bytekeeper.backend.model.Contact;
import com.bytekeeper.backend.model.DTO.InventoryDTO;
import com.bytekeeper.backend.model.DTO.UserDTO;
import com.bytekeeper.backend.model.User;
import com.bytekeeper.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RequestMapping(value="/contact")
@RestController
public class ContactController {
    private static Contact contact;

    public static Contact getContact() {
        return contact;
    }
    public static void setContact(Contact contact) {
        ContactController.contact = contact;
    }

    @PostMapping(value="/send", consumes="application/json")
    public ResponseEntity<?> receive(@RequestBody Contact contact) {
        setContact(contact);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value="/message", produces="application/json")
    public ResponseEntity<Contact> message() {
        return ResponseEntity.ok(getContact());
    }
}
