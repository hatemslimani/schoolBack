package com.pfa.pfa.Entity;

import com.pfa.pfa.Model.ClasseModel;
import com.pfa.pfa.Model.MatiereModel;
import com.pfa.pfa.Model.TeacherModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AvisEntity {

    private int id;
    private String title;
    private String content;
    private String file;
    private Date datePublication;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private int idTeacher;
    private int idClass;
    private Date dateDebut;
    private Date dateFin;
    private String type;
    private int idMatiere;
    private Date datedRattrapage;
    private TeacherModel teacher;
    private ClasseModel classe;
    private MatiereModel matiere;

}
