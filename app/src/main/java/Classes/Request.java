package Classes;

public class Request {
    private String HouseId,OwnerId,RenterId;
    private boolean IsAccepted;
    public Request(String houseId, String ownerId, String renterId) {
        HouseId = houseId;
        OwnerId = ownerId;
        RenterId = renterId;
        IsAccepted = false;
    }
    public Request(){}

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

    public String getRenterId() {
        return RenterId;
    }

    public void setRenterId(String renterId) {
        RenterId = renterId;
    }

    public boolean isAccepted() {
        return IsAccepted;
    }

    public void setAccepted(boolean accepted) {
        IsAccepted = accepted;
    }
}
