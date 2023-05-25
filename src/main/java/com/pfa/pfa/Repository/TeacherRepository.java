package com.pfa.pfa.Repository;

import com.pfa.pfa.Model.TeacherModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TeacherRepository extends JpaRepository<TeacherModel,Integer> {
    @Query("select t from TeacherModel t where t.idTeacher in (select m.Teacher.idTeacher from MatiereModel m where m.classe.idClass=:IdClass)")
    List<TeacherModel> getByClass(int IdClass);
    @Query("select s from TeacherModel s where s.matiers= :idClass")
    List<TeacherModel> getTeachersByClass(int idClass);
}
