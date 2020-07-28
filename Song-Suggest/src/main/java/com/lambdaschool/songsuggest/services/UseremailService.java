package com.lambdaschool.songsuggest.services;

import com.lambdaschool.songsuggest.models.Useremail;

import java.util.List;


public interface UseremailService {

    List<Useremail> findAll();


    Useremail findUseremailById(long id);


    void delete(long id);


    Useremail update(
            long useremailid,
            String emailaddress);


    Useremail save(
            long userid,
            String emailaddress);
}
