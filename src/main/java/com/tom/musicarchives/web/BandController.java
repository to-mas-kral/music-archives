package com.tom.musicarchives.web;

import com.tom.musicarchives.model.Band;
import com.tom.musicarchives.model.BandDAO;

import com.tom.musicarchives.utils.BandSearchQuery;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class BandController {
    private BandDAO dao;

    @Autowired
    public BandController(BandDAO dao) {
        this.dao = dao;
    }

    @GetMapping("/bands")
    public String allBands(Model model) {
        var bands = dao.getAllBands();
        var band_search_query = new BandSearchQuery();
        model.addAttribute("bands", bands);
        model.addAttribute("band_search_query", band_search_query);

        return "band/bands_view";
    }

    @GetMapping("/band/detail/{id}")
    public String bandDetail(Model model, @PathVariable int id) {
        var band = dao.getBandById(id);
        var albums = dao.getBandAlbums(id);
        var members = dao.getBandMembers(id);

        model.addAttribute("band", band);
        model.addAttribute("albums", albums);
        model.addAttribute("members", members);

        return "band/band_detail_view";
    }

    @GetMapping("/band/new")
    public String newBand(Model model) {
        model.addAttribute("band", new Band());
        return "band/band_new";
    }

    @GetMapping("/band/process_new")
    public RedirectView processForm(@Valid @ModelAttribute("band") Band band, BindingResult br, Model model) {
        if (br.hasErrors()) {
            return new RedirectView("/band/new");
        }

        if (band.getId() == 0) {
            dao.saveBand(band);
        } else {
            dao.updateBand(band);
        }

        return new RedirectView("/bands");
    }

    @GetMapping("/band/search")
    public String bandSearch(@Valid @ModelAttribute("band") BandSearchQuery bandSearchQuery, Model model) {
        var filtered_bands = dao.searchBands(bandSearchQuery);
        model.addAttribute("bands", filtered_bands);

        return "band/bands_search_view";
    }

    @GetMapping("/band/random")
    public String bandSearch(Model model) {
        List<Band> bands = new ArrayList<Band>();
        bands.add(dao.getRandomBand());
        model.addAttribute("bands", bands);

        return "band/bands_search_view";
    }

    /*@GetMapping("/update/{id}")
    public String updateUser(Model model, @PathVariable int id){
        try {
            User user = dao.getUserById(id);
            model.addAttribute("user", user);
            model.addAttribute("eyeColors", eyeColors);
            model.addAttribute("genders", genders);
            return "new";
        }catch (Exception e){
            return "error";
        }
    }*/


    /*@InitBinder
    public void initBinder(WebDataBinder db){
        StringTrimmerEditor e = new StringTrimmerEditor(true);
        db.registerCustomEditor(String.class, e);
    }*/
}
