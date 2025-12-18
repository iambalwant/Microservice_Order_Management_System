package com.inventoryservice.model.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InventoryEntity {

    @Id
    @SequenceGenerator(
            name = "inventory_id_sequence",
            sequenceName = "inventory_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "inventory_id_sequence"
    )
    private Integer inventoryId;
    private Integer productId;
    @PositiveOrZero(message = "quantity must be positive")
    private Integer quantityAvailable;
    private LocalDateTime updatedAt;

}
