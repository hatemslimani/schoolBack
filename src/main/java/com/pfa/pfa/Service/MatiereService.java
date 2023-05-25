package com.pfa.pfa.Service;

import com.pfa.pfa.Entity.MatiereEntity;
import com.pfa.pfa.Model.MatiereModel;
import com.pfa.pfa.Repository.ClasseRepository;
import com.pfa.pfa.Repository.MatiereRepository;
import com.pfa.pfa.Repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MatiereService {
    @Autowired
    private MatiereRepository matiereRepository;
    @Autowired
    private TeacherRepository teacherRepository;
    @Autowired
    private ClasseRepository classeRepository;
    public MatiereModel store(MatiereEntity s) {
        MatiereModel matiereModel=new MatiereModel();
        matiereModel.setNom_matiere(s.getNom_matiere());
        matiereModel.setClasse(classeRepository.findById(s.getIdClass()).orElse(null));
        matiereModel.setTeacher(teacherRepository.findById(s.getIdTeacher()).orElse(null));
        return matiereRepository.save(matiereModel);
    }

    public List<MatiereModel> getAll(int idClasse) {
        return matiereRepository.getAllByClassId(idClasse);
    }

    public MatiereModel delete(int idMatiere) {
        matiereRepository.deleteById(idMatiere);
        return null;
    }

    public List<MatiereModel> getAllByClassByTeacher(int idTeacher, int idClasse) {
        return matiereRepository.getAllByClassByTeacher(idTeacher,idClasse);
    }

    public MatiereModel getById(int idMatiere) {
        return  this.matiereRepository.findById(idMatiere).orElse(null);
    }

    public List<Integer> getAllByClass(int idClass) {
        return this.matiereRepository.getAllByClass(idClass);
    }
}
