package com.github.evgeniiavak.shoppinglist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("shopping.list")
public class ShoppingListController {

    private final ShoppingListService shoppingListService;

    @Autowired
    public ShoppingListController(ShoppingListService shoppingListService) {
        this.shoppingListService = shoppingListService;
    }

    @GetMapping
    public ResponseEntity<ShoppingListModel> get() {
        return ResponseEntity.ok(shoppingListService.getShoppingList());
    }

    @PostMapping("checked")
    public ResponseEntity<ShoppingListModel> postChecked(@RequestParam String item) {
        return ResponseEntity.ok(shoppingListService.putChecked(item));
    }

    @PostMapping("unchecked")
    public ResponseEntity<ShoppingListModel> postUnchecked(@RequestParam String item) {
        return ResponseEntity.ok(shoppingListService.putUnChecked(item));
    }
}
