package application.adapters.out.persistence.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "buyers")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BuyerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String buyerId;
    private String userEmail; 
    private String primaryAddressDetails;
}