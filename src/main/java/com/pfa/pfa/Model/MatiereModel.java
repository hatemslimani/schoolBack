package com.pfa.pfa.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
@Entity
@AllArgsConstructor
@Data
@NoArgsConstructor
@Table(name="matiere")
public class MatiereModel {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id_matiere;
    private String nom_matiere;
    @ManyToOne
    @JoinColumn(name = "ClassId")
    private ClasseModel classe;
    @ManyToOne
    @JoinColumn(name="teacherId")
    private TeacherModel Teacher;
    @CreationTimestamp
    private Timestamp createdAt;
    @UpdateTimestamp
    private Timestamp updatedAt;

}
