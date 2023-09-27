package com.aacademy.moonlight.entity.car;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Entity
@Table(name = "file_resources")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FileResourcesForCar {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "image_name",nullable = false)
    @NotEmpty
    private String imageName;

    @Lob
    @Column(name = "photo", columnDefinition="LONGBLOB")
    private byte[] photo;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "car_id")
    @JsonBackReference
    private Car car;

}
