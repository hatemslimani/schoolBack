package com.pfa.pfa.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name="class")
@Entity
public class ClasseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idClass;
    private String nom;
    private int nbLimit;
    private int nbActuel;
    @CreationTimestamp
    private Timestamp createdAt;
    @UpdateTimestamp
    private Timestamp updatedAt;
    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "classee",cascade = CascadeType.ALL, orphanRemoval = true)
    List<StudentModel> students;
    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "classe",cascade = CascadeType.REMOVE)
    List<MatiereModel> matiers;
}
