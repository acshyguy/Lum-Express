package africa.semicolon.lumexpress.data.repositories;

import africa.semicolon.lumexpress.data.models.VerificationToken;
import africa.semicolon.lumexpress.exception.VerificationTokenException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
@SpringBootTest
@Slf4j
class VerificationTokenRepositoryTest {

    @Autowired
    private VerificationTokenRepository verificationTokenRepository;
    private VerificationToken verificationToken;


    @BeforeEach
    void setUp() {
        verificationToken = new VerificationToken();
        verificationToken.setToken("12345");
        verificationToken.setUserEmail("test@gmail.com");
        verificationTokenRepository.deleteAll();
    }

    @Test
    void findByUserEmail() {
        verificationTokenRepository.save(verificationToken);
        VerificationToken foundVerificationToken =
                verificationTokenRepository.findByUserEmail("test@gmail.com").orElseThrow(() ->
                        new VerificationTokenException("token not found"));
        log.info("found token::{}",foundVerificationToken);
        assertThat(foundVerificationToken).isNotNull();
        assertThat(foundVerificationToken.getToken())
                .isEqualTo(verificationToken.getToken());
    }

    @Test
    void findByToken() {
         verificationTokenRepository.save(verificationToken);
         VerificationToken token = verificationTokenRepository.findByToken(verificationToken.getToken())
                 .orElseThrow(() -> new VerificationTokenException("token not found"));
         assertThat(token).isNotNull();
         assertThat(token.getToken()).isEqualTo("12345");

    }
}