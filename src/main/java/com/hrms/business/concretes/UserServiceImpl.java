package com.hrms.business.concretes;

import com.hrms.business.abstracts.UserService;
import com.hrms.core.utilities.results.*;
import com.hrms.dtos.userDtos.CreateUserDto;
import com.hrms.dtos.userDtos.UpdateUserDto;
import com.hrms.dtos.userDtos.GetUserDto;
import com.hrms.repository.UserRepository;
import com.hrms.core.entities.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Result add(CreateUserDto createUserDto) {
        User user = this.modelMapper.map(createUserDto, User.class);
        String userEmail = user.getEmailAddress().toLowerCase();
        List<User> existingUsers = this.userRepository.findAll();

        for (User existingUser : existingUsers) {
            if (existingUser.getEmailAddress().equalsIgnoreCase(userEmail)) {
                return new ErrorResult("Bu email zaten mevcut");
            }
        }

        this.userRepository.save(user);
        return new SuccessResult("Kullanıcı eklendi");
    }

    @Override
    public Result delete(int id) {
        User user = this.userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Kullanıcı bulunamadı"));
        this.userRepository.delete(user);
        return new SuccessResult(user.getEmailAddress() + " adresli kullanıcı silindi");
    }

    @Override
    public Result update(int id, UpdateUserDto updateUserDto) {
        Optional<User> resultUser = this.userRepository.findById(id);
        if (resultUser.isPresent()) {
            resultUser.get().setEmailAddress(updateUserDto.getEmailAddress());
            resultUser.get().setPassword(updateUserDto.getPassword());

            this.modelMapper.map(this.userRepository.save(resultUser.get()), UpdateUserDto.class);
            return new SuccessResult("Kullanıcı güncellendi");
        } else {
            return new ErrorResult("Kullanıcı bulunamadı");
        }
    }

    @Override
    public DataResult<List<GetUserDto>> getAll() {
        return new SuccessDataResult<List<GetUserDto>>(this.userRepository.getUserDto(), "Kullanıcılar listelendi");
    }
}
