package com.sodaware.web.domain;

import com.sodaware.web.model.SodaStyle;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Soda {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(length = 36, columnDefinition = "varchar", nullable = false, updatable = false)
    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID id;

    @Version
    private Long version;

    @CreationTimestamp
    @Column(updatable = false)
    private Timestamp createdDate;

    @UpdateTimestamp
    private Timestamp lastModifiedDate;

    private String sodaName;

    @Enumerated(EnumType.STRING)
    private SodaStyle sodaStyle;

    @Column(unique = true)
    private Long upc;

    private BigDecimal price;
    private Integer minOnHand;
    private Integer quantityToWare;


}
