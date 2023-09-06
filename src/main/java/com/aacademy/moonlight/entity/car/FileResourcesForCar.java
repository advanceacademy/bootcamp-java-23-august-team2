package com.aacademy.moonlight.entity.car;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
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
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "image_name",nullable = false)
    @NotEmpty
    private String imageName;

    @Lob
    @Column(name = "photo", columnDefinition="BLOB")
    private Byte[] photo;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonBackReference
    private Car car;

    //private List<FileResourcesForCar> fileResourcesForCar = new ArrayList<>();
}
