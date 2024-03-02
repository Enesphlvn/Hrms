package com.hrms.business.abstracts;

import com.hrms.core.utilities.results.DataResult;
import com.hrms.core.utilities.results.Result;
import com.hrms.core.entities.User;
import com.hrms.dtos.UserAddDto;
import com.hrms.dtos.UserUpdateDto;
import com.hrms.dtos.UsersGetAllDto;

import java.util.List;

public interface UserService {
    DataResult<List<UsersGetAllDto>> getAll();
    Result add(UserAddDto userAddDto);
    Result delete(int id);
    Result update(int id, UserUpdateDto userUpdateDto);
}
