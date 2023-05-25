package com.pfa.pfa.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;

@Entity
@AllArgsConstructor
@Data
@NoArgsConstructor
@Table(name="news")
public class NewsModel {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;
    private String title;
    private String content;
    private String file;
    private Date datePublication;
    private int idTeacher;
    private int idClass;
    private Date dateDebut;
    private Date dateFin;
    private String type;
    private int idMatiere;
    private Date datedRattrapage;
    @CreationTimestamp
    private Timestamp createdAt;
    @UpdateTimestamp
    private Timestamp updatedAt;
}
