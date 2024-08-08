package org.delivery.db;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@MappedSuperclass  //딱히 매핑을 하지않음
@Data
@SuperBuilder
public class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;
    protected BaseEntity() {
    }

}
