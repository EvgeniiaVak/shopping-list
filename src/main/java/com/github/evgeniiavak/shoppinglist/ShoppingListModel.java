package com.github.evgeniiavak.shoppinglist;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
public class ShoppingListModel {
    private List<String> unchecked;
    private Set<String> checked;
}
