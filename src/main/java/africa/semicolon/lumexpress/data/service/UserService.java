package africa.semicolon.lumexpress.data.service;

import africa.semicolon.lumexpress.data.dto.request.LoginRequest;
import africa.semicolon.lumexpress.data.dto.response.LoginResponse;

public interface UserService {
    LoginResponse login(LoginRequest loginRequest);
}
