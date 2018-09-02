package com.github.evgeniiavak.shoppinglist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundListOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;

@RestController
@RequestMapping("shopping.list")
public class ShoppingListController {

    @Autowired
    private StringRedisTemplate redisTemplate;

    private BoundListOperations<String, String> uncheckedList;
    private BoundListOperations<String, String> checkedList;

    @PostConstruct
    public void init() {
        uncheckedList = redisTemplate.boundListOps("shopping-list:unchecked");
        checkedList = redisTemplate.boundListOps("shopping-list:checked");
    }


    @GetMapping
    public ResponseEntity<ShoppingListModel> get() {
        return getShoppingListEntity();
    }

    @PostMapping("checked")
    public ResponseEntity<ShoppingListModel> postChecked(@RequestParam String item) {
        checkedList.leftPush(item);
        checkedList.trim(0, 30);

        return getShoppingListEntity();
    }

    @PostMapping("unchecked")
    public ResponseEntity<ShoppingListModel> postUnchecked(@RequestParam String item) {
        uncheckedList.leftPush(item);

        return getShoppingListEntity();
    }

    private ResponseEntity<ShoppingListModel> getShoppingListEntity() {
        List<String> unchecked = uncheckedList.range(0, -1);
        List<String> checked = checkedList.range(0, -1);
        return ResponseEntity.ok(new ShoppingListModel(unchecked, checked));
    }
}
