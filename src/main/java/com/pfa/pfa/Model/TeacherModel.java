package com.pfa.pfa.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name="teacher")
@Entity
public class TeacherModel {
    @Id
    @GeneratedValue
    private int idTeacher;
    private String numInscript;
    private String nom;
    private String prenom;
    private Date dateNaissance;
    @CreationTimestamp
    private Timestamp createdAt;
    @UpdateTimestamp
    private Timestamp updatedAt;
    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "Teacher",cascade = CascadeType.REMOVE)
    List<MatiereModel> matiers;
}
