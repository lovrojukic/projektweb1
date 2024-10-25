package projekt.jedan.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = "tickets")
public class Ticket {

    @Id
    private String id;
    private String vatin;
    private String firstName;
    private String lastName;
    @Column(name = "created_at")
    private LocalDateTime createdAt;


}
