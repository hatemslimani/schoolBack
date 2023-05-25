package com.pfa.pfa.Entity;

import com.pfa.pfa.Model.ClasseModel;
import lombok.Data;

import java.sql.Date;
import java.sql.Timestamp;
@Data
public class StudentEntity {
    private int idStudent;
    private String numInscript;
    private String nom;
    private String prenom;
    private Date dateNaissance;
    private int idGroup;
    private ClasseModel classee;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private String nomClasse;

    public StudentEntity(int idStudent, String numInscript, String nom, String prenom, Date dateNaissance, Timestamp createdAt, ClasseModel classe) {
        this.idStudent = idStudent;
        this.numInscript = numInscript;
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
        this.createdAt = createdAt;
        this.classee = classe;
    }
}
