package com.hrms.business.concretes;

import com.hrms.business.abstracts.CandidateService;
import com.hrms.core.utilities.results.*;
import com.hrms.dtos.CandidateAddDto;
import com.hrms.dtos.CandidateGetAllDto;
import com.hrms.dtos.CandidateUpdateDto;
import com.hrms.repository.CandidateRepository;
import com.hrms.domain.Candidate;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CandidateImpl implements CandidateService {
    private final CandidateRepository candidateRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public CandidateImpl(CandidateRepository candidateRepository, ModelMapper modelMapper) {
        this.candidateRepository = candidateRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public DataResult<List<CandidateGetAllDto>> getAll() {
        return new SuccessDataResult<List<CandidateGetAllDto>>(this.candidateRepository.getCandidateDetails(), "Adaylar listelendi");
    }

    @Override
    public Result add(CandidateAddDto candidateAddDto) {
        Candidate candidate = modelMapper.map(candidateAddDto, Candidate.class);

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
    public Result update(int id, CandidateUpdateDto candidateUpdateDto) {
        Optional<Candidate> resultCandidate = this.candidateRepository.findById(id);
        if (resultCandidate.isPresent()) {
            resultCandidate.get().setEmailAddress(candidateUpdateDto.getEmailAddress());
            resultCandidate.get().setPassword(candidateUpdateDto.getPassword());
            resultCandidate.get().setName(candidateUpdateDto.getName());
            resultCandidate.get().setSurname(candidateUpdateDto.getSurname());
            resultCandidate.get().setAge(candidateUpdateDto.getAge());
            resultCandidate.get().setCity(candidateUpdateDto.getCity());
            resultCandidate.get().setProfession(candidateUpdateDto.getProfession());

            modelMapper.map(this.candidateRepository.save(resultCandidate.get()), CandidateUpdateDto.class);
            return new SuccessResult("Aday güncellendi");
        }else {
            return new ErrorResult("Aday bulunamadı");
        }
    }
}
