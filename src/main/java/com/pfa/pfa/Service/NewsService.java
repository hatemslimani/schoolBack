package com.pfa.pfa.Service;

import com.pfa.pfa.Model.NewsModel;
import com.pfa.pfa.Repository.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsService {
    @Autowired
    private NewsRepository newsRepository;

    public NewsModel save(NewsModel newsModel) {
        return newsRepository.save(newsModel);
    }

    public List<NewsModel> getAll() {
        return newsRepository.findAll(Sort.by(Sort.Direction.DESC, "createdAt"));
    }

    public List<NewsModel> getAllNews() {
        return this.newsRepository.getAllNews();
    }

    public List<NewsModel> getAllAbsence(List<Integer> idTeachers) {
        return this.newsRepository.getAllAbsence(idTeachers);
    }
}
