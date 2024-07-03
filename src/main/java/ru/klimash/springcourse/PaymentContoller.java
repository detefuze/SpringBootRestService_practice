package ru.klimash.springcourse;

import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/payment") // бин отвечает за маршрутизацию
public class PaymentContoller {

    private static final String SUCCES_STATUS = "success";
    private static final String ERROR_STATUS = "error";
    private static final int CODE_SUCCESS = 100;
    private static final int AUTH_FAILURE = 102;
    BaseResponse main_response = new BaseResponse(ERROR_STATUS, AUTH_FAILURE);

    @GetMapping("")
    public String showPayment(Model model) {
        model.addAttribute("response", main_response);
        return "payment";
    }

    @GetMapping("/pay")
    public String showPay() {
        return "pay";
    }

    @GetMapping("/status")
    @ResponseBody
    public BaseResponse showStatus() {
        return main_response;
    }

    @PostMapping( "payment/suggest")
    public String pay(@RequestParam String key)
    {
        String sharedKey = "123";
        if (sharedKey.equalsIgnoreCase(key)) {
            main_response = new BaseResponse(SUCCES_STATUS, CODE_SUCCESS);
        }
        else
        {
            main_response = new BaseResponse(ERROR_STATUS, AUTH_FAILURE);
        }
        return "redirect:/payment";
    }

}
