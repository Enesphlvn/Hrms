package com.hrms.business.concretes;

import com.hrms.business.abstracts.CandidateService;
import com.hrms.core.utilities.results.*;
import com.hrms.domain.City;
import com.hrms.dtos.candidateDtos.CreateCandidateDto;
import com.hrms.dtos.candidateDtos.GetCandidateDetailDto;
import com.hrms.dtos.candidateDtos.GetCandidateDto;
import com.hrms.dtos.candidateDtos.UpdateCandidateDto;
import com.hrms.repository.CandidateRepository;
import com.hrms.domain.Candidate;
import com.hrms.repository.CityRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CandidateServiceImpl implements CandidateService {
    private final CandidateRepository candidateRepository;
    private final CityRepository cityRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public CandidateServiceImpl(CandidateRepository candidateRepository, CityRepository cityRepository, ModelMapper modelMapper) {
        this.candidateRepository = candidateRepository;
        this.cityRepository = cityRepository;
        this.modelMapper = modelMapper;
    }

//    @Override
//    public DataResult<List<GetCandidateDetailDto>> getCandidateDetailDto() {
//        return new SuccessDataResult<List<GetCandidateDetailDto>>(this.candidateRepository.getCandidateDetailDto(), "Aday detayları listelendi");
//    }

    @Override
    public DataResult<List<GetCandidateDto>> getAll() {
        return new SuccessDataResult<List<GetCandidateDto>>(this.candidateRepository.getCandidateDto(), "Adaylar listelendi");
    }

    @Override
    public Result add(CreateCandidateDto createCandidateDto) {
        Candidate candidate = this.modelMapper.map(createCandidateDto, Candidate.class);

        City city = this.cityRepository.getById(createCandidateDto.getCityId());

        candidate.setCity(city);

        this.candidateRepository.save(candidate);
        return new SuccessResult("Aday eklendi");
    }

    @Override
    public Result delete(int id) {
        Candidate candidate = this.candidateRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Aday bulunamadı"));
        this.candidateRepository.delete(candidate);
        return new SuccessResult(candidate.getName() + " " + candidate.getSurname() + " adlı aday silindi");
    }

    @Override
    public Result update(UpdateCandidateDto updateCandidateDto) {
        Optional<Candidate> resultCandidate = this.candidateRepository.findById(updateCandidateDto.getId());
        City city = this.cityRepository.getById(updateCandidateDto.getCityId());

        if (resultCandidate.isPresent()) {
            resultCandidate.get().setEmailAddress(updateCandidateDto.getEmailAddress());
            resultCandidate.get().setPassword(updateCandidateDto.getPassword());
            resultCandidate.get().setName(updateCandidateDto.getName());
            resultCandidate.get().setSurname(updateCandidateDto.getSurname());
            resultCandidate.get().setAge(updateCandidateDto.getAge());
            resultCandidate.get().setProfession(updateCandidateDto.getProfession());
            resultCandidate.get().setCity(city);

            this.modelMapper.map(this.candidateRepository.save(resultCandidate.get()), UpdateCandidateDto.class);
            return new SuccessResult("Aday güncellendi");
        }else {
            return new ErrorResult("Aday bulunamadı");
        }
    }
}
