package africa.semicolon.lumexpress.service;

import africa.semicolon.lumexpress.data.models.VerificationToken;
import africa.semicolon.lumexpress.data.service.VerificationTokenService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
@SpringBootTest
@Slf4j
class VerificationTokenServiceImplTest {

    @Autowired
    private VerificationTokenService verificationTokenService;
    private VerificationToken verificationToken;

    @BeforeEach
    void setUp() {
        verificationToken = verificationTokenService
                .createToken("test@gmail.com");
    }

    @Test
    void createTokenTest() {
        log.info("token -> {}", verificationToken);
        assertThat(verificationToken).isNotNull();
        assertThat(verificationToken.getToken().length()).isEqualTo(5);
    }

    @Test
    void isValidVerificationTokenTest(){
        assertThat(verificationToken).isNotNull();
        boolean response = verificationTokenService
                .isValidVerificationToken(verificationToken.getToken());
        assertThat(response).isTrue();
    }
}