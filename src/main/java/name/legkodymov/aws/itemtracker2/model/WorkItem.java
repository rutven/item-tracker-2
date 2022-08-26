package name.legkodymov.aws.itemtracker2.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
public class WorkItem {

    @Id
    @GeneratedValue
    private Long id;

    @NotBlank(message = "User name is required")
    @Column(name = "user_name")
    private String userName;

    private String description;

    @NotBlank(message = "Guide name is required")
    @Column(name = "guide_name")
    private String guideName;

    private String status;

    private Boolean isActive;

    @Column(name = "start_date")
    private LocalDate startDate;

}
