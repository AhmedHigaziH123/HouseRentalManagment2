package Classes;

public class House {
    private String Location,Rooms,Phone,Price,HouseId,OwnerId;
    private boolean IsRented;

    public House(String location, String rooms, String phone, String price,String houseId,String ownerId) {
        Location = location;
        Rooms = rooms;
        Phone = phone;
        Price = price;
        HouseId=houseId;
        OwnerId=ownerId;
        IsRented=false;
    }
    public House(){}

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public String getRooms() {
        return Rooms;
    }

    public void setRooms(String rooms) {
        Rooms = rooms;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }
    public boolean isRented() {
        return IsRented;
    }
    public void setRented(boolean rented) {
        IsRented = rented;

    }

    public String getHouseId() {
        return HouseId;
    }

    public void setHouseId(String houseId) {
        HouseId = houseId;
    }

    public String getOwnerId() {
        return OwnerId;
    }

    public void setOwnerId(String ownerId) {
        OwnerId = ownerId;
    }
}
