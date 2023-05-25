package com.pfa.pfa.Controller;

import com.pfa.pfa.Model.ClasseModel;
import com.pfa.pfa.Service.ClasseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "https://schoolback.azurewebsites.net")
@RestController
@RequestMapping("/classe")
public class ClaseeController {
    @Autowired
    private ClasseService classeService;
    @GetMapping("/")
    public List<ClasseModel> getAll()
    {
        return classeService.getAll();
    }
    @GetMapping("/{idClasse}")
    public ClasseModel getById(@PathVariable int idClasse)
    {
        return classeService.getById(idClasse);
    }
    @PostMapping("save")
    public ClasseModel store(@RequestBody ClasseModel c)
    {
        return classeService.store(c);
    }
    @GetMapping("/delete/{idClass}")
    public void delete(@PathVariable int idClass)
    {
        classeService.delete(idClass);
    }
    @PostMapping("update/{idClasse}")
    public ClasseModel update(@RequestBody ClasseModel c,@PathVariable int idClasse)
    {
        return classeService.update(c,idClasse);
    }
    @GetMapping("/getByTeacher/{idTeacher}")
    public List<ClasseModel> getByTeacher(@PathVariable int idTeacher)
    {
        return classeService.getByTeacher(idTeacher);
    }
}
