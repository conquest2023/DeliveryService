package org.delivery.api.domain.user.controller.model;


import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.delivery.db.user.enums.UserStatus;


import java.time.LocalDateTime;

@MappedSuperclass
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponse {

    private  Long id;

    private  String name;


    private String email;


    private UserStatus status;


    private String address;


    private LocalDateTime registered_at;

    private  LocalDateTime unregistered_at;

    private  LocalDateTime last_Login_at;
}
