package com.milenyumsoft.springsecurity.dto;

public record AuthResponseDTO(String username, String message, String jwt, boolean status) {
}
