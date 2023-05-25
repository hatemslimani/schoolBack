package com.pfa.pfa.Controller;

import com.pfa.pfa.Entity.StudentEntity;
import com.pfa.pfa.Model.ClasseModel;
import com.pfa.pfa.Model.StudentModel;
import com.pfa.pfa.Model.User;
import com.pfa.pfa.Service.ClasseService;
import com.pfa.pfa.Service.StudentService;
import com.pfa.pfa.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentService studentService;
    @Autowired
    private ClasseService classeService;
    @Autowired
    private UserService userService;
    @GetMapping("/")
    public List<StudentEntity> getAll()
    {
        return studentService.getAll();
    }
    @GetMapping("/{idStudent}")
    public StudentModel getById(@PathVariable int idStudent)
    {
        return studentService.getById(idStudent);
    }
    @GetMapping("/delete/{idStudent}")
    public void delete(@PathVariable int idStudent)
    {
        studentService.delete(idStudent);
    }
    @PostMapping("/store")
    public StudentModel store(@RequestBody StudentEntity s)
    {
        StudentModel st=new StudentModel();
        st.setClassee(classeService.getById(s.getIdGroup()));
        st.setDateNaissance(s.getDateNaissance());
        st.setNumInscript(generateNumIscrit());
        st.setNom(s.getNom());
        st.setPrenom(s.getPrenom());
        User user=new User();
        user.setUserName(st.getNumInscript());
        user.setPassword(st.getNumInscript());
        user.setFirstLogin(true);
        user.setRole("USER");
        userService.CreateUser(user);
        return studentService.store(st);
    }
    public String generateNumIscrit(){
        return "N"+Math.round(System.currentTimeMillis()% 1000000);
    }
    @GetMapping("/notAffected")
    public List<StudentModel> getStudentNotAffected()
    {
        return studentService.getStudentNotAffected();
    }

    @GetMapping("/affected/{idStudent}/{idClasse}")
    public StudentModel affectedStudent(@PathVariable int idStudent,@PathVariable int idClasse)
    {
        StudentModel s=this.studentService.getById(idStudent);
        ClasseModel classeModel=classeService.getById(idClasse);
        classeModel.setNbActuel(classeModel.getNbActuel()+1);
        s.setClassee(classeModel);
        this.classeService.store(classeModel);
        return this.studentService.store(s);
    }
    @GetMapping("/getByClass/{idClasse}")
    public List<StudentModel> getByClass(@PathVariable int idClasse)
    {
        return studentService.getByClass(idClasse);
    }
    @GetMapping("/desAffected/{idStudent}/{idClasse}")
    public StudentModel desAffected(@PathVariable int idStudent,@PathVariable int idClasse)
    {
        return studentService.desAffected(idStudent,idClasse);
    }
    @PostMapping("update/{idStudent}")
    public StudentModel update(@RequestBody StudentEntity s, @PathVariable int idStudent)
    {
        return studentService.update(s,idStudent);
    }
}
