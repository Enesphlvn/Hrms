package com.hrms.business.concretes;

import com.hrms.business.abstracts.JobAdvertService;
import com.hrms.core.utilities.results.*;
import com.hrms.domain.*;
import com.hrms.dtos.jobAdvertDtos.CreateJobAdvertDto;
import com.hrms.dtos.jobAdvertDtos.GetJobAdvertDetailDto;
import com.hrms.dtos.jobAdvertDtos.UpdateJobAdvertDto;
import com.hrms.dtos.jobAdvertDtos.GetJobAdvertDto;
import com.hrms.repository.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JobAdvertServiceImpl implements JobAdvertService {
    private final JobAdvertRepository jobAdvertRepository;
    private final ModelMapper modelMapper;
    private final EmployerRepository employerRepository;
    private final PositionRepository positionRepository;
    private final CandidateRepository candidateRepository;
    private final CityRepository cityRepository;

    @Autowired
    public JobAdvertServiceImpl(JobAdvertRepository jobAdvertRepository, ModelMapper modelMapper, EmployerRepository employerRepository, PositionRepository positionRepository, CandidateRepository candidateRepository, CityRepository cityRepository) {
        this.jobAdvertRepository = jobAdvertRepository;
        this.modelMapper = modelMapper;
        this.employerRepository = employerRepository;
        this.positionRepository = positionRepository;
        this.candidateRepository = candidateRepository;
        this.cityRepository = cityRepository;
    }

    @Override
    public DataResult<List<GetJobAdvertDto>> getAll() {
        return new SuccessDataResult<List<GetJobAdvertDto>>(this.jobAdvertRepository.getJobAdvertDto(), "İş ilanları listelendi");
    }

    @Override
    public DataResult<List<GetJobAdvertDetailDto>> getJobAdvertDetails() {
        return new SuccessDataResult<List<GetJobAdvertDetailDto>>(this.jobAdvertRepository.getJobAdvertDetails(), "İş ilanı detayları listelendi");
    }

    @Override
    public Result add(CreateJobAdvertDto createJobAdvertDto) {
        JobAdvert jobAdvert = this.modelMapper.map(createJobAdvertDto, JobAdvert.class);

        jobAdvert.setEmployer(this.employerRepository.getById(createJobAdvertDto.getEmployerId()));
        jobAdvert.setPosition(this.positionRepository.getById(createJobAdvertDto.getPositionId()));
        jobAdvert.setCandidate(this.candidateRepository.getById(createJobAdvertDto.getCandidateId()));
        jobAdvert.setCity(this.cityRepository.getById(createJobAdvertDto.getCityId()));

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
    public Result update(UpdateJobAdvertDto updateJobAdvertDto) {
        Optional<JobAdvert> resultJobAdvert = this.jobAdvertRepository.findById(updateJobAdvertDto.getId());

        City city = this.cityRepository.getById(updateJobAdvertDto.getCityId());
        Employer employer = this.employerRepository.getById(updateJobAdvertDto.getEmployerId());
        Candidate candidate = this.candidateRepository.getById(updateJobAdvertDto.getCandidateId());
        Position position = this.positionRepository.getById(updateJobAdvertDto.getPositionId());

        if (resultJobAdvert.isPresent()) {
            resultJobAdvert.get().setTitle(updateJobAdvertDto.getTitle());
            resultJobAdvert.get().setDescription(updateJobAdvertDto.getDescription());
            resultJobAdvert.get().setMinSalary(updateJobAdvertDto.getMinSalary());
            resultJobAdvert.get().setMaxSalary(updateJobAdvertDto.getMaxSalary());
            resultJobAdvert.get().setNumberOfAvailablePositions(updateJobAdvertDto.getNumberOfAvailablePositions());
            resultJobAdvert.get().setDeadline(updateJobAdvertDto.getDeadline());
            resultJobAdvert.get().setAdvertSituation(updateJobAdvertDto.isAdvertSituation());
            resultJobAdvert.get().setEmployer(employer);
            resultJobAdvert.get().setCandidate(candidate);
            resultJobAdvert.get().setPosition(position);
            resultJobAdvert.get().setCity(city);

            this.modelMapper.map(this.jobAdvertRepository.save(resultJobAdvert.get()), UpdateJobAdvertDto.class);
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
