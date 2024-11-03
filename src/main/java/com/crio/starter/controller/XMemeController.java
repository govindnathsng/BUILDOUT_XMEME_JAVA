package com.crio.starter.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import com.crio.starter.data.Meme;
import com.crio.starter.service.MemeService;
import java.util.List;

@RestController
@RequestMapping(value = "", produces = "application/json")
public class XMemeController {

    @Autowired
    private MemeService memeService;

    @PostMapping("/memes")
    public String submitMeme(@RequestBody @Valid Meme meme) {
        // return memeService.saveMeme(meme);
        String memeId = memeService.saveMeme(meme);
        return String.format("{\"id\": \"%s\"}", memeId);
    }

    // @GetMapping("/memes")
    // public List<Meme> getMemes(
    //         @RequestParam(required = false, defaultValue = "0") int page,
    //         @RequestParam(required = false, defaultValue = "100") int pageSize) {
        
    //     // List<Meme> allMemes = memeService.getAllMemes();
    //     // int start = Math.max(0, allMemes.size() - 100);
    //     // int end = allMemes.size();
    //     return memeService.getMemes(page, pageSize);
    // // List<Meme> latestMemes = memes.size() > 100 ? memes.subList(memes.size() - 100, memes.size()) : memes;
    // // return latestMemes;
        
    // }

    @GetMapping("/memes")
    public List<Meme> getMemes() {
        
        List<Meme> allMemes = memeService.getLatestMemes();
        
    // List<Meme> latestMemes = memes.size() > 100 ? memes.subList(memes.size() - 100, memes.size()) : memes;
    return allMemes;
        
    }

    @GetMapping("/memes/{id}")
    public Meme getMeme(@PathVariable String id) {
        return memeService.getMeme(id);
    }

    @PatchMapping("/memes/{id}")
    public void updateMeme(@PathVariable String id, @RequestBody Meme updatedMeme) {
        memeService.updateMeme(id, updatedMeme);
    }

    @DeleteMapping("/memes/{id}")
    public void deleteMeme(@PathVariable String id) {
        memeService.deleteMeme(id);
    }
}
