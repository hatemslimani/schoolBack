package com.pfa.pfa.Repository;

import com.pfa.pfa.Model.ClasseModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ClasseRepository extends JpaRepository<ClasseModel,Integer> {
    @Query("select c from ClasseModel c , StudentModel s where s.classee.idClass=c.idClass and s.idStudent=:idStudent")
    ClasseModel getClassByStudent(int idStudent);
}
