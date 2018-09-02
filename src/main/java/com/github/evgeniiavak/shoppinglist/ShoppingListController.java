package com.github.evgeniiavak.shoppinglist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("shopping-list")
public class ShoppingListController {

    private final ShoppingListService shoppingListService;

    @Autowired
    public ShoppingListController(ShoppingListService shoppingListService) {
        this.shoppingListService = shoppingListService;
    }

    @GetMapping
    public String get (Model model) {
        model.addAttribute("shoppingListModel", shoppingListService.getShoppingList());
        return "template";
    }

    @PostMapping("checked")
    public String postChecked(@RequestParam String item) {
        shoppingListService.putChecked(item);
        return "template";
    }

    @PostMapping("unchecked")
    public String postUnchecked(@RequestParam String item) {
        shoppingListService.putUnChecked(item);
        return "template";
    }
}
