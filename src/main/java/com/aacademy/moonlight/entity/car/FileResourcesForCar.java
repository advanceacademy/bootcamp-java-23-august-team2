package com.aacademy.moonlight.entity.car;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "image_name")
    @NotNull
    private String imageName;

    @Lob
    @Column(name = "photo", columnDefinition="BLOB")
    private Byte[] photo;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonManagedReference
    private Car car;

    //private List<FileResourcesForCar> fileResourcesForCar = new ArrayList<>();
}
