import com.lendico.test.TestApplication;
import com.lendico.test.model.Installment;
import com.lendico.test.services.RepaymentService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import java.time.LocalDate;
import java.util.List;

@SpringBootTest(classes = TestApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestRepaymentIT {

    @Autowired
    TestRestTemplate restTemplate;

    @LocalServerPort
    int port;
    RepaymentService service;

    @Test
    void TestService() {

        double loanAmount = 5000;
        double nominalRate = 5;
        int duration = 24;
        LocalDate startDate = LocalDate.now();

        /**
        List<Installment> installments = service.getInstallments(loanAmount, nominalRate, duration, startDate);
        Assertions.assertEquals(installments.size(), duration);
         **/
        String url = "http://localhost:" + port + "/generate-plan";
        restTemplate.po
        System.out.println(port);
    }
}
