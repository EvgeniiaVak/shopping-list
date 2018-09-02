package com.github.evgeniiavak.shoppinglist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
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
        return "redirect:/shopping-list";
    }

    @PostMapping("unchecked")
    public String postUnchecked(@RequestParam String item) {
        shoppingListService.putUnChecked(item);
        return "redirect:/shopping-list";
    }
}
