package tacos;

import lombok.Data;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import org.hibernate.validator.constraints.CreditCardNumber;

import java.util.Date;


@Data
public class Order {

    private Long id;

    private Date placedAt;

    @NotBlank(message = "Podanie imienia i nazwiska jest obowiazkowe.")
    private String name;

    @NotBlank(message = "Podanie ulicy jest obowiazkowe.")
    private String street;

    @NotBlank(message = "Podanie miejscowości jest obowiazkowe.")
    private String city;

    @NotBlank(message = "Podanie województwa jest obowiazkowe.")
    private String state;

    @NotBlank(message = "Podanie kodu pocztowego jest obowiazkowe.")
    private String zip;

    @CreditCardNumber(message = "To nie jest prawdiłowy numer karty")
    private String ccNumber;

    @Pattern(regexp = "^(0[1-9]|1[0-2])([\\/])([1-9][1-9])$", message = "Wartość musi być w formacie MM/RR.")
    private String ccExpiration;

    @Digits(integer = 3, fraction = 0, message = "Nieprawidłowy kod CVV.")
    private String ccCVV;
}
