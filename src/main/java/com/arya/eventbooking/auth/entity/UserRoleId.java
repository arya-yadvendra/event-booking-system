package com.arya.eventbooking.auth.entity;

import java.io.Serializable;
import jakarta.persistence.Embeddable;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class UserRoleId implements Serializable {
    private Long userId;
    private Long roleId;
}
