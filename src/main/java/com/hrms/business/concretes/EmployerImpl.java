package com.hrms.business.concretes;

import com.hrms.business.abstracts.EmployerService;
import com.hrms.core.utilities.results.*;
import com.hrms.dtos.EmployerAddDto;
import com.hrms.dtos.EmployerDetailDto;
import com.hrms.dtos.EmployerUpdateDto;
import com.hrms.repository.EmployerRepository;
import com.hrms.domain.Employer;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployerImpl implements EmployerService {
    private final EmployerRepository employerRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public EmployerImpl(EmployerRepository employerRepository, ModelMapper modelMapper) {
        this.employerRepository = employerRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public DataResult<List<EmployerDetailDto>> getAll() {
        return new SuccessDataResult<List<EmployerDetailDto>>(this.employerRepository.getEmployerDetails(), "İşverenler listelendi");
    }

    @Override
    public Result add(EmployerAddDto employerAddDto) {
        Employer employer = modelMapper.map(employerAddDto, Employer.class);
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
    public Result update(int id, EmployerUpdateDto employerUpdateDto) {
        Optional<Employer> resultEmployer = this.employerRepository.findById(id);
        if (resultEmployer.isPresent()) {
            resultEmployer.get().setEmailAddress(employerUpdateDto.getEmailAddress());
            resultEmployer.get().setPassword(employerUpdateDto.getPassword());
            resultEmployer.get().setCompanyName(employerUpdateDto.getCompanyName());

            modelMapper.map(this.employerRepository.save(resultEmployer.get()), EmployerUpdateDto.class);
            return new SuccessResult("İşveren güncellendi");
        } else {
            return new ErrorResult("İşveren bulunamadı");
        }
    }
}
