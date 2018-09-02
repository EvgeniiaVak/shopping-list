package com.github.evgeniiavak.shoppinglist;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ShoppingListModel {
    private List<String> unchecked;
    private List<String> checked;
}
