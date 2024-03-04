package com.example.ctapi.services;

import com.example.ctapi.dtos.response.AgencyDto;

import java.io.IOException;

public interface IAgencyService {
    AgencyDto getAgencyById( String id)  throws IOException;
}
