package ru.vsu.aviatickets.api.entities.dto.request;

public class UserRequestDTO {
    private String code;

    public UserRequestDTO(String code) {
        this.code = code;
    }

    public UserRequestDTO() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
