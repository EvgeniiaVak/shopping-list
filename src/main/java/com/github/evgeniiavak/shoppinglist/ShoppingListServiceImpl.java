package com.github.evgeniiavak.shoppinglist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundListOperations;
import org.springframework.data.redis.core.BoundZSetOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ShoppingListServiceImpl implements ShoppingListService {
    private final BoundListOperations<String, String> unchecked;
    private final BoundZSetOperations<String, String> checked;

    @Autowired
    public ShoppingListServiceImpl(StringRedisTemplate redisTemplate) {
        unchecked = redisTemplate.boundListOps(UNCHECKED);
        checked = redisTemplate.boundZSetOps(CHECKED);
    }

    @Override
    public ShoppingListModel getShoppingList() {
        List<String> unchecked = this.unchecked.range(0, -1);
        Set<String> checked = this.checked.reverseRange(0, -1);
        return new ShoppingListModel(unchecked, checked);
    }

    @Override
    public ShoppingListModel putUnChecked(String item) {
        unchecked.leftPush(item);
        checked.remove(item);

        return getShoppingList();
    }

    @Override
    public ShoppingListModel putChecked(String item) {
        checked.add(item, System.currentTimeMillis());
        // TODO: 9/4/18 remove the exact item
        unchecked.remove(1, item);

        int size = Math.toIntExact(Optional.ofNullable(checked.zCard()).orElse(0L));

        if (size > 20) {
            checked.removeRange(0, size - 20);
        }

        return getShoppingList();
    }
}
