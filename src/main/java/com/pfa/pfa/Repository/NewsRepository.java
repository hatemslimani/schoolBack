package com.pfa.pfa.Repository;

import com.pfa.pfa.Model.NewsModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NewsRepository extends JpaRepository<NewsModel,Integer> {
    @Query("select m from NewsModel m where m.type='news'")
    List<NewsModel> getAllNews();
    @Query("select m from NewsModel m where m.type='absence' and m.idTeacher in :idTeachers")
    List<NewsModel> getAllAbsence(List<Integer> idTeachers);
}
