package com.hrms.business.concretes;

import com.hrms.business.abstracts.JobAdvertService;
import com.hrms.core.utilities.results.*;
import com.hrms.domain.*;
import com.hrms.dtos.JobAdvertAddDto;
import com.hrms.dtos.JobAdvertDetailDto;
import com.hrms.dtos.JobAdvertUpdateDto;
import com.hrms.repository.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JobAdvertImpl implements JobAdvertService {
    private final JobAdvertRepository jobAdvertRepository;
    private final ModelMapper modelMapper;
    private final EmployerRepository employerRepository;
    private final PositionRepository positionRepository;
    private final CandidateRepository candidateRepository;
    private final CityRepository cityRepository;

    @Autowired
    public JobAdvertImpl(JobAdvertRepository jobAdvertRepository, ModelMapper modelMapper, EmployerRepository employerRepository, PositionRepository positionRepository, CandidateRepository candidateRepository, CityRepository cityRepository) {
        this.jobAdvertRepository = jobAdvertRepository;
        this.modelMapper = modelMapper;
        this.employerRepository = employerRepository;
        this.positionRepository = positionRepository;
        this.candidateRepository = candidateRepository;
        this.cityRepository = cityRepository;
    }

    @Override
    public DataResult<List<JobAdvertDetailDto>> getAll() {
        return new SuccessDataResult<List<JobAdvertDetailDto>>(this.jobAdvertRepository.getJobAdvertDetails(), "İş ilanları listelendi");
    }

    @Override
    public Result add(JobAdvertAddDto jobAdvertAddDto) {
        JobAdvert jobAdvert = modelMapper.map(jobAdvertAddDto, JobAdvert.class);

        jobAdvert.setEmployer(employerRepository.getById(jobAdvertAddDto.getEmployerId()));
        jobAdvert.setPosition(positionRepository.getById(jobAdvertAddDto.getPositionId()));
        jobAdvert.setCandidate(candidateRepository.getById(jobAdvertAddDto.getCandidateId()));
        jobAdvert.setCity(cityRepository.getById(jobAdvertAddDto.getCityId()));

        this.jobAdvertRepository.save(jobAdvert);
        return new SuccessResult("İş ilanı eklendi");
    }

    @Override
    public Result delete(int id) {
        JobAdvert jobAdvert = this.jobAdvertRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("İş ilanı bulunamadı"));
        this.jobAdvertRepository.delete(jobAdvert);
        return new SuccessResult(jobAdvert.getTitle() + " başlıklı iş ilanı silindi");
    }

    @Override
    public Result update(int id, JobAdvertUpdateDto jobAdvertUpdateDto) {
        Optional<JobAdvert> resultJobAdvert = this.jobAdvertRepository.findById(id);
        if (resultJobAdvert.isPresent()) {
            resultJobAdvert.get().setTitle(jobAdvertUpdateDto.getTitle());
            resultJobAdvert.get().setDescription(jobAdvertUpdateDto.getDescription());
            resultJobAdvert.get().setMinSalary(jobAdvertUpdateDto.getMinSalary());
            resultJobAdvert.get().setMaxSalary(jobAdvertUpdateDto.getMaxSalary());
            resultJobAdvert.get().setNumberOfAvailablePositions(jobAdvertUpdateDto.getNumberOfAvailablePositions());
            resultJobAdvert.get().setDeadline(jobAdvertUpdateDto.getDeadline());
            resultJobAdvert.get().setAdvertSituation(jobAdvertUpdateDto.isAdvertSituation());

            modelMapper.map(this.jobAdvertRepository.save(resultJobAdvert.get()), JobAdvertUpdateDto.class);
            return new SuccessResult("İş ilanı güncellendi");
        } else {
            return new ErrorResult("İş ilanı bulunamadı");
        }

    }

    @Override
    public DataResult<List<JobAdvert>> findByAdvertSituationTrue() {
        return new SuccessDataResult<List<JobAdvert>>(this.jobAdvertRepository.getByAdvertSituationTrue(), "Açık olan iş ilanları listelendi");
    }
}
