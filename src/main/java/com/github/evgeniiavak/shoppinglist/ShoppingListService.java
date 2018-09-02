package com.github.evgeniiavak.shoppinglist;

public interface ShoppingListService {
    String UNCHECKED = "shopping-list:unchecked";
    String CHECKED = "shopping-list:checked";

    ShoppingListModel getShoppingList();
    ShoppingListModel putChecked(String item);
    ShoppingListModel putUnChecked(String item);
}
