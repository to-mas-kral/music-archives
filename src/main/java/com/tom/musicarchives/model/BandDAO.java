package com.tom.musicarchives.model;

import java.util.List;

public interface BandDAO {
    Band getBandById(int id);

    void saveBand(Band band);

    void updateBand(Band band);

    void deleteBand(int id);

    List<Band> getAllBands();

    List<Album> getBandAlbums(int id);

    List<Member> getBandMembers(int id);
}
