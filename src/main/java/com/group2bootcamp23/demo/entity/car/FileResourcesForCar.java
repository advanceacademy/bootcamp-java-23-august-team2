package com.group2bootcamp23.demo.entity.car;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "file_resources")

public class FileResourcesForCar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "image_name")
    @NotNull
    @NotBlank
    private String imageName;

    @Lob
    @Column(name = "photo", columnDefinition="BLOB")
    private Byte[] photo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cars")
    @JsonManagedReference
    private Car car;
}
