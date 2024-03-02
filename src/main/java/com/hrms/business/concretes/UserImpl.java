package com.hrms.business.concretes;

import com.hrms.business.abstracts.UserService;
import com.hrms.core.utilities.results.*;
import com.hrms.dtos.UserAddDto;
import com.hrms.dtos.UserUpdateDto;
import com.hrms.dtos.UsersGetAllDto;
import com.hrms.repository.UserRepository;
import com.hrms.core.entities.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public UserImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Result add(UserAddDto userAddDto) {
        User user = modelMapper.map(userAddDto, User.class);
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
    public Result update(int id, UserUpdateDto userUpdateDto) {
        Optional<User> resultUser = this.userRepository.findById(id);
        if (resultUser.isPresent()) {
            resultUser.get().setEmailAddress(userUpdateDto.getEmailAddress());
            resultUser.get().setPassword(userUpdateDto.getPassword());

            modelMapper.map(userRepository.save(resultUser.get()), UserUpdateDto.class);
            return new SuccessResult("Kullanıcı güncellendi");
        } else {
            return new ErrorResult("Kullanıcı bulunamadı");
        }
    }

    @Override
    public DataResult<List<UsersGetAllDto>> getAll() {
        List<User> users = this.userRepository.findAll();
        List<UsersGetAllDto> usersDto = users.stream().map(user -> modelMapper.map(user, UsersGetAllDto.class)).collect(Collectors.toList());
        return new SuccessDataResult<List<UsersGetAllDto>>(usersDto, "Kullanıcılar listelendi");
    }
}
