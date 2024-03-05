package com.hrms.business.concretes;

import com.hrms.business.abstracts.EmployerService;
import com.hrms.core.utilities.results.*;
import com.hrms.dtos.employerDtos.CreateEmployerDto;
import com.hrms.dtos.employerDtos.GetEmployerDto;
import com.hrms.dtos.employerDtos.UpdateEmployerDto;
import com.hrms.repository.EmployerRepository;
import com.hrms.domain.Employer;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployerServiceImpl implements EmployerService {
    private final EmployerRepository employerRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public EmployerServiceImpl(EmployerRepository employerRepository, ModelMapper modelMapper) {
        this.employerRepository = employerRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public DataResult<List<GetEmployerDto>> getAll() {
        return new SuccessDataResult<List<GetEmployerDto>>(this.employerRepository.getEmployerDto(), "İşverenler listelendi");
    }

    @Override
    public Result add(CreateEmployerDto createEmployerDto) {
        Employer employer = this.modelMapper.map(createEmployerDto, Employer.class);
        String companyName = employer.getCompanyName().toLowerCase();
        String emailAddress = employer.getEmailAddress().toLowerCase();
        String[] emailParts = emailAddress.split("@");

        List<Employer> existingCompanies = this.employerRepository.findAll();

        for (Employer existingCompany : existingCompanies) {
            if (existingCompany.getCompanyName().equalsIgnoreCase(companyName)) {
                return new ErrorResult("Bu isimde bir şirket zaten mevcut");
            }
        }

        if (emailParts.length != 2) {
            return new ErrorResult("Geçersiz email adresi");
        }

        String emailCompanyName = emailParts[0];

        if (!emailCompanyName.equalsIgnoreCase(companyName)) {
            return new ErrorResult("Şirket ismi ve email adresi uyuşmuyor");
        }

        this.employerRepository.save(employer);
        return new SuccessResult("İşveren eklendi");
    }

    @Override
    public Result delete(int id) {
        Employer employer = this.employerRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("İşveren bulunamadı"));
        this.employerRepository.delete(employer);
        return new SuccessResult(employer.getCompanyName() + " adlı işveren silindi");
    }

    @Override
    public Result update(int id, UpdateEmployerDto updateEmployerDto) {
        Optional<Employer> resultEmployer = this.employerRepository.findById(id);
        if (resultEmployer.isPresent()) {
            resultEmployer.get().setEmailAddress(updateEmployerDto.getEmailAddress());
            resultEmployer.get().setPassword(updateEmployerDto.getPassword());
            resultEmployer.get().setCompanyName(updateEmployerDto.getCompanyName());

            this.modelMapper.map(this.employerRepository.save(resultEmployer.get()), UpdateEmployerDto.class);
            return new SuccessResult("İşveren güncellendi");
        } else {
            return new ErrorResult("İşveren bulunamadı");
        }
    }
}
