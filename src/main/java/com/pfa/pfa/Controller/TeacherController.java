package com.pfa.pfa.Controller;

import com.pfa.pfa.Model.StudentModel;
import com.pfa.pfa.Model.TeacherModel;
import com.pfa.pfa.Service.StudentService;
import com.pfa.pfa.Service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/teacher")
public class TeacherController {
    @Autowired
    private TeacherService teacherService;
    @GetMapping("/")
    public List<TeacherModel> getAll()
    {
        return teacherService.getAll();
    }
    @GetMapping("/{idTeacher}")
    public TeacherModel getById(@PathVariable int idTeacher)
    {
        return teacherService.getById(idTeacher);
    }
    @GetMapping("/delete/{idTeacher}")
    public void delete(@PathVariable int idTeacher)
    {
        teacherService.delete(idTeacher);
    }
    @PostMapping("store")
    public TeacherModel store(@RequestBody TeacherModel s)
    {
        s.setNumInscript(generateNumIscrit());
        return teacherService.store(s);
    }
    public String generateNumIscrit(){
        return "N"+Math.round(System.currentTimeMillis()% 1000000);
    }
    @GetMapping("getByClass/{idClass}")
    public TeacherModel getByClass(@PathVariable int idClass)
    {
        return teacherService.getByClass(idClass);
    }
    @PostMapping("update/{idTeacher}")
    public TeacherModel update(@RequestBody TeacherModel s,@PathVariable int idTeacher)
    {
        return teacherService.update(s,idTeacher);
    }
}
