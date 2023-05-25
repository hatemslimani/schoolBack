package com.pfa.pfa.Repository;

import com.pfa.pfa.Model.StudentModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<StudentModel,Integer> {
    @Query("select s from StudentModel s where s.classee is null")
    List<StudentModel> getStudentNotAffected();
    @Query("select s from StudentModel s where s.classee.idClass=?1")
    List<StudentModel> getByClass(@Param("idClass") int idClass);
    @Query("select s from StudentModel s where s.numInscript= :username")
    StudentModel getByNum(String username);
    //@Query("select NEW com.pfa.pfa.Entity.StudentEntity(s.idStudent,s.numInscript,s.nom,s.prenom,s.dateNaissance ,s.createdAt,s.classee.nom) from StudentModel s ")
    //List<StudentEntity> getAll();
}
