package net.springboot.pyszkomat_backend.dto.frontend;

import net.springboot.pyszkomat_backend.enumeration.MenuItemCategory;
import net.springboot.pyszkomat_backend.model.MenuItem;

public class MenuItemFrontendDto {

    public Long id;
    public String name;
    public String description;
    public MenuItemCategory category;
    public float price;
    public String photoUrl;

    public MenuItemFrontendDto() {
    }

    public MenuItemFrontendDto(MenuItem menuItem) {
        id = menuItem.id;
        name = menuItem.name;
        description = menuItem.description;
        category = menuItem.category;
        price = menuItem.price;
        photoUrl = menuItem.photoUrl;
    }
}
