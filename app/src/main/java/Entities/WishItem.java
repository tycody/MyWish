package Entities;

/**
 * Created by kris on 23/01/2015.
 */
public class WishItem {
    private int id;
    private int wishlistId;
    private String item_name;
    private String imageUrl;

    public WishItem(int id, int wishlistId, String item_name, String imageUrl) {
        this.id = id;
        this.wishlistId = wishlistId;
        this.item_name = item_name;
        this.imageUrl = imageUrl;
    }

    public WishItem(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getWishlistId() {
        return wishlistId;
    }

    public void setWishlistId(int wishlistId) {
        this.wishlistId = wishlistId;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
