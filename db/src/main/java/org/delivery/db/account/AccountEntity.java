package org.delivery.db.account;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.delivery.db.BaseEntity;
import org.springframework.context.annotation.Primary;

@SuperBuilder  //account의 id가 뜨지않음
@Data

@Entity
@EqualsAndHashCode(callSuper = true)
@Table(name = "account") //연동
public class AccountEntity extends BaseEntity {
    protected AccountEntity() {
    }

    // 필요한 다른 생성자

 /*   @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;
*/
}
