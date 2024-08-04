package pl.dominik.atipera.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Owner {
    @JsonProperty("login")
    private String ownerLogin;

    public String getOwnerLogin() {
        return ownerLogin;
    }

    public void setOwnerLogin(String ownerLogin) {
        this.ownerLogin = ownerLogin;
    }
}
