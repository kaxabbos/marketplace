package com.marketplace.appUser.converter;

import com.marketplace.appUser.AppUser;
import com.marketplace.appUser.UserDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UserDtoToUserConverter implements Converter<UserDto, AppUser> {

    @Override
    public AppUser convert(UserDto source) {
        return new AppUser(
                source.username()
        );
    }
}
