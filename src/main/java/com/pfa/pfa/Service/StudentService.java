package com.pfa.pfa.Service;

import com.pfa.pfa.Entity.StudentEntity;
import com.pfa.pfa.Model.ClasseModel;
import com.pfa.pfa.Model.StudentModel;
import com.pfa.pfa.Model.TeacherModel;
import com.pfa.pfa.Repository.ClasseRepository;
import com.pfa.pfa.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private ClasseRepository classeRepository;
    public List<StudentEntity> getAll()
    {
        List<StudentModel> studentList=studentRepository.findAll();
        List<StudentEntity>studentEntityList=new ArrayList<>();
        for (int i=0;i<studentList.size();i++){
                StudentEntity st=new StudentEntity(
                        studentList.get(i).getIdStudent(),studentList.get(i).getNumInscript(),studentList.get(i).getNom(),
                        studentList.get(i).getPrenom(),studentList.get(i).getDateNaissance(),studentList.get(i).getCreatedAt(),
                        classeRepository.getClassByStudent(studentList.get(i).getIdStudent())
                );
                studentEntityList.add(st);
        }

        return studentEntityList;
    }
    public StudentModel store(StudentModel s)
    {
        return studentRepository.save(s);
    }
    public StudentModel getById(int idStudent)
    {
        return studentRepository.findById(idStudent).orElse(null);
    }

    public void delete(int idStudent) {
        this.studentRepository.deleteById(idStudent);
    }

    public List<StudentModel> getStudentNotAffected() {
        return studentRepository.getStudentNotAffected();
    }

    public List<StudentModel> getByClass(int idClass) {
        return studentRepository.getByClass(idClass);
    }

    public StudentModel desAffected(int idStudent, int idClasse) {
        StudentModel studentModel=this.studentRepository.findById(idStudent).orElse(null);
        if (studentModel!=null){
            studentModel.setClassee(null);

            ClasseModel classeModel=classeRepository.findById(idClasse).orElse(null);
            classeModel.setNbActuel(classeModel.getNbActuel()-1);
            classeRepository.save(classeModel);
            return this.studentRepository.save(studentModel);
        }
        return null;
    }
    public StudentModel update(StudentEntity s, int idStudent)
    {
        StudentModel studentModel=studentRepository.findById(idStudent).orElse(null);
        if (studentModel!=null){
            studentModel.setNom(s.getNom());
            studentModel.setPrenom(s.getPrenom());
            studentModel.setDateNaissance(s.getDateNaissance());
            studentModel.setClassee(classeRepository.findById(s.getIdGroup()).orElse(null));
            return studentRepository.save(studentModel);
        }
        return null;
    }

    public StudentModel getUserByNUm(String username) {
        return this.studentRepository.getByNum(username);
    }
}
