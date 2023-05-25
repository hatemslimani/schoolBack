package com.pfa.pfa.Service;

import com.pfa.pfa.Model.TeacherModel;
import com.pfa.pfa.Repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherService {
    @Autowired
    private TeacherRepository teacherRepository;
    public List<TeacherModel> getAll()
    {
        return teacherRepository.findAll();
    }
    public TeacherModel store(TeacherModel s)
    {
        return teacherRepository.save(s);
    }
    public TeacherModel getById(int idTeacher)
    {
        return teacherRepository.findById(idTeacher).orElse(null);
    }

    public void delete(int idStudent) {
        this.teacherRepository.deleteById(idStudent);
    }

    public TeacherModel getByClass(int idClass) {
        return null;
    }
    public TeacherModel update(TeacherModel s,int idTeacher)
    {
        TeacherModel teacherModel=teacherRepository.findById(idTeacher).orElse(null);
        if (teacherModel!=null){
            teacherModel.setNom(s.getNom());
            teacherModel.setPrenom(s.getPrenom());
            teacherModel.setDateNaissance(s.getDateNaissance());
            return teacherRepository.save(teacherModel);
        }
        return null;
    }

    public List<TeacherModel> getTeachersByClass(int idClass) {
        return this.teacherRepository.getTeachersByClass(idClass);
    }
}
