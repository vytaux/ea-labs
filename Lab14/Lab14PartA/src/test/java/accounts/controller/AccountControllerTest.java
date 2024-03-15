package accounts.controller;

import accounts.service.AccountResponse;
import accounts.service.AccountService;
import jakarta.persistence.EntityManagerFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@ExtendWith(SpringExtension.class)
@WebMvcTest(AccountController.class)
// What about entityManagerFactory dependency????
@AutoConfigureDataJpa
class AccountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AccountService accountService;

    @Test
    void testGetAccountByAccountNumber() throws Exception {
        // given
        String accountNumber = "123";
        AccountResponse accountResponse = new AccountResponse(accountNumber, 100, "holder1");
        when(accountService.getAccount(anyString())).thenReturn(accountResponse);

        // when & then
        mockMvc.perform(get("/account/{accountNumber}", accountNumber)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void testCreateAccount() throws Exception {
        // given
        String accountNumber = "123";
        String amount = "100";
        String accountHolder = "holder1";

        // when & then
        mockMvc.perform(get("/createaccount/{accountNumber}/{amount}/{accountHolder}", accountNumber, amount, accountHolder)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}