package org.delivery.db.user.repository;



import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.delivery.db.user.BaseEntity;
import org.delivery.db.user.enums.UserStatus;

import java.time.LocalDateTime;

@Entity(name ="user")  //value에 있는 entity 값을 찾겠다
@Table
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class  UserEntity  extends BaseEntity {

    @Column(length = 50,nullable = false)
    private  String name;

    @Column(length = 100,nullable = false)
    private String email;


    @Column(length = 50,nullable = false)
    @Enumerated(EnumType.STRING)
    private  UserStatus status;

    @Column(length = 150,nullable = false)
    private String address;


    private LocalDateTime registered_at;

    private  LocalDateTime unregistered_at;

    private  LocalDateTime last_Login_at;

    @Column(length = 100,nullable = false)
    private  String password;

}
