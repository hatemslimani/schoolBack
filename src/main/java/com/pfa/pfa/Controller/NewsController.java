package com.pfa.pfa.Controller;

import com.pfa.pfa.Entity.AvisEntity;
import com.pfa.pfa.Model.NewsModel;
import com.pfa.pfa.Service.*;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = {"https://schoolback.azurewebsites.net","http://localhost:8100"})
@RestController
@RequestMapping("/news")
public class NewsController {
    @Autowired
    private NewsService newsService;
    @Autowired
    private ClasseService classeService;
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private MatiereService matiereService;
    @Autowired
    private UserService userService;
    @Autowired
    StudentService studentService;
    @Autowired
    ServletContext context;
    private Path foundFile;
    @PostMapping("/store")
    public NewsModel addNews(@RequestParam("filee") MultipartFile file , @RequestParam("title") String title,
                             @RequestParam("content") String content)throws IOException {

        boolean isExit = new File(context.getRealPath("/Avis/")).exists();
        if (!isExit) {
            new File(context.getRealPath("/Avis/")).mkdir();
        }
        String filename = file.getOriginalFilename();
        long millis = System.currentTimeMillis();
        String rndchars = RandomStringUtils.randomAlphanumeric(4);
        String newFileName = "avis_" + rndchars  + "_" + millis + "." + FilenameUtils.getExtension(filename);
        File serverFile = new File(context.getRealPath("/Avis/" + File.separator + newFileName));
        try {
            FileUtils.writeByteArrayToFile(serverFile, file.getBytes());

        } catch (Exception e) {
            e.printStackTrace();
        }
        NewsModel newsModel=new NewsModel();
        newsModel.setContent(content);
        newsModel.setFile( newFileName);
        newsModel.setType("news");
        newsModel.setTitle(title);
        return newsService.save(newsModel);

    }
    @GetMapping("/")
    public List<AvisEntity> getAll()
    {
        List<NewsModel> news=newsService.getAll();
        List<AvisEntity> avisList=new ArrayList<>();
        for (NewsModel newsModel : news) {
            AvisEntity avisEntity = new AvisEntity();
            if (newsModel.getType().equals("absence")) {
                    avisEntity.setType(newsModel.getType());
                    avisEntity.setTeacher(teacherService.getById(newsModel.getIdTeacher()));
                    avisEntity.setDateDebut(newsModel.getDateDebut());
                    avisEntity.setDateFin(newsModel.getDateFin());

            } else {
                if (newsModel.getType().equals("rattrapage")) {
                    avisEntity.setType(newsModel.getType());
                    avisEntity.setTeacher(teacherService.getById(newsModel.getIdTeacher()));
                    avisEntity.setClasse(classeService.getById(newsModel.getIdClass()));
                    avisEntity.setMatiere(matiereService.getById(newsModel.getIdMatiere()));
                    avisEntity.setDatedRattrapage(newsModel.getDatedRattrapage());
                }else{
                    avisEntity.setType(newsModel.getType());
                    avisEntity.setTitle(newsModel.getTitle());
                    avisEntity.setFile(newsModel.getFile());
                    avisEntity.setContent(newsModel.getContent());
                }
            }
            avisEntity.setCreatedAt(newsModel.getCreatedAt());
            avisList.add(avisEntity);
        }
        return avisList;
    }
   @GetMapping("/downloadFile/{fileCode}")
   public ResponseEntity<?> downloadFile(@PathVariable("fileCode") String fileCode) throws FileNotFoundException {
       File file = new File(context.getRealPath("/Avis/" + fileCode));


       if (!file.exists()){
           return new ResponseEntity<>("File not found", HttpStatus.NOT_FOUND);
       }
       HttpHeaders headers = new HttpHeaders();
       headers.add(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=file.pdf");
       headers.setContentType(MediaType.APPLICATION_PDF);
       InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
       return ResponseEntity.ok()
               .headers(headers)
               .body(resource);
   }


    @PostMapping("/addAvis")
    public NewsModel addAvis(@RequestBody AvisEntity s) {
        if (s.getType().equals("absence")){
            NewsModel newsModel=new NewsModel();
            newsModel.setDateDebut(s.getDateDebut());
            newsModel.setDateFin(s.getDateFin());
            newsModel.setType(s.getType());
            newsModel.setIdTeacher(s.getIdTeacher());
            this.newsService.save(newsModel);
        }else{
            if (s.getType().equals("rattrapage")){
                NewsModel newsModel=new NewsModel();
                newsModel.setType(s.getType());
                newsModel.setIdTeacher(s.getIdTeacher());
                newsModel.setIdMatiere(s.getIdMatiere());
                newsModel.setIdClass(s.getIdClass());
                newsModel.setDatedRattrapage(s.getDatedRattrapage());
                this.newsService.save(newsModel);
            }
        }
        return  null;
    }
    @GetMapping("/news")
    public List<NewsModel> getAllNews() {
        return this.newsService.getAllNews();
    }
    @GetMapping("/absence")
    public List<AvisEntity> getAllAbsence(@AuthenticationPrincipal UserDetails userDetails) {
        List<Integer> idTeachers=matiereService.getAllByClass(studentService.getUserByNUm(userDetails.getUsername()).getClassee().getIdClass());
        List<NewsModel> news=this.newsService.getAllAbsence(idTeachers);
        List<AvisEntity> avisList=new ArrayList<>();
        for (NewsModel newsModel : news) {
            AvisEntity avisEntity = new AvisEntity();
            avisEntity.setType(newsModel.getType());
            avisEntity.setTeacher(teacherService.getById(newsModel.getIdTeacher()));
            avisEntity.setDateDebut(newsModel.getDateDebut());
            avisEntity.setDateFin(newsModel.getDateFin());
            avisEntity.setCreatedAt(newsModel.getCreatedAt());
            avisList.add(avisEntity);
        }
        return avisList;
    }
}
