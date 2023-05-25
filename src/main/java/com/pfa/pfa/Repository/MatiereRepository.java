package com.pfa.pfa.Repository;

import com.pfa.pfa.Model.MatiereModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MatiereRepository extends JpaRepository<MatiereModel,Integer> {
    @Query("select m from MatiereModel m where m.classe.idClass= :idClasse")
    List<MatiereModel> getAllByClassId(int idClasse);
    @Query("select m from MatiereModel m where m.Teacher.idTeacher= :idTeacher")
    List<MatiereModel> getAllByTeacherId(int idTeacher);
    @Query("select m from MatiereModel m where m.Teacher.idTeacher= :idTeacher and m.classe.idClass= :idClasse")
    List<MatiereModel> getAllByClassByTeacher(int idTeacher, int idClasse);
    @Query("select s.Teacher.idTeacher from MatiereModel s where s.classe.idClass= :idClass")
    List<Integer> getAllByClass(int idClass);
}
