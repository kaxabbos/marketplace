package com.marketplace.security;

import com.marketplace.appUser.AppUser;
import com.marketplace.appUser.MyUserPrincipal;
import com.marketplace.appUser.UserDto;
import com.marketplace.appUser.UserService;
import com.marketplace.appUser.converter.UserToUserDtoConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final JwtProvider jwtProvider;
    private final UserToUserDtoConverter userToUserDtoConverter;
    private final UserService userService;

    public Map<String, Object> createLoginInfo(Authentication authentication) {
        MyUserPrincipal principal = (MyUserPrincipal) authentication.getPrincipal();

        AppUser user = principal.user();
        UserDto userDto = userToUserDtoConverter.convert(user);

        String token = jwtProvider.createToken(authentication);

        Map<String, Object> loginResultMap = new HashMap<>();

        loginResultMap.put("user", userDto);
        loginResultMap.put("token", token);

        return loginResultMap;
    }
}
