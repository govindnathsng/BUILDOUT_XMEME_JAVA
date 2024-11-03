package com.crio.starter.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import com.crio.starter.data.Meme;
import com.crio.starter.exceptions.MemeAlreadyExistsException;
import com.crio.starter.exceptions.MemeConstraintViolationException;
import com.crio.starter.exceptions.MemeNotFoundException;
import com.crio.starter.repository.MemeRepository;

@Component
public class MemeService {

    @Autowired
    private MemeRepository memeRepository;
    private final Pattern urlPattern = Pattern.compile("(http|https):\\/\\/(www)?[-a-zA-Z0-9@:%._\\+~#=]{1,256}\\.[a-zA-Z0-9()]{1,6}\\b([-a-zA-Z0-9()@:%_\\+.~#?&//=]*)");

    public String saveMeme(Meme meme) {
        if (meme.getName() == null || meme.getName().trim().isEmpty()) {
            throw new MemeConstraintViolationException(List.of("Name cannot be null or empty"));
        }
        ensureURLMatchesPattern(meme);
        ensureMemeDoesNotAlreadyExist(meme);
        meme.setCreatedAt(System.currentTimeMillis());
        meme.setLastUpdatedAt(System.currentTimeMillis());
        Meme savedMeme = memeRepository.save(meme);
        // return savedMeme.getId(); // Return the ID of the saved meme
        return String.valueOf(savedMeme.getId());
    }

    public List<Meme> getMemes(int page, int pageSize) {
        return memeRepository.findAll(PageRequest.of(page, pageSize)).getContent();
    }
    // public List<Meme> getAllMemes() {
    //     return memeRepository.findAll();
    // }
    public List<Meme> getLatestMemes() {
        List<Meme> memes = memeRepository.findAll(); // Assuming getAllMemes() retrieves all memes from the repository
        return memes.stream()
                    .sorted(Comparator.comparingLong(Meme::getLastUpdatedAt).reversed()) // Sort by last update time in descending order
                    .limit(100) // Limit to the latest 100 memes
                    .collect(Collectors.toList());
    }

    public Meme getMeme(String id) {
        Optional<Meme> optionalMeme = memeRepository.findById(id);
        return optionalMeme.orElseThrow(MemeNotFoundException::new);
    }

    public void deleteMeme(String id) {
        Meme meme = getMeme(id);
        memeRepository.delete(meme);
    }

    public void updateMeme(String id, Meme updatedMeme) {
        Meme oldMeme = getMeme(id);
        if (!oldMeme.getName().equals(updatedMeme.getName())) {
            throw new MemeConstraintViolationException(List.of("Cannot update name of the author"));
        }

        oldMeme.setLastUpdatedAt(System.currentTimeMillis());
        if (updatedMeme.getCaption() != null) oldMeme.setCaption(updatedMeme.getCaption());
        if (updatedMeme.getUrl() != null) oldMeme.setUrl(updatedMeme.getUrl());
        memeRepository.save(oldMeme);
    }

    private void ensureMemeDoesNotAlreadyExist(Meme meme) {
        String name = meme.getName();
        String caption = meme.getCaption();
        String url = meme.getUrl();
        Meme existingMeme = memeRepository.findByNameAndCaptionAndUrl(name, caption, url);
        if (existingMeme != null) {
            throw new MemeAlreadyExistsException();
        }
    }

    private void ensureURLMatchesPattern(Meme meme) {
        String url = meme.getUrl();
        Matcher matcher = urlPattern.matcher(url);
        if (!matcher.matches()) {
            throw new MemeConstraintViolationException(List.of("Posted URL is not in valid format"));
        }
    }
}
