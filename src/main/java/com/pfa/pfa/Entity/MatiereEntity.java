package com.pfa.pfa.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MatiereEntity {
    private int id_matiere;
    private String nom_matiere;
    private int idTeacher;
    private String numInscript;
    private String nomTeacher;
    private String prenomTeacher;
    private int idClass;
    private String nomClasse;
}
