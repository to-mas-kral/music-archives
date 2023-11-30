package com.tom.musicarchives.model;

import com.tom.musicarchives.utils.BandSearchQuery;

import java.util.List;

public interface BandDAO {
    Band getBandById(int id);

    void saveBand(Band band);

    void updateBand(Band band);

    void deleteBand(int id);

    List<Band> getAllBands();

    List<Album> getBandAlbums(int id);

    List<Member> getBandMembers(int id);

    List<Band> searchBands(BandSearchQuery bandSearchQuery);

    Band getRandomBand();
}
