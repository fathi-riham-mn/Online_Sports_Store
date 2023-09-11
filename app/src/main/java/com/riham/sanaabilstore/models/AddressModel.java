package com.riham.sanaabilstore.models;

public class AddressModel {
    String userAddress;
    boolean isSelected;

    public AddressModel() {
    }

    public AddressModel(String userAddress, boolean isSelected) {
        this.userAddress = userAddress;
        this.isSelected = isSelected;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
