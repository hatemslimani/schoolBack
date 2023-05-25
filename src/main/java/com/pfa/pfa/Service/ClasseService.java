package com.pfa.pfa.Service;

import com.pfa.pfa.Model.ClasseModel;
import com.pfa.pfa.Model.MatiereModel;
import com.pfa.pfa.Repository.ClasseRepository;
import com.pfa.pfa.Repository.MatiereRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClasseService {
    @Autowired
    private ClasseRepository classeRepository;
    @Autowired
    MatiereRepository matiereRepository;
    public List<ClasseModel> getAll()
    {
        return classeRepository.findAll();
    }
    public ClasseModel store(ClasseModel c)
    {
        return classeRepository.save(c);
    }
    public ClasseModel getById(int idClasse)
    {
        return classeRepository.findById(idClasse).orElse(null);
    }

    public void delete(int idClass) {
        this.classeRepository.deleteById(idClass);
    }

    public ClasseModel update(ClasseModel c, int idClasse) {
        ClasseModel classeModel=this.classeRepository.findById(idClasse).orElse(null);
        if (classeModel!=null){
            classeModel.setNom(c.getNom());
            classeModel.setNbLimit(c.getNbLimit());
            this.classeRepository.save(classeModel);
        }
        return null;
    }

    public List<ClasseModel> getByTeacher(int idTeacher) {
        List<MatiereModel> matiereModels=this.matiereRepository.getAllByTeacherId(idTeacher);
        List<ClasseModel> list=new ArrayList<>();
        for (MatiereModel matiereModel : matiereModels) {
            if (!list.contains(matiereModel.getClasse()))
            list.add(matiereModel.getClasse());
        }
        return list;
    }
}
