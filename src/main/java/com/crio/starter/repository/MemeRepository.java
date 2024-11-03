package com.crio.starter.repository;

import java.util.List;
import com.crio.starter.data.Meme;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemeRepository extends MongoRepository<Meme, String> {

    public Meme findByNameAndCaptionAndUrl(String name, String caption, String url);

    public Page<Meme> findAll(Pageable pageable);

    public List<Meme> findAll();

}
