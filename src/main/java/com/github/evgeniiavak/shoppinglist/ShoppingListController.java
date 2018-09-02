package com.github.evgeniiavak.shoppinglist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("shopping.list")
public class ShoppingListController {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @GetMapping
    public ResponseEntity<ShoppingListModel> get() {
        List<String> unchecked = redisTemplate.boundListOps("shopping-list:unchecked").range(0, -1);
        List<String> checked = redisTemplate.boundListOps("shopping-list:checked").range(0, -1);
        return ResponseEntity.ok(new ShoppingListModel(unchecked, checked));
    }
}
