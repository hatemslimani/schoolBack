package com.pfa.pfa.Controller;

import com.pfa.pfa.Entity.MatiereEntity;
import com.pfa.pfa.Model.MatiereModel;
import com.pfa.pfa.Service.MatiereService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "https://schoolback.azurewebsites.net")
@RestController
@RequestMapping("/matiere")
public class MatiereController {
    @Autowired
    private MatiereService matiereService;
    @PostMapping("/store")
    public MatiereModel store(@RequestBody MatiereEntity s)
    {
        System.out.println(s);
        return matiereService.store(s);
    }
    @GetMapping("/{idClasse}")
    public List<MatiereModel> getAll(@PathVariable int idClasse)
    {
        return matiereService.getAll(idClasse);
    }

    @GetMapping("/delete/{idMatiere}")
    public MatiereModel delete(@PathVariable int idMatiere)
    {
         matiereService.delete(idMatiere);
         return null;
    }
    @GetMapping("/getAllByClassByTeacher/{idTeacher}/{idClasse}")
    public List<MatiereModel> getAllByClassByTeacher(@PathVariable int idTeacher,@PathVariable int idClasse)
    {
        return matiereService.getAllByClassByTeacher(idTeacher,idClasse);
    }
}
