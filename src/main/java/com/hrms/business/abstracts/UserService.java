package com.hrms.business.abstracts;

import com.hrms.core.entities.User;
import com.hrms.core.utilities.results.DataResult;
import com.hrms.core.utilities.results.Result;
import com.hrms.dtos.userDtos.CreateUserDto;
import com.hrms.dtos.userDtos.UpdateUserDto;
import com.hrms.dtos.userDtos.GetUserDto;

import java.util.List;

public interface UserService {
    DataResult<List<GetUserDto>> getAll();
    Result add(CreateUserDto createUserDto);
    Result delete(int id);
    Result update(UpdateUserDto updateUserDto);

    DataResult<User> getByEmail(String email);
}
